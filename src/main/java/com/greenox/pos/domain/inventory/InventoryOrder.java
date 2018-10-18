package com.greenox.pos.domain.inventory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.greenox.pos.util.Constants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "inventory-pos")
public class InventoryOrder {
    @Id
    private String id;
    @DBRef
    private Vendor vendor;
    private int number;
    private LocalDateTime entryTime;
    private LocalDateTime completionTime;
    private Constants.INVENTORY_STATUS status;
    private List<String> notes;
    private Set<InventoryCategorised> categorisedItems;
    private Float netAmount;
    private Float advanceAmount;
    private Float pendingAmount;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
    }

    public Constants.INVENTORY_STATUS getStatus() {
        return status;
    }

    public void setStatus(Constants.INVENTORY_STATUS status) {
        this.status = status;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public Set<InventoryCategorised> getCategorisedItems() {
        return categorisedItems;
    }

    public void setCategorisedItems(Set<InventoryCategorised> categorisedItems) {
        this.categorisedItems = categorisedItems;
    }

    public Float getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Float netAmount) {
        this.netAmount = netAmount;
    }

    public Float getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(Float advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public Float getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(Float pendingAmount) {
        this.pendingAmount = pendingAmount;
    }
}
