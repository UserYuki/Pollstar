package com.Toine.pollstar.Core.Interface;

import com.Toine.pollstar.Core.Model.DTO.UserDetails.UserDTO;
import com.Toine.pollstar.Core.Model.Request.UserCreateRequest;
import com.Toine.pollstar.Core.Model.Request.UserPatchRequest;
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
    long readUserIDbyUsername (String username);
    void patchAccount(UserPatchRequest userPatchRequest);
    UserDTO getUserDTOfromUserbyID(long id);

    //------------------ Voter Stuff
    Voter CreateVoter(VoterCreateRequest voterCreateRequest);
    Voter LoginVoter(VoterCreateRequest voterCreateRequest);
    void DBSaveVoter(Voter voter);
    Voter DBGetVoter(int voterID);

}
