package com.examserver.repos;

import com.examserver.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel , Long> {

    public UserModel findByUserName(String userName);

}
