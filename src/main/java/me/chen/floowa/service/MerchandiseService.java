package me.chen.floowa.service;

import me.chen.floowa.dto.ItemDto;
import me.chen.floowa.model.Merchandise;
import me.chen.floowa.repository.MerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchandiseService {

    @Autowired
    MerchandiseRepository merchandiseRepository;

    /**
     * Save merchandise from DTO
     * @param itemDto
     * @return
     */
    public Merchandise save(ItemDto itemDto){
        Merchandise merchandise = new Merchandise();
        merchandise.setName(itemDto.getName());
        merchandise.setPriceCustomer(itemDto.getCustomerPrice());
        merchandise.setPriceAgent(itemDto.getAgentPrice());
        merchandise.setStockCount(itemDto.getStockCount());
        merchandise.setLeadTime(itemDto.getLeadTime());
        merchandise.setMinimalOrderCustomer(itemDto.getMoqCustomer());
        merchandise.setMinimalOrderAgent(itemDto.getMoqAgent());
        merchandise.setImgUrl(itemDto.getImageUrl());
        merchandise.setDescription(itemDto.getDescription());
        merchandise.setActive(true);

        return merchandiseRepository.save(merchandise);
    }

    /**
     * return merchandise by id.
     * @param id
     * @return
     */
    public Optional<Merchandise> findById(String id){
        return merchandiseRepository.findById(id);
    }

    public List<Merchandise> findAll(){
        return merchandiseRepository.findAll();
    }

    public List<Merchandise> findMerchandiseByActive(boolean active){
        return merchandiseRepository.findByActive(active);
    }
}
