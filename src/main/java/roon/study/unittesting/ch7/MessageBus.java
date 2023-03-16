package roon.study.unittesting.ch7;

import org.springframework.stereotype.Component;

@Component
public interface MessageBus {
    static void sendEmailChangedCommand(int userId, String email) {
        System.out.println("sent message");
    }
}
