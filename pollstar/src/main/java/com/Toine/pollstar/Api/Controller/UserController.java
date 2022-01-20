package com.Toine.pollstar.Api.Controller;

import com.Toine.pollstar.Core.Interface.IUserContainer;
import com.Toine.pollstar.Core.Model.DTO.JWTPayload;
import com.Toine.pollstar.Core.Model.DTO.UserDetails.UserDTO;
import com.Toine.pollstar.Core.Model.Request.UserCreateRequest;
import com.Toine.pollstar.Core.Model.Request.UserPatchRequest;
import com.Toine.pollstar.Core.Model.Request.VoterCreateRequest;
import com.Toine.pollstar.Core.Model.User;
import com.Toine.pollstar.Core.Model.Voter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController
{
    @Autowired
    IUserContainer IUC;


    @PostMapping
    public ResponseEntity CreateUser (@RequestBody UserCreateRequest userCreateRequest)
    {
        try
        {
            IUC.CreateUser(userCreateRequest);

        }
        catch(RuntimeException rtEx)
        {
            return new ResponseEntity(rtEx, HttpStatus.IM_USED);
        }

        return ResponseEntity.ok().build();

    }

    @GetMapping("/verify")
    //POST at http://localhost:XXXX/poll/
    public ResponseEntity<User> NameVerify(@RequestBody User user)
    {
        if(!IUC.NameVerify(user.getUserName(), user.getPassword()))
        {
            String entity = "Your input Username and Password do not match any items in our system.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }
        else
        {
            String entity = "BABABOEUY";
            return new ResponseEntity(entity, HttpStatus.CREATED);
        }
    }

    @GetMapping("/retrieveID")
    public ResponseEntity RetrieveID(@RequestHeader("Authorization") String Authorization)
    {
        JWTPayload jwtP = new JWTPayload();
        try {
            String[] chunks = Authorization.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            ObjectMapper objm = new ObjectMapper();
            jwtP = objm.readValue(payload, JWTPayload.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            String entity = "Something went wrong, please try logging out and back in.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }
            long entity = IUC.readUserIDbyUsername(jwtP.getSub());
            return new ResponseEntity(entity, HttpStatus.CREATED);
    }

    @GetMapping("/getVoter")
    public ResponseEntity<VoterCreateRequest> getVoter(@RequestHeader("Authorization") String Authorization)
    {
        JWTPayload jwtP = new JWTPayload();
        try {
            String[] chunks = Authorization.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            ObjectMapper objm = new ObjectMapper();
            jwtP = objm.readValue(payload, JWTPayload.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            String entity = "Something went wrong, please try logging out and back in.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }
        Voter voter = IUC.readUserByUsername(jwtP.getSub()).getVoter();
        VoterCreateRequest vCR = new VoterCreateRequest();
        vCR.setVoterID(java.util.Optional.of(voter.getVoterID()));
        vCR.setUUID1(voter.getUUID1());
        vCR.setUUID2(voter.getUUID2());
        return new ResponseEntity(vCR, HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity UpdateUser(@RequestBody UserPatchRequest uPR, @RequestHeader("Authorization") String Authorization)
    {
        JWTPayload jwtP = new JWTPayload();
        try {
            String[] chunks = Authorization.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            ObjectMapper objm = new ObjectMapper();
            jwtP = objm.readValue(payload, JWTPayload.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            String entity = "Something went wrong, please try logging out and back in.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }
        if(!IUC.NameVerify( jwtP.getSub(), uPR.getCurrentPassword())){
            String entity = "Wrong password, please try again.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }
        else
        {
            try{
                IUC.patchAccount(uPR);
                return new ResponseEntity( HttpStatus.OK);
            }
            catch(RuntimeException REx){
                return new ResponseEntity(REx, HttpStatus.CONFLICT);
            }

        }
    }

    @GetMapping()
    public ResponseEntity<UserDTO> UserDetails(@RequestHeader("Authorization") String Authorization)
    {
        JWTPayload jwtP = new JWTPayload();
        try {
            String[] chunks = Authorization.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            ObjectMapper objm = new ObjectMapper();
            jwtP = objm.readValue(payload, JWTPayload.class);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            String entity = "Something went wrong, please try logging out and back in.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }
        try
        {
            long UiD = IUC.readUserIDbyUsername(jwtP.getSub());
            UserDTO DITTO = IUC.getUserDTOfromUserbyID(UiD);
            return ResponseEntity.ok(DITTO);
        }
        catch(Exception exception)
        {
            return new ResponseEntity(exception, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/name")
    public ResponseEntity<String> ReqUserName(@RequestHeader("Authorization") String Authorization)
    {
        JWTPayload jwtP = new JWTPayload();
        try {
            String[] chunks = Authorization.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));
            ObjectMapper objm = new ObjectMapper();
            jwtP = objm.readValue(payload, JWTPayload.class);
            return ResponseEntity.ok(jwtP.getSub());
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }


}
