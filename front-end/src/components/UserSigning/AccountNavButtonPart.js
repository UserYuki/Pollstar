import react from "react";
import UserPage from "./UserPage.js";
import './UserPagesStyle.css';
import {useState,useEffect} from 'react'
import axios from "axios";
import { useCookies } from 'react-cookie';
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

const baseURL = "http://localhost:8080/";

const AccountNavButtonPart = (props) => {
    const [username, setUsername] = useState();
    const [cookies, setCookie] = useCookies(['JWT']);
    
    useEffect(() => {
        try
        {
            const JWT = cookies.JWT
            console.log(JWT);
            let headerConfig = {
                headers: {
                  Authorization: JWT
                }
              }
            axios.get(`${baseURL}api/user/name`, headerConfig).then((response) => {
                if(response.status == 200){setUsername(response.data);}
            }).catch((ex) => {console.log(ex); setUsername(null);})
        }
        catch(Exception)
        {
            console.log(Exception);
        }
    }, [cookies.JWT])

    if(!username) {return ( <div>
            <Link to="/User">
               Sign in/up!
            </Link>
            </div>
        )}
    

    if(username){
        return(
            <div>
                Welcome, <Link to="/Account">{username}</Link>!
            </div>
        )
    }

}
export default AccountNavButtonPart