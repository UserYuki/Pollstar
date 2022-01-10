package com.Toine.pollstar.Repository.Interfaces;

import com.Toine.pollstar.Core.Model.Voter;

public interface IVoterStorage
{
    Voter saveVotertoDB(Voter voter);
    Voter getVoterfromDBbyID(int id);

}
