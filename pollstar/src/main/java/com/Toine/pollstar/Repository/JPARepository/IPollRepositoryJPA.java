package com.Toine.pollstar.Repository.JPARepository;

import com.Toine.pollstar.Core.Model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPollRepositoryJPA extends JpaRepository<Poll, Integer>
{
    Poll getPollByPollID(int pollid);
}
