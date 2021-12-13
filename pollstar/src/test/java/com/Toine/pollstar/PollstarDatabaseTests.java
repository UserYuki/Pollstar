//package com.Toine.pollstar;
//
//import com.Toine.pollstar.Core.Interface.IPollContainer;
//import com.Toine.pollstar.Core.Interface.IUserContainer;
//import com.Toine.pollstar.Core.Model.Choice;
//import com.Toine.pollstar.Core.Model.Poll;
//import com.Toine.pollstar.Core.Model.User;
//import com.Toine.pollstar.Core.Model.Voter;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Assertions;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//
//@SpringBootApplication
//class PollstarDatabaseTests implements CommandLineRunner
//{
//    @Autowired
//    IPollContainer IPC;
//
//    @Autowired
//    IUserContainer IUC;
//
//    public static void main(String[] args) {
//        SpringApplication.run(PollstarDatabaseTests.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // =======================================
//
//        // Create a Poll
//        Poll poll = new Poll();
//        poll.setPollName("test");
//        poll.setUser(new User("Yuki", "Yuki@Gmail.com", "nah", false));
//        poll.setPollCreationDate(new Date(System.currentTimeMillis()));
//        List<Choice> Cs = new ArrayList<>();
//        Choice cOne = new Choice();
//        Choice cTwo = new Choice();
//        cOne.setPoll(poll);
//        cOne.setChoiceName("Choice One");
//
//        Voter vOne = new Voter();
//        vOne.setVoterID(111);
//
//        List<Voter> VlOne = new ArrayList<>();
//        VlOne.add(vOne);
//
//        cOne.setVoters(VlOne);
//
//        cTwo.setPoll(poll);
//        cTwo.setChoiceName("Choice Two");
//
//        Voter vTwo = new Voter();
//        vOne.setVoterID(112);
//        Voter vThree = new Voter();
//        vOne.setVoterID(113);
//
//        List<Voter> VlTwoNThree = new ArrayList<>();
//        VlTwoNThree.add(vTwo);
//        VlTwoNThree.add(vThree);
//
//        List<Choice> ClOne = new ArrayList<>();
//        ClOne.add(cOne);
//
//        vOne.setChoices(ClOne);
//
//        List<Choice> ClTwo = new ArrayList<>();
//        ClOne.add(cTwo);
//
//        vTwo.setChoices(ClTwo);
//        vThree.setChoices(ClTwo);
//
//        cTwo.setVoters(VlTwoNThree);
//
//        Cs.add(cOne);
//        Cs.add(cTwo);
//        poll.setPollChoices(Cs);
//
//
//        //IPC.save(post);
//
//    }
//
//
//
//}
