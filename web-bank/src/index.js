import React from 'react';
import ReactDOM from 'react-dom/client';
import Root from './Routes/Root';
import { createBrowserRouter,RouterProvider } from 'react-router-dom';
import "./index.css";

const router = createBrowserRouter([
    {
      path: "/",
      element: <Root/>,
    },
  ]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <RouterProvider router={router} />
);