package com.greenox.pos.domain.inventory;

public class InventoryItem{
    private String item;
    private Float orderQuantity;
    private Float receivedQuantity;
    private Float costPerUnit;
    private Float netAmount;
    private String note;

    public Float getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(Float costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public Float getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Float netAmount) {
        this.netAmount = netAmount;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Float getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Float orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Float getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(Float receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryItem)) return false;

        InventoryItem that = (InventoryItem) o;

        return getItem().equals(that.getItem());
    }

    @Override
    public int hashCode() {
        return getItem().hashCode();
    }
}