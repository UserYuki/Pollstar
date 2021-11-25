package com.Toine.pollstar.Core.Model.Container;

import com.Toine.pollstar.Core.Interface.IUserContainer;
import com.Toine.pollstar.Core.Model.Request.UserCreateRequest;
import com.Toine.pollstar.Core.Model.User;
import com.Toine.pollstar.Repository.Interfaces.IUserStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserContainer implements IUserContainer
{
    public List<User> users;
    private final BCryptPasswordEncoder passwordEncoder;
    //public UserContainer() { this.users = new ArrayList<>();  }

    @Autowired
    IUserStorage DAL;

    public User readUserByUsername (String username) {
        return DAL.returnUserbyUserNameinDB(username).orElseThrow(EntityNotFoundException::new);
    }

    public void CreateUser(UserCreateRequest userCreateRequest) {
        User user = new User();
        Optional<User> byUsername = DAL.returnUserbyUserNameinDB(userCreateRequest.getUsername());
        if (byUsername.isPresent()) {
            throw new RuntimeException("User already registered. Please use different username.");
        }
        user.setUserName(userCreateRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        DAL.saveUsertoDB(user);
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

    public boolean NameVerify(String UserN, String Pwd)
    {
        return DAL.VerifyAccountbyUserNameinDB(UserN, Pwd);
    }

    public boolean EmailVerify(String EmailAddr, String Pwd)
    {
        return DAL.VerifyAccountbyEmailinDB(EmailAddr, Pwd);
    }



}
