package fightinggame.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fightinggame.users.User;

public class RemoteModelAccessTest {
    private RemoteModelAccess remoteModelAccess = new RemoteModelAccess();

    @Test
    @DisplayName("Test if one can make a user, change its passwords, and then delete it")
    private void putAndDeleteUserTest(){
        User user = remoteModelAccess.postUser("test", "test", "test");
        assertNotNull(user, "Cant create a new user");
        user.getUserData().changePassword("test2");
        assertTrue(remoteModelAccess.putUser(user), "Cant change users password");
        assertTrue(remoteModelAccess.deleteUser(user), "Cant delete user");
        assertFalse(remoteModelAccess.deleteUser(user), "Shouldnt be able to delete nonexisting user");
    }
}
