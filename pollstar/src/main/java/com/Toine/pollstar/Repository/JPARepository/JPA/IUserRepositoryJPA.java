package com.Toine.pollstar.Repository.JPARepository.JPA;

import com.Toine.pollstar.Core.Model.Poll;
import com.Toine.pollstar.Core.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepositoryJPA extends JpaRepository<User, Integer>
{
    Optional<User> findByUserName(String username);
    boolean getUserByUserNameAndPassword(String UserName, String Password);
    boolean getUserByeMailAddressAndPassword(String EmailAddress, String Password);
}
