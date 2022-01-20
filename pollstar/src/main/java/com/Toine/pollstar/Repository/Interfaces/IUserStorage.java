package com.Toine.pollstar.Repository.Interfaces;

import com.Toine.pollstar.Core.Model.DTO.UserDetails.UserDTO;
import com.Toine.pollstar.Core.Model.User;

import java.util.Optional;

public interface IUserStorage
{
    boolean VerifyAccountbyUserNameinDB(String UserName, String Password);
    boolean VerifyAccountbyEmailinDB(String eMailAddress, String Password);
    Optional<User> returnUserbyUserNameinDB(String username);
    Optional<User> returnUserbyeMailAddressinDB(String eMailAddress);
    Optional<User> returnUserbyVoterIDinDB(int voterID);
    void saveUsertoDB(User user);
    long returnUserIDbyUsernameinDB(String username);
    Optional<User> returnUserbyID(long id);
    UserDTO getUserByUserID(long UserID);
    User saveGetUsertoDB(User user);
}
