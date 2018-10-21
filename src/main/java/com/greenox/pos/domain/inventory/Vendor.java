package com.greenox.pos.domain.inventory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.greenox.pos.util.Constants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "vendor")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Vendor {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private Long phoneNumber;
    private String emailId;
    private Float balance;
    private String addedBy;
    private String updatedBy;
    private LocalDateTime entryTime;
    private LocalDateTime updateTime;
    private Constants.VENDOR_STATUS status;
    private Constants.VENDOR_TYPE type;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Constants.VENDOR_STATUS getStatus() {
        return status;
    }

    public void setStatus(Constants.VENDOR_STATUS status) {
        this.status = status;
    }

    public Constants.VENDOR_TYPE getType() {
        return type;
    }

    public void setType(Constants.VENDOR_TYPE type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
