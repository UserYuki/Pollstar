package com.Toine.pollstar.Core.Model.DTO.UserDetails;
import com.Toine.pollstar.Core.Model.Poll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userName;
    private String eMailAddress;
    private List<PollDTO> poll;
}