function Button(props){
    
    return (
        <button type={props.type} className={props.className+" flex justify-center items-center border-2 border-blue-600 p-1 h-7 min-w-[40px] rounded shadow bg-blue-600 text-white  hover:text-blue-600 hover:bg-white hover:border-blue-600 hover:border-2"} onClick={props.onClick} >
            {props.children}
        </button>
    )
}
export default Button;