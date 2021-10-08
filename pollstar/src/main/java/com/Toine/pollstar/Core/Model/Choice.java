package com.Toine.pollstar.Core.Model;


import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

public class Choice
{
    private int choiceID;
    private String choiceName;
    private List<Voter> voters; //maybe just the int of the voter

    public Choice(int cID, String name)
    {
        this.choiceID = cID;
        this.choiceName = name;
        this.voters = new ArrayList<>();
    }

    public Choice(){}

    public int getChoiceID(){return choiceID;}
    public void setChoiceID(int choiceID) {this.choiceID = choiceID;}
    public String getChoiceName() {return choiceName;}
    public void setChoiceName(String choiceName) {this.choiceName = choiceName;}
    public List<Voter> getVoters() {return voters;}
    public void setVoters() {this.voters = voters;}

    public Voter getVoter(int voterID) {for(Voter voter : voters) {if(voterID == voter.getVoterID()){return voter;}}return null;}
    public boolean addVoter(Voter voter) {if (voters.add(voter)){return true;} return false;}
}
