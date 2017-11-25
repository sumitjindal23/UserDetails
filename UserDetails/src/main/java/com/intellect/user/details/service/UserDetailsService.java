package com.intellect.user.details.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intellect.user.details.dao.UserDetailsDao;
import com.intellect.user.details.dto.UserDetailsDto;
import com.intellect.user.details.entity.UserDetailsEntity;
import com.intellect.user.details.exception.ExceptionMessages;
import com.intellect.user.details.exception.GenericException;
/**
 * 
 * @author Sumit23
 *
 */
@Component
public class UserDetailsService {

	@Autowired
	UserDetailsDao userDao;
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		   Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	
	/**
	 * 
	 * @param userDto
	 */
	public void create(UserDetailsDto userDto) {

		validateNull(userDto);
		
		validateRegexPattern(userDto);

		UserDetailsEntity userDetails = mappingField(userDto);

		userDao.create(userDetails);
	}


	/**
	 * 
	 * @param user
	 */
	public void update(UserDetailsDto user) {
		
		if (user.getPincode() == null) {
			throw new GenericException(ExceptionMessages.PINCODE_NULL);
		}

		if (StringUtils.isEmpty(user.getBirthDate())) {
			throw new GenericException(ExceptionMessages.BIRTH_DATE_NULL);
		}
		UserDetailsEntity userDetails=new UserDetailsEntity();
		userDetails.setPincode(user.getPincode());
		
		userDetails.setBirthDate(convertStringToDate(user.getBirthDate()));
		userDao.update(userDetails);

	}
	/**
	 * 
	 * @param userId
	 */
	public void delete(String userId) {
		userDao.delete(userId);
	}

	/**
	 * 
	 * @param userDto
	 * @return
	 */
	private UserDetailsEntity mappingField(UserDetailsDto userDto) {

		UserDetailsEntity userDetails = new UserDetailsEntity();

		userDetails.setId(userDto.getId());

		userDetails.setFname(userDto.getFname());

		userDetails.setLname(userDto.getLname());

		userDetails.setEmail(userDto.getEmail());

		userDetails.setPincode(userDetails.getPincode());
	
		userDetails.setBirthDate(convertStringToDate(userDto.getBirthDate()));

		return userDetails;
	}
	
	/**
	 * 
	 * @param userDto
	 */
	private void validateNull(UserDetailsDto userDto) {

		if (StringUtils.isEmpty(userDto.getId())) {
			throw new GenericException(ExceptionMessages.USER_ID_NULL);
		}

		if (StringUtils.isEmpty(userDto.getFname())) {
			throw new GenericException(ExceptionMessages.FIRST_NAME_NULL);
		}

		if (StringUtils.isEmpty(userDto.getLname())) {
			throw new GenericException(ExceptionMessages.LAST_NAME_NULL);
		}

		if (StringUtils.isEmpty(userDto.getEmail())) {
			throw new GenericException(ExceptionMessages.EMAIL_NULL);
		}

		if (userDto.getPincode() == null) {
			throw new GenericException(ExceptionMessages.PINCODE_NULL);
		}

		if (StringUtils.isEmpty(userDto.getBirthDate())) {
			throw new GenericException(ExceptionMessages.BIRTH_DATE_NULL);
		}

	}
	
	/**
	 * 
	 * @param dateString
	 * @return
	 */
	public Date convertStringToDate(String dateString)
	{
	    Date date = null;
	    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	    try{
	        date = df.parse(dateString);
	    }
	    catch ( Exception ex ){
	        throw new GenericException(ExceptionMessages.DATE_FORMAT_WRONG);
	    }
	    if(date.after(new Date())){
	    	throw new GenericException(ExceptionMessages.BIRTH_DATE_FUTURE_DATE);
	    }
	    
	    return date;
	}
	
	private void validateRegexPattern(UserDetailsDto userDto) {
		
		Matcher matcher=VALID_EMAIL_ADDRESS_REGEX.matcher(userDto.getEmail());
		if(!matcher.matches()){
			throw new GenericException(ExceptionMessages.EMAIL_FORMAT_WRONG);
		}
	}

}
