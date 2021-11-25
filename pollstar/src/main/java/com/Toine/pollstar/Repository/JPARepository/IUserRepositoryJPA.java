package com.Toine.pollstar.Repository.JPARepository;

import com.Toine.pollstar.Core.Model.Poll;
import com.Toine.pollstar.Core.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepositoryJPA extends JpaRepository<User, Integer>
{
    boolean getUserByUserNameAndPassword(String UserName, String Password);
    boolean getUserByeMailAddressAndPassword(String EmailAddress, String Password);
}
