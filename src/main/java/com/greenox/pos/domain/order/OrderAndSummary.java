package com.greenox.pos.domain.order;

import java.util.HashMap;
import java.util.List;

public class OrderAndSummary {
    private List<Order> orders;
    private HashMap<String, Integer> summary;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
        this.calculateSummary();
    }

    private void calculateSummary() {
        summary = new HashMap<>();
        orders.forEach(order -> {
            order.getItems().forEach(item -> {
                int quantity = summary.getOrDefault(item.getName(), 0);
                summary.put(item.getName(), quantity + item.getQuantity());
            });
        });
    }

    public HashMap<String, Integer> getSummary() {
        return summary;
    }

    public void setSummary(HashMap<String, Integer> summary) {
        this.summary = summary;
    }
}
