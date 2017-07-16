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

	private Integer createdBy;
	private Integer modifiedBy;
	private String userLogo;
	private String pwdUUID;

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

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getPwdUUID() {
		return pwdUUID;
	}

	public void setPwdUUID(String pwdUUID) {
		this.pwdUUID = pwdUUID;
	}

}
