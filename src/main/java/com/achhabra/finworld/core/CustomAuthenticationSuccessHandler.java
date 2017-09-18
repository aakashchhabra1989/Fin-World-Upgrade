package com.achhabra.finworld.core;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	protected Log logger = LogFactory.getLog(this.getClass());
	 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }
 
    protected void handle(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(request, authentication);
 
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
 
    /** Builds the target URL according to the logic defined in the main class Javadoc. */
    protected String determineTargetUrl(HttpServletRequest request,Authentication authentication) {
        boolean isUser = false;
        boolean isOfficeUser = false;
        boolean isOfficeAdmin = false;
        boolean isSuperAdmin = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("INDIVIDUAL USER")) {
                isUser = true;
                break;
            }else if (grantedAuthority.getAuthority().equals("OFFICE USER")) {
            	isOfficeUser = true;
                break;
            }else if (grantedAuthority.getAuthority().equals("OFFICE ADMIN")) {
            	isOfficeAdmin = true;
                break;
            }else if (grantedAuthority.getAuthority().equals("SUPER ADMIN")) {
            	isSuperAdmin = true;
                break;
            }
            
        }
 
        if(isUser || isOfficeUser || isOfficeAdmin || isSuperAdmin){
        	Object userDetails= authentication.getPrincipal();
        	request.getSession(true).setAttribute("userDetails",userDetails);
        }
        if (isUser) {
            return "/navigate/loadHomePage.html";
        } else if(isOfficeAdmin){
        	return "/navigate/loadHomePage.html";
        } else if (isOfficeUser) {
            return "/navigate/loadHomePage.html";
        } else if (isSuperAdmin) {
            return "/navigate/loadHomePage.html";
        } else {
            throw new IllegalStateException();
        }
    }
 
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
