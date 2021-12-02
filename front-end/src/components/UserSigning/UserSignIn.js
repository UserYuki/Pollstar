import React from "react";
import axios from "axios";
import { Button, Form, FormGroup, Label, Input, FormText, Alert } from "reactstrap";

const baseURL = "http://localhost:8080/";

const UserSignIn = (props) => {
  const [email, setEmail] = React.useState("");
  const [username, setUsername] = React.useState("");
  const [password, setPassword] = React.useState("");
  localStorage.removeItem("JWT");


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
    //alert(response.data.Authorization)
  });
}

function submitEmail() 
{
  alert(email + password);
  return false;
}
 
  return (
    <>
      <div class="float-container">
        <div class="float-child">
        <Form onSubmit={submitUsername}>
            <FormGroup>
              <Label for="UserName">Username</Label>
              <Input
                type="text"
                name="username"
                id="UserName"
                onChange={(e) => setUsername(e.target.value)}
                placeholder="Enter username here..."
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
        </div>
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
      </div>

    </>
  );
};
export default UserSignIn;
