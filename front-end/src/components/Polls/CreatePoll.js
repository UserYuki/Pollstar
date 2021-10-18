import axios from "axios";
import React from "react";
import { Input, Form, FormGroup, Label } from 'reactstrap';
//import React, {Component} from 'react';

const baseURL = "http://localhost:8080/";

const CreatePoll = (props) => {
  const [post, setPost] = React.useState(null);
  const [pollID, setPollID] = React.useState();

  React.useEffect(() => {
    axios.get(`${baseURL}poll/0`).then((response) => {
      setPost(response.data);
    });
  }, []);

  function readPost()
  {
    axios.get(`${baseURL}poll/` + pollID).then((response) => {
      setPost(response.data);
    });
  }

  function createPost() {
    axios
      .post(`${baseURL}poll/`, {
        pollName: "Test Poll: "+ pollID,
        pollID: pollID
      })
      .then((response) => {
        console.log(response);
        axios.get(`${baseURL}`+response.data).then((res) => {
          setPost(res.data);
        });
      });
  }

  if (!post) return "No post!"

  return (
    <Form>
        <FormGroup>
            <Label for="pollName">Poll name:</Label>
            <Input type="text" name="poll" id="pollName" placeholder="Enter name of Poll" />
        </FormGroup>
    </Form>
  );
}

export default CreatePoll