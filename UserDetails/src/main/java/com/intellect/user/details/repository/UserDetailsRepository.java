package com.intellect.user.details.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.intellect.user.details.entity.UserDetailsEntity;

/**
 * 
 * @author Sumit23
 *
 */
@Repository
public interface UserDetailsRepository extends MongoRepository<UserDetailsEntity, String> {

	UserDetailsEntity findByEmailAndIsActive(String email, Boolean isActive);

	UserDetailsEntity findById(String id);

}
