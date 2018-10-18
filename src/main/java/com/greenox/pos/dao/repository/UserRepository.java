package com.greenox.pos.dao.repository;

import com.greenox.pos.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsernameAndPassword(String username, String password);
}
