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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "USER_ROLE")
public class UserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	@JsonView(DataTablesOutput.View.class)
	private Long roleId;

	@Column(name = "ROLE_NAME", nullable = false)
	@JsonView(DataTablesOutput.View.class)
	private String roleName;

	@Column(name = "DESCRIPTION")
	@JsonView(DataTablesOutput.View.class)
	private String description;

	@Column(name = "STATUS", nullable = false)
	@JsonView(DataTablesOutput.View.class)
	private Integer status;

	@Column(name = "CREATED_BY", nullable = false)
	@JsonView(DataTablesOutput.View.class)
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DT", nullable = false)
	@JsonView(DataTablesOutput.View.class)
	private Date createdDT;

	@Column(name = "MODIFIED_BY", nullable = false)
	@JsonView(DataTablesOutput.View.class)
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_DT", nullable = false)
	@JsonView(DataTablesOutput.View.class)
	private Date modifiedDT;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userRole")
	private Set<UserRoleMapping> userRoleMapping = new HashSet<UserRoleMapping>(0);

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userRole")
	private Set<RoleMenuMapping> roleMenuMapping = new HashSet<RoleMenuMapping>(0);

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public Set<UserRoleMapping> getUserRoleMapping() {
		return userRoleMapping;
	}

	public void setUserRoleMapping(Set<UserRoleMapping> userRoleMapping) {
		this.userRoleMapping = userRoleMapping;
	}

	public Set<RoleMenuMapping> getRoleMenuMapping() {
		return roleMenuMapping;
	}

	public void setRoleMenuMapping(Set<RoleMenuMapping> roleMenuMapping) {
		this.roleMenuMapping = roleMenuMapping;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
