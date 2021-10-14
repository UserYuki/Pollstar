import React from 'react'

    const Polls = ({ polls }) => {
      return (
        <div>
          <center><h1>Poll List</h1></center>
          {polls.map((poll) => (
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">{poll.pollName}</h5>
                <h6 class="card-subtitle mb-2 text-muted">{poll.pollCreationDate}</h6>
                <h7 class="card-text">{poll.pollID}</h7>
                <p class="card-text">{poll.pollLockedStatus}</p>
              </div>
            </div>
          ))}
        </div>
      )
    };

    export default Polls