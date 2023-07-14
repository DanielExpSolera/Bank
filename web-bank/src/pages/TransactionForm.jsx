import React, { useRef, useState } from 'react';
import Header from '../layout/Header';
import db from '../assets/bankAccount.json';
import { useNavigate } from 'react-router-dom';

const TransactionForm = () => {
  const navigate = useNavigate();

  const [accType, setAccType] = useState('Select');
  const refAmount = useRef();
  const refRemittance = useRef();

  const handleAccType = (e) => {
    setAccType(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (accType === 'Saving') {
      const account = db[0].accounts[1].transactions;
      account.push({
        amount: -refAmount.current?.value,
        paid_to: refRemittance.current?.value,
      });
      alert('Transaction completed succesfully ✔');
      navigate('/global');
    } else if (accType === 'Checking') {
      const account = db[0].accounts[0].transactions;
      account.push({
        amount: -refAmount.current?.value,
        paid_to: refRemittance.current?.value,
      });
      alert('Transaction completed succesfully ✔');
      navigate('/global');
    } else if (accType === 'Select') {
      alert('You need to select an account');
    }
  };

  return (
    <>
      <Header />

      <form onSubmit={handleSubmit}>
        <label>Amount you want to send</label>
        <input type="number" placeholder="$1" ref={refAmount} />
        <label>Name of the person:</label>
        <input type="text" placeholder="Jhon" ref={refRemittance} />
        <label>Sending to </label>
        <input type="number" placeholder="Account number" />
        <label>From which account </label>
        <select id="accountType" onChange={handleAccType}>
          <option value="Select">Select</option>
          {db[0].accounts.map((acc, i) => (
            <option key={i} value={acc.type}>
              {acc.type}
            </option>
          ))}
        </select>
        <input type="submit" value="Send Money" />
      </form>
    </>
  );
};

export default TransactionForm;
