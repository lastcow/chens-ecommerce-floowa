package me.chen.floowa.repository;

import me.chen.floowa.model.CouponNvidia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NvidiaCouponRepository extends JpaRepository<CouponNvidia, String> {
}
