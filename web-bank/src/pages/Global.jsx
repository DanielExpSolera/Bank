import db from '../assets/bankAccount.json';
import Card from '../components/Card';
import styles from '../components/Card.module.css';
import Header from '../layout/Header';
import { NavLink } from 'react-router-dom';

const Global = () => {

  return (
    <>
      <Header />
      <div className={styles['acc-container']}>
        {db[0].accounts.map((acc, i) => (
          <Card className={styles['card-component'] } key={i} style={{width: '40px'}}>
            <h2>{acc.type}</h2><NavLink to={'/global/new-trans'}>transfer money</NavLink>
            <hr></hr>
            {acc.transactions.map((trans, i) => (
              <div className={styles['trans-container']}key={i}>
                <p>Amount: ${trans.amount}</p>
                {trans.amount < 0 ?<p>
                  Paid to: {trans.paid_to}
                </p>:""}
              </div>
            ))}
          </Card>
        ))}
      </div>
    </>
  );
};

export default Global;
