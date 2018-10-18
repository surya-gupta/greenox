package com.greenox.pos.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenox.pos.domain.inventory.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class InventoryDao {
    @Autowired
    InventoryRepository inventoryRepository;

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
}
