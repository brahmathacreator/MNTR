package com.org.mntr.entity;
// Generated 11-Aug-2017 13:10:52 by Hibernate Tools 5.2.3.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Category generated by hbm2java
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "category", catalog = "rdev")
public class Category implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonView(DataTablesOutput.View.class)
	private Long categoryId;
	@JsonView(DataTablesOutput.View.class)
	private String categoryName;
	@JsonView(DataTablesOutput.View.class)
	private String masterPassword;
	@JsonView(DataTablesOutput.View.class)
	private String description;
	@JsonView(DataTablesOutput.View.class)
	private Integer status;
	@JsonView(DataTablesOutput.View.class)
	@CreatedBy
	private String createdBy;
	@JsonView(DataTablesOutput.View.class)
	@LastModifiedBy
	private String modifiedBy;
	@JsonView(DataTablesOutput.View.class)
	@CreatedDate
	private Date createdDt;
	@JsonView(DataTablesOutput.View.class)
	@LastModifiedDate
	private Date modifiedDt;
	@JsonView(DataTablesOutput.View.class)
	private String categoryType;

	private Set<Template> templates = new HashSet<Template>(0);

	public Category() {
	}

	public Category(String categoryName, String masterPassword, Integer status, String createdBy, String modifiedBy,
			Date createdDt, Date modifiedDt, String categoryType) {
		this.categoryName = categoryName;
		this.masterPassword = masterPassword;
		this.status = status;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.categoryType = categoryType;
	}

	public Category(String categoryName, String masterPassword, Integer status, String createdBy, String modifiedBy,
			Date createdDt, Date modifiedDt, String description, String categoryType, Set<Template> templates) {
		this.categoryName = categoryName;
		this.masterPassword = masterPassword;
		this.status = status;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.description = description;
		this.categoryType = categoryType;
		this.templates = templates;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "CATEGORY_ID", unique = true, nullable = false)
	public Long getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "CATEGORY_NAME", nullable = false)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(name = "MASTER_PASSWORD", nullable = false)
	public String getMasterPassword() {
		return this.masterPassword;
	}

	public void setMasterPassword(String masterPassword) {
		this.masterPassword = masterPassword;
	}

	@Column(name = "STATUS", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "CREATED_BY", nullable = false)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "MODIFIED_BY", nullable = false)
	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DT", nullable = false, length = 19)
	public Date getCreatedDt() {
		return this.createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_DT", nullable = false, length = 19)
	public Date getModifiedDt() {
		return this.modifiedDt;
	}

	public void setModifiedDt(Date modifiedDt) {
		this.modifiedDt = modifiedDt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
	public Set<Template> getTemplates() {
		return this.templates;
	}

	public void setTemplates(Set<Template> templates) {
		this.templates = templates;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "CATEGORY_TYPE", nullable = false, length = 50)
	public String getCategoryType() {
		return this.categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

}
