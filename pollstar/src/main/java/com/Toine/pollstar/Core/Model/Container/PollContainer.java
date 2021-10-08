package com.Toine.pollstar.Core.Model.Container;

import com.Toine.pollstar.Core.Model.Choice;
import com.Toine.pollstar.Core.Model.Poll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PollContainer
{
    public List<Poll> polls;

    public PollContainer()
    {
     polls = new ArrayList<>();
     polls.add(new Poll(0,"Test", null, new Date(), 1, false ));
    }

    public boolean addPoll(int id, String name, List<Choice> choices, Date date, int ownerID, boolean locked)
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

    public boolean addPoll(int id, String name, Date date, int ownerID, boolean locked)
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


}
