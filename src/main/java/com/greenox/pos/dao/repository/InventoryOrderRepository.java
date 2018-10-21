package com.greenox.pos.dao.repository;

import com.greenox.pos.domain.inventory.InventoryOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;

public interface InventoryOrderRepository extends MongoRepository<InventoryOrder, String> {

    @Query(value = "{'entryTime' : { $gte : ?0 }}", fields = "{entryTime : 1, _id : 0}", count = true)
    long findByOrderTimeGreaterThanEqual(LocalDateTime today);
}
