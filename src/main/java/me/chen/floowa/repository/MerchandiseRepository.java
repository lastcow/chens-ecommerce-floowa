package me.chen.floowa.repository;

import me.chen.floowa.model.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchandiseRepository extends JpaRepository<Merchandise, String> {
    List<Merchandise> findByActive(boolean active);
}
