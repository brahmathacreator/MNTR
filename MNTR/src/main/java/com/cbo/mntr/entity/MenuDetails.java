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

@Entity
@Table(name = "MENU_DETAILS")
public class MenuDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MENU_ID", unique = true, nullable = false)
	private Long menuId;

	@Column(name = "USER_DEF_MENU_ID", nullable = false)
	private Integer userDefMenuKey;

	@Column(name = "MENU_NAME", nullable = false)
	private String menuName;

	@Column(name = "MENU_DESC", nullable = false)
	private String menuDesc;

	@Column(name = "URL", nullable = false)
	private String menuURL;

	@Column(name = "MENU_TYPE", nullable = false)
	private Integer menuType;

	@Column(name = "HAS_CHILD", nullable = false)
	private Integer hasChild;

	@Column(name = "MENU_PRIORITY", nullable = false)
	private Integer menuPriority;

	@Column(name = "MENU_MASTER", nullable = false)
	private Integer menuMaster;

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

	@Column(name = "ICON_NAME", nullable = false)
	private String iconName;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "menuDetails")
	private Set<RoleMenuMapping> roleMenuMapping = new HashSet<RoleMenuMapping>(0);

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public String getMenuURL() {
		return menuURL;
	}

	public void setMenuURL(String menuURL) {
		this.menuURL = menuURL;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public Integer getMenuPriority() {
		return menuPriority;
	}

	public void setMenuPriority(Integer menuPriority) {
		this.menuPriority = menuPriority;
	}

	public Integer getMenuMaster() {
		return menuMaster;
	}

	public void setMenuMaster(Integer menuMaster) {
		this.menuMaster = menuMaster;
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

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Integer getUserDefMenuKey() {
		return userDefMenuKey;
	}

	public void setUserDefMenuKey(Integer userDefMenuKey) {
		this.userDefMenuKey = userDefMenuKey;
	}

	public Integer getHasChild() {
		return hasChild;
	}

	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}

}
