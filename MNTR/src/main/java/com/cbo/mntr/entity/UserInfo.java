package com.cbo.mntr.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "USER_DETAILS", uniqueConstraints = @UniqueConstraint(columnNames = "USER_ID"))
public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_KEY", unique = true, nullable = false)
	private Long userKey;

	@Column(name = "USER_ID", unique = true, nullable = false)
	private String userId;

	@Column(name = "USERNAME", nullable = false)
	private String userName;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "PHONE_NO", nullable = false)
	private String phno;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_LOGIN_DT", nullable = false)
	private Date lastLoginDT;

	@Column(name = "LOCK_STATUS", nullable = false)
	private Integer lockStatus;

	@Column(name = "INVALID_PASS_ATTEMPTS", nullable = false)
	private Integer invalidPassAtmpts;

	@Column(name = "LOGIN_TYPE", nullable = false)
	private Integer loginType;

	@Column(name = "USER_LOGO_NAME", nullable = false)
	private String userLogoName;

	@Column(name = "STATUS", nullable = false)
	private Integer status;

	@Column(name = "CREATED_BY", nullable = false)
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DT", nullable = false)
	private Date createdDT;

	@Column(name = "MODIFIED_BY", nullable = false)
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_DT", nullable = false)
	private Date modifiedDT;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userDetails")
	private Set<UserRoleMapping> URMapSet = new HashSet<UserRoleMapping>(0);

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private PasswordDetails passwordDetails;

	public Long getUserKey() {
		return userKey;
	}

	public void setUserKey(Long userKey) {
		this.userKey = userKey;
	}

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

	public Set<UserRoleMapping> getURMapSet() {
		return URMapSet;
	}

	public void setURMapSet(Set<UserRoleMapping> uRMapSet) {
		URMapSet = uRMapSet;
	}

	public String getUserLogoName() {
		return userLogoName;
	}

	public void setUserLogoName(String userLogoName) {
		this.userLogoName = userLogoName;
	}

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public PasswordDetails getPasswordDetails() {
		return passwordDetails;
	}

	public void setPasswordDetails(PasswordDetails passwordDetails) {
		this.passwordDetails = passwordDetails;
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
