package com.Toine.pollstar.Api.Controller;

import com.Toine.pollstar.Core.Interface.IPollContainer;
import com.Toine.pollstar.Core.Interface.IUserContainer;
import com.Toine.pollstar.Core.Model.Poll;
import com.Toine.pollstar.Core.Model.Request.UserCreateRequest;
import com.Toine.pollstar.Core.Model.Request.VoterCreateRequest;
import com.Toine.pollstar.Core.Model.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/voter")
public class VoterController
{
    @Autowired
    IUserContainer IUC;

    @Autowired
    IPollContainer IPC;

    @PostMapping("/create")
    public ResponseEntity<Voter> CreateVoter (@RequestBody VoterCreateRequest voterCreateRequest)
    {
        try{
            Voter v = IUC.CreateVoter(voterCreateRequest);
            return ResponseEntity.ok().body(v);
        }
        catch(Exception ex) {
            return new ResponseEntity(ex.toString(), HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Voter> ReturnVoter (@RequestBody VoterCreateRequest voterCreateRequest)
    {
        try{
            Voter v = IUC.LoginVoter(voterCreateRequest);
            return ResponseEntity.ok().body(v);
        }
        catch(RuntimeException ex) {
            return CreateVoter(voterCreateRequest);
        }

    }



    @GetMapping("/getPoll/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable(value = "id") int id)
    {
        Poll poll = IPC.getPollfromDBbyID(id);

        if(poll != null)
        {

            return ResponseEntity.ok().body(poll);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/vote")
    //POST at http://localhost:XXXX/poll/id number where a vote needs to be added
    public ResponseEntity<Poll> AddVoter(@RequestBody Voter voter, @RequestParam int pollID, @RequestParam int ChoiceID) //use Request param
    {
        System.out.println(ChoiceID);
        System.out.println(voter);
        //System.out.println(pollID);
        try
        {
            //IPC.CastVotetoDB(voter, id);
            IPC.CastVotetoDB(voter, pollID, ChoiceID);
            return new ResponseEntity(pollID, HttpStatus.OK);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            String entity = "Poll with id " + ChoiceID + " does not exist. or something else maybe";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }
    }

}
