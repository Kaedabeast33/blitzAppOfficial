import { useEffect,useState } from "react";


function Cell(props){
   
    //For highlight available days
    //if is current day

    return(
            <div onClick={props.onClick} className={props.className + " flex min-h-2 border-b border-r hover:bg-black hover:opacity-20 hover:text-white "}>
                {props.children}
            </div>
    )
    }

export default Cell;