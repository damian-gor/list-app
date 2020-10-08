package com.damgor.listapp.controllers;

import com.damgor.listapp.models.DTOs.UserDTO;
import com.damgor.listapp.security.services.UserDetailsServiceExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDetailsServiceExt userDetailsService;

    @GetMapping("/getOtherUsers")
    public List<UserDTO> getOtherUsers () {
        return userDetailsService.getOtherUsers();
    }

    @GetMapping("/friendsList")
    public List<UserDTO> getFriendsList () {
        return userDetailsService.getFriendsList();
    }

    @PutMapping("/friendsList")
    public List<UserDTO> updateFriendsList (@RequestBody List<UserDTO> newFriendsList) {
        return userDetailsService.updateFriendsList(newFriendsList);
    }

}
