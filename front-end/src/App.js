import logo from './logo.svg';
import './App.css';

import axios from "axios";
import React from "react";
import {useState,useEffect} from 'react'
import { useCookies } from 'react-cookie';
import { CustomInput, FormText, Input } from 'reactstrap';
import Nav from './components/Nav.js';
import UserPage from './components/UserSigning/UserPage.js';
import CreatePoll from './components/Polls/CreatePoll.js';
import ViewPoll from './components/Polls/ViewPoll.js';
import VotePoll from './components/Polls/VotePoll.js';
import Home from './components/Home.js';

import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import UserSignUp from './components/UserSigning/UserSignUp';
import UserSignIn from './components/UserSigning/UserSignIn';

export default function App() 
{
    //creating IP state
    const [ip, setIP] = useState('');
    //creating cookie
    const [cookies, setCookie] = useCookies(['Voter', 'IP', 'uuid2']);
    const baseURL = "http://localhost:8080/";
    //creating function to load ip address from the API
    const getData = async () => {
      const res = await axios.get('https://geolocation-db.com/json/')
      //console.log(res.data);
      setIP(res.data.IPv4)
      setCookie('IP', res.data.IPv4, { path: '/' });

      axios.post(`https://api.random.org/json-rpc/4/invoke`, {
        "jsonrpc": "2.0",
        "method": "getUsage",
        "params": {
            "apiKey": "598c9146-7fce-45fa-8eae-10ed6d6e9b01" //secret api key don't look!
        },
        "id": 15998
      }).then((res) => {
        if(res.data.result.requestsLeft < 15 ) {console.log("no requests remaining"); return;}
      })

      axios.post(`https://api.random.org/json-rpc/4/invoke`, {
        "jsonrpc": "2.0",
        "method": "generateUUIDs",
        "params": {
            "apiKey": "598c9146-7fce-45fa-8eae-10ed6d6e9b01",
            "n": 1
        },
        "id": 15998
      }).then((res) => {
        setCookie('uuid2', res.data.result.random.data[0], { path: '/' });
      })
    }
    
    useEffect( () => {
      //passing getData method to the lifecycle method
      if(cookies.IP && cookies.uuid2) {return;}
      console.log("gettin")
      getData()
      if(!cookies.Voter || cookies.IP && cookies.uuid2)
      {
        push();
      }
    }, [])

    function push()
    {
      axios
      .post(`${baseURL}voter/create`, {
        uuid1: cookies.IP,
        uuid2: cookies.uuid2
      })
      .then((response) => {
        console.log(response.status)
        setCookie('Voter', response.data.voterID, { path: '/' });
      });
      
      
  }
    

  return(
    <>

     
      <Router>
      <Nav/>
      <Switch>
          <Route path="/CreatePoll">
            <CreatePoll />
          </Route>
          <Route path="/ViewPoll/:passedPollID">
            <ViewPoll />
          </Route>
          <Route path="/ViewPoll">
            <ViewPoll />
          </Route>
          <Route path="/VotePoll">
            <VotePoll />
          </Route>
          <Route exact path="/User/SignIn">
            <UserSignIn />
          </Route>
          <Route exact path="/User/SignUp:passedUserName">
            <UserSignUp />
          </Route>
          <Route exact path="/User/SignUp">
            <UserSignUp />
          </Route>
          <Route exact path="/User">
            <UserPage />
          </Route>
      </Switch>
      </Router>
    </>
  );
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

