package com.profile.profileservice.repositories;

import com.profile.profileservice.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
