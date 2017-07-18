package com.cbo.mntr.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.cbo.mntr.constants.SSValidationConfig;
import com.cbo.mntr.validators.Phone;

public class UserInfoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = SSValidationConfig.textGeneralMin, max = SSValidationConfig.textGeneralMax)
	private String userId;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = SSValidationConfig.textGeneralMin, max = SSValidationConfig.textGeneralMax)
	private String userName;

	@NotNull
	@NotEmpty
	@NotBlank
	@Email
	private String email;

	@NotNull
	@NotEmpty
	@NotBlank
	@Phone
	private String phno;

	private Long createdBy;
	private Long modifiedBy;
	private String userLogo;
	private String pwdUUID;
	private Long userKey;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getUserLogo() {
		return userLogo;
	}

	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
	}

	public String getPwdUUID() {
		return pwdUUID;
	}

	public void setPwdUUID(String pwdUUID) {
		this.pwdUUID = pwdUUID;
	}

	public Long getUserKey() {
		return userKey;
	}

	public void setUserKey(Long userKey) {
		this.userKey = userKey;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
