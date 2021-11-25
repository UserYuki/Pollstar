package com.Toine.pollstar.Core.Interface;

import com.Toine.pollstar.Core.Model.Poll;

import java.util.List;

public interface IPollContainer
{
    void savePoll(Poll poll);
    Poll getPoll(int pID);
    boolean addPoll(Poll poll);
    List<Poll> getPolls();

}
