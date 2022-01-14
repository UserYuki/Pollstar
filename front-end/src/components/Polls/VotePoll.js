import axios from "axios";
import React from "react";
import { Input, Form, FormGroup, Label } from 'reactstrap';
import { BrowserRouter as useHistory, Redirect, Router, Switch, Route, Link } from "react-router-dom";
import { useCookies } from 'react-cookie';
import { useTimer } from 'use-timer';
//import React, {Component} from 'react';

const baseURL = "http://localhost:8080/";

const VotePoll = (props) => {
  const [post, setPost] = React.useState(null);
  const [pollID, setPollID] = React.useState();
  const [voted, setVoted] = React.useState(false);
  const [cookies, setCookie] = useCookies(['Voter']);
  const JWT = localStorage.getItem("JWT");
  const { time, start, pause, reset, status, autostart } = useTimer({
    autostart: true,
    initialTime: 3,
    endTime: 0,
    timerType: 'DECREMENTAL',
    onTimeOver: () => {
      readPost();
      start();
    },
  })

  React.useEffect(() => {
    axios.get(`${baseURL}voter/getPoll/`).then((response) => {
      setPost(response.data);
    });
  }, []);

  function readPost(passedPID)
  {
    var PID = pollID;
    if ( passedPID > 0 ) {PID = passedPID;}
    if (!PID || PID == undefined || PID == 0) {return;}
    else {setPollID(PID)}
    
      axios.get(`${baseURL}voter/getPoll/` + PID).then((response) => {
      
        setPost(response.data);
        if(!status == "RUNNING")
        {
          start();
        }
  
      }).catch(function (error) {
        pause();
      });
    

  }

  function vote(optionID)
  {
    axios
    .post(`${baseURL}voter/vote?pollID=`+pollID+`&ChoiceID=`+optionID, cookies.Voter ).then((res) => {
      if(res.status == 200)
      {
        setVoted(true);
      }
    })
    //
  }

  if(voted) return(<Redirect to={"/ViewPoll/"+pollID} />) 

  if (!post) return (
    <div>
    <Input placeholder="Poll ID" min={0} max={100} id="PollIDInput" type="number"/>
    <button onClick={() => readPost(document.getElementById("PollIDInput").value)}>Read Post</button>
    </div>
  )

  return (
    <div>
    <h1>Poll Name = {post.pollName}</h1>
    <h1>Poll ID = {post.pollID}</h1>
    <h1>Poll Created on = {post.pollCreationDate}</h1>
<table>
      <tbody>
        {post.pollChoices && post.pollChoices.map(choice =>
            <tr key={choice.choiceID}>
              
                <td><button onClick={() => vote(choice.choiceID)} key={choice.choiceName}>Vote</button> {choice.choiceName} {choice.voteAmount}</td>
                
            </tr>
        )}
      </tbody>
      </table>
      <Input placeholder="Poll ID" min={0} max={100} id="PollIDInput" type="number"/>
    <button onClick={() => readPost(document.getElementById("PollIDInput").value)}>Read Post</button>
  </div>
  );
}

export default VotePoll