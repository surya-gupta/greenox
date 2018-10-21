package com.greenox.pos.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenox.pos.dao.repository.InventoryOrderRepository;
import com.greenox.pos.dao.repository.InventoryRepository;
import com.greenox.pos.dao.repository.VendorRepository;
import com.greenox.pos.domain.inventory.Inventory;
import com.greenox.pos.domain.inventory.InventoryItem;
import com.greenox.pos.domain.inventory.InventoryOrder;
import com.greenox.pos.domain.inventory.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class InventoryDao implements InitializingBean {
    private static final Logger LOG = LoggerFactory.getLogger(InventoryDao.class);
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private InventoryOrderRepository inventoryOrderRepository;
    @Autowired
    private VendorRepository vendorRepository;

    private void loadInventory() {
        StringBuilder text = new StringBuilder();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("inventory.json");
        if (inputStream != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    text.append(line);
                }
                Inventory inventory = new ObjectMapper().readValue(text.toString(), Inventory.class);
                inventoryRepository.save(inventory);
            } catch (Exception ex) {
                LOG.info("Error in inventory load ", ex);
            }
        }
    }

    public List<Inventory> getCategorisedInventory() {
        return inventoryRepository.findAll();
    }

    public Vendor createOrUpdateVendor(Vendor vendor) {
       vendor=vendorRepository.save(vendor);
       LOG.info("Vendor updated/saved {}",vendor.getName());
       return vendor;
    }

    public InventoryOrder saveOrder(InventoryOrder inventoryOrder) throws Exception {
        LOG.info("Saving inventory {}");
        deleteEmptyItems(inventoryOrder);
        if (inventoryOrder.getCategorisedItems().size() == 0) return null;
        inventoryOrder.setEntryTime(LocalDateTime.now());
        inventoryOrder.setAddedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        inventoryOrder.setInvNum(orderCountOnDay()+1);
        InventoryOrder order = inventoryOrderRepository.save(inventoryOrder);
        return order;
    }

    public List<Vendor> getVendors() {
        LOG.info("get all vendors");
        return vendorRepository.findAll();
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    private void deleteEmptyItems(InventoryOrder inventoryOrder) {

        inventoryOrder.getCategorisedItems().forEach(category -> {
            Set<InventoryItem> inventoryItems = new HashSet<>();
            category.getInventoryItems().forEach(item -> {
                if (item != null && item.getOrderQuantity() != null && item.getOrderQuantity() > 0) {
                    inventoryItems.add(item);
                }
            });
            category.setInventoryItems(inventoryItems);
        });

        inventoryOrder.setCategorisedItems(inventoryOrder.getCategorisedItems().stream()
                .filter(items -> {
                    return items.getInventoryItems().size() > 0;
                }).collect(Collectors.toSet()));

    }

    private long orderCountOnDay() {
        return inventoryOrderRepository.findByOrderTimeGreaterThanEqual(LocalDate.now().atStartOfDay());
    }
}
