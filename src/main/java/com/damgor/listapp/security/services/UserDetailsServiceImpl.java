package com.damgor.listapp.security.services;

import com.damgor.listapp.models.DTOs.UserDTO;
import com.damgor.listapp.models.User;
import com.damgor.listapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsServiceExt {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    @Override
    public String getUserName () {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     return userDetails.getUsername();
    }

    @Override
    public UserDTO getUserDTO() {
        User user = userRepository.findByUsername(getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + getUserName()));
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getId());
        userDTO.setUserName(user.getUsername());
        return userDTO;
    }

    @Override
    public UserDTO getUserDTObyId(Long userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new UsernameNotFoundException("User Not Found with userId: " + userId));
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getId());
        userDTO.setUserName(user.getUsername());
        return userDTO;
    }
}