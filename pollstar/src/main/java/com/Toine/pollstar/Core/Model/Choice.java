package com.Toine.pollstar.Core.Model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "choice", indexes = {
        @Index(name = "idx_choice_poll_id", columnList = "poll_id")
})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "choiceID")
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, updatable = false)
    private int choiceID;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "poll_id", nullable = false)
    @JsonIgnore
    private Poll poll;
    @Column(name = "choice_name")
    private String choiceName;


    @ManyToMany(targetEntity = com.Toine.pollstar.Core.Model.Voter.class, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Choice_Voter",
            joinColumns = { @JoinColumn(name = "choiceID") },
            inverseJoinColumns = { @JoinColumn(name = "voterID") })
    public List<Voter> voters = new ArrayList<>(); //maybe just the int of the voter


    public Choice() {
    }//this.voters = new ArrayList<Voter>();

    public Choice(int cID, String name)
    {
        this.choiceID = cID;
        this.choiceName = name;
        this.voters = new ArrayList<>();
    }
    public Choice(int cID, String name, List<Voter> voter)
    {
        this.choiceID = cID;
        this.choiceName = name;
        this.voters = voter;
    }

//    public int getChoiceID() {
//        return choiceID;
//    }
//
//    public void setChoiceID(int choiceID) {
//        this.choiceID = choiceID;
//    }
//
    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }
//
//    public String getChoiceName() {
//        return choiceName;
//    }
//
//    public void setChoiceName(String choiceName) {
//        this.choiceName = choiceName;
//    }
//
//    public List<Voter> getVoters() {
//        return voters;
//    }
//
//    public void setVoters(List<Voter> voters) {
//        this.voters = voters;
//    }


    //public Voter getVoter(int voterID) {for(Voter voter : voters) {if(voterID == voter.getVoterID()){return voter;}}return null;}
    //public boolean addVoter(Voter voter) {if (voters.add(voter)){return true;} return false;}

    public boolean voterVoted(int voterID)
    {
        for(Voter voter : voters)
        {
            if(voter.getVoterID() == voterID)
            {
                return true;
            }
        }
        return false;
    }

    public int getVoteAmount()
    {
        return voters.size();
    }

//    @Transactional
//    public boolean AddVote(Voter voter)    {
//        //FIXME: maybe check if not voted somewhere else, already?
//        voter.getChoices().add(this);
//        return this.voters.add(voter);
//    }

    public void addVote(Voter voter) {
        if (!voters.contains(voter)) {
            voters.add(voter);
            voter.addChoice(this);
        }
    }
    public void removeVote(Voter voter) {
        if (voters.contains(voter)) {
            voters.remove(voter);
            voter.removeChoice(this);
        }
    }

    @Override
    public String toString()
    {
        return choiceName + choiceID + getVoteAmount();
    }
}
