package com.greenox.pos.domain.inventory;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Document(collection = "inventory")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Inventory {
    @Id
    public String id;
    public Set<CategoryAndList> inventory;
    private String addedBy;
    private String updatedBy;
    private LocalDateTime entryTime;
    private LocalDateTime updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<CategoryAndList> getInventory() {
        return inventory;
    }

    public void setInventory(Set<CategoryAndList> inventory) {
        this.inventory = inventory;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}

class CategoryAndList {
    private String category;
    private Set<String> inventoryItems;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<String> getInventoryItems() {
        return inventoryItems;
    }

    public void setInventoryItems(Set<String> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }
}
