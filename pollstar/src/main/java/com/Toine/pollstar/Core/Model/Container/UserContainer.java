package com.Toine.pollstar.Core.Model.Container;

import com.Toine.pollstar.Core.Interface.IUserContainer;
import com.Toine.pollstar.Core.Model.DTO.UserDetails.UserDTO;
import com.Toine.pollstar.Core.Model.Request.UserCreateRequest;
import com.Toine.pollstar.Core.Model.Request.UserPatchRequest;
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
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //might not be allowed to init like this... (if break check this)
    //public UserContainer() { this.users = new ArrayList<>();  }

    @Autowired
    IUserStorage userDAL;

    @Autowired
    IVoterStorage voterDAL;

    public UserContainer( IUserStorage userDAL, IVoterStorage voterDAL ) //for Testing using mocks
    {
        this.userDAL = userDAL;
        this.voterDAL = voterDAL;
    }

    public User readUserByUsername (String username) {
        return userDAL.returnUserbyUserNameinDB(username).orElseThrow(EntityNotFoundException::new);
    }

    public User CreateUser(long userID, String userName, String eMailAddress, String password, boolean admin)
    {
        User createdUser = new User();
        createdUser.setUserID(userID);
        createdUser.setUserName(userName);
        createdUser.setEMailAddress(eMailAddress);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setAdmin(admin);

        return createdUser;
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

            user.setVoter( DBGetVoter(userCreateRequest.getVCR().getVoterID().get()));
            User u = userDAL.saveGetUsertoDB(user);
            u.getVoter().setUser(u);

        System.out.println(DBGetVoter(userCreateRequest.getVCR().getVoterID().get()).getUser());


    }

    @Override
    public long readUserIDbyUsername(String username) {
        return userDAL.returnUserIDbyUsernameinDB(username);
    }

    @Override
    public UserDTO getUserDTOfromUserbyID(long id) {return userDAL.getUserByUserID(id);}

    @Override
    public void patchAccount(UserPatchRequest userPatchRequest) {
        User user = userDAL.returnUserbyID(userPatchRequest.getUserID()).get();
        if(!NameVerify(user.getUserName(), userPatchRequest.getCurrentPassword()))
        {
            throw new RuntimeException("The given ID doesn't match with the current account details.");
        }
        else {

            if(userPatchRequest.getNewUsername() == null) {}
            else
            {
                user.setUserName(userPatchRequest.getNewUsername());
            }
            if(userPatchRequest.getNewEMailAddress() == null) {}
            else
            {
                user.setEMailAddress(userPatchRequest.getNewEMailAddress());
            }
            if(userPatchRequest.getNewPassword() != null ) {
            if(userPatchRequest.getNewPassword() == userPatchRequest.getConfirmedNewPassword())
            {
                System.out.println("1" + userPatchRequest.getNewPassword());
                System.out.println("2" + userPatchRequest.getConfirmedNewPassword());
                user.setPassword(passwordEncoder.encode(userPatchRequest.getNewPassword()));

            }
            else
            {
                throw new RuntimeException("New password must be confirmed with an identical password.");
            }
            }
            System.out.println("a");
            userDAL.saveUsertoDB(user);
        }

    }


    @Override
    public Voter CreateVoter(VoterCreateRequest voterCreateRequest) {
        Voter voter = new Voter();

        voter.setUUID1(voterCreateRequest.getUUID1());
        voter.setUUID2(voterCreateRequest.getUUID2());

        return voterDAL.saveVotertoDB(voter);
    }

    @Override
    public Voter LoginVoter(VoterCreateRequest voterCreateRequest) {
        Optional<Voter> voter = voterDAL.getVoterfromDBbyUuid1and2(voterCreateRequest.getUUID1(), voterCreateRequest.getUUID2());
        if(voter.isPresent())
        {
            return voter.get();
        }
        else
        {
            throw new RuntimeException("Doesn't exist");
        }
    }

    @Override
    public void DBSaveVoter(Voter voter) {
        voterDAL.saveVotertoDB(voter);
    }

    @Override
    public Voter DBGetVoter(int voterID) {
        return voterDAL.getVoterfromDBbyID(voterID);
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

        if(test.isEmpty()) { return false; }

        return passwordEncoder.matches(Pwd, test.get().getPassword());
    }

    public boolean EmailVerify(String EmailAddr, String Pwd)
    {
        return userDAL.VerifyAccountbyEmailinDB(EmailAddr, Pwd);
    }



}
