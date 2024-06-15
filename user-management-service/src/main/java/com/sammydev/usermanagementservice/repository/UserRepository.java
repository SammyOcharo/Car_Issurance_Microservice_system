package com.sammydev.usermanagementservice.repository;

import com.sammydev.usermanagementservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

}
