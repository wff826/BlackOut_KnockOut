import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';
import logo from '../assets/BlackIP_logo.png';

function Navbar() {
  return (
    <nav className="navbar">
      <div className="navbar-container">
        <Link to="/" className="navbar-logo">
          <img src={logo} alt="BlackIP Logo" className="logo-image" />
        </Link>
        <div className="nav-menu">
          <Link to="/intro" className="nav-item">소개</Link>
          <Link to="/ip" className="nav-item active">IP 주소</Link>
          <Link to="/blacklist" className="nav-item">Black list</Link>
        </div>
        <div className="navbar-right">
          {/* Empty div to help with centering */}
        </div>
      </div>
    </nav>
  );
}

export default Navbar; 