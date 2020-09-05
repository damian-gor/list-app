package com.damgor.listapp.security.services;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailsServiceExt extends UserDetailsService {
    String getUserName ();
}
