package com.cbo.mntr.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ROLE_MAPPING")
public class UserRoleMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UR_MAPPING_ID", unique = true, nullable = false)
	private Long urMappingId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_KEY")
	private UserInfo userDetails;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ROLE_ID")
	private UserRole userRole;

	public Long getUrMappingId() {
		return urMappingId;
	}

	public void setUrMappingId(Long urMappingId) {
		this.urMappingId = urMappingId;
	}

	public UserInfo getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserInfo userDetails) {
		this.userDetails = userDetails;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}
