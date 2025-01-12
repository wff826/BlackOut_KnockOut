import styles from './HomePage.module.css';
import logoWhite from '../assets/logo_white.png';
import backgroundImage from '../assets/HomePage.png';
import backgroundOverlay from '../assets/HomePage_bg.png';
import { useEffect, useState } from 'react';

const TypeWriter = ({ text }) => {
  const [displayText, setDisplayText] = useState('');
  
  useEffect(() => {
    let i = 0;
    setDisplayText('');
    
    const typing = setInterval(() => {
      if (i <= text.length) {
        setDisplayText(text.substring(0, i));
        i++;
      } else {
        clearInterval(typing);
      }
    }, 100);
    
    return () => clearInterval(typing);
  }, [text]);
  
  return <span>{displayText}</span>;
};

const HomePage = () => {
  const [isVisible, setIsVisible] = useState(false);
  const [activeFeature, setActiveFeature] = useState(null);

  useEffect(() => {
    setIsVisible(true);
  }, []);

  const handleFeatureClick = (featureName) => {
    setActiveFeature(activeFeature === featureName ? null : featureName);
  };

  return (
    <div className={`${styles.container} ${isVisible ? styles.visible : ''}`}>
      <div className={styles.hero}>
        <div className={styles.backgroundLayers}>
          <img src={backgroundImage} alt="" className={styles.backgroundImage} />
          <img src={backgroundOverlay} alt="" className={styles.backgroundOverlay} />
        </div>
        
        <div className={styles.heroContent}>
          <img src={logoWhite} alt="BlackIP" className={styles.logo} />
          <h1 className={styles.title}>
            <TypeWriter text="Revolutionizing DDoS Defense Through the Power of Blockchain" />
          </h1>
        </div>
        
        <div className={styles.features}>
          <div 
            className={`${styles.feature} ${activeFeature === 'ephemeral' ? styles.featureActive : ''}`}
            onClick={() => handleFeatureClick('ephemeral')}
          >
            <h2>Ephemeral</h2>
            <p><strong>-자동화된 IP 교체:</strong>클라우드 API를 활용해 실시간으로 서버 IP를 교체<br />
            <strong>-DDoS 무력화:</strong> 공격 트래픽이 특정 IP를 타겟으로 몰리더라도, IP가 교체되면서 공격은 그 즉시 무의미해집니다.</p>
          </div>

          <div 
            className={`${styles.feature} ${activeFeature === 'transparent' ? styles.featureActive : ''}`}
            onClick={() => handleFeatureClick('transparent')}
          >
            <h2>Transparent</h2>
            <p><strong>-투명한 방어 기록:</strong> 블록체인 상에 IP 발급, 교체, 소멸 이력이 저장되며, 이는 누구도 조작할 수 없음<br />
            <strong>-공격자 데이터 관리:</strong> 공격자 IP 및 행위 이력이 체계적으로 저장되어, 차후 분석 및 방어 강화에 사용</p>
          </div>

          <div 
            className={`${styles.feature} ${activeFeature === 'sustainable' ? styles.featureActive : ''}`}
            onClick={() => handleFeatureClick('sustainable')}
          >
            <h2>Sustainable</h2>
            <p><strong>-서버리스 및 클라우드 통합:</strong> BlackIP는 클라우드 플랫폼과 연동하여, 서버 환경에 IP 자동 로테이션 기능을 간단히 추가<br />
            <strong>-확장 가능성:</strong> 사용자는 복잡한 설정 없이 API 연동만으로 즉시 IP 교체와 DDoS 방어 기능을 적용</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default HomePage;