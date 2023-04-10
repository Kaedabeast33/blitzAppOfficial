// import Card from "./card"
import { useState } from "react"
import Circle from "./circle"
import Buttons from "./Buttons"

function CardData(props){
    const [visible, setVisible]= useState(true);
    const toggleVisible=()=>{
     setVisible(prev=>!prev)
     console.log(visible);
    }
    const [accepted, setAccept]=useState(false)
    const toggleAccept=()=>{
        setAccept(prev=>!prev)
        console.log("this is event acceptance this is no longer" + accepted)
    }
    if(visible){
        return(
            <tr className="">
                <td className="relative top-0"><Circle evntContent="" accepted={true}></Circle></td>
                <td>
                    <p className="text-[8px]">stuff1 lfdsafklsadfkladjfkdsalfdsklfldksflds;</p>
                    <p className="text-[8px]">stuff flds;</p>
                    <p className="text-[8px]">stuff ;</p>
                </td>
                <Buttons onClick={toggleVisible} accept={toggleAccept}/>
            </tr>
        )
    }else{
        return(<tr></tr>)
    }
}
export default CardData