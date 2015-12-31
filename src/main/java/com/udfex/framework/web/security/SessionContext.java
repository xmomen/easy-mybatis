package com.udfex.framework.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created by Jeng on 2015/4/12.
 */
@Component
public class SessionContext {

    public UserDetails getUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            return null;
        }
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails){
            return (UserDetails) principal;
        }
        return null;
    }
}
