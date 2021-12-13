import React from 'react';
import axios from "axios";
import { Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';


const baseURL = "http://localhost:8080/";

const UserSignUp = (props) => {
  const saved = localStorage.getItem("JWT");
  const [email, setEmail] = React.useState("");
  const [username, setUsername] = React.useState("");
  const [password, setPassword] = React.useState("");
  localStorage.removeItem("JWT");

  function checkPassword(e)
  {
    if(e !=)
  }


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
            <FormGroup>
              <Label for="examplePassword">Confirm Password</Label>
              <Input
                type="password"
                name="password"
                id="examplePassword"
                onChange={(e) => checkPassword(e.target.value)}
                placeholder="Please input password..."
              />
            </FormGroup>
            <Button type="submit" value="Submit">Submit</Button>
          </Form>      
        </div>
      </div>

    </>
  );
}
export default UserSignUp;