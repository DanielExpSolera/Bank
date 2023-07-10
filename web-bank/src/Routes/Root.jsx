import Login from '../pages/Login.jsx';
import Header from '../layout/Header.jsx';
import { useEffect } from 'react';
import { useState } from 'react';
function Root() {  
  const [data, setData] = useState([]);  
  
  const fetchData = () => {  
      console.log("fetchData is being called.")
      fetch("http://localhost:8080/users")
          .then((res) => res.json()) 
      .then((payload) => setData(payload));
  };  
  
  useEffect(() => {  
    fetchData();  
  }, []);  
  
  console.log(data);  
  
  return (  
    <div className="App">  
      <Header/>  
      <Login/>  
    </div>  
  );  
}  

export default Root;
