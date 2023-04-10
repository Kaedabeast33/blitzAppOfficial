function BoxContainer(props){
    return (
        <div className="min-w-[250px] sm:min-w-[350px] min-h-[100vh] flex flex-col rounded bg-white shadow relative overflow-hidden">{props.children} 
        
        </div>
    )
}
export default BoxContainer;