package me.chen.floowa.service;

import me.chen.floowa.model.Role;
import me.chen.floowa.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    /**
     * Create role
     * @param role
     * @return
     */
    public Role create(Role role){
        return roleRepository.save(role);
    }

    /**
     * Create role
     * @param roleName
     * @param roleDesc
     * @return
     */
    public Role create(String roleName, String roleDesc){
        Role role = new Role();
        role.setName(roleName);
        role.setDescription(roleDesc);

        return roleRepository.save(role);
    }
}
