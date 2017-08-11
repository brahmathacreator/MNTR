package com.org.mntr.dto;

import java.io.Serializable;

public class URLProps implements Serializable, Comparable<URLProps> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long menuId;
	private Integer userDefMenuKey;
	private String menuName;
	private String menuDesc;
	private String menuURL;
	private Integer menuType;
	private Integer menuPriority;
	private Integer menuMaster;
	private String iconName;
	private Integer opsType;
	private Integer hasChild;

	@Override
	public int compareTo(URLProps o) {
		return this.menuPriority - o.getMenuPriority();
	}

	@Override
	public boolean equals(Object obj) {
		return this.menuId.equals(obj);
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

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

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
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

	public Integer getOpsType() {
		return opsType;
	}

	public void setOpsType(Integer opsType) {
		this.opsType = opsType;
	}

}
