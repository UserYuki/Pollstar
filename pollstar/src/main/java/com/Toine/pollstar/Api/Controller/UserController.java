package com.Toine.pollstar.Api.Controller;

import com.Toine.pollstar.Core.Interface.IUserContainer;
import com.Toine.pollstar.Core.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController
{
    @Autowired
    IUserContainer IUC;



    @GetMapping()
    //POST at http://localhost:XXXX/poll/
    public ResponseEntity<User> NameVerify(@RequestBody User user)
    {
        if(!user.NameVerify(user.getUserName(), user.getPassword()))
        {
            String entity = "Your input Username and Password do not match any items in our system.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }
        else
        {

            return new ResponseEntity(HttpStatus.CREATED);
        }
    }


}
