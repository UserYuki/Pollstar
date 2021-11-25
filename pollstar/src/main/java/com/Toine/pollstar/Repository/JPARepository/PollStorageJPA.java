package com.Toine.pollstar.Repository.JPARepository;

import com.Toine.pollstar.Core.Model.Poll;
import com.Toine.pollstar.Repository.Interfaces.IPollStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PollStorageJPA implements IPollStorage
{

    @Autowired
    IPollRepositoryJPA repo;

    @Override
    public Poll getPollByID(int pollid) {
        return repo.getPollByPollID(pollid);
    }

    @Override
    public Poll savePoll(Poll poll) {
        return repo.saveAndFlush(poll);
    }
}
