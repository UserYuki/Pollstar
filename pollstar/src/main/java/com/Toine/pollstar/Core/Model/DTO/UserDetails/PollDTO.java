package com.Toine.pollstar.Core.Model.DTO.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PollDTO
{
    private int pollID;
    private String pollName;
    //private List<ChoiceDTO> pollChoices = new ArrayList<ChoiceDTO>();
    private Date pollCreationDate;
    private boolean pollLockedStatus;
}
