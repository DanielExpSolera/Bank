import logo from '../assets/chase-icon-png-5.jpg';
import { BsFillHouseFill } from 'react-icons/bs';
import { BiUser } from 'react-icons/bi';
import { AiFillBank } from 'react-icons/ai';


import styles from './Header.module.css';

const Header = () => {
  return (
    <header className={styles.header}>
      <img className={styles.logoImg} src={logo} alt="bank-logo" />
      <ul>
        <li>
          <BsFillHouseFill /> Home
        </li>
        <li><BiUser/>My Profile</li>
        <li><AiFillBank/>Bank Accounts</li>
      </ul>
    </header>
  );
};

export default Header;
