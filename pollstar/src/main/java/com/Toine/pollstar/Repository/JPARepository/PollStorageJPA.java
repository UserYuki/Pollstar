package com.Toine.pollstar.Repository.JPARepository;

import com.Toine.pollstar.Core.Model.Poll;
import com.Toine.pollstar.Core.Model.User;
import com.Toine.pollstar.Repository.Interfaces.IPollStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    @Override
    public void savePolltoDB(Poll poll) {
        repo.save(poll);
    }

    @Override
    public Poll savePolltoDBandGet(Poll poll) {
       return repo.save(poll);
    }

}
