package com.Toine.pollstar.Core.Model.Container;

import com.Toine.pollstar.Core.Interface.IPollContainer;
import com.Toine.pollstar.Core.Model.Choice;
import com.Toine.pollstar.Core.Model.Poll;
import com.Toine.pollstar.Core.Model.User;
import com.Toine.pollstar.Core.Model.Voter;
import com.Toine.pollstar.Repository.Interfaces.IChoiceStorage;
import com.Toine.pollstar.Repository.Interfaces.IPollStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class PollContainer implements IPollContainer
{
    private List<Poll> polls;
    private List<Choice> choices;

    @Autowired
    IPollStorage pollDAL;

    @Autowired
    IChoiceStorage choiceDAL;

    //private ChoiceContainer choiceContainer;

    public PollContainer()
    {
     polls = new ArrayList<>();
     //choiceContainer = new ChoiceContainer();

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
    public Poll addPolltoDBandGetBack(Poll poll) //not necessary, big!
    {
        Poll saved = pollDAL.savePolltoDBandGet(poll);

        for(Choice c : saved.getPollChoices()) //putting poll in choice and saving them to db
        {
            c.setPoll(saved);
            choiceDAL.saveChoicetoDBandGet(c).getChoiceID();
            //c.setChoiceID(choiceDAL.saveChoicetoDBandGet(c).getChoiceID());
        }

        saved.setPollChoices(choiceDAL.GetAllByPoll(saved));
        return saved;
    }

    @Override
    public boolean CastVotetoDB(Voter voter, int Choiceid) {
        Poll poll = pollDAL.getPollbyChoiceID(Choiceid);

        poll.getPollChoices().forEach(choice -> System.out.println(choice.getChoiceName()));

        return poll.castVote(voter, Choiceid);
    }

//    @Override
//    public boolean CastVotetoDB(Voter voter, Poll poll, int Choiceid) {
//        return getPollfromDBbyID(poll.getPollID()).castVote(voter, Choiceid);
//    }

    public Poll CreatePoll(String pollName, List<String> tbChoices, User pollOwnerID) //accept linkedlist of choice names, give list to create choice list -> create choice
    {
        //insert pollname, list of strings to be choices, pollowner / pollownerid idk yet
        Poll temp = new Poll(polls.size(), pollName, CreateChoiceList(tbChoices), new Date(), pollOwnerID, false);//TODO: createchoice is passed by value, is subject to change
        polls.add(temp);
        return temp; //TODO: Again; passed by value...
    }


    //Choice / option bs:
    public List<Choice> CreateChoiceList(List<String> RawChoices)
    {
        List<Choice> PollOptions = new LinkedList<Choice>();
        for (var item : RawChoices)
        {
            PollOptions.add(CreateChoice(item));
        }
        return PollOptions;
    }
    public Choice CreateChoice(String name)
    {
        Choice temp = new Choice(choices.size(), name);
        return temp;
    }


    @Override
    public void savePoll(Poll poll)
    {
        addPoll(poll);
        pollDAL.savePoll(poll);
    }
}
