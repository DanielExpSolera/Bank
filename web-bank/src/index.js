import ReactDOM from 'react-dom/client';
import Root from './Routes/Root';
import { createBrowserRouter,RouterProvider } from 'react-router-dom';
import "./index.css";
import Global from './pages/Global';
import NewUser from './pages/NewUser';
import Profile from './pages/Profile';
import TransactionForm from './pages/TransactionForm';

const router = createBrowserRouter([
    {
      path: "/",
      element: <Root/>,
    },
    {
      path: "/global",
      element: <Global/>,
      
    },
    {
      path: "/newuser",
      element: <NewUser/>,
    },
    {
      path: "user/profile",
      element: <Profile />,
    },
    {
      path: "global/new-trans",
      element: <TransactionForm />,
    }
  ]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <RouterProvider router={router} />
);