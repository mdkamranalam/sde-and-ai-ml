package com.mycode.hellospringboot;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("whatsapp")
public class WhatsAppService implements NotificationService{
    @Override
    public void send(String title) {
        System.out.println("Sending WhatsApp notification");
        System.out.println(title);
    }
}
