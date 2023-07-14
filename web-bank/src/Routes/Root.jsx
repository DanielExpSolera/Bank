import Login from '../pages/Login.jsx';
import Header from '../layout/Header.jsx';


import { useEffect } from 'react';
import { useState } from 'react';
function Root() {
  const [users, setUsers] = useState([]);
  const [loggedUsers, setLoggedUsers] = useState([]);
  const handleLoggedUser = () => {
    setLoggedUsers(loggedUser)
  };
  const loggedUser = {};
  const fetchData = () => {
    fetch('http://localhost:8081/users')
      .then((res) => res.json())
      .then((payload) => setUsers(payload));
  };

  useEffect(() => {
    fetchData();
  }, []);

  

  return (

    
    <div className="App">
      <Header />
      <Login users={users} loggedUser={loggedUser} handleLoggedUser = {handleLoggedUser}/>
    </div>
  );
}

export default Root;
