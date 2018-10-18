package com.greenox.pos.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenox.pos.dao.repository.InventoryOrderRepository;
import com.greenox.pos.dao.repository.InventoryRepository;
import com.greenox.pos.dao.repository.VendorRepository;
import com.greenox.pos.domain.inventory.Inventory;
import com.greenox.pos.domain.inventory.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class InventoryDao {
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

            }
        }
    }

    private Vendor createOrUpdateVendor(Vendor vendor) {
       vendor=vendorRepository.save(vendor);
       LOG.info("Vendor updated/saved {}",vendor.getName());
       return vendor;
    }
}
