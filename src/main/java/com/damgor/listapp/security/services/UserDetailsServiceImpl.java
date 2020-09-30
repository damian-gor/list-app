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

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsServiceExt {

  @Autowired private UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user =
        userRepository
            .findByUsername(username)
            .orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }

  @Override
  public String getUserName() {
    UserDetails userDetails =
        (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return userDetails.getUsername();
  }

  @Override
  public UserDTO getUserDTO() {
    User user =
        userRepository
            .findByUsername(getUserName())
            .orElseThrow(
                () ->
                    new UsernameNotFoundException(
                        "User Not Found with username: " + getUserName()));
    UserDTO userDTO = new UserDTO();
    userDTO.setUserId(user.getId());
    userDTO.setUserName(user.getUsername());
    return userDTO;
  }

  @Override
  public UserDTO getUserDTObyId(Long userId) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with userId: " + userId));
    UserDTO userDTO = new UserDTO();
    userDTO.setUserId(user.getId());
    userDTO.setUserName(user.getUsername());
    return userDTO;
  }

  public List<UserDTO> getOtherUsers() {
    UserDetails userDetails =
        (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<UserDTO> users = new ArrayList<>();
    userRepository
        .findAll()
        .forEach(
            user -> {
              if (!user.getUsername().equals(userDetails.getUsername())) {
                UserDTO userDTO = new UserDTO();
                userDTO.setUserId(user.getId());
                userDTO.setUserName(user.getUsername());
                users.add(userDTO);
              }
            });
    return users;
  }

  @Override
  public List<UserDTO> getFriendsList() {
    List<UserDTO> friends = new ArrayList<>();
    userRepository
        .getOne(getUserDTO().getUserId())
        .getFriends()
        .forEach(
            user -> {
              UserDTO userDTO = new UserDTO();
              userDTO.setUserId(user.getId());
              userDTO.setUserName(user.getUsername());
              friends.add(userDTO);
            });
    return friends;
  }

  @Override
  public List<UserDTO> updateFriendsList(List<UserDTO> newFriendsList) {
    User user =
            userRepository
                    .findByUsername(getUserName())
                    .orElseThrow(
                            () -> new UsernameNotFoundException("User Not Found with userName: " + getUserName()));
    Set<User> updatedFriendsSet = userDTOsListToUsersSet(newFriendsList);
    user.setFriends(updatedFriendsSet);
    user = userRepository.save(user);

    return usersSetToUserDTOsList(user.getFriends());
  }

  private List<UserDTO> usersSetToUserDTOsList (Set<User> usersSet){
    List<UserDTO> userDTOsList = new ArrayList<>();
    usersSet.forEach( user -> {
      UserDTO userDTO = new UserDTO();
      userDTO.setUserId(user.getId());
      userDTO.setUserName(user.getUsername());
      userDTOsList.add(userDTO);
    });
    return userDTOsList;

  }

  private Set<User> userDTOsListToUsersSet (List<UserDTO> usersDTOsList){
    List<Long> ids = new ArrayList<>();
    usersDTOsList.forEach(userDTO -> ids.add(userDTO.getUserId()));

    Set<User> usersSet = new HashSet<>();
    usersSet.addAll(userRepository.findUsersByIdIsIn(ids));
    return usersSet;
  }


}

