package com.machines.vending.domain.models;

import com.machines.vending.domain.exceptions.InvalidPasswordException;
import com.machines.vending.domain.exceptions.InvalidUsernameException;
import lombok.Builder;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
@Getter
public class User extends Model {
    private Integer id;
    private String username;
    private String password;
    private String role;

    public static void validate(final User user) throws InvalidUsernameException, InvalidPasswordException {
        validateUsername(user.getUsername());
        validatePassword(user.getPassword());
    }

    private static void validateUsername(final String username) throws InvalidUsernameException {
        final String regex = "^(?=.*[A-Za-z0-9_]).{6,12}$";
        if (isNotValid(username, regex)) {
            throw new InvalidUsernameException();
        }
    }


    private static void validatePassword(final String password) throws InvalidPasswordException {
        final String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@%#$-_]).{8,20}$";
        if (isNotValid(password, regex)) {
            throw new InvalidPasswordException();
        }
    }

    private static boolean isNotValid(final String stringToCheck,
                                      final String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringToCheck);
        return !matcher.matches();
    }
}
