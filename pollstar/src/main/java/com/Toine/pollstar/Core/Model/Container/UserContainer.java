package com.Toine.pollstar.Core.Model.Container;

import com.Toine.pollstar.Core.Interface.IUserContainer;
import com.Toine.pollstar.Core.Model.User;
import com.Toine.pollstar.Repository.Interfaces.IUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserContainer implements IUserContainer
{
    public List<User> users;

    public UserContainer()
    {
        this.users = new ArrayList<>();
    }

    @Autowired
    IUserStorage DAL;

    public boolean CreateUser(String userName, String eMailAddr, String password, boolean admin)
    {
        try
        {
            users.add(new User(userName, eMailAddr, password, admin));
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }

    }

    public boolean OPUser(User admin, User user)
    {
        if(admin.getAdmin())
        {
            user.setAdmin(true);
            return user.getAdmin();
        }
        else
        {
            return false;
        }

    }

    public boolean DEOPUser(User admin, User user)
    {
        if(admin.getAdmin())
        {
            user.setAdmin(false);
            return !user.getAdmin();
        }
        else
        {
            return false;
        }
    }

    public boolean NameVerify(String UserN, String Pwd)
    {
        return DAL.VerifyAccountbyUserNameinDB(UserN, Pwd);
    }

    public boolean EmailVerify(String EmailAddr, String Pwd)
    {
        return DAL.VerifyAccountbyEmailinDB(EmailAddr, Pwd);
    }



}
