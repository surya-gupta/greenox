package com.greenox.order.dao;

import com.greenox.order.domain.Item;
import com.greenox.order.domain.Order;
import com.greenox.order.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class OrderDao {
    @Autowired
    private OrderRepository orderRepository;

    public Order addOrder(Order order) {
        order.setOrderNumber(101 + orderCountOnDay());
        order.setOrderTime(LocalDateTime.now());
        order.setStatus(Constants.ORDER_STATUS.NEW);
        order.setCashier(SecurityContextHolder.getContext().getAuthentication().getName());
        if (order.getSource() != Constants.ORDER_SOURCE.Kiosk) {
            order.setPayment(null);
        }
        calculateTotalAndUpdate(order);
        return orderRepository.save(order);
    }

    public List<Order> allOpenOrders() {
        return orderRepository.findAllOpenOrder(new Sort(Sort.Direction.ASC, "orderTime"));
    }

    public void updateOrderStatus(String id, Constants.ORDER_STATUS status) {
        Order order = orderRepository.findById(id).get();
        order.setStatus(status);
        order.setServer(SecurityContextHolder.getContext().getAuthentication().getName());
        order.setServeTime(LocalDateTime.now());
        final Order save = orderRepository.save(order);
    }

    private long orderCountOnDay() {
        return orderRepository.findByOrderTimeGreaterThanEqual(LocalDate.now().atStartOfDay());
    }
    private void calculateTotalAndUpdate(Order order) {
        int total = 0;
        for (Item item : order.getItems()) {
            total += item.getPrice() * item.getQuantity();
        }
        order.setTotal(total);
        order.setNetTotal(total * (100 - order.getDiscount()) / 100);
    }
}
