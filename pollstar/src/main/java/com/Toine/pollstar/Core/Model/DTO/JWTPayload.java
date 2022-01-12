package com.Toine.pollstar.Core.Model.DTO;

import lombok.Data;

@Data
public class JWTPayload {
    private String sub;
    private long exp;
}
