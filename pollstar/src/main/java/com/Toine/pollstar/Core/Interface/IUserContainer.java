package com.Toine.pollstar.Core.Interface;

import com.Toine.pollstar.Core.Model.Request.UserCreateRequest;
import com.Toine.pollstar.Core.Model.User;

public interface IUserContainer
{
    boolean OPUser(User admin, User user);
    boolean DEOPUser(User admin, User user);
    boolean NameVerify(String UserN, String Pwd);
    User readUserByUsername (String username);
    void CreateUser(UserCreateRequest userCreateRequest);

}
