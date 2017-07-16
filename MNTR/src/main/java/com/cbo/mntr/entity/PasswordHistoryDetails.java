package com.cbo.mntr.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PASSWORD_HISTORY_DETAILS")
public class PasswordHistoryDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PWD_HIS_REF_ID", nullable = false)
	private Integer pwdHisRefId;

	@Column(name = "USER_KEY", nullable = false)
	private Integer userKey;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	public Integer getPwdHisRefId() {
		return pwdHisRefId;
	}

	public void setPwdHisRefId(Integer pwdHisRefId) {
		this.pwdHisRefId = pwdHisRefId;
	}

	public Integer getUserKey() {
		return userKey;
	}

	public void setUserKey(Integer userKey) {
		this.userKey = userKey;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
