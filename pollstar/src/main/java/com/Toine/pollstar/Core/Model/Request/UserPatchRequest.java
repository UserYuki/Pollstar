package com.Toine.pollstar.Core.Model.Request;

import lombok.Data;

@Data
public class UserPatchRequest {
    private long userID;
    private String newUsername;
    private String newEMailAddress;
    private String currentPassword;
    private String newPassword;
    private String confirmedNewPassword;
}