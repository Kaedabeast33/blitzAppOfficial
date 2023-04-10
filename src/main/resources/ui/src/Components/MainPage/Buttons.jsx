import { useState } from "react";

function Buttons(props){
  
    return(
        <td className="flex ">
            <div className="flex p-3 text-sm">
                <button className=" btn border-2 rounded-lg border-slate-300 btn-primary bg-slate-300 text-white mr-4 shadow  hover:bg-white hover:text-black  " onClick={props.accept} >Accept</button>
                <button className="btn border-2 rounded-lg border-red-600 text-white btn-blue bg-red-600 shadow  hover:bg-white hover:text-black " onClick={props.onClick} >Descline</button>
            </div>
        </td>
    )
}
export default Buttons;