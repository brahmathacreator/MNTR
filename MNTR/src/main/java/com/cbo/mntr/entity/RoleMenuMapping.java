package com.cbo.mntr.entity;

import java.io.Serializable;

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
@Table(name = "ROLE_MENU_MAPPING")
public class RoleMenuMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RM_MAPPING_ID", unique = true, nullable = false)
	private Long RMMappingId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID")
	private UserRole userRole;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MENU_ID")
	private MenuDetails menuDetails;

	public Long getRMMappingId() {
		return RMMappingId;
	}

	public void setRMMappingId(Long rMMappingId) {
		RMMappingId = rMMappingId;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public MenuDetails getMenuDetails() {
		return menuDetails;
	}

	public void setMenuDetails(MenuDetails menuDetails) {
		this.menuDetails = menuDetails;
	}

}
