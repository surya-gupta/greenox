package com.greenox.pos.domain.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.greenox.pos.domain.inventory.Item;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

import static com.greenox.pos.util.Constants.*;

@Document(collection = "order")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {
    @Id
    private String id;
    private long orderNumber;
    private ORDER_STATUS status;
    private Customer customer;
    @Indexed
    private LocalDateTime orderTime;
    private ORDER_SOURCE source;
    private Set<Item> items;
    private int total;
    private short discount;
    private int netTotal;
    private PAYMENT_MODE payment;
    private String note;
    private String cashier;
    private String server;
    private LocalDateTime serveTime;

    public LocalDateTime getServeTime() {
        return serveTime;
    }

    public void setServeTime(LocalDateTime serveTime) {
        this.serveTime = serveTime;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public short getDiscount() {
        return discount;
    }

    public void setDiscount(short discount) {
        this.discount = discount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(int netTotal) {
        this.netTotal = netTotal;
    }

    public ORDER_SOURCE getSource() {
        return source;
    }

    public void setSource(ORDER_SOURCE source) {
        this.source = source;
    }

    public PAYMENT_MODE getPayment() {
        return payment;
    }

    public void setPayment(PAYMENT_MODE payment) {
        this.payment = payment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ORDER_STATUS getStatus() {
        return status;
    }

    public void setStatus(ORDER_STATUS status) {
        this.status = status;
    }
}
