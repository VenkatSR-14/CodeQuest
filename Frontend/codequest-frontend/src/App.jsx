import logo from './logo.svg';
import './App.css';
import { Home } from './pages/Home';
import Navbar from './Component/navbar';
import {BrowserRouter} from 'react-router-dom';
import { Routes, Route } from 'react-router-dom';
import Register from "./pages/Register";
import Login from "./pages/Login";
import ProlemPage from "./pages/problemSolve"

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path ="/register" element = {<Register/>} />
        <Route path ="/login" element = {<Login/>} />
        <Route path = "/problems/:problemId" element = {<ProlemPage/>} />
      </Routes>
    </BrowserRouter>
  );
}


export default App;
