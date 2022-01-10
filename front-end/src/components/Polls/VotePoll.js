import axios from "axios";
import React from "react";
import { Input, Form, FormGroup, Label } from 'reactstrap';
import { useCookies } from 'react-cookie';
import { useTimer } from 'use-timer';
//import React, {Component} from 'react';

const baseURL = "http://localhost:8080/";

const VotePoll = (props) => {
  const [post, setPost] = React.useState(null);
  const [pollID, setPollID] = React.useState();

  const [cookies, setCookie] = useCookies(['IP']);
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

  function readPost()
  {
    let headerConfig = {
      headers: {
        Authorization: JWT
      }
    }
    axios.get(`${baseURL}voter/getPoll/${pollID}`, headerConfig).then((response) => {
      setPost(response.data);
    });
  }

  function vote(optionID)
  {
    axios
    .post(`${baseURL}voter/vote?pollID=`+pollID+`&ChoiceID=`+optionID, {
      voterID: 17,
      uuid1: cookies.IP,
      uuid2: "test2"
    })
  }

  if (!post) return (
    <div>
    <Input placeholder="Poll ID" min={0} max={100} type="number" onChange={event => setPollID(event.target.value)}/>
    <button onClick={readPost}>Read Post</button>
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
    <Input placeholder="Poll ID" min={0} max={100} type="number" onChange={event => setPollID(event.target.value)}/>
    <button onClick={readPost}>Read Post</button>
  </div>
  );
}

export default VotePoll