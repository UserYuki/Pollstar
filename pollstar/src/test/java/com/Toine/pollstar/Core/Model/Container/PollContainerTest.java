package com.Toine.pollstar.Core.Model.Container;

import com.Toine.pollstar.Core.Interface.IUserContainer;
import com.Toine.pollstar.Core.Model.Choice;
import com.Toine.pollstar.Core.Model.Poll;
import com.Toine.pollstar.Core.Model.User;
import com.Toine.pollstar.Core.Model.Voter;
import com.Toine.pollstar.Repository.Interfaces.IChoiceStorage;
import com.Toine.pollstar.Repository.Interfaces.IPollStorage;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PollContainerTest {


    //to be mocked:
    @Mock
    private IPollStorage pollDAL;
    @Mock
    private IChoiceStorage choiceDAL;
    @Mock
    private IUserContainer userDAL;

    private PollContainer pollContainer = new PollContainer(pollDAL,choiceDAL, userDAL);

    //test data:
    private Poll poll;

    @Before
    public void setup(){
        pollDAL = mock(IPollStorage.class);
        choiceDAL = mock(IChoiceStorage.class);
        userDAL = mock(IUserContainer.class);
//        //to be mocked:
//        private IPollStorage pollDAL = mock(IPollStorage.class);
//        private IChoiceStorage choiceDAL = mock(IChoiceStorage.class);
//        private IUserContainer userDAL = mock(IUserContainer.class);


        pollContainer = new PollContainer(pollDAL,choiceDAL, userDAL);

        List<Choice> choices = new ArrayList<Choice>();
        choices.add(new Choice(2, "Choice one"));
        choices.add(new Choice(3, "Choice two"));
        choices.add(new Choice(4, "Choice three"));
        User user = new User("Tester", "Test@Email.nothing", "veryyStrong", false);
        poll = new Poll(1, "Test Poll", choices, new Date(), user, false);
    }

//    @Test
//    void addPoll() {
//    }
//
//    @Test
//    void getPoll() {
//    }
//
//    @Test
//    void getPolls() {
//    }

    @Test
    public void savePollandgetbyID()
    {
        setup();
        poll.setPollID(10);
        pollDAL.savePolltoDB(poll);
        Poll pollDB = pollContainer.getPollfromDBbyID(10);
        assertEquals(poll, pollDB);
    }

    @Test
    public void addPolltoDBandGetBack()
    {
        setup();
        poll.setPollID(15);
        Poll pollDB = pollContainer.addPolltoDBandGetBack(poll);
        verify(pollDAL, times(1)).savePoll(poll);

    }

    @Test
    public void castVotetoDB()
    {
        setup();


        poll.setPollID(20);
        Voter voter = new Voter();
        voter.setVoterID(5);
        voter.setUUID1("RandomIp");
        voter.setUUID2("number random 2");

        when(userDAL.DBGetVoter(voter.getVoterID())).thenReturn(voter);
        when(pollDAL.getPollByID(poll.getPollID())).thenReturn(poll);

        pollContainer.CastVotetoDB(voter, 20, 2);

        assertTrue(poll.voterVotedwithoutDeletingIt(voter));
        verify(userDAL, times(1)).DBSaveVoter(voter);

    }

    @Test
    public void savePoll()
    {
        setup();
        poll.setPollID(25);
        pollContainer.savePoll(poll);

        verify(pollDAL, times(1)).savePolltoDB(poll);
        assertTrue(pollContainer.getPolls().contains(poll));
    }
}