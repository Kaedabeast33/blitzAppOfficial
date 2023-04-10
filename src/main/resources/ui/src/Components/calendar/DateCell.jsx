import { useState } from "react";

function DateCell(props){
 
//  const [isTodayAvailable,setIsTodayAvailable]=useState(false)
//     const isAvailable=(dateTitle,dateAvailablity,monthTitle,monthAvailability,yearTitle,yearAvailability)=>{
//         if(monthTitle===monthAvailability && yearTitle===yearAvailability && dateTitle==dateAvailablity){
//             ("yes");
//             return true;
//         }else{
//             ("no");
//             return false;
//         }
//     }

    if(props.isCurrentDay){
        return(
            <div onClick={props.onClick} className={props.className + " h-12 flex h-[20vh]  bg-indigo-600  border-b border-r hover:bg-indigo-600 hover:opacity-40 hover:text-white "}>
                {props.children}
            </div>
        )
    //if is not arrows or date
    }else{
       

            return(
                <div onClick={props.onClick} className={props.className + " min-h-12 flex h-[20vh] border-b border-r hover:bg-black hover:opacity-20 hover:text-white "}>
                   {props.children}
                </div> 
            )
    
       
    }
    // const checkMY=props.checkMY;
    // let D=props.cellsDate
    // let M=checkMY [0][2]
    // let Y=checkMY [0][3]
    // let availability=checkMY [0][0]
    // availability.forEach(element => {
    //    let splitElement= element.split(" ");
    //    useEffect(()=>{
    //    if(isAvailable(D,splitElement[1],M,splitElement[0],Y,splitElement[2])){
    //    setIsTodayAvailable(true)}},[props.checkMY]
    //    );
    // })
}
export default DateCell;