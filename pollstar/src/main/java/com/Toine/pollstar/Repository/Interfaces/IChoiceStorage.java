package com.Toine.pollstar.Repository.Interfaces;

import com.Toine.pollstar.Core.Model.Choice;
import com.Toine.pollstar.Core.Model.Poll;

import java.util.List;

public interface IChoiceStorage
{
    //List<Choice> getChoiceForFirstPollById(int id);
    List<Choice> GetAllChoicesByPoll(Poll poll);
    List<Choice> GetAllByPoll(Poll poll);
    Choice saveChoicetoDBandGet(Choice Choice);
}
