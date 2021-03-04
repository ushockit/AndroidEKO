package com.example.myapplication.data;

import com.example.myapplication.business.exceptions.UserNotFoundException;
import com.example.myapplication.data.model.LoggedInUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private static List<LoggedInUser> users = new ArrayList<>();

    static {
        users.add(new LoggedInUser(
                java.util.UUID.randomUUID().toString(),
                "Jane Doe"));
        users.add(new LoggedInUser(
                java.util.UUID.randomUUID().toString(),
                "Alex Silov"));
        users.add(new LoggedInUser(
                java.util.UUID.randomUUID().toString(),
                "Jane Moli"));
    }

    public Result<LoggedInUser> login(String username, String password) {

        try {
//            // TODO: handle loggedInUser authentication
//            LoggedInUser fakeUser =
//                    new LoggedInUser(
//                            java.util.UUID.randomUUID().toString(),
//                            "Jane Doe");
            LoggedInUser user = searchUserByName(username);
            return new Result.Success<>(user);
        } catch (UserNotFoundException e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public LoggedInUser searchUserByName(String name) throws UserNotFoundException {
        LoggedInUser user = users.stream().filter(u -> u.getDisplayName().equals(name)).findFirst().orElse(null);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    public void logout() {
        // TODO: revoke authentication
    }
}