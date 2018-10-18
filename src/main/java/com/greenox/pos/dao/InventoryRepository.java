package com.greenox.pos.dao;

import com.greenox.pos.domain.inventory.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface InventoryRepository extends MongoRepository<Inventory, String> {
}