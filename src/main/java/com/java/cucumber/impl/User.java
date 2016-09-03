package com.java.cucumber.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * Created by mgupta on 9/3/16.
 */
public class User {

    private String userName;
    private String password;

    private User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    private static final Logger logger = Logger.getLogger(User.class);

    public static User create(String userName, String password) {
        boolean validate = PasswordValidator.validate(password);
        if (validate) {
            logger.info("Successfully created new user.");
            return new User(userName, password);
        }
        return null;
    }

    private static class PasswordValidator {

        private static Pattern p = Pattern.compile("(([A-Z].*[0-9])|([0-9].*[A-Z]))");

        private static boolean validate(String password) {
            if (password.length() < 4) {
                logger.error("Couldn't create user. Supplied password must be 4 characters long.");
                return false;
            }
            Matcher m = p.matcher(password);
            if (!m.find()) {
                logger.error("Couldn't create user. Password must be a combination of characters and numbers.");
                return false;
            }
            return true;
        }

    }

}
