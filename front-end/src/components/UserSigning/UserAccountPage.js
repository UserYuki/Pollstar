import react from "react";
import "./UserPagesStyle.css";
import { Button, Form, FormGroup, Label, Input, FormText, Alert } from "reactstrap";
import { BrowserRouter as useHistory, Redirect, Router, Switch, Route, Link } from "react-router-dom";
import {useState,useEffect} from 'react'
import axios from "axios";
import { useCookies } from 'react-cookie';


const baseURL = "http://localhost:8080/";

const UserAccountPage = (props) => {
    const[polls, setPolls] = react.useState();
    const[username, setUsername] = react.useState("");
    const[eMailAddress, setEMailAddress] = react.useState("");
    const[newPassword, setNewPassword] = react.useState("");
    const[confirmNewPassword, setConfirmNewPassword] = react.useState("");
    const[currentPassword, setCurrentPassword] = react.useState("");
    const[redirect, setRedirect] = react.useState();
    const [cookies, setCookie, removeCookie] = useCookies(['Voter', 'JWT', 'ID' ]);

    const JWT = cookies.JWT
    const UID = cookies.ID

    useEffect( () => {
        if(polls){return;}
        let headerConfig = {
            headers: {
              Authorization: JWT
            }
          }
        axios.get(`${baseURL}api/user`, headerConfig).then((response) => {
            setUsername(response.data.userName);
            setPolls(response.data.poll)
            setEMailAddress(response.data.emailAddress)
            console.log("reload")
        })
    }, [cookies.JWT])

    function PollRedirect(e) 
    {  
      e.preventDefault()
      var selectedPollID = document.getElementById("pollSelecter").value
      setRedirect("/ViewPoll/" + selectedPollID);
    }

    function logout(e)
    {
      e.preventDefault()
      cookies.JWT = 0;
      removeCookie("JWT");
      removeCookie("ID");
      setRedirect("/User");
      console.log(redirect);
    }

    if(redirect!= undefined) return(<Redirect to={redirect} />) 

  return (
    <div>



      <div class="split left">
        <div class="centered">
          <Form>
            <FormGroup>
                <Label>Username:</Label>
                <Input
                onChange={e => setUsername(e.target.value)}
                value={username}

                placeholder = "Enter Your New Username Here..."
                type="text"
                />
            </FormGroup>
            <FormGroup>
                <Label>eMail Address:</Label>
                <Input
                onChange={e => setEMailAddress(e.target.value)}
                value={eMailAddress}

                placeholder="Enter Your New Email Here..."
                type="email"
                />
            </FormGroup>
            <FormGroup>
            <FormGroup>
                <Label>New Password:</Label>
                <Input
                onChange={e => setNewPassword(e.target.value)}
                value={newPassword}

                placeholder = "Enter Your New Password Here..."
                type="password"
                />
            </FormGroup>
            <FormGroup>
                <Label> Confirm New Password</Label>
                <Input
                onChange={e => setConfirmNewPassword(e.target.value)}
                value={confirmNewPassword}

                placeholder="Enter Confirm New Password Here..."
                type="password"
                />
            </FormGroup>
            </FormGroup>
            <FormGroup>
                <Label>Current Password:</Label>
                <Input
                onChange={e => setCurrentPassword(e.target.value)}
                value={currentPassword}

                placeholder="Enter Current Password Here..."
                type="password"
                />
            </FormGroup>
            <Button> Submitto! </Button>
          </Form>
          
        </div>
        
      </div>
      
      <div class="split right">
        <div class="centered">
          <Form onSubmit={PollRedirect}>
            <FormGroup>
              <Label for="exampleSelectMulti">Select Multiple</Label>
              <Input type="select" name="selectMulti" id="pollSelecter" /*onChange={(e) => setRedirect(e.target.value)}*/  multiple>
              {polls && polls.map(poll =>
                <option key={poll.pollID} value={poll.pollID}>{poll.pollName}</option>
              )}
              </Input>
            </FormGroup>
            <Button>Go to selected Poll</Button>
          </Form>
          <br/>
          <br/>
          <br/>
          <Button class="rightLogoutButton" onClick={logout}> Logout </Button>
        </div>
      </div>
      
    </div>
  );
};
export default UserAccountPage;
