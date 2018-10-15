package com.greenox.order.controller;

import com.greenox.order.dao.OrderDao;
import com.greenox.order.domain.Order;
import com.greenox.order.domain.OrderAndSummary;
import com.greenox.order.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Secured({"ROLE_ORDER_ENTRY"})
@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
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