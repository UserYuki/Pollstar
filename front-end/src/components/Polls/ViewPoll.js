import axios from "axios";
import React from "react";
import { useParams } from "react-router";
import { Input, Form, FormGroup, Label, Progress } from 'reactstrap';
//import React, {Component} from 'react';
import { useTimer } from 'use-timer';

const baseURL = "http://localhost:8080/";

const ViewPoll = (props) => {
  const {passedPollID} = useParams();
  const [post, setPost] = React.useState(null);
  const [pollID, setPollID] = React.useState();
  const [totalVotes, setTotalVotes] = React.useState(0);
  const { time, start, pause, reset, status, autostart } = useTimer({
    autostart: true,
    initialTime: 3,
    endTime: 0,
    timerType: 'DECREMENTAL',
    onTimeOver: () => {
      start();
      readPost();
    },
  })

  const JWT = localStorage.getItem("JWT");

  const style = {
    width: '95%',
  };


  

  React.useEffect(() => {
    if(passedPollID > 0)
    {
      readPost(passedPollID)
     
    }
  }, [passedPollID])


  React.useEffect(() => {
    if(post != null){
          var votes = 0
    post.pollChoices.forEach(choice => {
      votes = votes + choice.voteAmount
      
      setTotalVotes(votes);

      console.log(totalVotes)
    });
    }

  }, [post])

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


  if (post == null) return (
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

    <div style={style}>    
      {post.pollChoices && post.pollChoices.map(choice =>
          <menu key={choice.choiceID} >
              <div style={style}>{choice.choiceName} 
              <Progress value={choice.voteAmount} max={totalVotes}> {choice.voteAmount} </Progress>
              </div>
          </menu>
      )}
      </div>
 <p></p>
 <Input placeholder="Poll ID" min={0} max={100} id="PollIDInput" type="number"/>
    <button onClick={() => readPost(document.getElementById("PollIDInput").value)}>Read Post</button>
  </div>
  );
}

export default ViewPoll