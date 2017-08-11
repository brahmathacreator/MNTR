package com.org.mntr.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.org.mntr.constants.SSValidationConfig;
import com.org.mntr.validators.FieldValid;

@FieldValid(firstField = "password", secondField = "cPassword")
public class PasswordDetailsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min = SSValidationConfig.passGeneralMin, max = SSValidationConfig.passGeneralMax)
	@Pattern(regexp = SSValidationConfig.passGeneralPattern)
	private String password;

	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min = SSValidationConfig.passGeneralMin, max = SSValidationConfig.passGeneralMax)
	@Pattern(regexp = SSValidationConfig.passGeneralPattern)
	private String cPassword;

	private Long passRefId;
	private String uuid;
	private Date uuidDT;
	private Long modifiedBy;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getcPassword() {
		return cPassword;
	}

	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getUuidDT() {
		return uuidDT;
	}

	public void setUuidDT(Date uuidDT) {
		this.uuidDT = uuidDT;
	}

	public Long getPassRefId() {
		return passRefId;
	}

	public void setPassRefId(Long passRefId) {
		this.passRefId = passRefId;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
