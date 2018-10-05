package com.greenox.order.dao;

import com.greenox.order.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsernameAndPassword(String username, String password);
}
