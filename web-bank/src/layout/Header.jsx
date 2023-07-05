import logo from '../assets/chase-icon.png';
import { BiUser } from 'react-icons/bi';
import { AiFillBank } from 'react-icons/ai';
import { BiLogOut } from 'react-icons/bi';

import styles from './Header.module.css';
import { NavLink } from 'react-router-dom';

const Header = (props) => {
  console.log(props.isAccess)
  return (
    <header className={styles.header}>
      <img className={styles['logo-img']} src={logo} alt="bank-logo" />
        <ul>
          <li>
            <NavLink to="/user/profile">
              <BiUser /> My Profile
            </NavLink>
          </li>
          <li>
            <NavLink to="/user">
              <AiFillBank /> Bank Accounts
            </NavLink>
          </li>
          <li>
            <NavLink to="/login">
              <BiLogOut /> Log Out
            </NavLink>
          </li>
        </ul>
      
    </header>
  );
};

export default Header;
