import React from 'react';
import db from '../assets/bankAccount.json';
import Card  from '../components/Card';
import styles from '../components/Card.module.css'
import Header from '../layout/Header';

const Global = () => {
  return (
    <>
    <Header/>
      <div>
        {db[0].accounts.map((acc, i) => (
          <Card className={styles['card-component']} key={i}>
            <h2>{acc.type}</h2>
            <hr></hr>
            {acc.transactions.map((trans,i) =>
                <div key={i}>
                    <p>Amount: ${trans.amount}</p>
                    <p>Amount: ${trans.paid_to.length > 0? trans.paid_to: "n/a"}</p>
                </div>
            )}
          </Card>
        ))}
      </div>
    </>
  );
};

export default Global;
