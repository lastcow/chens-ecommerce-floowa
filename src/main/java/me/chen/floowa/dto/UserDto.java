package me.chen.floowa.dto;

import lombok.Data;
import me.chen.floowa.model.Role;
import me.chen.floowa.model.User;

@Data
public class UserDto {

    private String id;
    private String username;
    private String role;
    private String name;
    private String email;
    private String contactNumber;
    private String wechat;
    private String qq;

    public static UserDto convert(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setName(user.getLastName()+" " + user.getFirstName());
        userDto.setEmail(user.getEmail());
        userDto.setContactNumber(user.getContactNumber());
        userDto.setWechat(user.getWechat());
        userDto.setQq(user.getQq());
        userDto.setRole(user.getRole().getName());
        return userDto;
    }
}
