package com.greenox.order.dao;

import com.greenox.order.domain.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface OrderRepository extends MongoRepository<Order, String> {

    @Query(value = "{'status' : 'NEW'}")
    List<Order> findAllOpenOrder(Sort sort);

    @Query(value = "{'orderTime' : { $gte : ?0 }}", fields = "{orderTime : 1, _id : 0}", count = true)
    long findByOrderTimeGreaterThanEqual(LocalDateTime today);
}
