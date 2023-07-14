import styles from './Forms.module.css';
import { useNavigate,NavLink } from 'react-router-dom';
import { useRef } from 'react';

const NewUser = () => {
  const navigate = useNavigate();


  const refEmail = useRef();
  const refName = useRef();
  const refLastName = useRef();
  const refPassword = useRef();
  const refRepPassword = useRef();

  const onSubmit = (e) => {
    e.preventDefault();
    const data = {
        email: refEmail.current?.value,
        name: refName.current?.value,
        lastName: refLastName.current?.value,
        password: refPassword.current?.value,
    }
    console.table(data)

    // POST METHOD TO CREATE A NEW USER
    alert("New User Created Succesfully ✔")
    navigate("/")
  };

  return (
    <>
      <div className={styles['form-container']}>
        <form className={styles.formContainer} onSubmit={onSubmit}>
          <label>
            Email<span> *</span>
          </label>
          <input
          
            ref={refEmail}
            className={styles['input-box']}
            type="email"
            placeholder="example@solera.com"
            
          />
          <label>
            Name<span> *</span>
          </label>
          <input
            ref={refName}
            className={styles['input-box']}
            type="text"
            placeholder="Ignacio"
            
          />
          <label>
            Last Name<span> *</span>
          </label>

          <input
            ref={refLastName}
            className={styles['input-box']}
            type="text"
            placeholder="Medina"
            
          />
          <label>
            New Password<span> *</span>
          </label>

          <input
            ref={refPassword}
            className={styles['input-box']}
            type="password"
            placeholder="• • • • • •"
            
          />
          <label>
            Confirm Password<span> *</span>
          </label>

          <input
            ref={refRepPassword}
            className={styles['input-box']}
            type="password"
            placeholder="• • • • • •"
            
          />
          <input className={styles.button} type="submit"></input>
          <p>Do you already have an account? <NavLink to="/">Click Here</NavLink></p>

        </form>
      </div>
    </>
  );
};

export default NewUser;
