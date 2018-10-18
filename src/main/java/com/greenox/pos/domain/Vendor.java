package com.greenox.pos.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.greenox.pos.util.Constants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Document(collection = "vendor")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Vendor {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private Set<String> phoneNumbers;
    private String emailId;
    private Float balance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private LocalDateTime entryDate;
    private Constants.VENDOR_STATUS status;
    private Constants.VENDOR_TYPE type;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
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

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
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
