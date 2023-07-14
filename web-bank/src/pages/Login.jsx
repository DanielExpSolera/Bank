import styles from './Forms.module.css';
import { useRef, useState } from 'react';
import { useNavigate, NavLink } from 'react-router-dom';

const Login = (props) => {
  const [loggedUser, setLoggedUser] = useState({});
  const navigate = useNavigate();

  const refEmail = useRef();
  const refPassword = useRef();
  console.log('usuario desde login ', loggedUser);

  const submit = (e) => {
    e.preventDefault();
    const inputEmail = refEmail.current?.value;
    const inputPassword = refPassword.current?.value;
    let isValidUser = false;

    for (const user of props.users) {
      if (user.email === inputEmail && user.password === inputPassword) {
        isValidUser = true;
      }
    }
    if (isValidUser) {
      alert('You logged in Successfully ✔ ');
      navigate('/global');
    } else alert('Incorrect email or/and password');
  };

  return (
    <>
      <div className={styles['form-container']}>
        <form className={styles.formContainer} onSubmit={submit}>
          <label>Email</label>
          <input
            ref={refEmail}
            className={styles['input-box']}
            type="text"
            placeholder="Your Email"
          />
          <label>Password</label>

          <input
            ref={refPassword}
            className={styles['input-box']}
            type="password"
            placeholder="Your Email"
          />
          <input className={styles.button} type="submit"></input>
        </form>
        <p>
          New user? <NavLink to="/newuser">Click Here</NavLink>
        </p>
      </div>
    </>
  );
};

export default Login;
