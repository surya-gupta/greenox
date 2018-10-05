package com.greenox.order.util;

public class Constants {
    public enum ORDER_SOURCE {Kiosk, Zomato, Swiggy, FoodPanda}

    public enum PAYMENT_MODE {Cash, Paytm, PhonePe, GPay, Mobikwik, FreeCharge}

    public enum ORDER_STATUS {NEW, CANCELLED, REJECTED, MODIFIED , COMPLETED}
}
