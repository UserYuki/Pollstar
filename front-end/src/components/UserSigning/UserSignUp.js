import React from 'react';
import axios from "axios";
import { BrowserRouter as Redirect} from "react-router-dom";
import { Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import { useCookies } from 'react-cookie';

const baseURL = "http://localhost:8080/";

const UserSignUp = (props) => {
  const saved = localStorage.getItem("JWT");
  const [email, setEmail] = React.useState("");
  const [username, setUsername] = React.useState("");
  const [password, setPassword] = React.useState("");
  const [errorMsg, setErrorMsg] = React.useState("");
  const[redirect, setRedirect] = React.useState();
  const [cookies, setCookie] = useCookies(['Voter']);

  function checkPassword(e)
  {
    if(e !== password){
      //document.getElementById("MatchErrorLabel").hidden = false;
      setErrorMsg("Error: Passwords do not match");
    }
    else{
      setErrorMsg("");
    }
    
  }

  function reCheckInput()
  {
    checkPassword(document.getElementById("confirmPasswordUp").value)
  }


async function submitUsername(e) 
{  
  e.preventDefault()
  reCheckInput()
  if(errorMsg.length >= 1)
  {

  }
  else
  {
    await axios
    .post(`${baseURL}api/user`, {
      username: username,
      emailAddress: email, //doesn't make sense but needs to be formatted like this...
      password: password,
      vcr: cookies.Voter  //this also doesn't make sense but needs to be formatted like this...
    })
    .then((response) => {
      if(response.status == 200)
      {
        //redirect
        setRedirect("/SignIn")
      }
      else
      {
        //if literally anything else, set the error to the error message
        setErrorMsg(response.data['message'])
        console.log(response.data['message'])
      }
      //alert(response.data.Authorization)
    });
  }

}

if(redirect!= undefined) return(<div> Please login! <br/> <h1> {"<====="} </h1> </div>) 

  return (
    <>
      <div class="float-container">
        <Form onSubmit={submitUsername}>
            <FormGroup>
              <Label for="UserName">Username</Label>
              <Input
                type="text"
                name="username"
                id="UserNameUp"
                onChange={(e) => setUsername(e.target.value)}
                placeholder="Enter username here..."
              />
            </FormGroup>
            <FormGroup>
              <Label for="exampleEmail">Email</Label>
              <Input
                type="email"
                name="email"
                id="EmailUp"
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Enter Email here..."
              />
            </FormGroup>
            <FormGroup>
              <Label for="examplePassword">Password</Label>
              <Input
                type="password"
                name="password"
                id="PasswordUp"
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Please input password..."
              />
            </FormGroup>
            <FormGroup>
              <Label for="examplePassword">Confirm Password</Label>
              <Input
                type="password"
                name="password"
                id="confirmPasswordUp"
                onChange={(e) => checkPassword(e.target.value)}
                placeholder="Please input password..."
              />
              <Label id="MatchErrorLabel">{errorMsg}</Label>
            </FormGroup>
            <Button type="submit" disabled={errorMsg.length} value="Submit">Submit</Button>
          </Form>      
      </div>

    </>
  );
}
export default UserSignUp;