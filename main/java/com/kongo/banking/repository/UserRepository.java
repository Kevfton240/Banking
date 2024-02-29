package com.kongo.banking.repository;

import com.kongo.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    //select * from User where firstname = "Kevin"
    List<User> findAllByFirstname(String firstname);

    //Select * from User where firstname like "%Kevin%"
    List<User> findAllByFirstnameContaining(String firstname);

    //Select * from User where firstname like "Kevin"
    List<User> findAllByFirstnameContainingIgnoreCase(String firstname);


    @Query("from User where firstname = :fn")
    List<User> searchByFirstname(@Param("fn")String firstname);

    @Query("from User where firstname = '%:firstname%' ")
    List<User> searchFirstnameContaining(String firstname);


    Optional<User> findByEmail(String email);
}
