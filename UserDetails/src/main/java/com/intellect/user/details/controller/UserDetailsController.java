package com.intellect.user.details.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intellect.user.details.dto.UserDetailsDto;
import com.intellect.user.details.dto.UserDetailsResponseDto;
import com.intellect.user.details.exception.GenericException;
import com.intellect.user.details.service.UserDetailsService;

/**
 * 
 * @author Sumit23
 *
 */
@RestController
@RequestMapping(path = "/userdetails")
public class UserDetailsController {

	@Autowired
	UserDetailsService userService;

	/**
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public ResponseEntity<UserDetailsResponseDto> create(@RequestBody final UserDetailsDto user) {

		UserDetailsResponseDto response = new UserDetailsResponseDto();
		response.setUserId(user.getId());
		try {
			userService.create(user);
			response.setResMsg("Successfully created User");

			return ResponseEntity.ok(response);
		} catch (GenericException e) {
			response.setResMsg("Error while creating User");
			response.setValErrors(e.getMessage());

			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
		}

	}

	/**
	 * 
	 * @param userId
	 * @param user
	 * @return
	 */
	@RequestMapping(path = "/update/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<UserDetailsResponseDto> update(@PathVariable final String userId,
			@RequestBody final UserDetailsDto user) {

		UserDetailsResponseDto response = new UserDetailsResponseDto();
		response.setUserId(userId);
		try {
			user.setId(userId);
			userService.update(user);
			response.setResMsg("Successfully updated User");
			return ResponseEntity.ok(response);
		} catch (GenericException e) {
			response.setResMsg("Error while updating User");
			response.setValErrors(e.getMessage());

			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
		}

	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(path = "/delete/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<UserDetailsResponseDto> delete(@PathVariable final String userId) {

		UserDetailsResponseDto response = new UserDetailsResponseDto();
		response.setUserId(userId);
		try {
			userService.delete(userId);
			response.setResMsg("Successfully deleted User");
			return ResponseEntity.ok(response);
		} catch (GenericException e) {
			response.setResMsg("Error while deleting User");
			response.setValErrors(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
		}

	}
}
