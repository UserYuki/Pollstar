package com.Toine.pollstar.Core.Model.Container;

import com.Toine.pollstar.Core.Interface.IPollContainer;
import com.Toine.pollstar.Core.Interface.IUserContainer;
import com.Toine.pollstar.Core.Model.Choice;
import com.Toine.pollstar.Core.Model.Poll;
import com.Toine.pollstar.Core.Model.User;
import com.Toine.pollstar.Core.Model.Voter;
import com.Toine.pollstar.Repository.Interfaces.IChoiceStorage;
import com.Toine.pollstar.Repository.Interfaces.IPollStorage;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PollContainer implements IPollContainer
{
    private List<Poll> polls = new ArrayList<>();
    private List<Choice> choices;

    @Autowired
    IPollStorage pollDAL;

    @Autowired
    IChoiceStorage choiceDAL;

    @Autowired
    IUserContainer IUC;

    //private ChoiceContainer choiceContainer;

    public PollContainer()
    {
     polls = new ArrayList<>();
     //choiceContainer = new ChoiceContainer();

    }
    public PollContainer(IPollStorage pollDAL, IChoiceStorage choiceDAL, IUserContainer IUC)
    {
        this.pollDAL = pollDAL;
        this.choiceDAL = choiceDAL;
        this.IUC = IUC;
    }

    //public Poll dbGetPoll() {pollDAL.getPollByID()}

    public boolean addPoll(Poll poll)
    {
        try
        {
            polls.add(poll);
            return true;
        }
        catch (Exception exception)
        {
            return false;
        }

    }

    public Poll getPoll(int pID)
    {
        for (Poll poll : polls)
        {
            if(pID == poll.getPollID())
            {
                return poll;
            }
        }
        return null;
    }

    public List<Poll> getPolls()
    {
        return polls;
    }

    @Override
    public Poll getPollfromDBbyID(int ID) {
        return pollDAL.getPollByID(ID);
    }


    @Override
    public Poll addPolltoDBandGetBack(@NotNull Poll poll) //necessary, big!
    {

        poll.getPollChoices().forEach((Choice c) -> c.setPoll(poll) );


        Poll save = pollDAL.savePoll(poll);

        //save.getPollChoices().forEach((Choice c) -> System.out.println("n: " + c.getChoiceName() + ", id: " + c.getChoiceID()));



        return save;
    }

    @Override
    public boolean CastVotetoDB(Voter voter, int pollID, int Choiceid) {
        //Poll poll = pollDAL.getPollbyChoiceID(Choiceid);
        Poll poll = pollDAL.getPollByID(pollID);
        Voter votee = IUC.DBGetVoter(voter.getVoterID());

        if(poll.castVote(votee, Choiceid)){IUC.DBSaveVoter(votee); return true;} else{return false;}
    }






    @Override
    public void savePoll(Poll poll)
    {
        addPoll(poll);
        pollDAL.savePolltoDB(poll);
    }
}
