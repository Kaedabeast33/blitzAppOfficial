import CellData from "./CellData";

function AvavilableCell(props){
    
            

        return(
            <div onClick={props.onClick} className={props.className + " min-h-12 flex h-[20vh] bg-red-100 border-b border-r hover:bg-black hover:opacity-20 hover:text-white "}>
               {props.children}
               
            </div>
        )
        
    
}
export default AvavilableCell;

