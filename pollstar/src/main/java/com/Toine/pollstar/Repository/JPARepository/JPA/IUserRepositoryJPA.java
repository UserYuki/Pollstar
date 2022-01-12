package com.Toine.pollstar.Repository.JPARepository.JPA;

import com.Toine.pollstar.Core.Model.DTO.UserDetails.UserDTO;
import com.Toine.pollstar.Core.Model.Poll;
import com.Toine.pollstar.Core.Model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IUserRepositoryJPA extends JpaRepository<User, Long>
{
    Optional<User> findByUserName(String username);
    Optional<User> findByeMailAddress(String eMail);
    Optional<User> getUserByUserNameAndPassword(String UserName, String Password);
    boolean getUserByeMailAddressAndPassword(String EmailAddress, String Password);
    Optional<User> findUserByUserID(long UserID);

    UserDTO getUserByUserID(long UserID);


}
