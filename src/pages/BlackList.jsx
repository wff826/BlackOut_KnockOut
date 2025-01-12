import { useState, useEffect } from 'react';
import styles from './BlackList.module.css';

const BlackList = () => {
  const [blacklistData, setBlacklistData] = useState([]);

  useEffect(() => {
    const savedHistory = localStorage.getItem('ipHistory');
    if (savedHistory) {
      // Filter only attacked IPs (where endedAt is '-')
      const attackedIPs = JSON.parse(savedHistory)
        .filter(entry => entry.endedAt === '-')
        .map(entry => ({
          destinationIP: entry.ip,
          startedAt: entry.startedAt,
          endedAt: 'Under Attack'
        }));
      setBlacklistData(attackedIPs);
    }
  }, []);

  return (
    <div className={styles.container}>
      <div className={styles.header}>
        <h2 className={styles.title}>블랙리스트</h2>
        <div className={styles.helpIcon}>ⓘ</div>
      </div>

      <div className={styles.tableContainer}>
        <table className={styles.table}>
          <thead>
            <tr>
              <th>Destination IP Address</th>
              <th>Started At</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {blacklistData.map((item, index) => (
              <tr key={index}>
                <td>{item.destinationIP}</td>
                <td>{item.startedAt}</td>
                <td style={{ color: '#FF1744', fontWeight: 'bold' }}>
                  {item.endedAt}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default BlackList;
