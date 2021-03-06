package com.Toine.pollstar.Core.Model;

import com.fasterxml.jackson.annotation.*;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Entity
@Table(name = "poll")
public class Poll
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "poll_id")
    private int pollID;
    @Column()
    private String pollName;


    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private List<Choice> pollChoices = new ArrayList<Choice>();
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date pollCreationDate;

    @Column()
    private boolean pollLockedStatus;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "user_user_id")
    private User user;






    public Poll(int pollID, String pollName, List<Choice> pollChoices, Date pollCreationDate, User user, boolean pollLockedStatus)
    {
        this.pollID = pollID;
        this.pollName = pollName;
        this.pollChoices = pollChoices;
        this.pollCreationDate = pollCreationDate;
        this.user = user;
        this.pollLockedStatus = pollLockedStatus;
    }


    public Poll(){  }//this.pollChoices = new ArrayList<>();  }

    @Transactional
    public void addChoice(Choice choice){
        //As said Hibernate will ignore it when persist this relationship.
        //Add it mainly for the consistency of this relationship for both side in the Java instance
        //System.out.println("D id: " + choice.getChoiceID());

        this.pollChoices.add(choice);

        choice.setPoll(this);

    }

    //Getters and setters D:
    public int getPollID() {return pollID;}
    public void setPollID(int pollID) {this.pollID = pollID;}
    public String getPollName() {return pollName;}
    public void setPollName(String pollName) {this.pollName = pollName;}
    public List<Choice> getPollChoices() {return pollChoices;}
    //public void setPollChoices(List<Choice> pollChoices) {this.pollChoices = pollChoices;}
    public Date getPollCreationDate() {return pollCreationDate;}
    public void setPollCreationDate(Date pollCreationDate) {this.pollCreationDate = pollCreationDate;}
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public boolean getPollLockedStatus() {return pollLockedStatus;}
    public void setPollLockedStatus(boolean pollLockedStatus) {this.pollLockedStatus = pollLockedStatus;}

    /*
    TODO
     */
    public boolean voterVoted(Voter voter)
    {
        for(Choice c : pollChoices)
        {
            if(c.voterVoted(voter.getVoterID()))
            {
                try
                {
                    c.removeVote(voter);
                    return false;
                }
                catch (Exception ex)
                {
                    return c.voterVoted(voter.getVoterID());
                }
            }

        }
        return false;
    }

    public boolean voterVotedwithoutDeletingIt(Voter voter)
    {
        for(Choice c : pollChoices)
        {
            if(c.voterVoted(voter.getVoterID()))
            {
                try
                {
                    return true;
                }
                catch (Exception ex)
                {
                    return c.voterVoted(voter.getVoterID());
                }
            }

        }
        return false;
    }

    public boolean castVote(Voter voter, int choiceID)
    {
        if(!voterVoted(voter) && !pollLockedStatus)
        {
            for (Choice c : pollChoices) {
                if (choiceID == c.getChoiceID()) {
                    c.addVote(voter);
                    return true;
                }
            }
        }
        return false;
    }

    public String ToString()
    {
        //AtomicReference<String> test = new AtomicReference<>("");
        String test = "";
        pollChoices.forEach((Choice c) -> {
            test.concat(", ");
            test.concat(c.getChoiceName());
        });
        return pollName + "(" + pollID + ") Owner: "+user.toString()+  ", Choices; \'" + test + "\'";
    }
}
