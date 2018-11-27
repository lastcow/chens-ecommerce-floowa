package me.chen.floowa.service;

import me.chen.floowa.model.CouponNvidia;
import me.chen.floowa.repository.NvidiaCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    /**
     * Create coupon recode based on code only
     * Everything else is default
     * @param code
     * @return
     */
    public CouponNvidia create(String code){
        CouponNvidia couponNvidia = new CouponNvidia();
        couponNvidia.setUsed(false);
        couponNvidia.setValid(true);
        couponNvidia.setCode(code);
        couponNvidia.setAppliedDate(new Date());
        couponNvidia.setGoodForUnits(1);

        return nvidiaCouponRepository.save(couponNvidia);
    }

    /**
     * Update nvidia coupon entity
     * @param code
     * @return
     */
    public CouponNvidia makeUsed(String code){
        CouponNvidia couponNvidia = nvidiaCouponRepository.findByCode(code);
        couponNvidia.setUsed(true);
        return nvidiaCouponRepository.save(couponNvidia);
    }

    /**
     * Get all nvidia coupons based on used status.
     * @param used
     * @return
     */
    public List<CouponNvidia> getNvidiaCouponseUsed(boolean used){
        return nvidiaCouponRepository.getByUsed(used);
    }

    /**
     * Get coupons count based used status
     * @param used
     * @return
     */
    public int getTotalNvidiaCouponsByUsed(boolean used){
        return nvidiaCouponRepository.countCouponNvidiaByUsed(used);
    }
}
