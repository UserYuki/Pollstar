package com.Toine.pollstar.Repository.Interfaces;

import com.Toine.pollstar.Core.Model.Voter;

import java.util.Optional;

public interface IVoterStorage
{
    Voter saveVotertoDB(Voter voter);
    Voter getVoterfromDBbyID(int id);
    Optional<Voter> getVoterfromDBbyUuid1and2(String id1, String id2);

}
