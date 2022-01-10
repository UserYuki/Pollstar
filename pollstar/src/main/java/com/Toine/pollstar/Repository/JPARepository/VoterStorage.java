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
    public Voter saveVotertoDB(Voter voter) {
        return repo.saveAndFlush(voter);
    }

    @Override
    public Voter getVoterfromDBbyID(int id) {
        return repo.findVoterByVoterID(id);
    }

}
