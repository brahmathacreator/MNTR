package com.org.mntr.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.org.mntr.constants.StatusConstants;

public class ActualUser implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserInfoDto userInfo;
	private MenuDetailsDto currentUrlDetails;

	public ActualUser(UserInfoDto userInfo) {
		this.userInfo = userInfo;
	}

	public ActualUser() {
		super();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for (String role : userInfo.getRoles())
			authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

	@Override
	public String getPassword() {
		return userInfo.getHashPwd();
	}

	@Override
	public String getUsername() {
		return userInfo.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return userInfo.getStatus() == StatusConstants.active ? true : false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return userInfo.getLockStatus() == StatusConstants.active ? true : false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return userInfo.getStatus() == StatusConstants.active ? true : false;
	}

	@Override
	public boolean isEnabled() {
		return userInfo.getStatus() == StatusConstants.active ? true : false;
	}

	public UserInfoDto getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoDto userInfo) {
		this.userInfo = userInfo;
	}

	public MenuDetailsDto getCurrentUrlDetails() {
		return currentUrlDetails;
	}

	public void setCurrentUrlDetails(MenuDetailsDto currentUrlDetails) {
		this.currentUrlDetails = currentUrlDetails;
	}

}
