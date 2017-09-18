package com.achhabra.finworld.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class CoreUtils {

	public static Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}
	
	public static List<String> getRoles(Integer role) {

		List<String> roles = new ArrayList<String>();

		if (role.intValue() == 1) {
			roles.add("SUPER ADMIN");			
		} else if (role.intValue() == 2) {
			roles.add("OFFICE ADMIN");
		} else if(role.intValue() == 3){
			roles.add("OFFICE USER");
		} else if(role.intValue() == 4){
			roles.add("INDIVIDUAL USER");
		}
		return roles;
	}
	
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
	
	public static void getDetails(HttpServletRequest request){
		Object principal = SecurityContextHolder.getContext()
			     .getAuthentication().getPrincipal();
			HttpSession session = request.getSession(true); //create a new session

			// put the UserDetails object here.
			session.setAttribute("userDetails", principal);
	}

}
