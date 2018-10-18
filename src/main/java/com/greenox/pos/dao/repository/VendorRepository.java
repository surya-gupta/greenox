package com.greenox.pos.dao.repository;

import com.greenox.pos.domain.inventory.Vendor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VendorRepository extends MongoRepository<Vendor, String> {
}
