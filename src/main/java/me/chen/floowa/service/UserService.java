package me.chen.floowa.service;

import me.chen.floowa.model.User;
import me.chen.floowa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Create new user
     * @param user
     * @return
     */
    public User create(User user){
        return userRepository.save(user);
    }

    /**
     * Get all users
     * @return
     */
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * Get user details by username.
     * @param username
     * @return
     */
    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }
}
