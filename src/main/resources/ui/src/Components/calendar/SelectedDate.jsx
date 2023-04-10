import { useEffect } from "react";
import { useState } from "react";
import {format} from "date-fns"

function SelectedDate(props){
    const date=props.currentDate
    const currentDateFormatted = format(date,"dd LLLL yyyy");

  
    return(
        <p>Selected Dates:{" " +currentDateFormatted} </p>
    )
}
export default SelectedDate;