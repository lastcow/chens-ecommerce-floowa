package me.chen.floowa.service;

import me.chen.floowa.dto.AddressDto;
import me.chen.floowa.model.Address;
import me.chen.floowa.model.User;
import me.chen.floowa.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;


    /**
     * Save address from addressDto object
     * @param addressDto
     * @return
     */
    public Address saveAddress(AddressDto addressDto, User user){
        Address address = new Address();
        address.setAddress1(addressDto.getAddress1());
        address.setAddress2(addressDto.getAddress2());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCompany(addressDto.getCompany());
        address.setCountry(addressDto.getCountry());
        address.setMemo(addressDto.getMemo());
        address.setName(addressDto.getName());
        address.setContact(addressDto.getContact());
        address.setPostcode(addressDto.getPostcode());
        address.setUser(user);

        return addressRepository.save(address);
    }

    /**
     * Get user's addresses list
     * @param username
     * @return
     */
    public List<Address> findAddressesByUsername(String username){
        return  addressRepository.findAddressesByUserUsername(username);
    }
}
