package fightinggame.users;

/**
 * UserData is a container of all data about a user such as password.
 * Future iterations might also include MMR, unlocked characters,
 * achievements, key controls, etc.
 */
public class UserData {
    private String password;

    /**
     * Contructs UserData but will throw exception on bad input.
     * 
     * @param password the password stored in the new {@code UserData}
     * @throws IllegalArgumentException on bad input such as no password
     */
    public UserData(String password) throws IllegalArgumentException {
        validUserData(password);
        this.password = password;
    }

    /**
     * Validate user data and throw exception if data is not valid
     *
     * @param password the password to use for the {@code UserData}
     * @throws IllegalArgumentException if user data is invalid, meaning that it is empty
     *                                  or contains characters that are not alphaNumeric
     */
    private static void validUserData(String password) throws IllegalArgumentException {
        // Shall detect bad input
        boolean isEmpty = password.isEmpty();
        boolean notOnlyContainsLettersAndNumbers = !password.matches("[a-zA-Z0-9]*");
        if (isEmpty || notOnlyContainsLettersAndNumbers)
            throw new IllegalArgumentException("Invalid password, cannot have empty password or password with characters that are not alphaNumeric.");
    }

    /**
     * Checks if this userData has the same fields as other UserData
     * 
     * @param u UserData to compare
     * @return true if they are equal
     */
    public boolean equals(UserData u) {
        return this.getPassword().equals(u.getPassword());
    }

    /**
     * Returns password
     *
     * @return password stored in the new {@code UserData}
     */
    public String getPassword() {
        return password;
    }

    /**
     * Validates and changes user password.
     * 
     * @param password
     * @throws IllegalArgumentException if password user is trying to change to
     *                                  is invalid 
     */
    public void changePassword(final String password) throws IllegalArgumentException {
        validUserData(password);
        this.password = password;
    }
}
