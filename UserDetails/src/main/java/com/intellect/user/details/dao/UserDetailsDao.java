package com.intellect.user.details.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intellect.user.details.entity.UserDetailsEntity;
import com.intellect.user.details.exception.ExceptionMessages;
import com.intellect.user.details.exception.GenericException;
import com.intellect.user.details.repository.UserDetailsRepository;

/**
 * 
 * @author Sumit23
 *
 */
@Component
public class UserDetailsDao {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	/**
	 * 
	 * @param user
	 */
	public void create(UserDetailsEntity user) {

		if (userDetailsRepository.findById(user.getId()) != null) {
			throw new GenericException(ExceptionMessages.USER_ID_ALREADY_EXIST);
		}

		if (userDetailsRepository.findByEmailAndIsActive(user.getEmail(), true) != null) {
			throw new GenericException(ExceptionMessages.ACTIVE_USER_FOR_EMAIL);
		}
		user.setIsActive(true);
		userDetailsRepository.save(user);
	}

	/**
	 * 
	 * @param user
	 */
	public void update(UserDetailsEntity user) {

		UserDetailsEntity userDetails = userDetailsRepository.findById(user.getId());

		if (userDetails == null) {
			throw new GenericException(ExceptionMessages.USER_ID_NOT_EXIST);
		}

		userDetails.setPincode(user.getPincode());
		userDetails.setBirthDate(user.getBirthDate());

		userDetailsRepository.save(userDetails);

	}

	/**
	 * 
	 * @param userId
	 */
	public void delete(String userId) {

		UserDetailsEntity userDetails = userDetailsRepository.findById(userId);

		if (userDetails == null) {
			throw new GenericException(ExceptionMessages.USER_ID_NOT_EXIST);
		}
		if (userDetails.getIsActive() == false) {
			throw new GenericException(ExceptionMessages.ALREADY_DEACTIVATED_USER);
		}
		userDetails.setIsActive(false);

		userDetailsRepository.save(userDetails);
	}

}
