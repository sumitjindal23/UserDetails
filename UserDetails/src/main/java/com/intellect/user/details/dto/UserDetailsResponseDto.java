package com.intellect.user.details.dto;

/**
 * 
 * @author Sumit23
 *
 */
public class UserDetailsResponseDto {

	private String resMsg;

	private String userId;

	private String valErrors;

	/**
	 * 
	 */
	public UserDetailsResponseDto() {
		super();
	}

	/**
	 * @return the resMsg
	 */
	public String getResMsg() {
		return resMsg;
	}

	/**
	 * @param resMsg the resMsg to set
	 */
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the valErrors
	 */
	public String getValErrors() {
		return valErrors;
	}

	/**
	 * @param valErrors the valErrors to set
	 */
	public void setValErrors(String valErrors) {
		this.valErrors = valErrors;
	}
	
	
	
}
