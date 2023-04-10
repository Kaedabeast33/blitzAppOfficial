import Circle from "./circle";
import CardData from "./CardData";
function Card(props){
    const evntContent={
        title:"title",
        description:"this is a description"
    }
    return(
    <div className="">
        <div className="flex flex-col justify-center w-full bg-gray-300 h-24">
            <div className="flex justify-center">
                <h2>{props.cardName}</h2>
            </div>
            <div className="overflow-scroll w-full">
            <table className="">
                <tbody className="">
                <CardData/>
                <CardData/>
                <CardData/>
                <CardData/>
                <CardData/>
                <CardData/>
                <CardData/>
               
                </tbody>
            </table>
            </div>
        </div>
    </div>
    )
}
export default Card;