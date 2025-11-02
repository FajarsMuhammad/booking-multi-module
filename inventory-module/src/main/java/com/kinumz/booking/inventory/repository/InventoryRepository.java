package com.kinumz.booking.inventory.repository;

import com.kinumz.booking.inventory.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
    InventoryItem findByProductCode(String productCode);
}
