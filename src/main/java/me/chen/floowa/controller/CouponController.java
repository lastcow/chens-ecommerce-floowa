package me.chen.floowa.controller;

import me.chen.floowa.model.CouponNvidia;
import me.chen.floowa.service.NvidiaCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/system/coupon")
public class CouponController {

    @Autowired
    NvidiaCouponService nvidiaCouponService;

    @GetMapping(value = "/nvidia")
    public String nvidiaCoupons(ModelMap modelMap){

        modelMap.addAttribute("coupons", getCouponNvidias());

        return "coupon_nvidia";
    }

    @PostMapping(value = "/nvidia/use")
    @ResponseBody
    public String nvidiaCouponUsed(ModelMap modelMap, @RequestBody Map<String, Object> payload){
        String code = payload.get("code").toString();
        nvidiaCouponService.makeUsed(code);

        modelMap.addAttribute("coupons", getCouponNvidias());

        // Get counts of usable coupons,
        return String.valueOf(nvidiaCouponService.getTotalNvidiaCouponsByUsed(false));
    }


    /**
     * Get Nvidia Coupons
     * @return
     */
    private List<CouponNvidia> getCouponNvidias(){
        return nvidiaCouponService.getNvidiaCouponseUsed(false);
    }

}
