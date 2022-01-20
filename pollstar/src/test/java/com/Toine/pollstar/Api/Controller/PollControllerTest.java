package com.Toine.pollstar.Api.Controller;

import com.Toine.pollstar.Api.Config.AuthenticationConfigConstants;
import com.Toine.pollstar.Api.Config.SecurityConfiguration;
import com.Toine.pollstar.Core.Interface.IPollContainer;
import com.Toine.pollstar.Core.Model.Container.UserContainer;
import com.Toine.pollstar.Core.Model.Poll;
import com.Toine.pollstar.Core.Model.Request.UserCreateRequest;
import com.Toine.pollstar.Core.Model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import net.minidev.json.parser.JSONParser;
import org.apache.catalina.filters.CsrfPreventionFilter;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.Date;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


///@AutoConfigureMockMvc
//@Import(PollController.class)
@ContextConfiguration(classes = {SecurityConfiguration.class})
@WebAppConfiguration
public class PollControllerTest {


//    @Autowired
//    private MockMvc mockMvc;
//
//    private String token;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;

//    @BeforeEach
//    public void setup() {
//
//        String token = JWT.create()
//                .withSubject("user")
//                .withExpiresAt(new Date(System.currentTimeMillis() + AuthenticationConfigConstants.EXPIRATION_TIME))
//                .sign(Algorithm.HMAC512(AuthenticationConfigConstants.SECRET.getBytes()));
//        this.token = AuthenticationConfigConstants.TOKEN_PREFIX + token;
//
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//
//    }


    @Test
    void confirmTest() throws Exception
    {
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/poll/test")
//                .accept(MediaType.APPLICATION_JSON))
//                .andReturn();
//        String resultTest = result.getResponse().getContentAsString();
//        assertNotNull(resultTest);
//        assertEquals("Works!", resultTest);
    }


    @Test
    void getPoll()
    {
    }


    @Test
    public void createPoll() throws Exception
    {
//        Poll poll = new Poll(0,"Test", new LinkedList<>(), new Date(), new User(), false );
//
//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        String json = ow.writeValueAsString(poll);
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
//                        .post("/poll")
//                        .header(HttpHeaders.AUTHORIZATION, token)
//                        .content(json)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andReturn();
//        String resultTest = result.getResponse().getContentAsString();
//        assertNotNull(resultTest);
//        assertEquals("0", resultTest);
    }

    @Test
    void addVoter() {
    }

    @Test
    void getAllPolls() {
    }
}