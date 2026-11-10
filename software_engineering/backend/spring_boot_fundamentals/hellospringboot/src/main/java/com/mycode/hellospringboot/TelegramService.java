package com.mycode.hellospringboot;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("telegram")
public class TelegramService implements NotificationService{
    @Override
    public void send(String title) {
        System.out.println("Sending Telegram notification");
        System.out.println(title);
    }
}
