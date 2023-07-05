import Login from '../pages/Login.jsx';
import Header from '../layout/Header.jsx';
import { useEffect } from 'react';
function Root() {
    const fetchData = () => {
        fetch("http://localhost:8080/users")
            .then((res) => res.json())
            .then((payload) => setData(payload));
        console.log(data);
    };
    useEffect(() => {
        fetchData();
    }, []); 
  return (
    <div className="App">
    <Header/>
      <Login/>
    </div>
  );
}

export default Root;
