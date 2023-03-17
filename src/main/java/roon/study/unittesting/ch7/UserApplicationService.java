package roon.study.unittesting.ch7;

import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {
    private UserStorage userStorage;
    private CompanyStorage companyStorage;
    private MessageBus messageBus;

    public UserApplicationService(UserStorage userStorage, CompanyStorage companyStorage, MessageBus messageBus) {
        this.userStorage = userStorage;
        this.companyStorage = companyStorage;
        this.messageBus = messageBus;
    }

    public void changeEmail(int userId, String newEmail) {
        // get user
        // get company
        // user.changeEmail()
        // save company
        // save user
        // send message

        User user = userStorage.getUserById(userId);
        String sampleCompanyName = "naver";
        Company company = companyStorage.getCompanyByName(sampleCompanyName);

        user.changeEmail(newEmail, company);

        companyStorage.save(sampleCompanyName, company);
        userStorage.save(userId, user);

        messageBus.sendEmailChangedCommand(userId, newEmail);
    }
}
