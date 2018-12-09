package me.chen.floowa.bootstrap;

import me.chen.floowa.model.CouponNvidia;
import me.chen.floowa.model.Role;
import me.chen.floowa.model.User;
import me.chen.floowa.service.NvidiaCouponService;
import me.chen.floowa.service.RoleService;
import me.chen.floowa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    NvidiaCouponService nvidiaCouponService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        // Create role admin
        Role role = roleService.create("ROLE_ADMIN", "Administrator");
        User user = new User();
        user.setUsername("lastcow");
        user.setPassword(passwordEncoder.encode("abc"));
        user.setEmail("ebiz@chen.me");
        user.setContactNumber("301-338 3398");
        user.setWechat("Chens LLC");
        user.setQq("13948");
        user.setFirstName("John");
        user.setLastName("Steve");
        user.setActive(true);
        user.setRole(role);
        userService.create(user);

        role = roleService.create("ROLE_AGENT", "Sales agent");
        user = new User();
        user.setUsername("lastcow1");
        user.setPassword(passwordEncoder.encode("abc"));
        user.setEmail("ebiz1@chen.me");
        user.setContactNumber("301-338 3398");
        user.setWechat("Chens LLC");
        user.setQq("13948");
        user.setFirstName("John");
        user.setLastName("Steve");
        user.setActive(true);
        user.setRole(role);
        userService.create(user);

        role = roleService.create("ROLE_CUSTOMER", "Customer");
        user = new User();
        user.setUsername("lastcow2");
        user.setPassword(passwordEncoder.encode("abc"));
        user.setEmail("ebiz2@chen.me");
        user.setContactNumber("301-338 3398");
        user.setWechat("Chens LLC");
        user.setQq("13948");
        user.setFirstName("John");
        user.setLastName("Steve");
        user.setActive(true);
        user.setRole(role);
        userService.create(user);

        role = roleService.create("ROLE_SUPPLIER", "Supplier");
        user = new User();
        user.setUsername("lastcow3");
        user.setPassword(passwordEncoder.encode("abc"));
        user.setEmail("ebiz3@chen.me");
        user.setContactNumber("301-338 3398");
        user.setWechat("Chens LLC");
        user.setQq("13948");
        user.setFirstName("John");
        user.setLastName("Steve");
        user.setActive(true);
        user.setRole(role);
        userService.create(user);

        // Add coupon
        CouponNvidia couponNvidia = new CouponNvidia();
        couponNvidia.setCode("7xq7vbnow");
        couponNvidia.setValid(true);
        couponNvidia.setUsed(false);
        nvidiaCouponService.create(couponNvidia);

        couponNvidia = new CouponNvidia();
        couponNvidia.setCode("xggfred8o");
        couponNvidia.setValid(true);
        couponNvidia.setUsed(false);
        nvidiaCouponService.create(couponNvidia);

    }
}
