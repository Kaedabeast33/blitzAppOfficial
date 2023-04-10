
import {Link} from "react-router-dom"
import { useCookies } from "react-cookie";
function Navbar(props){
const [cookies, setCookies,removeCookies]= useCookies(["user"])
    return(
        <div className="w-full">
            <div className="navbar h-6 w-full bg-red-600 flex justify-between">
                <div onClick={removeCookies} className="bg-black text-white text-[10px] w-9" ><Link to="/login">logout</Link></div>
                <div className="bg-black text-white text-[10px] w-9"><Link to="/Settings">Settings</Link></div>
                <div className="bg-black text-white text-[10px] w-9"><Link to="/Admin">Admin</Link></div>
            </div>
            <main>
                {props.children}
            </main>
        </div>
    )
}
export default Navbar;