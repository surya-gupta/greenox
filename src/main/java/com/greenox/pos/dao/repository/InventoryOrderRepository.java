package com.greenox.pos.dao.repository;

import com.greenox.pos.domain.inventory.InventoryOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryOrderRepository extends MongoRepository<InventoryOrder, String> {
}
