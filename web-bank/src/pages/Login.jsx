import styles from './Forms.module.css';
import { useRef } from 'react';
import { useNavigate, NavLink } from 'react-router-dom';

const Login = ({onAccess}) => {
  const navigate = useNavigate();

  const refName = useRef();
  const refPassword = useRef();
  

  const submit = (e) => {
    e.preventDefault();
    const inputName = refName.current?.value;
    const inputPassword = refPassword.current?.value;

    if (inputName === 'solera@solera.com' && inputPassword === 'bootcamp2') {
      alert('You logged in Successfully ✔ ');
      navigate('/user')
    } else
    alert('Incorrect email or/and password ');

  };

  return (
    <>
      <div className={styles['form-container']}>
        <form className={styles.formContainer} onSubmit={submit}>
          <label>Email</label>
          <input
            ref={refName}
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
