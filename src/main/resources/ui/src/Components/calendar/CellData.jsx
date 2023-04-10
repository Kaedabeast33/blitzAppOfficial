import { useEffect } from "react";
import { useRef } from "react";
import { useState,useLayoutEffect } from "react";

function CellData(props){
    //not needed until we add event animations
const [eventDays,setEventDays]=useState(2)
const ref= useRef(null);
const[width,setWidth]=useState(0)
const[height,setHeight]=useState(0);
const[numbers,setNumbers]=useState([]);
const[one,setOne]=useState(1)
const[headerWidth,setHeaderWidth]=useState("35px")

useLayoutEffect(() => {
  setWidth(ref.current.offsetWidth);
  setHeight(ref.current.offsetHeight);
  setOne(ref.current.offsetWidth)
  
}, [])
useEffect(()=>{
    function handleWindowResize(){
        setWidth(ref.current.clientWidth);
        setHeight(ref.current.clientHeight);
        setOne(ref.current.offsetWidth)
        
       
    }
    window.addEventListener("resize",handleWindowResize);
    
    return()=>{
        
        window.removeEventListener("resize",handleWindowResize)
    }
},[])

useEffect(()=>{
   
    setHeaderWidth(`${one*eventDays}px`)
    
},[one])

// Event Logic
const [showEventBar,setShowEventBar]=useState(false);
const hasEvent= props.hasEvent;
const setHasEvent= props.setHasEvent;

let theLowestDate = "";
const handleEventToggle =()=>{
    setShowEventBar(true)
}
const handleEventOutToggle=()=>{
    setShowEventBar(false)
}
//if event(id)'s lowest date is the same as today's date... then push into an array of event obj, and forEach event Obj put the information in the cell...
const getFirstDayFromDates=(eventObj)=>{
        // eventObj.forEach(eventObj=>{
            eventObj.reduce((pre,cur)=>{
                theLowestDate = Date.parse(pre) > Date.parse(cur) ? cur : pre
                
                return theLowestDate;
        // })
   
    })
}
getFirstDayFromDates(props.eventData)
console.log(theLowestDate)

    return(
    <div>
        {hasEvent ?
        <div ref={ref} onMouseEnter={handleEventToggle} onMouseLeave={handleEventOutToggle} className={"border-b-1 border-slate-200 border-r-1"}>
            <style>
                {`.header{
                    width:100% !important;
                }`}
            </style>
            <div>
            
                {showEventBar ? <div className="header h-1 bg-blue-300"></div> : <div className=' h-1 w-full '></div> }           
                <div>
                    <div>{props.date}</div>
                </div>
                <div className="text-[8px] leading-3 h-full overflow-auto w-full grid grid-rows-2">
                    <div className="h-[40%] p-[2px] w-full  "></div>
                    
                    <div >
                        <p className="h-[40%] w-full p-[2px]  "> </p>
                    </div>
                </div>
            </div>
        </div> 
    :
    <div ref={ref}  className={"border-b-1 border-slate-200 border-r-1"}>
        <style>
            {`.header{
                width:100% !important;
            }`}
        </style>
        <div>
        
            <div className=' h-1 w-full '></div> 
            <div>
                <div>{props.date}</div>
            </div>
            <div className="text-[8px] leading-3 h-full overflow-auto w-full grid grid-rows-2">
                <div className="h-[40%] p-[2px] w-full  "></div>
                
                <div >
                    <p className="h-[40%] w-full p-[2px]  "> </p>
                </div>
            </div>
        </div>
    </div>          
    }

</div> 
          
    )
}
export default CellData;