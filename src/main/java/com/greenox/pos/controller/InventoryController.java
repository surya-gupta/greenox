package com.greenox.pos.controller;

import com.greenox.pos.dao.InventoryDao;
import com.greenox.pos.domain.inventory.Inventory;
import com.greenox.pos.domain.inventory.InventoryOrder;
import com.greenox.pos.domain.inventory.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/inventory")
public class InventoryController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private InventoryDao inventoryDao;

    @Secured({"ROLE_INVENTORY_VENDORS"})
    @RequestMapping(value = "category", method = RequestMethod.GET)
    public Inventory getCategorisedInventory() throws Exception {
        LOG.info("Calling all category");
        return inventoryDao.getCategorisedInventory().get(0);
    }

    @Secured({"ROLE_INVENTORY_ENTRY"})
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public InventoryOrder addOrder(@RequestBody InventoryOrder order) throws Exception {
        LOG.info("Calling addOrder");
        return inventoryDao.saveOrder(order);
    }

    @Secured({"ROLE_INVENTORY_VENDORS"})
    @RequestMapping(value = "vendor/create", method = RequestMethod.POST)
    public Vendor createVendor(@RequestBody Vendor vendor) throws Exception {
        LOG.info("Calling addVendor");
        vendor.setAddedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        vendor.setEntryTime(LocalDateTime.now());
        return inventoryDao.createOrUpdateVendor(vendor);
    }

    @Secured({"ROLE_INVENTORY_VENDORS"})
    @RequestMapping(value = "vendor/update", method = RequestMethod.POST)
    public Vendor addOrUpdateVendor(@RequestBody Vendor vendor) throws Exception {
        LOG.info("Calling updateVendor");
        vendor.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        vendor.setUpdateTime(LocalDateTime.now());
        return inventoryDao.createOrUpdateVendor(vendor);
    }

    @Secured({"ROLE_INVENTORY_VENDORS"})
    @RequestMapping(value = "vendor/all", method = RequestMethod.GET)
    public List<Vendor> getVendor() throws Exception {
        LOG.info("Calling updateVendor");
        return inventoryDao.getVendors();
    }
}
