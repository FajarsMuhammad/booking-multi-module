package com.kinumz.booking.inventory.service;

import com.kinumz.booking.inventory.entity.InventoryItem;
import com.kinumz.booking.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional
    public boolean reserve(String productCode, int qty) {
        InventoryItem item = inventoryRepository.findByProductCode(productCode);
        if (item == null || item.getAvailable() < qty) {
            return false;
        }
        item.setAvailable(item.getAvailable() - qty);
        inventoryRepository.save(item);
        return true;
    }

    @Transactional
    public void release(String productCode, int qty) {
        InventoryItem item = inventoryRepository.findByProductCode(productCode);
        if (item == null) {
            return;
        }
        item.setAvailable(item.getAvailable() + qty);
        inventoryRepository.save(item);
    }
}
