package com.greenox.pos.util;

public class Constants {
    public enum ORDER_SOURCE {Kiosk, Zomato, Swiggy, FoodPanda}

    public enum PAYMENT_MODE {Cash, Paytm, PhonePe, GPay, Mobikwik, FreeCharge}

    public enum ORDER_STATUS {NEW, CANCELLED, REJECTED, MODIFIED , COMPLETED}

    public enum INVENTORY_STATUS {NEW, CANCELLED, INCOMPLETE, COMPLETED}

    public enum VENDOR_STATUS {Current, Discontinued}

    public enum VENDOR_TYPE {Service, Shop, Distributor}
}
