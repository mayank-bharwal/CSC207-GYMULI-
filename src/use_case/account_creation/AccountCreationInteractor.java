package use_case.account_creation;

import use_case.account_creation.AccountCreationUserDataAccessInterface;
import entity.User;
import entity.UserFactory;
import java.time.LocalDateTime;
import java.util.Collections;

public class AccountCreationInteractor implements AccountCreationInputBoundary {
    final AccountCreationUserDataAccessInterface accountDataAccessObject;
    final AccountCreationOutputBoundary accountPresenter;
    final UserFactory userFactory;

    public AccountCreationInteractor(AccountCreationUserDataAccessInterface accountDataAccessObject,
                                     AccountCreationOutputBoundary accountPresenter,
                                     UserFactory userFactory) {
        this.accountDataAccessObject = accountDataAccessObject;
        this.accountPresenter = accountPresenter;
        this.userFactory = userFactory;
    }


    @Override
    public void execute(AccountCreationInputData inputData) {
        System.out.println("AccountCreationInteractor: execute called with username: " + inputData.getUsername());

        System.out.println("Username: " + inputData.getUsername());
        System.out.println("Password: " + inputData.getPassword());
        System.out.println("Repeat Password: " + inputData.getRepeatPassword());
        System.out.println("Program of Study: " + inputData.getProgramOfStudy());
        System.out.println("Interests: " + inputData.getInterests());
        System.out.println("Bio: " + inputData.getBio());
        System.out.println("Age: " + inputData.getAge());

        if (accountDataAccessObject.AccountExists(inputData.getUsername())) {
            System.out.println("AccountCreationInteractor: Account already exists");
            accountPresenter.setFailView("Account already exists");
        } else if (!inputData.getPassword().equals(inputData.getRepeatPassword())) {
            System.out.println("AccountCreationInteractor: Passwords are not equal");
            accountPresenter.setFailView("Passwords are not equal");
        } else if (inputData.getUsername().equals("")
                || inputData.getPassword().equals("")
                || inputData.getRepeatPassword().equals("")
                || inputData.getProgramOfStudy().equals("")
                || inputData.getInterests().isEmpty()) {
            System.out.println("AccountCreationInteractor: Please fill in all fields");
            accountPresenter.setFailView("Please fill in all fields");
        } else {
            LocalDateTime date = LocalDateTime.now();
            User user = userFactory.createUser(inputData.getUsername(), inputData.getPassword(),
                    inputData.getBio(), inputData.getAge(), inputData.getProgramOfStudy(), inputData.getInterests(), Collections.emptyList(),
                    date);
            System.out.println("AccountCreationInteractor: Saving user");
            accountDataAccessObject.save(user);
            AccountCreationOutputData outputData = new AccountCreationOutputData(user.getUsername(), date.toString(), false);
            accountPresenter.setPassView(outputData);
        }
    }

    @Override
    public void change(AccountCreationInputData inputData) {

    }



}

