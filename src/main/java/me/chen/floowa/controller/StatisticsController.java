package me.chen.floowa.controller;

import me.chen.floowa.service.NvidiaCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/statistics")
public class StatisticsController {

    @Autowired
    NvidiaCouponService nvidiaCouponService;

    @PostMapping(value = "/nvidiacouponcount")
    public int getTotalUsableNvidiaCoupons(){

        return nvidiaCouponService.getTotalNvidiaCouponsByUsed(false);
    }
}
