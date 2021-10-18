import logo from './logo.svg';
import './App.css';

import axios from "axios";
import React from "react";
import { Input } from 'reactstrap';
import Nav from './components/Nav.js';
import UserPage from './components/UserSigning/UserPage.js';
import CreatePoll from './components/Polls/CreatePoll.js';
//import React, {Component} from 'react';

const baseURL = "http://localhost:8080/";

export default function App() 
{
  return(
    <div>
      <Nav/>
      <CreatePoll/>
    </div>
  )
}


/*
export default function App() {
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
    <div>
      
      <Nav/>
      <UserPage/>

      <h1>Poll Name = {post.pollName}</h1>
      <h1>Poll ID = {post.pollID}</h1>
      <h1>Poll Created on = {post.pollCreationDate}</h1>
      <Input placeholder="Poll ID" min={0} max={100} type="number" onChange={event => setPollID(event.target.value)}/>
      <button onClick={createPost}>Create Post</button>
      <button onClick={readPost}>Read Post</button>
    </div>
  );
}*/

/*class App extends Component {
  state = {
    polls: []
  }

  componentDidMount() {
    fetch('http://localhost:8080/poll')
    .then(res => res.json())
    .then((data) => {
      this.setState({ polls: data })
    })
    .catch(console.log)
  }

  render () {
    return (
      <Polls polls={this.state.polls} />
    )
  }
}

export default App;

/*function App() {
  return (
    <div className="App">
      <Test/>
    </div>
  );
}

export default App;*/

