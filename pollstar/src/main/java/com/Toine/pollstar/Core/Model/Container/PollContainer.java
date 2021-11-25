package com.Toine.pollstar.Core.Model.Container;

import com.Toine.pollstar.Core.Interface.IPollContainer;
import com.Toine.pollstar.Core.Model.Choice;
import com.Toine.pollstar.Core.Model.Poll;
import com.Toine.pollstar.Core.Model.User;
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
    IPollStorage DAL;

    //private ChoiceContainer choiceContainer;

    public PollContainer()
    {
     polls = new ArrayList<>();
     //choiceContainer = new ChoiceContainer();

    }

    public boolean addPoll(int id, String name, List<Choice> choices, Date date, User ownerID, boolean locked)
    {
        try
        {
            polls.add(new Poll(id, name, choices, date, ownerID, locked));
            return true;
        }
        catch (Exception exception)
        {
            return false;
        }

    }

    public boolean addPoll(int id, String name, Date date, User ownerID, boolean locked)
    {
        try
        {
            polls.add(new Poll(id, name, date, ownerID, locked));
            return true;
        }
        catch (Exception exception)
        {
            return false;
        }

    }

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
        DAL.savePoll(poll);
    }
}
