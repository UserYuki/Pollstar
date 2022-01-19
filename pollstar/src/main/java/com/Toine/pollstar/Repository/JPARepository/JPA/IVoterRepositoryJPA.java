package com.Toine.pollstar.Repository.JPARepository.JPA;

import com.Toine.pollstar.Core.Model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IVoterRepositoryJPA extends JpaRepository<Voter, Integer>
{
    Voter findVoterByVoterID(int VoterID);
    Optional<Voter> findVoterByUUID2(String UUID2);
    Optional<Voter> findVoterByUUID1AndUUID2(String UUID1, String UUID2);
}
