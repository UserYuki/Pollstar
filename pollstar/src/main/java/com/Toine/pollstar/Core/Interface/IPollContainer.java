package com.Toine.pollstar.Core.Interface;

import com.Toine.pollstar.Core.Model.Poll;
import com.Toine.pollstar.Core.Model.Voter;

import java.util.List;

public interface IPollContainer
{
    void savePoll(Poll poll);
    Poll getPoll(int pID);
    boolean addPoll(Poll poll);
    List<Poll> getPolls();

    Poll getPollfromDBbyID(int ID);

    Poll addPolltoDBandGetBack(Poll poll);

    boolean CastVotetoDB(Voter voter, int pollID, int ChoiceID);

}
