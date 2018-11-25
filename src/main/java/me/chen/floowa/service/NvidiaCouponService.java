package me.chen.floowa.service;

import me.chen.floowa.model.CouponNvidia;
import me.chen.floowa.repository.NvidiaCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NvidiaCouponService {

    @Autowired
    NvidiaCouponRepository nvidiaCouponRepository;

    /**
     * Get all nvidia coupons
     * @return
     */
    public List<CouponNvidia> getNvidiaCouponse(){
        return nvidiaCouponRepository.findAll();
    }

    public CouponNvidia create(CouponNvidia couponNvidia){
        return nvidiaCouponRepository.save(couponNvidia);
    }
}
