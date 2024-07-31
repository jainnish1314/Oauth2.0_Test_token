package com.example.testDatabase.Repository;

import com.example.testDatabase.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends JpaRepository<UserEntity,Long> {

}
