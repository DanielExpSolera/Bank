import React from 'react';
import Header from '../layout/Header';
import Card from '../components/Card';
import db from '../assets/bankAccount.json'
import avatar from "../assets/avatar.png"
import styles from './Profile.module.css'


const Profile = () => {
    return (
    <>
      <Header/>
      <Card>
        <img src={avatar} alt='default avatar'/>
        <div className={styles['details-component']["profile"]}>
            <h2>{db[0].firstName} {db[0].lastName}</h2>
            <hr></hr>
            <p><b>Email:</b> {db[0].email}</p>
            <p><b>Phone Number:</b> (643) 313-401</p>
            <p><b>Address:</b> 5th Ave New York, NY</p>
        </div>
      </Card>
    </>
  );
};

export default Profile;
