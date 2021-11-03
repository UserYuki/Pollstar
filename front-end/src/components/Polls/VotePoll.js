import axios from "axios";
import React from "react";
import { Input, Form, FormGroup, Label } from 'reactstrap';
//import React, {Component} from 'react';

const baseURL = "http://localhost:8080/";

const VotePoll = (props) => {
  const [post, setPost] = React.useState(null);
  const [pollID, setPollID] = React.useState();

  React.useEffect(() => {
    axios.get(`${baseURL}poll/6`).then((response) => {
      setPost(response.data);
    });
  }, []);

  function readPost()
  {
    axios.get(`${baseURL}poll/` + pollID).then((response) => {
      setPost(response.data);
    });
  }

  if (!post) return "No post!"

  return (
    <div>
    <h1>Poll Name = {post.pollName}</h1>
    <h1>Poll ID = {post.pollID}</h1>
    <h1>Poll Created on = {post.pollCreationDate}</h1>
    <h1>Poll Option 1:</h1>
    <h2> {post.pollChoices[0].choiceName}  <button> Vote </button> </h2>
    
    <h1>Poll Option 2:</h1>
    <h2> {post.pollChoices[1].choiceName} <button> Vote </button> </h2>

    <Input placeholder="Poll ID" min={0} max={100} type="number" onChange={event => setPollID(event.target.value)}/>
    <button onClick={readPost}>Read Post</button>
  </div>
  );
}

export default VotePoll