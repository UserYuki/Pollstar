package com.Toine.pollstar.Core.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "poll")
public class Poll
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pollID")
    private int pollID;
    @Column()
    private String pollName;
    @OneToMany(mappedBy = "choiceID")
    private List<Choice> pollChoices;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date pollCreationDate;

    @Column()
    private boolean pollLockedStatus;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_user_id", unique = true)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public Poll(int pollID, String pollName, List<Choice> pollChoices, Date pollCreationDate, User userID, boolean pollLockedStatus)
    {
        this.pollID = pollID;
        this.pollName = pollName;
        this.pollChoices = pollChoices;
        this.pollCreationDate = pollCreationDate;
        this.user = userID;
        this.pollLockedStatus = pollLockedStatus;
    }
    public Poll(int pollID, String pollName, Date pollCreationDate, User userID, boolean pollLockedStatus)
    {
        this.pollID = pollID;
        this.pollName = pollName;
        this.pollChoices = new LinkedList<>();
        this.pollCreationDate = pollCreationDate;
        this.user = userID;
        this.pollLockedStatus = pollLockedStatus;
    }

    public Poll(){}

    //Getters and setters D:
    public int getPollID() {return pollID;}
    public void setPollID(int pollID) {this.pollID = pollID;}
    public String getPollName() {return pollName;}
    public void setPollName(String pollName) {this.pollName = pollName;}
    public List<Choice> getPollChoices() {return pollChoices;}
    public void setPollChoices(List<Choice> pollChoices) {this.pollChoices = pollChoices;}
    public Date getPollCreationDate() {return pollCreationDate;}
    public void setPollCreationDate(Date pollCreationDate) {this.pollCreationDate = pollCreationDate;}
    public User getUserID() {return user;}
    public void setUserID(User pollOwnerID) {this.user = pollOwnerID;}
    public boolean getPollLockedStatus() {return pollLockedStatus;}
    public void setPollLockedStatus(boolean pollLockedStatus) {this.pollLockedStatus = pollLockedStatus;}

    /*
    TODO
     */
    public boolean voterVoted(int voterID)
    {
        for(Choice c : pollChoices)
        {
            return c.voterVoted(voterID);
        }
        return false;
    }
    public boolean castVote(Voter voter, int choiceID)
    {
        if(voterVoted(voter.getVoterID())){return false;}
        for(Choice c : pollChoices)
        {
            if(choiceID == c.getChoiceID())
            {
                c.AddVote(voter);
                return true;
            }
        }
        return false;
    }
}
