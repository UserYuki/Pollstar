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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "voter_voterid", referencedColumnName = "voterid")
    private Voter voter;

    public Voter getVoter() {
        return voter;
    }


    public User() {     }

    public User(long userID, String userName, String eMailAddress, String password, boolean admin)
    {
        this.userID = userID;
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


    @Override
    public String toString()
    {
        return "User: " + userName + " (" +userID+ ")";
    }

}
