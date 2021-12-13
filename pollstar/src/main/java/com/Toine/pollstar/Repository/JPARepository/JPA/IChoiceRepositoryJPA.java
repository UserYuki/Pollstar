package com.Toine.pollstar.Repository.JPARepository.JPA;

import com.Toine.pollstar.Core.Model.Choice;
import com.Toine.pollstar.Core.Model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface IChoiceRepositoryJPA extends JpaRepository<Choice, Integer>
{
    //List<Choice> getAllByPollPollID(Poll poll);
    List<Choice> getChoiceByPoll(Poll poll);
    List<Choice> getAllByPoll(Poll poll);


}
