package roon.study.unittesting.ch7;

public class UserFactory {
    public static User newInstance(int id, String email, UserType type) {
        return new User(id, email, type);
    }
}
