package com.Toine.pollstar.Core.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Poll
{
    private int pID;
    private String name;
    private List<Choice> choices;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date createdOn;
    private int ownerID;
    private boolean locked;

    public Poll(int pID, String name, List<Choice> choices, Date createdOn, int ownerID, boolean locked)
    {
        this.pID = pID;
        this.name = name;
        this.choices = choices;
        this.createdOn = createdOn;
        this.ownerID = ownerID;
        this.locked = locked;
    }
    public Poll(int pID, String name, Date createdOn, int ownerID, boolean locked)
    {
        this.pID = pID;
        this.name = name;
        this.choices = new LinkedList<>();
        this.createdOn = createdOn;
        this.ownerID = ownerID;
        this.locked = locked;
    }
    public Poll(){}


    public int getPollId() {return pID;}

    public String getPollName() {return name;}

    @Override
    public String toString()
    {
        return "Poll{" +
                "pID=" + pID +
                ", name=\'" + name + "\'" +
                ", locked=" + locked +
                "}";
    }
}
