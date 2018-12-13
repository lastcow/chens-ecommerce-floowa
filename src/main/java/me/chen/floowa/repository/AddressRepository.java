package me.chen.floowa.repository;

import me.chen.floowa.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

    /**
     * Get all address by login user name
     * @param userName
     * @return
     */
    public List<Address> findAddressesByUserUsername(String userName);
}
