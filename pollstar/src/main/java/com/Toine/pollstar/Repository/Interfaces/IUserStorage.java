package com.Toine.pollstar.Repository.Interfaces;

public interface IUserStorage
{
    boolean VerifyAccountbyUserNameinDB(String UserName, String Password);
    boolean VerifyAccountbyEmailinDB(String eMailAddress, String Password);
}
