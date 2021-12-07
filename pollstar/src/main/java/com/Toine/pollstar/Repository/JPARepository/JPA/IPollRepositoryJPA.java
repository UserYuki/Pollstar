package com.Toine.pollstar.Repository.JPARepository.JPA;

import com.Toine.pollstar.Core.Model.Choice;
import com.Toine.pollstar.Core.Model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPollRepositoryJPA extends JpaRepository<Poll, Integer>
{
    Poll getPollByPollID(int pollid);
    Poll getPollByPollAndPollChoicesContains(Choice choice);
}
