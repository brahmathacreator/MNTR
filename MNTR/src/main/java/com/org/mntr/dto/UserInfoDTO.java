package com.org.mntr.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.org.mntr.constants.SSValidationConfig;
import com.org.mntr.validators.Phone;

public class UserInfoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userKey;

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

	private Date lastLoginDT;
	private Integer lockStatus;
	private Integer invalidPassAtmpts;
	private Integer loginType;
	private String userLogoName;
	private Integer status;
	private Long createdBy;
	private Date createdDT;
	private Long modifiedBy;
	private Date modifiedDT;
	private String pwdUUID;
	private String hashPwd;
	private Long roleId;

	private List<String> roles = new ArrayList<String>();
	private List<URLProps> parentURLList = new ArrayList<URLProps>();
	private List<URLProps> childURLList = new ArrayList<URLProps>();
	private String conCatRoles;

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

	public Date getLastLoginDT() {
		return lastLoginDT;
	}

	public void setLastLoginDT(Date lastLoginDT) {
		this.lastLoginDT = lastLoginDT;
	}

	public Integer getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}

	public Integer getInvalidPassAtmpts() {
		return invalidPassAtmpts;
	}

	public void setInvalidPassAtmpts(Integer invalidPassAtmpts) {
		this.invalidPassAtmpts = invalidPassAtmpts;
	}

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public String getUserLogoName() {
		return userLogoName;
	}

	public void setUserLogoName(String userLogoName) {
		this.userLogoName = userLogoName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedDT() {
		return createdDT;
	}

	public void setCreatedDT(Date createdDT) {
		this.createdDT = createdDT;
	}

	public Date getModifiedDT() {
		return modifiedDT;
	}

	public void setModifiedDT(Date modifiedDT) {
		this.modifiedDT = modifiedDT;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getHashPwd() {
		return hashPwd;
	}

	public void setHashPwd(String hashPwd) {
		this.hashPwd = hashPwd;
	}

	public List<URLProps> getParentURLList() {
		return parentURLList;
	}

	public void setParentURLList(List<URLProps> parentURLList) {
		this.parentURLList = parentURLList;
	}

	public List<URLProps> getChildURLList() {
		return childURLList;
	}

	public void setChildURLList(List<URLProps> childURLList) {
		this.childURLList = childURLList;
	}

	public String getConCatRoles() {
		return conCatRoles;
	}

	public void setConCatRoles(String conCatRoles) {
		this.conCatRoles = conCatRoles;
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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
