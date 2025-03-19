package com.testApp.demo.Infrastructure.Repositories.UserRepository;

import com.testApp.demo.Infrastructure.Repositories.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByName(String username);

    UserEntity findByEmail(String email);
}
