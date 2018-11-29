package me.chen.floowa.controller;

import me.chen.floowa.model.Merchandise;
import me.chen.floowa.service.MerchandiseService;
import me.chen.floowa.service.NvidiaCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data/statistics")
public class StatisticsController {

    @Autowired
    NvidiaCouponService nvidiaCouponService;
    @Autowired
    MerchandiseService merchandiseService;

    @PostMapping(value = "/nvidiacouponcount")
    public int getTotalUsableNvidiaCoupons(){

        return nvidiaCouponService.getTotalNvidiaCouponsByUsed(false);
    }

    /**
     * Get actived merchandise count.
     * @return
     */
    @PostMapping(value = "/itemscount")
    public int getTotalItemsCount(){

        List<Merchandise> merchandises = merchandiseService.findMerchandiseByActive(true);
        return merchandises == null ? 0 : merchandises.size();

    }
}
