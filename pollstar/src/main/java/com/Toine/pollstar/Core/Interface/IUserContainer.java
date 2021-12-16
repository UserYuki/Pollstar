package com.Toine.pollstar.Core.Interface;

import com.Toine.pollstar.Core.Model.Request.UserCreateRequest;
import com.Toine.pollstar.Core.Model.Request.VoterCreateRequest;
import com.Toine.pollstar.Core.Model.User;
import com.Toine.pollstar.Core.Model.Voter;

public interface IUserContainer
{
    boolean OPUser(User admin, User user);
    boolean DEOPUser(User admin, User user);
    boolean NameVerify(String UserN, String Pwd);
    User readUserByUsername (String username);
    void CreateUser(UserCreateRequest userCreateRequest);


    //------------------ Voter Stuff
    void CreateVoter(VoterCreateRequest voterCreateRequest);
    void DBSaveVoter(Voter voter);
    Voter DBGetVoter(int voterID);

}
