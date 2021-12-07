package com.Toine.pollstar.Repository.JPARepository;

import com.Toine.pollstar.Core.Model.Voter;
import com.Toine.pollstar.Repository.Interfaces.IVoterStorage;
import com.Toine.pollstar.Repository.JPARepository.JPA.IUserRepositoryJPA;
import com.Toine.pollstar.Repository.JPARepository.JPA.IVoterRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VoterStorage implements IVoterStorage
{
    @Autowired
    IVoterRepositoryJPA repo;

    @Override
    public void saveVotertoDB(Voter voter) {
        repo.save(voter);
    }

}
