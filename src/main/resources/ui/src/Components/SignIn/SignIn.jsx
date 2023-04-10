import Button from "../common/Button";
import { Link, useNavigate } from "react-router-dom";
import { useEffect } from "react"
import { useContext,useState } from "react";
import { AccountContext } from "./accountContext";
import { useCookies } from "react-cookie";
function LoginForms(props){

const {switchToSignup,signedUp}=useContext(AccountContext);
const [usernameReg,setUsernameReg]=useState("");
    const [passwordReg,setPasswordReg]=useState("");
    const[cookies,setCookies]=useCookies(["user"])
    const [isPending,setIspending]=useState(false)
    function submitForm(e){
        e.preventDefault()
        setIspending(true)
        fetch("/api/users/login",{
            method:"POST",
            body: JSON.stringify({
                username:usernameReg,
                password:passwordReg,
               
            }),
            headers:{
                "Content-type": "application/json; charset=UTF-8",
                "Access-Control-Allow-Origin": "*"
            }
            }).then(res=>{
               res.json().then(json=>{
                if(json[3]==="Login200"){
                console.log(json)
                setCookies("username",json[0],{path:"/"})
                setCookies("userId",json[2],{path:"/"})
                console.log(cookies.username)
                console.log(cookies.userId)
                signedUp()
                }else{console.log("passwrod incorrect")}
            })
            
            
    })
    }
    return(
    <div>
    <div className="w-[100%] flex flex-col items-center mt-10px">
        <div className=" w-[100%] h-[100%] flex flex-col justify-center items-center">
            
            <input onChange={(e)=>{
                    setUsernameReg(e.target.value);}} className=" w-[80%] h-[42px] rounded border-slate-300 border-2 pl-1 pr-1 placeholder:ml-1 focus:ring-0 focus:border-current hover:border-red-600 focus:border-red-600 transition duration-450 ease-out hover:ease-in focus:ease-in" placeholder="Username"></input>
            <input onChange={(e)=>{
                    setPasswordReg(e.target.value);}} className=" w-[80%] h-[42px] rounded border-slate-300 border-2 pl-1 pr-1 placeholder:ml-1 focus:ring-0 focus:border-current hover:border-red-600 focus:border-red-600 transition duration-450 ease-out hover:ease-in focus:ease-in"  placeholder="Password"></input>
       
        </div>
        
        <Button onClick={submitForm} className="">Sign in</Button>
    </div>
    <p>Don't have an account?<span onClick={switchToSignup} className="semi-bold text-red-600 mt-5"> Sign up</span></p>
    </div>
    )       
    
}
export default LoginForms;