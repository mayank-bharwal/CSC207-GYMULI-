package use_case.account_creation;

import use_case.account_creation.AccountCreationUserDataAccessInterface;
import entity.User;
import entity.UserFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class AccountCreationInteractor implements AccountCreationInputBoundary {
    final AccountCreationUserDataAccessInterface accountDataAccessObject;
    final AccountCreationOutputBoundary accountPresenter;
    final UserFactory userFactory;

    /**
     *
     * @param accountDataAccessObject -
     * @param accountPresenter -
     * @param userFactory -
     */

    public AccountCreationInteractor(AccountCreationUserDataAccessInterface accountDataAccessObject,
                                     AccountCreationOutputBoundary accountPresenter,
                                     UserFactory userFactory) {
        this.accountDataAccessObject = accountDataAccessObject;
        this.accountPresenter = accountPresenter;
        this.userFactory = userFactory;
    }

    /**
     * interactor for account creation
     * @param inputData -
     */
    @Override

    public void execute(AccountCreationInputData inputData) {

        if (accountDataAccessObject.AccountExists(inputData.getUsername())) {
            accountPresenter.setFailView("Account already exists");
        } else if (!inputData.getPassword().equals(inputData.getRepeatPassword())) {
            accountPresenter.setFailView("Passwords are not equal");
        } else if (inputData.getUsername().equals("")
                || inputData.getPassword().equals("")
                || inputData.getRepeatPassword().equals("")
                || inputData.getProgramOfStudy().equals("")
                || inputData.getInterests().isEmpty()) {
            accountPresenter.setFailView("Please fill in all fields");
        } else {
            LocalDateTime date = LocalDateTime.now();
            User user = userFactory.createUser(inputData.getUsername(), inputData.getPassword(),
                    inputData.getBio(), inputData.getAge(), inputData.getProgramOfStudy(), inputData.getInterests(),new ArrayList<String>(),
                    new ArrayList<String>(), date);
            accountDataAccessObject.save(user);
            AccountCreationOutputData outputData = new AccountCreationOutputData(user.getUsername(), date.toString(), false);
            accountPresenter.setPassView(outputData);
        }

    }


}

