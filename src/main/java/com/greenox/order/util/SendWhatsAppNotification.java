package com.greenox.order.util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendWhatsAppNotification implements InitializingBean {
    private static final Logger LOG = LoggerFactory.getLogger(SendWhatsAppNotification.class);
    @Value("${ACCOUNT_SID}")
    private String accountSid;
    @Value("${AUTH_TOKEN}")
    private String authToken;

    @Override
    public void afterPropertiesSet() {
        Twilio.init(accountSid, authToken);
    }

    public String sendNotification(Long phoneNumber, String text) {
        try {
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("whatsapp:+91" + phoneNumber),
                    new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                    text)
                    .create();
            return message.getSid();
        } catch (Exception exception) {
            LOG.error("Exception", exception);
            return null;
        }
    }
}
