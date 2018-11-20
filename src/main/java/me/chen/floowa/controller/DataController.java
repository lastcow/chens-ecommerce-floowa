package me.chen.floowa.controller;

import me.chen.floowa.dto.UserDto;
import me.chen.floowa.model.Role;
import me.chen.floowa.model.User;
import me.chen.floowa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DataController {

    @Autowired
    UserService userService;

    /**
     * Get all Users
     * @param principal
     * @return
     */
    @GetMapping(value = "/data/users/get")
    @ResponseBody
    public List<UserDto> getUsers(Principal principal){

        // Get user role
        User loggedUser = userService.findUserByUsername(principal.getName());
        Role role = loggedUser == null ? null : loggedUser.getRole();

        List<User> userList = userService.getAllUsers();
        List<UserDto> userDtoList = new ArrayList<>();

        userList.forEach(user -> {
            userDtoList.add(UserDto.convert(user));
        });

        return userDtoList;
    }
}
