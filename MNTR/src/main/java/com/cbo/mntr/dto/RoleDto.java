package com.cbo.mntr.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

public class RoleDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonView(DataTablesOutput.View.class)
	private Long roleId;
	@JsonView(DataTablesOutput.View.class)
	private String roleName;
	@JsonView(DataTablesOutput.View.class)
	private Integer status;
	@JsonView(DataTablesOutput.View.class)
	private Long createdBy;
	@JsonView(DataTablesOutput.View.class)
	private Date createdDT;
	@JsonView(DataTablesOutput.View.class)
	private Long modifiedBy;
	@JsonView(DataTablesOutput.View.class)
	private Date modifiedDT;

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

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDT() {
		return createdDT;
	}

	public void setCreatedDT(Date createdDT) {
		this.createdDT = createdDT;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDT() {
		return modifiedDT;
	}

	public void setModifiedDT(Date modifiedDT) {
		this.modifiedDT = modifiedDT;
	}

}
