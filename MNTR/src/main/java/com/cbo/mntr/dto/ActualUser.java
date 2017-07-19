package com.cbo.mntr.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cbo.mntr.constants.StatusConstants;
import com.cbo.mntr.entity.UserInfo;

public class ActualUser implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserInfo userInfo;
	private List<String> roles;
	private List<String> urls;

	public ActualUser(UserInfo userInfo, List<String> roles, List<String> urls) {
		this.userInfo = userInfo;
		this.roles = roles;
		this.urls = urls;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for (String role : roles)
			authorities.add(new SimpleGrantedAuthority(role));
		for (String url : urls)
			authorities.add(new SimpleGrantedAuthority(url));
		return authorities;
	}

	@Override
	public String getPassword() {
		return userInfo.getPasswordDetails().getHashPwd();
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

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
