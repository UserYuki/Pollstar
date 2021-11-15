package com.Toine.pollstar.Repository.JPARepository;

import com.Toine.pollstar.Core.Model.Choice;
import com.Toine.pollstar.Core.Model.Poll;
import com.Toine.pollstar.Repository.Interfaces.IChoiceStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChoiceStorageJPA implements IChoiceStorage
{

    @Autowired
    IChoiceRepositoryJPA repo;

    @Override
    public List<Choice> GetAllChoicesByPoll(Poll poll) {
        return repo.getChoiceByPoll(poll);
    }


    //@Override
    //public List<Choice> getChoiceForFirstPollById(int id) {return repo.getAllByPollId(id); }

}
