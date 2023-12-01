package com.test.qpassessment.repository;


import com.test.qpassessment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Repository used by UserService to access database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(@Param("username") String username);

    User findByEmail(@Param("email") String email);
    
    Collection<User> findByAccountEnabled(@Param("accountEnabled") boolean accountEnabled);
    
}
