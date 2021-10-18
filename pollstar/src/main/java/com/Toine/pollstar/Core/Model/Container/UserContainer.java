package com.Toine.pollstar.Core.Model.Container;

import com.Toine.pollstar.Core.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserContainer
{
    public List<User> users;

    public UserContainer()
    {
        this.users = new ArrayList<>();
    }


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

}
