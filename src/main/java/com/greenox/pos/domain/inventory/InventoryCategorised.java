package com.greenox.pos.domain.inventory;

import java.util.Set;

public class InventoryCategorised {
    private String category;
    private Set<InventoryItem> inventoryItems;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryCategorised)) return false;

        InventoryCategorised that = (InventoryCategorised) o;

        return category.equals(that.category);
    }

    @Override
    public int hashCode() {
        return category.hashCode();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }

    public void setInventoryItems(Set<InventoryItem> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }
}
