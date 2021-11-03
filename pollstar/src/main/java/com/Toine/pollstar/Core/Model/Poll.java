package com.Toine.pollstar.Core.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Poll
{
    private int pollID;
    private String pollName;
    private List<Choice> pollChoices;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date pollCreationDate;
    private int pollOwnerID;
    private boolean pollLockedStatus;

    public Poll(int pollID, String pollName, List<Choice> pollChoices, Date pollCreationDate, int pollOwnerID, boolean pollLockedStatus)
    {
        this.pollID = pollID;
        this.pollName = pollName;
        this.pollChoices = pollChoices;
        this.pollCreationDate = pollCreationDate;
        this.pollOwnerID = pollOwnerID;
        this.pollLockedStatus = pollLockedStatus;
    }
    public Poll(int pollID, String pollName, Date pollCreationDate, int pollOwnerID, boolean pollLockedStatus)
    {
        this.pollID = pollID;
        this.pollName = pollName;
        this.pollChoices = new LinkedList<>();
        this.pollCreationDate = pollCreationDate;
        this.pollOwnerID = pollOwnerID;
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
    public int getPollOwnerID() {return pollOwnerID;}
    public void setPollOwnerID(int pollOwnerID) {this.pollOwnerID = pollOwnerID;}
    public boolean getPollLockedStatus() {return pollLockedStatus;}
    public void setPollLockedStatus(boolean pollLockedStatus) {this.pollLockedStatus = pollLockedStatus;}


    public boolean voterVoted(int voterID)
    {
        for(Choice c : pollChoices)
        {
            return c.voterVoted(voterID);
        }
        return false;
    }
    public boolean castVote(int voterID, int choiceID)
    {
        if(voterVoted(voterID)){return false;}
        for(Choice c : pollChoices)
        {
            if(choiceID == c.getChoiceID())
            {
                c.AddVote(voterID);
                return true;
            }
        }
        return false;
    }
}
