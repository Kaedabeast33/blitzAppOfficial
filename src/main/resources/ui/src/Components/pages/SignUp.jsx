import { Link, useNavigate } from "react-router-dom";
import Button from "../common/Button";
import TopContainer from "../SignIn/TopContainer";
import BoxContainer from "../SignIn/BoxContainer";
import Backdrop from "../SignIn/BackDrop";
import SignUpForm from "../SignIn/SignupForms";
import SignInForms from "../SignIn/SignIn"
import Login from "../SignIn/SignIn";
import {motion} from "framer-motion"
import { useState } from "react";
import { AccountContext } from "../SignIn/accountContext";

const backdropVariants = {
    expanded:{
        width: "233%",
        height:"1050px",
        borderRadius:"20%",
        transorm:"rotate(60deg)",
    },
    collapsed:{
        width:"160%",
        height:"550px",
        borderRadius:"50%",
        transform: "rotate(0deg)"
    }
}
const expandingTransition={
    type:"spring",
    duration:2.3,
    stiffness: 30,
}
function SignUp(props){
    const navigate = useNavigate("/")
    const[isExpanded,setIsExpanded]=useState(false);
const [active,setActive]=useState("login")

    const playExpandingAnimation = ()=>{
        setIsExpanded(true);
        setTimeout(()=>{
            setIsExpanded(false);

        },expandingTransition.duration *1000 -1500);
    }

    const switchToSignup=()=>{
        playExpandingAnimation();
        setTimeout(()=>{
            setActive("sign-up")
        }, 500);
        
    }
    const switchToLogin=()=>{
        playExpandingAnimation();
        setTimeout(()=>{
            setActive("login")
        }, 500);
        
    }
        
    const signedUp=()=>{
        navigate("/")
        
    };
        const contextValue={switchToLogin,switchToSignup,signedUp};
    return(
        <AccountContext.Provider value={contextValue}>
        <div className="flex justify-center items-center">
            <BoxContainer>
                <div className="w-[100%] h-[100%] flex justify-end flex-col pb-[5em] pr-[1.8em]">
                    <Backdrop transition={expandingTransition} animate={isExpanded? "expanded": "collapsed"} variants={backdropVariants}/>
                    {active === "sign-up" &&(
                        <div>
                            <div className="w-[100%] flex flex-col m-4">
                                <h2 className="mt-10 text-[30px] z-40 font-semibold text">Create </h2>
                                <h2 className="text-[30px] z-40 font-semibold text">Account</h2>
                                <h5 className="text-[11px]  mt-[8px]"> please register to continue!!</h5>
                            </div>
                            <div className="m-5 h-1 w-[100%]"></div>
                            <SignUpForm/>
                            
                        </div>
                    )}   
                    {active === "login" &&(
                       <div>
                            <div className="w-[100%] flex flex-col m-4">
                                <h2 className="mt-10 text-[30px] z-40 font-semibold text">Welcome </h2>
                                <h2 className="text-[30px] z-40 font-semibold text">Back</h2>
                                <h5 className="text-[11px]  mt-[8px]"> please sign-in to continue!!</h5>
                                
                            </div>
                            <div className="m-5 h-1 w-[100%]"></div>
                            <SignInForms/>
                            
                        </div>
                    )}
                </div>
            </BoxContainer>
        </div>
        </AccountContext.Provider>
    )
}
export default SignUp;