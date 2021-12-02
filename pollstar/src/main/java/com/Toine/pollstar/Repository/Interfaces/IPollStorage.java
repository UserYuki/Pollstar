package com.Toine.pollstar.Repository.Interfaces;

import com.Toine.pollstar.Core.Model.Poll;

import java.util.List;

public interface IPollStorage
{
    Poll getPollByID(int pollid);

    Poll savePoll(Poll poll);

    void savePolltoDB(Poll poll);

    Poll savePolltoDBandGet(Poll poll);

}
