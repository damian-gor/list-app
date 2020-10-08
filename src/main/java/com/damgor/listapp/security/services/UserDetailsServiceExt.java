package com.damgor.listapp.security.services;

import com.damgor.listapp.models.DTOs.UserDTO;
import com.damgor.listapp.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserDetailsServiceExt extends UserDetailsService {
    String getUserName ();
    UserDTO getUserDTO ();
    UserDTO getUserDTObyId (Long userId);
    List<UserDTO> getOtherUsers ();
    List<UserDTO> getFriendsList ();
    List<UserDTO> updateFriendsList (List<UserDTO> newFriendsList);
    Set<User> userDTOsListToUsersSet (List<UserDTO> usersDTOsList);
    User getUserById(Long userId);
}
