package com.cbo.mntr.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PASSWORD_DETAILS")
public class PasswordDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PASS_REF_ID", nullable = false)
	private Long passRefId;

	@Column(name = "PWD_UUID", nullable = false)
	private String pwdUUID;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UUID_GENERATION_DT", nullable = false)
	private Date uuidGenDT;

	@Column(name = "HASH_PWD", nullable = false)
	private String hashPwd;

	@Column(name = "ENCRYPTED_PWD", nullable = false)
	private String encryptedPwd;

	@Column(name = "STATUS", nullable = false)
	private Integer status;

	@Column(name = "CREATED_BY", nullable = false)
	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DT", nullable = false)
	private Date createdDT;

	@Column(name = "MODIFIED_BY", nullable = false)
	private Integer modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_DT", nullable = false)
	private Date modifiedDT;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_KEY")
	private UserInfo user;

	public String getPwdUUID() {
		return pwdUUID;
	}

	public void setPwdUUID(String pwdUUID) {
		this.pwdUUID = pwdUUID;
	}

	public Date getUuidGenDT() {
		return uuidGenDT;
	}

	public void setUuidGenDT(Date uuidGenDT) {
		this.uuidGenDT = uuidGenDT;
	}

	public String getHashPwd() {
		return hashPwd;
	}

	public void setHashPwd(String hashPwd) {
		this.hashPwd = hashPwd;
	}

	public String getEncryptedPwd() {
		return encryptedPwd;
	}

	public void setEncryptedPwd(String encryptedPwd) {
		this.encryptedPwd = encryptedPwd;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDT() {
		return createdDT;
	}

	public void setCreatedDT(Date createdDT) {
		this.createdDT = createdDT;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDT() {
		return modifiedDT;
	}

	public void setModifiedDT(Date modifiedDT) {
		this.modifiedDT = modifiedDT;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public Long getPassRefId() {
		return passRefId;
	}

	public void setPassRefId(Long passRefId) {
		this.passRefId = passRefId;
	}

}
