package com.greenox.pos.controller;

import com.greenox.pos.dao.OrderDao;
import com.greenox.pos.domain.order.Order;
import com.greenox.pos.domain.order.OrderAndSummary;
import com.greenox.pos.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Secured({"ROLE_INVENTORY_MANAGEMENT"})
@RestController
@RequestMapping(value = "/api/inventory")
public class InventoryController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderDao orderDao;

    @RequestMapping(value = "open", method = RequestMethod.GET)
    public @ResponseBody
    OrderAndSummary allOpenOrders() {
        LOG.info("Calling allOpenOrders");
        OrderAndSummary orderAndSummary = new OrderAndSummary();
        orderAndSummary.setOrders(orderDao.allOpenOrders());
        return orderAndSummary;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Order addOrder(@RequestBody Order order) {
        LOG.info("Calling addOrder");
        return orderDao.addOrder(order);
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public @ResponseBody
    List<Order> getOrder() {
        return null;
    }

    @RequestMapping(value = "update/{id}/status/{status}", method = RequestMethod.GET)
    public @ResponseBody
    void updateOrderStatus(@PathVariable String id, @PathVariable Constants.ORDER_STATUS status) {
        LOG.info("Calling updateOrderStatus");
        orderDao.updateOrderStatus(id, status);
    }
}
