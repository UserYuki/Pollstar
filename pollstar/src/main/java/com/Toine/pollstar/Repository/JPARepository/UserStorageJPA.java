package com.Toine.pollstar.Repository.JPARepository;

import com.Toine.pollstar.Repository.Interfaces.IUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserStorageJPA implements IUserStorage
{
    @Autowired
    IUserRepositoryJPA repo;


    @Override
    public boolean VerifyAccountbyUserNameinDB(String UserName, String Password) {
        return repo.getUserByUserNameAndPassword(UserName, Password);
    }

    @Override
    public boolean VerifyAccountbyEmailinDB(String EmailAddress, String Password)
    {
        return repo.getUserByeMailAddressAndPassword(EmailAddress, Password);
    }
}
