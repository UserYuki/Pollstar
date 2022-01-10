package com.Toine.pollstar.Api.Controller;

import com.Toine.pollstar.Core.Interface.IPollContainer;
import com.Toine.pollstar.Core.Model.Choice;
import com.Toine.pollstar.Core.Model.Container.PollContainer;
import com.Toine.pollstar.Core.Model.Poll;
import com.Toine.pollstar.Core.Model.User;
import com.Toine.pollstar.Core.Model.Voter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/poll")
public class PollController
{
    @Autowired
    IPollContainer IPC;

    @GetMapping("/test")
    @ResponseBody
    public String ConfirmTest()
    {
        IPC.savePoll(new Poll(0,"Test", new LinkedList<>(), new Date(), new User(), false ));
        return "Works!";
    }

    @GetMapping("{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable(value = "id") int id)
    {
        System.out.println("found and built!");
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

    @PostMapping()
    //POST at http://localhost:XXXX/poll/
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll)
    {
        if(!IPC.addPoll(poll))
        {
            String entity = "Poll with id " + poll.getPollID() + " already exists.";
            return new ResponseEntity(entity,HttpStatus.CONFLICT);
        }
        else
        {
            System.out.println(poll.ToString());
            Poll cPoll = IPC.addPolltoDBandGetBack(poll);

            //System.out.println(poll.ToString());
            //poll.getPollChoices().forEach((Choice c) -> System.out.println("c: " + c.getChoiceName()));
            return new ResponseEntity(cPoll.getPollID(),HttpStatus.CREATED);
        }
    }


//    @PostMapping("{id}")
//    //POST at http://localhost:XXXX/poll/id number where a vote needs to be added
//    public ResponseEntity<Poll> AddVoter(@RequestBody Voter voter, @PathVariable(value = "id") int id)
//    {
//        try
//        {
//
//            IPC.getPoll(id).castVote(voter, id);
//            return ResponseEntity.ok().body(IPC.getPoll(id));
//
//        }
//        catch(Exception ex)
//        {
//            String entity = "Poll with id " + id + " does not exist. or something else maybe";
//            return new ResponseEntity(entity,HttpStatus.CONFLICT);
//        }
//    }

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
            return ResponseEntity.ok().body(IPC.getPoll(ChoiceID));

        }
        catch(Exception ex)
        {
            System.out.println(ex);
            String entity = "Poll with id " + ChoiceID + " does not exist. or something else maybe";
            return new ResponseEntity(entity,HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<List<Poll>> getAllPolls() //add @RequestParam to check by pollOwner
    {
        if(IPC.getPolls() != null) {return ResponseEntity.ok().body(IPC.getPolls());}
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

}
