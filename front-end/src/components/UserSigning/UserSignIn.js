import React from "react";
import axios from "axios";
import { useParams } from "react-router";
import { Button, Form, FormGroup, Label, Input, FormText, Alert } from "reactstrap";
import { BrowserRouter as useHistory, Redirect, Router, Switch, Route, Link } from "react-router-dom";

const baseURL = "http://localhost:8080/";

const UserSignIn = (props) => {
  const [email, setEmail] = React.useState("");
  const [username, setUsername] = React.useState("");
  const [password, setPassword] = React.useState("");
  const[redirect, setRedirect] = React.useState();

async function submitUsername(e) 
{  
  e.preventDefault()
  await axios
  .post(`${baseURL}login`, {
    userName: username,
    password: password
  })
  .then((response) => {
    localStorage.setItem("JWT", response.data.Authorization)

    axios.get(`${baseURL}api/user/retrieveID`, 
    {headers: {Authorization: response.data.Authorization}}).then((res)=>{
      if(res.status == 201){localStorage.setItem("ID", res.data ); setRedirect("/Account")}
      else
      {
        console.log(res.data)
      }
    }).catch((error) => console.log(error))
  });
}

function submitEmail() 
{
  alert(email + password);
  return false;
}
if(redirect!= undefined) return(<Redirect to={redirect} />) 

  return (
    <>
      <div class="float-container">
        <Form onSubmit={submitUsername}>
            <FormGroup>
              <Label for="UserName">Username</Label>
              <Input
                type="text"
                name="username"
                id="UserName"
                onChange={(e) => setUsername(e.target.value)}
                placeholder="Please input username..."
              />
            </FormGroup>
            <FormGroup>
              <Label for="examplePassword">Password</Label>
              <Input
                type="password"
                name="password"
                id="examplePassword"
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Please input password..."
              />
            </FormGroup>
            <Button type="submit" value="Submit">Submit</Button>
          </Form>
        {/* 
        <div class="float-child2">
          <Form onSubmit={submitEmail}>
            <FormGroup>
              <Label for="exampleEmail">Email</Label>
              <Input
                type="email"
                name="email"
                id="exampleEmail"
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Enter Email here..."
              />
            </FormGroup>
            <FormGroup>
              <Label for="examplePassword">Password</Label>
              <Input
                type="password"
                name="password"
                id="examplePassword"
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Please input password..."
              />
            </FormGroup>
            <Button>Submit</Button>
          </Form>
        </div>
        */}
      </div>

    </>
  );
};
export default UserSignIn;
