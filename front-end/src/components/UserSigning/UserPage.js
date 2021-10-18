import react from "react";
import UserSignIn from "./UserSignIn.js";
import UserSignUp from "./UserSignUp.js";
import './UserPagesStyle.css';

const UserPage = (props) => {
    return (
        <div>
            <div class="split left">
                <div class="centered">
                    <UserSignIn/>
                </div>
            </div>

            <div class="split right">
                <div class="centered">
                    <UserSignUp/>
                </div>
            </div>
        </div>
    )
}
export default UserPage