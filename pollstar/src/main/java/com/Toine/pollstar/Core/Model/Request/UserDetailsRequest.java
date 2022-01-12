package com.Toine.pollstar.Core.Model.Request;

import com.Toine.pollstar.Core.Model.Poll;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDetailsRequest
{
    private String username;
    private String eMailAddress;
    private List<Poll> polls;
}