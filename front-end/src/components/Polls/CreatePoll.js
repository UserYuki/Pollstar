import axios from "axios";
import React from "react";
import { Input, Form, FormGroup, Label } from 'reactstrap';
import { BrowserRouter as useHistory, Redirect, Router, Switch, Route, Link } from "react-router-dom";
import ViewPoll from "./ViewPoll";
//import React, {Component} from 'react';

const baseURL = "http://localhost:8080/";

const CreatePoll = (props) => {
  //const [post, setPost] = React.useState(null);
  const [pollID, setPollID] = React.useState();
  const [PostedID, setID] = React.useState();

  function createPost() {
    //document.getElementById("pollName").value; krijg poll input box value
var today = new Date();
var date = today.getFullYear()+'/'+(today.getMonth()+1)+'/'+today.getDate();
//document.getElementById("o1").value = date;
let pollChoicess = 
  [
    { choiceID: 0,
      choiceName: document.getElementById("o1").value,
      voters: [0]   
    }, 
    { choiceID: 1,
      choiceName: document.getElementById("o2").value,
      voters: [1] 
    }
  ]

//document.getElementById("o1").value = JSON.stringify(pollChoices);
    axios
      .post(`${baseURL}poll/`, {
        pollName: document.getElementById("pollName").value,
        pollID: pollID,
        pollCreationDate: date,
        pollChoices: pollChoicess
      })
      .then((response) => {
        setID("/ViewPoll/" + response.data);
        axios.get(`${baseURL}poll/`+response.data).then((res) => {
          
        });
      });
      
      
  }

  if(PostedID != undefined) return(<Redirect to={PostedID} />) 

  return (
    <div>
    <Form>
        <FormGroup>
            <Label for="pollId">Poll ID:</Label>
            <Input type="number" name="poll" id="pollId" placeholder="Enter id of Poll" onChange={event => setPollID(event.target.value)}/>
            <Label for="pollName">Poll name:</Label>
            <Input type="text" name="poll" id="pollName" placeholder="Enter name of Poll"/>

            <Label for="pollOption">Option 1:</Label>
            <Input type="text" name="option" id="o1" placeholder="Enter option 1"/>
            <Label for="pollOption">Option2:</Label>
            <Input type="text" name="option" id="o2" placeholder="Enter option 2"/>
         </FormGroup>
         <Link  to="ViewPoll/0"> TEST </Link>
    </Form>
    
    <button onClick={createPost}>Savu!</button>
    </div>
  );
}

export default CreatePoll