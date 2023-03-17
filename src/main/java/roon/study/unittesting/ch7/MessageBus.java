package roon.study.unittesting.ch7;

import org.springframework.stereotype.Component;

@Component
public interface MessageBus {
    void sendEmailChangedCommand(int userId, String email);
}
