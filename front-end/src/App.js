import logo from './logo.svg';
import './App.css';

import axios from "axios";
import React from "react";
import {useState,useEffect} from 'react'
import { useCookies } from 'react-cookie';
import { CustomInput, FormText, Button} from 'reactstrap';
import Nav from './components/Nav.js';
import UserPage from './components/UserSigning/UserPage.js';
import UserAccountPage from './components/UserSigning/UserAccountPage';
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
  
    //creating cookie
    const [cookies, setCookie] = useCookies(['Voter', 'JWT', 'ID' ]);
    const baseURL = "http://localhost:8080/";
    const [ipadd, setipadd] = useState("");
    const [uuidadd, setuuidadd] = useState(0);
    //creating function to load ip address from the API
    const getData = async() => {
      await axios.get('https://geolocation-db.com/json/').then((res) => {setipadd(res.data.IPv4)})

      if(localStorage.getItem("uID") === null || localStorage.getItem("uID").length === 0 ) 
      {
        setuuidadd(Math.floor(Math.random() * 101))
      }
      else
      {
        setuuidadd(localStorage.getItem("uID"));
      }
      
    }
    useEffect( () => {
      getData()
      try{
        if(cookies.get('ID') < 3) {return;}
      }
      catch(Exception)
      {
        try
        {
          if(cookies.JWT === null) {return;}
            axios.get(`${baseURL}api/user/retrieveID`, 
            {headers: {Authorization: cookies.JWT}}).then((res)=>{
              if(res.status == 201){setCookie('ID', res.data, { path: '/' });}
              else
              {
                console.log(res.data)
              }
            }).catch((error) => console.log(error))
        }
        catch(Exc)
        {

        }
      }
    }, [])

    useEffect( () => {
      
      if(cookies.Voter === undefined && ipadd.length > 1 && uuidadd >= 0)
      {
        localStorage.setItem("uID", uuidadd);
        push(ipadd, uuidadd);
      }
      
    }, [ipadd, uuidadd])

    function push(ip, uuid2)
    {
      axios
      .post(`${baseURL}voter/login`, {
        uuid1: ip,
        uuid2: uuid2
      })
      .then((response) => {
        console.log(response.status)
        setCookie('Voter', {voterID: response.data.voterID, uuid1: ip, uuid2: uuid2}, { path: '/' });
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
          <Route exact path="/Account">
            <UserAccountPage />
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

