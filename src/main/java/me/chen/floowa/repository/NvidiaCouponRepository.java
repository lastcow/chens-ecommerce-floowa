package me.chen.floowa.repository;

import me.chen.floowa.model.CouponNvidia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NvidiaCouponRepository extends JpaRepository<CouponNvidia, String> {

    CouponNvidia findByCode(String code);
    List<CouponNvidia> getByUsed(boolean used);
    int countCouponNvidiaByUsed(boolean used);
}
