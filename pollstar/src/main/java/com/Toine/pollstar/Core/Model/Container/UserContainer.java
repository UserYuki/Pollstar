package com.Toine.pollstar.Core.Model.Container;

import com.Toine.pollstar.Core.Interface.IUserContainer;
import com.Toine.pollstar.Core.Model.Request.UserCreateRequest;
import com.Toine.pollstar.Core.Model.Request.VoterCreateRequest;
import com.Toine.pollstar.Core.Model.User;
import com.Toine.pollstar.Core.Model.Voter;
import com.Toine.pollstar.Repository.Interfaces.IUserStorage;
import com.Toine.pollstar.Repository.Interfaces.IVoterStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    IUserStorage userDAL;

    @Autowired
    IVoterStorage voterDAL;

    public User readUserByUsername (String username) {
        return userDAL.returnUserbyUserNameinDB(username).orElseThrow(EntityNotFoundException::new);
    }

    public void CreateUser(UserCreateRequest userCreateRequest) {
        User user = new User();
        Optional<User> byUsername = userDAL.returnUserbyUserNameinDB(userCreateRequest.getUsername());
        if (byUsername.isPresent()) {
            throw new RuntimeException("User "+ userCreateRequest.getUsername() +" already registered. Please use a different username.");
        }
        Optional<User> byeMail = userDAL.returnUserbyeMailAddressinDB(userCreateRequest.getEMailAddress());
        if (byeMail.isPresent()) {
            throw new RuntimeException("Email "+ userCreateRequest.getEMailAddress() +" already registered. Please use a different email, or login.");
        }
        user.setUserName(userCreateRequest.getUsername());
        user.setEMailAddress(userCreateRequest.getEMailAddress());
        user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        userDAL.saveUsertoDB(user);
    }

    @Override
    public long readUserIDbyUsername(String username) {
        return userDAL.returnUserIDbyUsernameinDB(username);
    }

    @Override
    public Voter CreateVoter(VoterCreateRequest voterCreateRequest) {
        Voter voter = new Voter();

        voter.setUUID1(voterCreateRequest.getUUID1());
        voter.setUUID2(voterCreateRequest.getUUID2());

        return voterDAL.saveVotertoDB(voter);
    }

    @Override
    public void DBSaveVoter(Voter voter) {
        voterDAL.saveVotertoDB(voter);
    }

    @Override
    public Voter DBGetVoter(int voterID) {
        return voterDAL.getVoterfromDBbyID(voterID);
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
        Optional<User> test = userDAL.returnUserbyUserNameinDB(UserN);

        return passwordEncoder.matches(Pwd, test.get().getPassword());
    }

    public boolean EmailVerify(String EmailAddr, String Pwd)
    {
        return userDAL.VerifyAccountbyEmailinDB(EmailAddr, Pwd);
    }



}
