

function Circle(props){
    if(props.pending)
    return(
        <p className="h-2 w-2 rounded-full border-purple col-span-2"/>
    )
    if(props.accepted)
    return(
        <p className="h-3 w-3 rounded-full bg-[#50d71e] relative top-0 col-span-2"/>
          
       
    )
    if(props.urgent)
    return(
        <p>red</p>
    )
    if(props.active)
    return(
        <p>green</p>
    )
    if(props.warning)
    return(
        <p>yellow</p>
    )
}
export default Circle;