import axios from "axios";
import React, { useState } from "react";
import { Input, Form, FormGroup, Label, Button } from 'reactstrap';
import { useCookies } from 'react-cookie';
import { BrowserRouter as useHistory, Redirect, Router, Switch, Route, Link } from "react-router-dom";
import ViewPoll from "./ViewPoll";
//import React, {Component} from 'react';




const baseURL = "http://localhost:8080/";

const CreatePoll = (props) => {
  

  //const [post, setPost] = React.useState(null);
  const [pollID, setPollID] = React.useState();
  const [PostedID, setID] = React.useState();
  const [inputList, setInputList] = useState([{ choiceName: "" }]);
  const [cookies, setCookie] = useCookies(['Voter', 'JWT', 'ID' ]);

  const JWT = cookies.get('JWT');
  const UID = cookies.get('ID');

    // handle input change
    const handleInputChange = (e, index) => {
      const { name, value } = e.target;
      const list = [...inputList];
      list[index][name] = value;
      setInputList(list);
    };

      // handle click event of the Remove button
  const handleRemoveClick = index => {
    const list = [...inputList];
    list.splice(index, 1);
    setInputList(list);
  };
 
  // handle click event of the Add button
  const handleAddClick = () => {
    setInputList([...inputList, { choiceName: "" }]);
  };

  function createPost() {
    //document.getElementById("pollName").value; krijg poll input box value
    var today = new Date();
    var date = today.getFullYear()+'/'+(today.getMonth()+1)+'/'+today.getDate();
    console.log(date)

    let headerConfig = {
      headers: {
        Authorization: JWT
      }
    }
  //document.getElementById("o1").value = JSON.stringify(pollChoices);
  console.log(JSON.stringify(inputList))
      axios
        .post(`${baseURL}poll`, {
          pollName: document.getElementById("pollName").value,
          //pollID: pollID,
          pollChoices: inputList,
          pollCreationDate: date,
          user: {userID: UID }
        }, headerConfig)
        .then((response) => {
          setID("/ViewPoll/" + response.data);

        });
  }

  if(PostedID != undefined) return(<Redirect to={PostedID} />) 

  return (
    <div>
        <FormGroup>
            <Label for="pollId">Poll ID:</Label>
            <Input type="number" name="poll" id="pollId" placeholder="Enter id of Poll" onChange={event => setPollID(event.target.value)}/>
            </FormGroup>
            <FormGroup>
            <Label for="pollName">Poll name:</Label>
            <Input type="text" name="poll" id="pollName" placeholder="Enter name of Poll"/>
            </FormGroup>
            
            <Button onClick={handleAddClick}>Add</Button>
            {inputList.map((x, i) => {
              
              return (
                <div className="box">
                  <FormGroup>
                  <Label for={"choice"+i.toString()}>Option {i + 1}:</Label>
                  <Input
                    name="choiceName"
                    
                    placeholder="Enter Option Name"
                    value={x.choiceName}
                    onChange={e => handleInputChange(e, i)}
                  />
                  <div className="btn-box">
                    {inputList.length !== 1 && <Button
                      className="mr10"
                      onClick={() => handleRemoveClick(i)}>Remove</Button>}
                  </div>
                  </FormGroup>
                </div>
              );
            })}
        
    
    
    <Button onClick={createPost}>Savu!</Button>
    </div>
  );
}

export default CreatePoll