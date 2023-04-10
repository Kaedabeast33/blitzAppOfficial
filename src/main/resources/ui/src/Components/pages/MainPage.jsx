import Calendar from "../calendar/Calendar";
import Card from "../MainPage/card";
import SelectedDate from "../calendar/SelectedDate";
import { useState } from "react";
import format from "date-fns/format";
import { Cookies, useCookies } from "react-cookie";
import { useEffect } from "react";

function MainPage(){
    const [date, setDate] = useState(new Date())
    const [theSelectedDate,setSelectedDate]= useState(new Date())
    const [cookies,setCookies]=useCookies(["user"])
    const fetchData=()=>{ 
        let userIdExists=false
        if(cookies.userId){
            userIdExists=true}
        if(userIdExists){
        let userId= cookies.userId.toString()
        let url= "/api/users/"+userId+"/events"
        console.log(userId+" this is the user ID")
        
            try{fetch(url,{ 
                method:"GET",
                body:null,
                headers:{
                "Content-type": "application/json; charset=UTF-8",
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Methods": "POST, GET, OPTIONS",
                "Access-Control-Allow-Headers": "X-PINGOTHER, Content-Type"
        
            }
            }
            ).then(res=>{
                res.json().then(dbRes=>{
                    console.log(dbRes)
                })
            })
        }catch{
            console.log("ERROR")
        }
    }else{
        console.log("user Not logged in")
    }
        
    }
    useEffect(fetchData,[])
    return(
        <div className="w-[100vw] h-[100vh] p-3">
            <div className="sm:grid sm:grid-cols-6 gap-1 grid grid-cols-1">
                <div className="sm:col-start-1 sm:col-span-2 cards-wrapper bg-gray-100 place-content-around sm:flex sm:flex-col sm:h-[100vh] grid grid-cols-2 gap-2 w-full h-40 p-2 ">
                    <Card cardName="Agenda"/>
                    <Card cardName="Recomended"/>
                </div>
                <div className="sm:col-start-3 sm:col-end-7 calendar-wrapper mt-10 flex flex-col justify-center items-center">
                    <div>{cookies.username}</div>
                    <SelectedDate setSelectedDate={setSelectedDate} currentDate={theSelectedDate}/>
                    <div className="mt-6 w-[100%]">
                        <Calendar value={date} setSelectedDate={setSelectedDate}></Calendar>
                    </div>
                </div> 
            </div>
        </div>
    )
}
export default MainPage;