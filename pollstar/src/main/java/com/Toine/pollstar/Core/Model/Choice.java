package com.Toine.pollstar.Core.Model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="choice")
public class Choice
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int choiceID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pollID")
    private Poll poll;
    @Column(name = "choice_name")
    private String choiceName;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Choice_Voter",
            joinColumns = { @JoinColumn(name = "choiceID") },
            inverseJoinColumns = { @JoinColumn(name = "voterID") }
    )
    private List<Voter> voters; //maybe just the int of the voter


    public Choice()
    {
        this.voters = new ArrayList<>();
    }
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

    public int getChoiceID() {
        return choiceID;
    }

    public void setChoiceID(int choiceID) {
        this.choiceID = choiceID;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public String getChoiceName() {
        return choiceName;
    }

    public void setChoiceName(String choiceName) {
        this.choiceName = choiceName;
    }

    public List<Voter> getVoters() {
        return voters;
    }

    public void setVoters(List<Voter> voters) {
        this.voters = voters;
    }

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
    public boolean AddVote(Voter voter)    {
        //FIXME: maybe check if not voted somewhere else, already?
        return voters.add(voter);
    }

    public boolean RemoveVote(Voter voter)
    {
        return voters.remove(voter);
    }

}
