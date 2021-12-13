package com.Toine.pollstar.Repository.JPARepository;

import com.Toine.pollstar.Core.Model.Choice;
import com.Toine.pollstar.Core.Model.Poll;
import com.Toine.pollstar.Repository.Interfaces.IPollStorage;
import com.Toine.pollstar.Repository.JPARepository.JPA.IPollRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class PollStorage implements IPollStorage
{

    @Autowired
    IPollRepositoryJPA repo;

    @Override
    public Poll getPollByID(int pollid) {
        return repo.getPollByPollID(pollid);
    }

    @Transactional
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

    @Override
    public Poll getPollbyChoiceID(int ChoiceID)
    {
        Choice c = new Choice();
        c.setChoiceID(ChoiceID);
        return repo.getPollByPollChoicesContains(c);
    }

}
