package roon.study.unittesting.ch5;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public interface MailService {
    void send(Message message);
    int numberSent();

    class Message {
        String message;
    }
}

@Component
class MailServiceStub implements MailService {
    private List<Message> messages = new ArrayList<>();

    @Override
    public void send(Message message) {
        messages.add(message);
    }

    public int numberSent() {
        return messages.size();
    }
}
