package roon.study.unittesting.ch7;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserStorage {
    private static Map<Integer, User> storage = new HashMap<>();

    public void save(int userId, User user) {
        storage.put(userId, user);
    }

    public User getUserById(int userId) {
        return storage.get(userId);
    }
}
