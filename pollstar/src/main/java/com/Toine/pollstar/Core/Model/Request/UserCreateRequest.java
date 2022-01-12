package com.Toine.pollstar.Core.Model.Request;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String username;
    private String eMailAddress;
    private String password;
    private VoterCreateRequest VCR;
}