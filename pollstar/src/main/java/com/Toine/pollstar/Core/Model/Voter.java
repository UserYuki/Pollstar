package com.Toine.pollstar.Core.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "voter")
public class Voter
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "voterID")
    private int voterID;
    @OneToOne()
    @JoinColumn(name = "userID")
    private User user;
    @Column(name = "UUID1")
    private String UUID1;
    @Column(name = "UUID2")
    private String UUID2;

    @ManyToMany(mappedBy = "voters")
    public List<Choice> choices;

    public Voter(){}

    public String getUUID1() {
        return UUID1;
    }

    public void setUUID1(String UUID1) {
        this.UUID1 = UUID1;
    }

    public String getUUID2() {
        return UUID2;
    }

    public void setUUID2(String UUID2) {
        this.UUID2 = UUID2;
    }

    public int getVoterID() {return voterID;}
    public void setVoterID(int voterID) {this.voterID = voterID;}
}
