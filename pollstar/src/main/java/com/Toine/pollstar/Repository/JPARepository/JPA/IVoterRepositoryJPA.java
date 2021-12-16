package com.Toine.pollstar.Repository.JPARepository.JPA;

import com.Toine.pollstar.Core.Model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVoterRepositoryJPA extends JpaRepository<Voter, Integer>
{
    Voter findVoterByVoterID(int VoterID);
}
