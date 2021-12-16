package com.Toine.pollstar.Repository.Interfaces;

import com.Toine.pollstar.Core.Model.Voter;

public interface IVoterStorage
{
    void saveVotertoDB(Voter voter);
    Voter getVoterfromDBbyID(int id);
}
