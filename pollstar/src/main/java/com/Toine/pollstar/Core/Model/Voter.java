package com.Toine.pollstar.Core.Model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "voter")
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "voterID")
public class Voter
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "voterID")
    private int voterID;
    @Getter
    @Setter
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "voter", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private User user;
    @Column(name = "UUID1")
    private String UUID1;
    @Column(name = "UUID2")
    private String UUID2;


    @ManyToMany( targetEntity = com.Toine.pollstar.Core.Model.Choice.class, mappedBy = "voters", cascade = CascadeType.ALL)
    public List<Choice> choices = new ArrayList<>();

    public void addChoice(Choice choice) {
        if (!choices.contains(choice)) {
            choices.add(choice);
            choice.addVote(this);
        }
    }

    public void removeChoice(Choice choice) {
        if (choices.contains(choice)) {
            choices.remove(choice);
            choice.removeVote(this);
        }
    }

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
