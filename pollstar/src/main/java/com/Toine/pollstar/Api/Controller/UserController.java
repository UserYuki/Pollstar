package com.Toine.pollstar.Api.Controller;

import com.Toine.pollstar.Core.Interface.IUserContainer;
import com.Toine.pollstar.Core.Model.Request.UserCreateRequest;
import com.Toine.pollstar.Core.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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

    @GetMapping()
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

    @PostMapping("/retrieveID")
    public ResponseEntity RetrieveID(@RequestBody UserCreateRequest userCreateRequest)
    {
        if(!IUC.NameVerify(userCreateRequest.getUsername(), userCreateRequest.getPassword()))
        {
            String entity = "Your input Username and Password do not match any items in our system.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }
        else {
            long entity = IUC.readUserIDbyUsername(userCreateRequest.getUsername());
            return new ResponseEntity(entity, HttpStatus.CREATED);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity UpdateUser(@RequestBody )
    {

    }

}
