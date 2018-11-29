package me.chen.floowa.service;

import me.chen.floowa.dto.RequestItemDto;
import me.chen.floowa.model.MerchandiseRequested;
import me.chen.floowa.repository.MerchandiseRequestedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchandiseRequestedService {

    @Autowired
    MerchandiseRequestedRepository merchandiseRequestedRepository;

    /**
     * Save merchandise request based Dto data
     * @param requestItemDto
     * @return
     */
    public MerchandiseRequested save(RequestItemDto requestItemDto){
        MerchandiseRequested merchandiseRequested = new MerchandiseRequested();
        merchandiseRequested.setName(requestItemDto.getName());
        merchandiseRequested.setURL(requestItemDto.getItemUrl());
        merchandiseRequested.setTargetPrice(requestItemDto.getTargetPrice());
        merchandiseRequested.setTargetQty(requestItemDto.getTargetQty());

        return merchandiseRequestedRepository.save(merchandiseRequested);
    }
}
