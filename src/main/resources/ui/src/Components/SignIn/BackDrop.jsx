import {motion} from "framer-motion"
function Backdrop(props){
    return(
        <motion.div transition={props.transition} initial={false} animate={props.animate} variants={props.variants} className="backdrop-gradient w-[160%] h-[550px] absolute flex flex-col rounded-full left-[-60px] top-[-420px] sm:top-[-400px]">{props.children}</motion.div>
    )
}
export default Backdrop;