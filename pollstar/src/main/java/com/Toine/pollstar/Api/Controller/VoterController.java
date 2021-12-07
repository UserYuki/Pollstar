package com.Toine.pollstar.Api.Controller;

import com.Toine.pollstar.Core.Interface.IUserContainer;
import com.Toine.pollstar.Core.Model.Request.UserCreateRequest;
import com.Toine.pollstar.Core.Model.Request.VoterCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/voter")
public class VoterController
{
    @Autowired
    IUserContainer IUC;

    @PostMapping
    public ResponseEntity CreateVoter (@RequestBody VoterCreateRequest voterCreateRequest)
    {
        IUC.CreateVoter(voterCreateRequest);
        return ResponseEntity.ok().build();
    }
}
