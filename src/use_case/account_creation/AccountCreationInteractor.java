package use_case.account_creation;

import use_case.account_creation.AccountCreationUserDataAccessInterface;
import entity.User;
import entity.UserFactory;
import use_case.add_friends.AddFriendsInputBoundary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ArrayList;

/**
 * Interactor class that handles the process of account creation.
 * It implements the {@link AccountCreationInputBoundary} interface and coordinates
 * the interaction between the input data, user data access, and output boundaries.
 */
public class AccountCreationInteractor implements AccountCreationInputBoundary {
    final AccountCreationUserDataAccessInterface accountDataAccessObject;
    final AccountCreationOutputBoundary accountPresenter;
    final UserFactory userFactory;

    /**
     * Constructs an {@code AccountCreationInteractor} with the specified data access object,
     * output boundary, and user factory.
     *
     * @param accountDataAccessObject The data access object to interact with user data.
     * @param accountPresenter        The output boundary to present the result of account creation.
     * @param userFactory             The factory to create new user instances.
     */

    public AccountCreationInteractor(AccountCreationUserDataAccessInterface accountDataAccessObject,
                                     AccountCreationOutputBoundary accountPresenter,
                                     UserFactory userFactory) {
        this.accountDataAccessObject = accountDataAccessObject;
        this.accountPresenter = accountPresenter;
        this.userFactory = userFactory;
    }

    /**
     * Executes the process of creating a user account based on the provided input data.
     * <p>
     * This method performs the following checks:
     * <ul>
     *     <li>Checks if an account with the provided username already exists.</li>
     *     <li>Checks if the provided passwords match.</li>
     *     <li>Checks if all required fields are filled.</li>
     * </ul>
     * If all checks pass, it creates a new {@link User} object and saves it using the data access object.
     * It then sets a success view through the output boundary.
     * If any checks fail, it sets a failure view with an appropriate message.
     *
     * @param inputData The data required to create an account, including username, password, program of study, interests, etc.
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

