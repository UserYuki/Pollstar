package com.Toine.pollstar.Repository.JPARepository;

import com.Toine.pollstar.Core.Model.User;
import com.Toine.pollstar.Repository.Interfaces.IUserStorage;
import com.Toine.pollstar.Repository.JPARepository.JPA.IUserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserStorage implements IUserStorage
{
    @Autowired
    IUserRepositoryJPA repo;


    @Override
    public boolean VerifyAccountbyUserNameinDB(String UserName, String Password) {
        return repo.getUserByUserNameAndPassword(UserName, Password).isPresent();
    }

    @Override
    public boolean VerifyAccountbyEmailinDB(String EmailAddress, String Password)
    {
        return repo.getUserByeMailAddressAndPassword(EmailAddress, Password);
    }

    @Override
    public Optional<User> returnUserbyUserNameinDB(String username)
    {
        return repo.findByUserName(username);
    }

    @Override
    public Optional<User> returnUserbyeMailAddressinDB(String eMailAddress) {
        return repo.findByeMailAddress(eMailAddress);
    }

    @Override
    public void saveUsertoDB(User user) {
        repo.save(user);
    }

    @Override
    public long returnUserIDbyUsernameinDB(String username) {

        return repo.findByUserName(username).get().getUserID();
    }
}
