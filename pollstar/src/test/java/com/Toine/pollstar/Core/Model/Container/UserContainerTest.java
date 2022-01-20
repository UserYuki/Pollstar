package com.Toine.pollstar.Core.Model.Container;

import com.Toine.pollstar.Core.Model.DTO.Mapper.UserMapper;
import com.Toine.pollstar.Core.Model.DTO.UserDetails.UserDTO;
import com.Toine.pollstar.Core.Model.Request.UserCreateRequest;
import com.Toine.pollstar.Core.Model.Request.UserPatchRequest;
import com.Toine.pollstar.Core.Model.Request.VoterCreateRequest;
import com.Toine.pollstar.Core.Model.User;
import com.Toine.pollstar.Core.Model.Voter;
import com.Toine.pollstar.Repository.Interfaces.IUserStorage;
import com.Toine.pollstar.Repository.Interfaces.IVoterStorage;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.persistence.EntityNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class UserContainerTest {

    //Mocks:
    @Mock
    private IUserStorage userDAL;
    @Mock
    private IVoterStorage voterDAL;

    //Class in question, that needs testing:
    private UserContainer userC = new UserContainer(userDAL, voterDAL);

    //Test Subjects:
    User user;
    User rawUser;
    Voter voter;
    VoterCreateRequest vCR;
    UserCreateRequest uCR;
    UserPatchRequest uPR;


    @BeforeEach
    public void setup()
    {
        //Double check, because funky:
        userDAL = mock(IUserStorage.class);
        voterDAL = mock(IVoterStorage.class);

        //Double checking, again:
        userC = new UserContainer(userDAL, voterDAL);

        //Data in Test subjects:
        user = userC.CreateUser(1,"Tester", "Test@Email.nothing", "veryyStrong", false);
        rawUser = new User(1,"Tester", "Test@Email.nothing", "veryyStrong", false);
        voter = new Voter();
        voter.setVoterID(5);
        voter.setUUID1("RandomIp");
        voter.setUUID2("98532608492 (Random int/String)");

        vCR = new VoterCreateRequest();
        vCR.setVoterID(Optional.of(5));
        vCR.setUUID1("RandomIp");
        vCR.setUUID2("98532608492 (Random int/String)");

        uCR = new UserCreateRequest();
        uCR.setUsername("Tester");
        uCR.setEMailAddress("Test@Email.nothing");
        uCR.setPassword("veryyStrong");
        uCR.setVCR(vCR);

        uPR = new UserPatchRequest();
        uPR.setUserID(1);
        uPR.setNewUsername("Tester2");
        uPR.setNewEMailAddress("Test2@Email.nothing");
        uPR.setNewPassword("veryyStrong2");
        uPR.setConfirmedNewPassword("veryyStrong2");
        uPR.setCurrentPassword("veryyStrong");



    }


    @Test
    void readUserByUsername()
    {
        //Case 1; success:
        //Arrange:
        when(userDAL.returnUserbyUserNameinDB(user.getUserName())).thenReturn(java.util.Optional.ofNullable(user));

        //Act
        User TESTUser = userC.readUserByUsername(user.getUserName());

        //Assert
        assertEquals(user, TESTUser);
        verify(userDAL, times(1)).returnUserbyUserNameinDB(user.getUserName());

    }

    @Test
    void readUserByUsernameException1()
    {
        //Case 2; exception thrown:
        //Arrange:
        when(userDAL.returnUserbyUserNameinDB(user.getUserName())).thenReturn(java.util.Optional.empty());

        //Act:
        assertThrows(EntityNotFoundException.class, ()  -> {
            userC.readUserByUsername("a");
        });

        //Assert
        verify(userDAL, times(1)).returnUserbyUserNameinDB("a");
    }

    @Test
    void createUser()
    {
        //1; Success:
        //Arrange
        when(userDAL.returnUserbyUserNameinDB(uCR.getUsername())).thenReturn(Optional.empty());
        when(userDAL.returnUserbyeMailAddressinDB(uCR.getEMailAddress())).thenReturn(Optional.empty());
        User userPlusVoter = user;
        userPlusVoter.setVoter(voter);
        when(userDAL.saveGetUsertoDB(Mockito.any(User.class))).thenReturn(userPlusVoter);
        when(voterDAL.getVoterfromDBbyID(uCR.getVCR().getVoterID().get())).thenReturn(voter);

        //Act
        userC.CreateUser(uCR);

        //Assert
        verify(userDAL, times(1)).saveGetUsertoDB(Mockito.any(User.class));



    }
    @Test
    void createUserException1()
    {
        //2; email exception;
        //Arrange
        when(userDAL.returnUserbyeMailAddressinDB(uCR.getEMailAddress())).thenReturn(Optional.ofNullable(user));

        //Act & Assert:
        RuntimeException e2 = assertThrows(RuntimeException.class, ()  -> {
            userC.CreateUser(uCR);
        });

        //Assert
        assertEquals("Email "+ uCR.getEMailAddress() +" already registered. Please use a different email, or login.", e2.getMessage());


    }

    @Test
    void createUserException2()
    {
        //3; username exception;
        //Arrange
        when(userDAL.returnUserbyUserNameinDB(uCR.getUsername())).thenReturn(Optional.ofNullable(user));

        //Act & Assert:
        RuntimeException e = assertThrows(RuntimeException.class, ()  -> {
            userC.CreateUser(uCR);
        });
        //Assert
        assertEquals("User "+ uCR.getUsername() +" already registered. Please use a different username.", e.getMessage());

    }

    @Test
    void readUserIDbyUsername()
    {
        //Arrange
        when(userDAL.returnUserIDbyUsernameinDB(user.getUserName())).thenReturn(user.getUserID());

        //Act
        long userID = userC.readUserIDbyUsername(user.getUserName());

        //Assert
        verify(userDAL, times(1)).returnUserIDbyUsernameinDB(user.getUserName());
        assertEquals(user.getUserID(), userID);

    }

    @Test
    void getUserDTOfromUserbyID()
    {
        //Arrange
        UserDTO input = UserMapper.INSTANCE.userToUserDTO(user);
        when(userDAL.getUserByUserID(user.getUserID())).thenReturn(input);

        //Act
        UserDTO output = userC.getUserDTOfromUserbyID(user.getUserID());

        //Assert
        verify(userDAL, times(1)).getUserByUserID(user.getUserID());
        assertEquals(input, output);
    }

    @Test
    void patchAccount()
    {
        //1; success:
        //Arrange
        when(userDAL.returnUserbyID(uPR.getUserID())).thenReturn(Optional.ofNullable(user));
        when(userDAL.returnUserbyUserNameinDB(user.getUserName())).thenReturn(Optional.ofNullable(user));
        //Act
        userC.patchAccount(uPR);
        //Assert
        verify(userDAL, times(1)).saveUsertoDB(Mockito.any(User.class));
    }

    @Test
    void patchAccountException1()
    {
        //2; new passwords don't match;
        //Arrange
        when(userDAL.returnUserbyID(uPR.getUserID())).thenReturn(Optional.ofNullable(user));
        when(userDAL.returnUserbyUserNameinDB(user.getUserName())).thenReturn(Optional.ofNullable(user));
        uPR.setConfirmedNewPassword("Dit Is Fout");
        //Act & Assert:
        RuntimeException e = assertThrows(RuntimeException.class, ()  -> {
            userC.patchAccount(uPR);
        }, "Expected accountPatching to throw, but it didn't");
        //Assert
        verify(userDAL, never()).saveUsertoDB(Mockito.any(User.class));
        assertEquals("New password must be confirmed with an identical password.", e.getMessage());

    }

    @Test
    void patchAccountException2()
    {
        //3; current password does not belong to account:
        //Arrange
        when(userDAL.returnUserbyID(uPR.getUserID())).thenReturn(Optional.ofNullable(user));
        when(userDAL.returnUserbyUserNameinDB(user.getUserName())).thenReturn(Optional.ofNullable(user));

        uPR.setCurrentPassword("Dit Is Fout");

        //Act & Assert:
        RuntimeException e2 = assertThrows(RuntimeException.class, ()  -> {
            userC.patchAccount(uPR);
        }, "Expected accountPatching to throw, but it didn't");
        //Assert
        verify(userDAL, never()).saveUsertoDB(Mockito.any(User.class));
        assertEquals("The given ID doesn't match with the current account details.", e2.getMessage());
    }

    @Test
    void createVoter()
    {
        //Arrange
        when(voterDAL.saveVotertoDB(Mockito.any(Voter.class))).thenReturn(voter);
        vCR.setVoterID(null);
        //Act
        Voter TESTVoter = userC.CreateVoter(vCR);

        //Assert
        verify(voterDAL, times(1)).saveVotertoDB(Mockito.any(Voter.class));
        assertEquals(voter, TESTVoter);
    }

    @Test
    void loginVoter()
    {
        //1; success:
        //Arrange
        when(voterDAL.getVoterfromDBbyUuid1and2(vCR.getUUID1(), vCR.getUUID2())).thenReturn(Optional.ofNullable(voter));

        //Act
        Voter TESTVoter = userC.LoginVoter(vCR);

        //Assert
        verify(voterDAL, times(1)).getVoterfromDBbyUuid1and2(vCR.getUUID1(), vCR.getUUID2());
        assertEquals(voter, TESTVoter);
    }

    @Test
    void loginVoterException1()
    {
        //2; nothing returns:
        //Arrange
        when(voterDAL.getVoterfromDBbyUuid1and2(vCR.getUUID1(), vCR.getUUID2())).thenReturn(Optional.empty());

        //Act
        RuntimeException e = assertThrows(RuntimeException.class, ()  -> {
            Voter TESTVoter2 = userC.LoginVoter(vCR);
        }, "Expected LoginVoter to throw, but it didn't");


        //Assert
        verify(voterDAL, times(1)).getVoterfromDBbyUuid1and2(vCR.getUUID1(), vCR.getUUID2());
        assertEquals("Doesn't exist", e.getMessage());
    }

    @Test
    void DBSaveVoter()
    {
        //Arrange
        //-

        //Act
        userC.DBSaveVoter(voter);

        //Assert
        verify(voterDAL, times(1)).saveVotertoDB(voter);
    }

    @Test
    void DBGetVoter()
    {
        //Arrange
        when(voterDAL.getVoterfromDBbyID(voter.getVoterID())).thenReturn(voter);

        //Act
        Voter TESTVoter = userC.DBGetVoter(voter.getVoterID());

        //Assert
        verify(voterDAL, times(1)).getVoterfromDBbyID(voter.getVoterID());
        assertEquals(voter, TESTVoter);
    }

    @Test
    void OPUser()
    {
        //1; success:
        //Arrange
        User Admin = new User();
        Admin.setAdmin(true);

        //Act
        boolean success = userC.OPUser(Admin, user);

        //Assert
        assertTrue(user.getAdmin());
        assertTrue(success);
    }

    @Test
    void OPUserForbidden()
    {
        //2; admin is not admin
        //Arrange
        User Admin = new User();
        Admin.setAdmin(false);

        //Act
        boolean success = userC.OPUser(Admin, user);

        //Assert
        assertFalse(user.getAdmin());
        assertFalse(success);
    }

    @Test
    void DEOPUser()
    {
        //1; success:
        //Arrange
        user.setAdmin(true);
        User Admin = new User();
        Admin.setAdmin(true);

        //Act
        boolean success = userC.DEOPUser(Admin, user);

        //Assert
        assertFalse(user.getAdmin());
        assertTrue(success);
    }
    @Test
    void DEOPUserForbidden()
    {
        //2; admin is not admin
        //Arrange
        User admin = new User();
        admin.setAdmin(false);
        user.setAdmin(true);

        //Act
        boolean success = userC.DEOPUser(admin, user);

        //Assert
        assertTrue(user.getAdmin());
        assertFalse(success);
    }


    @Test
    void nameVerify()
    {
        //1; success:
        //Arrange
        when(userDAL.returnUserbyUserNameinDB(rawUser.getUserName())).thenReturn(Optional.ofNullable(user));

        //Act
        boolean success = userC.NameVerify(rawUser.getUserName(), rawUser.getPassword());

        //Assert
        assertTrue(success);
        verify(userDAL, times(1)).returnUserbyUserNameinDB(rawUser.getUserName());
    }

    @Test
    void nameVerifyWrongInput()
    {
        //2; user not found:
        //Arrange
        when(userDAL.returnUserbyUserNameinDB(rawUser.getUserName())).thenReturn(Optional.empty());

        //Act
        boolean success = userC.NameVerify(rawUser.getUserName(), rawUser.getPassword());

        //Assert
        assertFalse(success);
        verify(userDAL, times(1)).returnUserbyUserNameinDB(rawUser.getUserName());
    }

    @Test
    void emailVerify()
    {
        //1; success:
        //Arrange
        when(userDAL.VerifyAccountbyEmailinDB(user.getEMailAddress(), user.getPassword())).thenReturn(true);

        //Act
        boolean success = userC.EmailVerify(user.getEMailAddress(), user.getPassword());

        //Assert
        assertTrue(success);
        verify(userDAL, times(1)).VerifyAccountbyEmailinDB(user.getEMailAddress(), user.getPassword());
    }

    @Test
    void emailVerifyWrongInput()
    {
        //2; email not correct:
        //Arrange
        when(userDAL.VerifyAccountbyEmailinDB(user.getEMailAddress(), user.getPassword())).thenReturn(false);

        //Act
        boolean success = userC.EmailVerify(user.getEMailAddress(), user.getPassword());

        //Assert
        assertFalse(success);
        verify(userDAL, times(1)).VerifyAccountbyEmailinDB(user.getEMailAddress(), user.getPassword());

    }
}