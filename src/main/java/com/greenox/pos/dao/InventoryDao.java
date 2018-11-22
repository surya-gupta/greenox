package com.greenox.pos.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenox.pos.dao.repository.InventoryOrderRepository;
import com.greenox.pos.dao.repository.InventoryRepository;
import com.greenox.pos.dao.repository.VendorRepository;
import com.greenox.pos.domain.inventory.Inventory;
import com.greenox.pos.domain.inventory.InventoryItem;
import com.greenox.pos.domain.inventory.InventoryOrder;
import com.greenox.pos.domain.inventory.Vendor;
import com.greenox.pos.util.Constants;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

@Component
public class InventoryDao implements InitializingBean {
    private static final Logger LOG = LoggerFactory.getLogger(InventoryDao.class);
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private InventoryOrderRepository inventoryOrderRepository;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

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
        if (vendor.getId() == null) {
            vendor.setBalance(0F);
            vendor.setAddedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            vendor.setEntryTime(LocalDateTime.now());
            vendor.setStatus(Constants.VENDOR_STATUS.Current);
        } else {
            vendor.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            vendor.setUpdateTime(LocalDateTime.now());
        }
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
        inventoryOrder.setStatus(Constants.INVENTORY_STATUS.NEW);
        InventoryOrder order = inventoryOrderRepository.save(inventoryOrder);
        return order;
    }

    public List<Vendor> getVendors() {
        LOG.info("get all vendors");
        return vendorRepository.findAll();
    }

    public List<InventoryOrder> searchInventory(HashMap<String, Object> searchCriteria) {
        Criteria criteria = new Criteria();
        if (searchCriteria.containsKey("vendor")) {
            criteria.and("vendor.$id").is(new ObjectId(String.valueOf(searchCriteria.get("vendor"))));
        }
        if (searchCriteria.containsKey("invNum")) {
            criteria.and("invNum").is(searchCriteria.get("invNum"));
        }
        if (searchCriteria.containsKey("status")) {
            criteria.and("status").is(searchCriteria.get("status"));
        }
        MatchOperation matchStage = Aggregation.match(criteria);
        SortOperation sortByEntryTimeDesc = sort(new Sort(Sort.Direction.DESC, "entryTime"));
        Aggregation aggregation = Aggregation.newAggregation(matchStage,sortByEntryTimeDesc);
        AggregationResults<InventoryOrder> output= mongoTemplate.aggregate(aggregation, "inventory-order", InventoryOrder.class);
        return output.getMappedResults();
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
