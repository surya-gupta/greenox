package com.greenox.pos.dao;

import com.greenox.pos.dao.repository.OrderRepository;
import com.greenox.pos.domain.inventory.Item;
import com.greenox.pos.domain.order.Customer;
import com.greenox.pos.domain.order.Order;
import com.greenox.pos.util.Constants;
import com.greenox.pos.util.SendWhatsAppNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDao {

    private static final Logger LOG = LoggerFactory.getLogger(OrderDao.class);
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SendWhatsAppNotification notify;

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
        Customer customer = order.getCustomer();
        String items = order.getItems().stream().map(Item::getName).collect(Collectors.joining(","));
        if (customer != null && customer.getPhoneNumber() != null) {
            String text = "Dear _%s_, your Order# *%s* is *%s*  item(s) : %s";
            String response = notify.sendNotification(customer.getPhoneNumber(),
                    String.format(text,
                            customer.getName(),
                            order.getOrderNumber(),
                            order.getStatus().toString().toLowerCase(),
                            items));
            LOG.info("Notified Order {} with response {}", order.getId(), response);
        }
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
