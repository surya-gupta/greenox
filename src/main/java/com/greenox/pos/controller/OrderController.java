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

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderDao orderDao;

    @Secured({"ROLE_ORDER_VIEW"})
    @RequestMapping(value = "open", method = RequestMethod.GET)
    public @ResponseBody
    OrderAndSummary allOpenOrders() {
        LOG.info("Calling allOpenOrders");
        OrderAndSummary orderAndSummary = new OrderAndSummary();
        orderAndSummary.setOrders(orderDao.allOpenOrders());
        return orderAndSummary;
    }

    @Secured({"ROLE_ORDER_ENTRY"})
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Order addOrder(@RequestBody Order order) {
        LOG.info("Calling addOrder");
        return orderDao.addOrder(order);
    }

    @Secured({"ROLE_ORDER_ENTRY"})
    @RequestMapping(value = "update/{id}/status/{status}/note/{note}", method = RequestMethod.GET)
    public @ResponseBody
    void updateOrderStatus(@PathVariable String id, @PathVariable Constants.ORDER_STATUS status, @PathVariable String note) {
        LOG.info("Calling updateOrderStatus");
        orderDao.updateOrderStatus(id, status, note);
    }
}