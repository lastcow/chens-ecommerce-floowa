package me.chen.floowa.repository;

import me.chen.floowa.model.MerchandiseRequested;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchandiseRequestedRepository extends JpaRepository<MerchandiseRequested, String> {
}
