package com.mindera.api.repository;


import com.mindera.api.domain.User;
import com.mindera.api.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    List<User> findUserByGender(Gender gender);
}
