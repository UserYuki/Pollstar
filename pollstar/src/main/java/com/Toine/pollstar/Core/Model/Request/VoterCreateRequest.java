package com.Toine.pollstar.Core.Model.Request;

import lombok.Data;

import javax.persistence.Column;
import java.util.Optional;

@Data
public class VoterCreateRequest
{
    private Optional<Integer> voterID;
    private String UUID1;
    private String UUID2;
}
