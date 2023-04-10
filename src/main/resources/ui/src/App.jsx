import { useState } from 'react'
import {Routes, Route} from "react-router-dom"
import Navbar from './Components/Layout/navbar.jsx'
import MainPage from "./Components/pages/MainPage.jsx"
// import Login from './Components/pages/SignIn.jsx'
import Settings from './Components/pages/SettingsPage.jsx'
import AdminPage from './Components/pages/AdminPage.jsx'
import './App.css'
import SignUp from './Components/pages/SignUp.jsx'
import {CookiesProvider} from "react-cookie"

function App() {
  

  return (
    <CookiesProvider>
    <Navbar>
   <Routes>
    <Route path="/" element={<MainPage/>}/>
    <Route path="/login" element={<SignUp/>}/>
    <Route path="/Settings" element={<Settings/>}/>
    <Route path="/Admin" element={<AdminPage/>}/>
   
   </Routes>
   </Navbar>
   </CookiesProvider>
  )
}

export default App
