import React from 'react';
import './IPMonitor.css';
import clockIcon from '../assets/clock.png';

function IPMonitor() {
  const currentIP = "34.64.82.75.";
  
  return (
    <div className="ip-monitor">
      <div className="ip-container">
        <div className="ip-header">
          <div className="ip-info">
            <div className="ip-row">
              <h2 className="ip-title">현재 서버 IP 주소</h2>
              <div className="ip-address">{currentIP}</div>
            </div>
          </div>
          <div className="status-container">
            <div className="timer">
              <img src={clockIcon} alt="clock" className="clock-icon" />
              <span>00 : 03 : 34</span>
            </div>
            <div className="status-badge">Safe</div>
          </div>
        </div>
        
        <div className="log-container">
          <h3>소멸 로그 리스트</h3>
          <table className="log-table">
            <thead>
              <tr>
                <th>Destination IP Address</th>
                <th>Started At</th>
                <th>Ended At</th>
              </tr>
            </thead>
            <tbody>
              {[...Array(10)].map((_, index) => (
                <tr key={index}>
                  <td>Destination IP Address</td>
                  <td>Started At</td>
                  <td>Ended At</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default IPMonitor; 