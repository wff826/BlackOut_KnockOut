import styles from './IPPage.module.css';
import { useState, useEffect } from 'react';

const IPPage = () => {
  const TIMER_DURATION = 30; // 30 seconds
  const EXPIRED_DURATION = 2; // Duration to show expired status in seconds

  // Function to generate random IP address
  const generateRandomIP = () => {
    const octets = Array.from({length: 4}, () => Math.floor(Math.random() * 256));
    return octets.join('.');
  };

  // Initialize current IP address
  const [currentIP] = useState(generateRandomIP());
  
  const [timeLeft, setTimeLeft] = useState(() => {
    const savedEndTime = localStorage.getItem('timerEndTime');
    if (!savedEndTime) {
      const endTime = Date.now() + (TIMER_DURATION * 1000);
      localStorage.setItem('timerEndTime', endTime.toString());
      return TIMER_DURATION;
    }
    const remaining = Math.ceil((parseInt(savedEndTime) - Date.now()) / 1000);
    return remaining > 0 ? remaining : 0;
  });

  const [status, setStatus] = useState('Safe');
  const [ipHistory, setIpHistory] = useState(() => {
    const savedHistory = localStorage.getItem('ipHistory');
    return savedHistory ? JSON.parse(savedHistory) : [];
  });

  const [isPaused, setIsPaused] = useState(false);
  const [isAttack, setIsAttack] = useState(false);

  // Clear existing timer data when component mounts
  useEffect(() => {
    localStorage.removeItem('timerEndTime');
    const newEndTime = Date.now() + (TIMER_DURATION * 1000);
    localStorage.setItem('timerEndTime', newEndTime.toString());
    setTimeLeft(TIMER_DURATION);
  }, []); // Only run once when component mounts

  useEffect(() => {
    if (isPaused) return; // Don't run timer if paused

    const timer = setInterval(() => {
      const savedEndTime = parseInt(localStorage.getItem('timerEndTime'));
      const now = Date.now();
      const remaining = Math.ceil((savedEndTime - now) / 1000);

      if (remaining <= 0) {
        setStatus('Expired');
        setTimeLeft(0);
        
        // Add the expired IP to history with end time
        const endTime = new Date().toLocaleString();
        setIpHistory(prev => {
          const newEntry = {
            ip: currentIP,
            startedAt: new Date(savedEndTime - (TIMER_DURATION * 1000)).toLocaleString(),
            endedAt: endTime
          };
          const updated = [newEntry, ...prev];
          localStorage.setItem('ipHistory', JSON.stringify(updated));
          return updated;
        });

        // Reset timer and generate new IP after EXPIRED_DURATION seconds
        setTimeout(() => {
          const newEndTime = Date.now() + (TIMER_DURATION * 1000);
          localStorage.setItem('timerEndTime', newEndTime.toString());
          setTimeLeft(TIMER_DURATION);
          setStatus('Safe');
        }, EXPIRED_DURATION * 1000);
      } else {
        setTimeLeft(remaining);
        setStatus('Safe');
      }
    }, 1000);

    return () => clearInterval(timer);
  }, [currentIP, isPaused]); // Add isPaused to dependencies

  // Format time to MM:SS
  const formatTime = (seconds) => {
    const minutes = Math.floor(Math.max(0, seconds) / 60);
    const remainingSeconds = Math.max(0, seconds) % 60;
    return `${minutes.toString().padStart(2, '0')}:${remainingSeconds.toString().padStart(2, '0')}`;
  };

  const handlePauseResume = () => {
    if (isPaused) {
      // When resuming, adjust the end time to account for paused duration
      const newEndTime = Date.now() + (timeLeft * 1000);
      localStorage.setItem('timerEndTime', newEndTime.toString());
    }
    setIsPaused(!isPaused);
  };

  const handleAttackSimulation = () => {
    setIsAttack(true);
    setStatus('Attack');
    
    // Add current IP to history with attack time
    const attackTime = new Date().toLocaleString();
    setIpHistory(prev => {
      const newEntry = {
        ip: currentIP,
        startedAt: attackTime,
        endedAt: '-'  // or you could put 'Under Attack'
      };
      const updated = [newEntry, ...prev];
      localStorage.setItem('ipHistory', JSON.stringify(updated));
      return updated;
    });

    // Generate new IP after short delay
    setTimeout(() => {
      const newEndTime = Date.now() + (TIMER_DURATION * 1000);
      localStorage.setItem('timerEndTime', newEndTime.toString());
      setTimeLeft(TIMER_DURATION);
      setStatus('Safe');
      setIsAttack(false);
    }, 2000);
  };

  return (
    <div className={styles.container}>
      <div className={styles.currentIP}>
        <div className={styles.ipSection}>
          <span className={styles.boldText}>현재 서버 IP 주소</span>
          <span>{currentIP}</span>
        </div>
        <div className={styles.status}>
          <span className={styles.timer}>{formatTime(timeLeft)}</span>
          <button 
            onClick={handlePauseResume}
            className={styles.pauseButton}
          >
            {isPaused ? 'Resume' : 'Pause'}
          </button>
          <span 
            onClick={handleAttackSimulation}
            className={`${styles.safeTag} 
              ${status === 'Expired' ? styles.expired : ''} 
              ${status === 'Attack' ? styles.attack : ''}`}
            style={{ cursor: 'pointer' }}
          >
            {status}
          </span>
        </div>
      </div>
      
      <h2 className={styles.historyTitle}>History</h2>
      <table className={styles.logTable}>
        <thead>
          <tr>
            <th>Expired IP Address</th>
            <th>Started At</th>
            <th>Ended At</th>
          </tr>
        </thead>
        <tbody>
          {ipHistory.map((entry, index) => (
            <tr key={index} className={styles.tableRow}>
              <td>{entry.ip}</td>
              <td>{entry.startedAt}</td>
              <td>{entry.endedAt}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default IPPage;
