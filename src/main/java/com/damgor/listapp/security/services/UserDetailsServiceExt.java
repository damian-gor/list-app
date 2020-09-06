package com.damgor.listapp.security.services;

import com.damgor.listapp.models.DTOs.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailsServiceExt extends UserDetailsService {
    String getUserName ();
    UserDTO getUserDTO ();
    UserDTO getUserDTObyId (Long userId);
}
