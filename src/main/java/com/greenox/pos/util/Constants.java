package com.greenox.pos.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface Constants {
    enum ORDER_SOURCE {Kiosk, Zomato, Swiggy, FoodPanda}

    enum PAYMENT_MODE {Cash, Paytm, PhonePe, GPay, Mobikwik, FreeCharge}

    enum ORDER_STATUS {NEW, CANCELLED, REJECTED, MODIFIED, COMPLETED}

    enum INVENTORY_STATUS {NEW, CANCELLED, INCOMPLETE, COMPLETED}

    enum VENDOR_STATUS {Current, Discontinued}

    enum VENDOR_TYPE {Service, Shop, Distributor}

    ObjectMapper mapper = new ObjectMapper();
}
