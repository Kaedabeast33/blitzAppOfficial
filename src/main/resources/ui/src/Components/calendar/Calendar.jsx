import Cell from "./Cell"
import DateCell from "./DateCell";
import AvavilableCell from "./AvailableCell";
import { Component, useState } from "react";
import {startOfMonth,endOfMonth, differenceInDays, sub, add,setDate,format,getDay} from "date-fns"
import { useEffect,componentDidMount } from "react";
import { useCookies } from "react-cookie";
import axios from "axios";
import CellData from "./CellData";
const daysOfWeek=[
    "Sun",
    "Mon",
    "Tue",
    "Wed",
    "Thu",
    "Fri",
    "Sat",
   ];
  const eventObj =[
    "Jan 4 2023",
    "Jan 5 2023",
    "Jan 2 2023",
]





function Calendar(props){
    //Testing to get the lowest date from an Array from postman
    let theLowestDate= new Date()
  
    const getFirstDayFromDates=()=>{
        
        eventObj.reduce((pre,cur)=>{
            theLowestDate = Date.parse(pre) > Date.parse(cur) ? cur : pre
            return theLowestDate;
        })
    }
    getFirstDayFromDates()
    console.log(theLowestDate)

    const [cookies,setCookies,removeCookies]=useCookies(["user"])
    const[availability,setAvailability]=useState([])
    let userId= cookies.userId.toString();
    let url= "/api/users/"+userId+"/events";
    const fetchData=()=>{ 
        if(cookies.userId){
            
            console.log(userId+" this is the user ID")
            axios.get(url,{headers:{
                "Content-type": "application/json; charset=UTF-8",
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Methods": "POST, GET, OPTIONS",
                "Access-Control-Allow-Headers": "X-PINGOTHER, Content-Type"}
            }).then(res=>{
                    const availability3 = res.data
                    console.log(availability3)
                    setAvailability(availability3)
                })
                .catch(err=>{
                    console.error("Error occured"+err)
            })
                    
               
            
        }else{
            console.log("user Not logged in")
        }
        
    }
    useEffect(fetchData,[])
    

//Checks    
//States
    //For the current calendar view
    const [currentCalendarMonth,setNewCalendarMonth]= useState(props.value);
    //For the to check current day values 
    const [todaysDate,setTodaysDate]= useState(new Date())
    
//Variables
    
   
    const startDate=startOfMonth(currentCalendarMonth)
    const endDate=endOfMonth(currentCalendarMonth)
   
    const numOfDays=differenceInDays(endDate,startDate)+1;
 
    const prefixDays= startDate.getDay();
   
    const suffixDays=6-endDate.getDay();
   
    const monthTitle = startDate.toString().split(" ")[1];
    const yearTitle = startDate.toString().split(" ")[3]
    const dateTitle=startDate.toString().split(" ")[2]
    const checkMY=[monthTitle,yearTitle]
    
    //FOR check default calendar
    const [isDefaultCalendar, setDefaultCalendar]=useState(true)
   

    
//HTML   
    //Arrows
    return(
    <div  className="w-[100%] sm:flex sm:justify-center overflow-auto">
     <div className="w-[130vw] min-w-[355px] sm:w-[50rem] border-t border-l  mb-20">
      <div className="grid grid-cols-7 ">
        <Cell className="h-[1rem] flex justify-center items-center" onClick={prevYearHandler}>{"<<"}</Cell>
        <Cell className="h-[1rem] flex justify-center items-center" onClick={prevMonthHandler}>{"<"}</Cell>
        <Cell className="h-[1rem] flex justify-center items-center col-span-3">{monthTitle + " " +yearTitle}</Cell>
        <Cell className="h-[1rem] flex justify-center items-center" onClick={nextMonthHandler}>{">"}</Cell>
        <Cell className="h-[1rem] flex justify-center items-center" onClick={nextYearHandler}>{">>"}</Cell>

        {/* days of the week */}
        {daysOfWeek.map((day)=>{
            return(
        <Cell key={day} className ="text-sm flex h-[1rem] justify-center items-center font-bold">{<p>{day}</p>}</Cell>
       )})}

        {/* {prefix days} */}
       {Array.from({length:prefixDays}).map((day,index)=>{
        console.log("prefix day")
        return(
            <Cell key={index}></Cell>
        )
       })}

        {/* this goes through each cell's date and checks if it's current date or matches availability date */}
        {Array.from({length:numOfDays}).map((day,index)=>{
            let isAvailable = false;
            
            // THE NUMBER FOR EACH CELL
            let date=index+1;
            //TOCHECK ARRAYS FORMATTING
            let todaysDateArray=[
                
                format(startDate,"MMM"),
                +format(startDate,"d")+date-1,
                format(startDate,"yyyy"),
            ]
                 //cells date
            let toCheckArray=[
                
                format(todaysDate,"MMM"),
                +format(todaysDate,"d"),
                format(todaysDate,"yyyy"),
            ]
            
            //TOCHECK IF AVAILABLE FUNCTION
             const checkIfAvailable=()=>{
                let todaysDateString=todaysDateArray.reduce(function (element,index){
                    
                    return element +" " +index
               })
               
               for(let i=0; i<availability.length;i++){
                console.log(availability[i].date)
                console.log(todaysDateString)
                console.log('--------------')
                    if(availability[i].date==todaysDateString){
                    isAvailable=true;
                    break;
                    }
                    
               }
                
                    
                
            }
            checkIfAvailable()
    //======================= DATE CELLS
            if(arraysEqual(toCheckArray,todaysDateArray)){
                return(
                    <DateCell key={index} checkMY={checkMY} onClick={()=>{selectDateHandler(date)}} isCurrentDay={true} className="text-sm h-auto flex-col ">
                         <CellData eventData={eventObj}  date={date} ></CellData>
                    </DateCell>
                )
            }
            else if(isAvailable){
               console.log("TRUEEEEEE")
                return(
                    <AvavilableCell key={index}  onClick={()=>{selectDateHandler(date)}}  className="text-sm h-auto flex-col ">
                        <CellData eventData={eventObj} date={date} ></CellData>
                    </AvavilableCell>
                )    
        }
// else if(isDefaultCalendar){
           
//  <DateCell key={index}  checkMY={checkMY} onClick={()=>{selectDateHandler(date)}} className="text-sm h-auto flex-col  ">
//                         <div>
//                             <div>{date}</div>
//                         </div>
//                         <div className="text-[8px] leading-3 h-full overflow-auto w-full grid grid-rows-2">
//                             <div className="h-[40%] p-[2px] w-full  ">This is data aboutdfsdffdsfsdfdsfdsfsgdf the event</div>
                            
//                             <div >
//                             <p className="h-[40%] w-full p-[2px]  "> this is who is going</p>
//                             </div>
//                         </div>
//                     </DateCell>
            
        // }
            else{
                
               
                
                
                
                return(
                    <DateCell key={index}  checkMY={checkMY} onClick={()=>{selectDateHandler(date)}} className="text-sm h-auto flex-col  ">
                        <CellData eventData={eventObj}  date={date} ></CellData>
                    </DateCell>
                )
            }
                    
            })}

            {Array.from({length:suffixDays}).map((cell,index)=>{
            //    console.log(index)
            if(index===7){
                    return null;
            }else{
                    return(
                        <Cell key={index}/>
                    )
                }   
            })}

      </div>
     </div>
    </div>
    )
    

//Handlers
function selectDateHandler(date){
    
        console.log("Selected")
        // let functionSelectedDate = format(startDate,"d");
        // functionSelectedDate = +functionSelectedDate
        
    // console.log(selectedDate)
    console.log(date)
   let selectedDate=new Date(add(startDate,{days:date-1}))
    console.log(selectedDate)
    props.setSelectedDate(selectedDate)
    
  


}
    function arraysEqual(a,b){
        for (var i = 0; i < a.length; ++i) {
            if (a[i] !== b[i]) return false;
          }
          return true;
    }
   function prevMonthHandler(){
        const prevMonth= sub(startDate,{years: 0,months: 1,weeks: 0,days: 0,hours: 0,minutes: 0,seconds: 0})
        console.log("previousMonth")         
        setNewCalendarMonth(new Date(prevMonth))
    }
    function nextMonthHandler(){
        console.log("nextMonth")
        const nextMonth=add(startDate,{years: 0,months: 1,weeks: 0,days: 0,hours: 0,minutes: 0,seconds: 0})
        setNewCalendarMonth(new Date(nextMonth))
    }
    function prevYearHandler(){
        const prevYear= sub(startDate,{years: 1,months: 0,weeks: 0,days: 0,hours: 0,minutes: 0,seconds: 0})
        setNewCalendarMonth(new Date(prevYear))
    }
    function nextYearHandler(){
        const nextYear= add(startDate,{years: 1,months: 0,weeks: 0,days: 0,hours: 0,minutes: 0,seconds: 0})
        setNewCalendarMonth(new Date(nextYear))
        console.log(currentCalendarMonth)
    }

}
export default Calendar;