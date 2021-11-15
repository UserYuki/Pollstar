package com.Toine.pollstar.Repository.JPARepository;

import com.Toine.pollstar.Core.Model.Choice;
import com.Toine.pollstar.Core.Model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IChoiceRepositoryJPA extends JpaRepository<Choice, Long>
{
    //List<Choice> getAllByPollPollID(Poll poll);
    List<Choice> getChoiceByPoll(Poll poll);


}
