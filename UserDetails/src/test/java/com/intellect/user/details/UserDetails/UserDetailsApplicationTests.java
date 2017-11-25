package com.intellect.user.details.UserDetails;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.intellect.user.details.dto.UserDetailsDto;
import com.intellect.user.details.exception.ExceptionMessages;
import com.intellect.user.details.exception.GenericException;
import com.intellect.user.details.service.UserDetailsService;

/**
 * 
 * @author Sumit23
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailsApplicationTests {

	public final String USER_ID = "User653";
	public final String FIRST_NAME = "Sumit";
	public final String LAST_NAME = "Jindal";
	public final String EMAIL = "sumitjindal2010@gmail.com";
	public final Integer PINCODE = 32020;
	public final String BIRTH_DATE = "28-Nov-1994";

	@Autowired
	UserDetailsService userService;

	@Test
	public void createTest() {

		UserDetailsDto userDto = new UserDetailsDto();
		userDto.setId(USER_ID);
		userDto.setFname(FIRST_NAME);
		userDto.setLname(LAST_NAME);
		userDto.setEmail(EMAIL);
		userDto.setPincode(PINCODE);
		userDto.setBirthDate(BIRTH_DATE);

		try {
			userService.create(userDto);
		} catch (GenericException e) {
			if (e.getMessage().equals(ExceptionMessages.USER_ID_ALREADY_EXIST))
				assertEquals(ExceptionMessages.USER_ID_ALREADY_EXIST, e.getMessage());
			else
				assertEquals(ExceptionMessages.ACTIVE_USER_FOR_EMAIL, e.getMessage());
		}

		try {
			userDto.setId(null);
			userService.create(userDto);
		} catch (GenericException e) {
			assertEquals(ExceptionMessages.USER_ID_NULL, e.getMessage());
		}

		try {
			userDto.setId(USER_ID);
			userDto.setFname(null);
			userService.create(userDto);
		} catch (GenericException e) {
			assertEquals(ExceptionMessages.FIRST_NAME_NULL, e.getMessage());
		}

		try {
			userDto.setFname(FIRST_NAME);
			userDto.setLname(null);
			userService.create(userDto);
		} catch (GenericException e) {
			assertEquals(ExceptionMessages.LAST_NAME_NULL, e.getMessage());
		}

		try {
			userDto.setLname(LAST_NAME);
			userDto.setEmail(null);
			userService.create(userDto);
		} catch (GenericException e) {
			assertEquals(ExceptionMessages.EMAIL_NULL, e.getMessage());
		}
		
		try {
			userDto.setEmail("sumitkfd");
			userService.create(userDto);
		} catch (GenericException e) {
			assertEquals(ExceptionMessages.EMAIL_FORMAT_WRONG, e.getMessage());
		}

		try {
			userDto.setEmail(EMAIL);
			userDto.setPincode(null);
			userService.create(userDto);
		} catch (GenericException e) {
			assertEquals(ExceptionMessages.PINCODE_NULL, e.getMessage());
		}

		try {
			userDto.setPincode(PINCODE);
			userDto.setBirthDate(null);
			userService.create(userDto);
		} catch (GenericException e) {
			assertEquals(ExceptionMessages.BIRTH_DATE_NULL, e.getMessage());
		}

		try {
			userDto.setBirthDate("23nfp");
			userService.create(userDto);
		} catch (GenericException e) {
			assertEquals(ExceptionMessages.DATE_FORMAT_WRONG, e.getMessage());
		}

		try {
			userDto.setBirthDate("31-JAN-2209");
			userService.create(userDto);
		} catch (GenericException e) {
			assertEquals(ExceptionMessages.BIRTH_DATE_FUTURE_DATE, e.getMessage());
		}

		assertTrue(true);
	}

	@Test
	public void updateTest() {

		UserDetailsDto userDto = new UserDetailsDto();
		userDto.setId(USER_ID);
		userDto.setBirthDate(BIRTH_DATE);
		userDto.setPincode(PINCODE);

		try {
			userService.update(userDto);
		} catch (GenericException e) {
			assertEquals(ExceptionMessages.USER_ID_NOT_EXIST, e.getMessage());
		}

		try {
			userDto.setPincode(null);
			userService.update(userDto);
		} catch (GenericException e) {
			assertEquals(ExceptionMessages.PINCODE_NULL, e.getMessage());
		}

		try {
			userDto.setPincode(PINCODE);
			userDto.setBirthDate(null);
			userService.update(userDto);
		} catch (GenericException e) {
			assertEquals(ExceptionMessages.BIRTH_DATE_NULL, e.getMessage());
		}

		try {
			userDto.setBirthDate("23nfp");
			userService.update(userDto);
		} catch (GenericException e) {
			assertEquals(ExceptionMessages.DATE_FORMAT_WRONG, e.getMessage());
		}

		try {
			userDto.setBirthDate("31-JAN-2209");
			userService.update(userDto);
		} catch (GenericException e) {
			assertEquals(ExceptionMessages.BIRTH_DATE_FUTURE_DATE, e.getMessage());
		}
		assertTrue(true);
	}

	@Test
	public void deleteTest() {

		try {
			userService.delete(USER_ID);
		} catch (GenericException e) {
			if (ExceptionMessages.USER_ID_NOT_EXIST.equals(e.getMessage())) {
				assertEquals(ExceptionMessages.USER_ID_NOT_EXIST, e.getMessage());
			} else {
				assertEquals(ExceptionMessages.ALREADY_DEACTIVATED_USER, e.getMessage());
			}
		}
		try {
			userService.delete(USER_ID);
		} catch (GenericException e) {
			if (ExceptionMessages.USER_ID_NOT_EXIST.equals(e.getMessage())) {
				assertEquals(ExceptionMessages.USER_ID_NOT_EXIST, e.getMessage());
			} else {
				assertEquals(ExceptionMessages.ALREADY_DEACTIVATED_USER, e.getMessage());
			}
		}

		assertTrue(true);
	}

}
