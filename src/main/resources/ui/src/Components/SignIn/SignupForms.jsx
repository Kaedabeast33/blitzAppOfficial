import Button from "../common/Button";
import { Link, useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { useContext } from "react";
import { AccountContext } from "./accountContext";
import { useState } from "react";
import { useCookies } from "react-cookie";


function SignUpForms(){
    const navigate = useNavigate();
    const[cookies,setCookies,removeCookies]=useCookies(["user"])
    const [usernameReg,setUsernameReg]=useState("");
    const [passwordReg,setPasswordReg]=useState("");
    const[passwordCheck,setPasswordCheck]=useState("")
    const [emailReg,setEmailReg]=useState("");
    const [isPending,setIspending]=useState(false)
    const {switchToLogin,signedUp}= useContext(AccountContext)
    function  submitForm(e){
        e.preventDefault()
        removeCookies()
        setIspending(true)
        console.log('form submitted')
        if(passwordCheck===passwordReg){
            fetch("/api/users/addUser",{
            method:"POST",
            body: JSON.stringify({
                username:usernameReg,
                password:passwordReg,
                email: emailReg,
            }),
            headers:{
                "Content-type": "application/json; charset=UTF-8",
                "Access-Control-Allow-Origin": "*"
            }
            }).then(res=>{
               res.json().then(json=>{
                if(json[3]==="register200"){
                setCookies("username",json[0],{path:"/"})
                setCookies("userId",json[2],{path:"/"})
                console.log(cookies.username)
                console.log(cookies.userId)
                }else{console.log("register failed")}
            })
            //    console.log(res.json()[2].data)
               
            //    setCookies("password")
            })
            setIspending(false)
            if(!isPending){
                
                signedUp()
            }
        }
    }   

    
    
    return(
        <div>
        <div className="w-[100%] flex flex-col items-center mt-10px">
            <form onSubmit={submitForm} className=" w-[100%] h-[100%] flex flex-col justify-center items-center">
                
                <input onChange={(e)=>{
                    setUsernameReg(e.target.value);
                    console.log(usernameReg)
                }} className=" w-[80%] h-[42px] rounded border-slate-300 border-2 pl-1 pr-1 placeholder:ml-1 focus:ring-0 focus:border-current hover:border-red-600 focus:border-red-600 transition duration-450 ease-out hover:ease-in focus:ease-in"
                 placeholder="Full Name"></input>

                <input onChange={(e)=>{
                    setEmailReg(e.target.value);
                    console.log(emailReg)
                }} className=" w-[80%] h-[42px] rounded border-slate-300 border-2 pl-1 pr-1 placeholder:ml-1 focus:ring-0 focus:border-current hover:border-red-600 focus:border-red-600 transition duration-450 ease-out hover:ease-in focus:ease-in"
                 placeholder="Email"></input>

                <input onChange={(e)=>{
                    setPasswordReg(e.target.value);
                    console.log(passwordReg)
                }} className=" w-[80%] h-[42px] rounded border-slate-300 border-2 pl-1 pr-1 placeholder:ml-1 focus:ring-0 focus:border-current hover:border-red-600 focus:border-red-600 transition duration-450 ease-out hover:ease-in focus:ease-in" 
                placeholder="Password"></input>

                <input onChange={(e)=>{
                    setPasswordCheck(e.target.value);
                    console.log(passwordCheck)
                }} className=" w-[80%] h-[42px] rounded border-slate-300 border-2 pl-1 pr-1 placeholder:ml-1 focus:ring-0 focus:border-current hover:border-red-600 focus:border-red-600 transition duration-450 ease-out hover:ease-in focus:ease-in" 
                 placeholder="Confirm Password"></input>
           
            </form>
            
            <Button type={"submit"} onClick={submitForm} className="">Sign In</Button>
        </div>
        <p>Already have an account?<span onClick={switchToLogin} className="semi-bold text-red-600 mt-5"> Login</span></p>
        </div>
    )
}
export default SignUpForms;