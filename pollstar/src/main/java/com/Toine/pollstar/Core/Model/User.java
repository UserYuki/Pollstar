package com.Toine.pollstar.Core.Model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.naming.Name;

public class User extends Voter
{
    private String userName;
    private String eMailAddr;
    private String password;
    private boolean admin;

    public User() {     }

    public User(String userName, String eMailAddr, String password, boolean admin)
    {
        this.userName = userName;
        this.eMailAddr = eMailAddr;
        this.password = password;
        this.admin = admin;
    }

    //getters and setters
    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
    public String getEMailAddr() {return eMailAddr;}
    public void setEMailAddr(String eMailAddr) {this.eMailAddr = eMailAddr;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public boolean getAdmin() {return admin;}
    public void setAdmin(boolean admin) {this.admin = admin;}

    public boolean Verify(String Identifier, String Pwd)
    {
        if(Identifier == null)
        {
            return password.equals(Pwd);
        }
        else if(Identifier.contains("@"))
        {
            return EmailVerify(Identifier, Pwd);
        }
        else
        {
            return NameVerify(Identifier, Pwd);
        }
    }
    public boolean NameVerify(String UserN, String Pwd)
    {
        if(userName.equals(UserN) && password.equals(Pwd))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean EmailVerify(String EmailAddr, String Pwd)
    {
        if(eMailAddr.equals(EmailAddr) && password.equals(Pwd))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean ChangeUserName(String newUserName, String Password)
    {
        if(Verify(null, Password))
        {
            setUserName(newUserName);
            return getUserName().equals(newUserName);
        }
        else
        {
            return false;
        }
    }
    public boolean ChangePassword(String newPassword, String password)
    {
        if(Verify(null, password))
        {
            setPassword(newPassword);
            return getPassword().equals(newPassword);
        }
        else
        {
            return false;
        }
    }
    public boolean ChangeEMail(String Email, String password)
    {
        if(Verify(null, password))
        {
            setEMailAddr(Email);
            return getEMailAddr().equals(Email);
        }
        else
        {
            return false;
        }
    }


}
