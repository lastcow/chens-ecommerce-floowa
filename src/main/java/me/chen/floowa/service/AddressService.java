package me.chen.floowa.service;

import me.chen.floowa.model.Address;
import me.chen.floowa.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    /**
     * Get user's addresses list
     * @param username
     * @return
     */
    public List<Address> findAddressesByUsername(String username){
        return  addressRepository.findAddressesByUserUsername(username);
    }
}
