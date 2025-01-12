import { BrowserRouter as Router, Routes, Route, NavLink } from 'react-router-dom';
import HomePage from './pages/HomePage';
import IPPage from './pages/IPPage';
import BlackList from './pages/BlackList';
import styles from './App.module.css';
import logo from './assets/BlackIP_logo.png';

function App() {
  return (
    <Router>
      <div>
        <nav className={styles.navbar}>
          <div className={styles.logo}>
            <NavLink to="/">
              <img src={logo} alt="BlackIP" />
            </NavLink>
          </div>
          <div className={styles.navLinks}>
            <NavLink 
              to="/intro" 
              className={({ isActive }) => isActive ? styles.activeLink : ''}
            >
              소개
            </NavLink>
            <NavLink 
              to="/ip" 
              className={({ isActive }) => isActive ? styles.activeLink : ''}
            >
              IP 주소
            </NavLink>
            <NavLink 
              to="/blacklist" 
              className={({ isActive }) => isActive ? styles.activeLink : ''}
            >
              Black list
            </NavLink>
          </div>
        </nav>

        <main className={styles.mainContent}>
          <Routes>
            <Route path="/intro" element={<HomePage />} />
            <Route path="/ip" element={<IPPage />} />
            <Route path="/blacklist" element={<BlackList />} />
            <Route path="/" element={<HomePage />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;