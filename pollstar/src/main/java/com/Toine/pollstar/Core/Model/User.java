package com.Toine.pollstar.Core.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userID;
    @Column()
    private String userName;

    @Getter
    @Setter
    @Column()
    private String eMailAddress;
    @Column()
    private String password;
    @Column()
    private boolean admin;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Poll> poll = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Voter voter;

    public Voter getVoter() {
        return voter;
    }

    public User() {     }

    public User(String userName, String eMailAddress, String password, boolean admin)
    {
        this.userName = userName;
        this.eMailAddress = eMailAddress;
        this.password = password;
        this.admin = admin;
    }

    //getters and setters
    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
    //public String getEMailAddr() {return eMailAddress;}
    //public void setEMailAddr(String eMailAddr) {this.eMailAddress = eMailAddr;}
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
    public boolean EmailVerify(String eMailAddress, String Pwd)
    {
        if(eMailAddress.equals(eMailAddress) && password.equals(Pwd))
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
            setEMailAddress(Email);
            return getEMailAddress().equals(Email);
        }
        else
        {
            return false;
        }
    }

    @Override
    public String toString()
    {
        return "User: " + userName + " (" +userID+ ")";
    }

}
