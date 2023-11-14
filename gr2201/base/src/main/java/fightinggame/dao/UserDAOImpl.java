package fightinggame.dao;

import fightinggame.users.User;
import fightinggame.users.UserId;
import fightinggame.utils.ReadWriteToFile;
import fightinggame.utils.json.users.UserModule;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.util.ArrayList;

public class UserDAOImpl implements CRUDDAO<User, UserId> {
    private ObjectMapper mapper = new ObjectMapper();
    private ReadWriteToFile readWriteToFile = new ReadWriteToFile(true);
    private String fileName = "users.json";

    public UserDAOImpl() {
        mapper.registerModule(new UserModule());
    }

    private String userListToJson(List<User> userList) {
        List<String> tempList = new ArrayList<>();
        userList.forEach(user -> tempList.add(serializeUser(user)));
        String resJson = String.join(",\n", tempList);
        return resJson;
    }

    /**
     * Convert User to Json String.
     * If not possible will return empty string.
     * 
     * @param user to convert
     * @return the Json String
     */
    private String serializeUser(User user) {
        try {
            return mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            System.out.println("There was an error in serializing");
            return "";
        }
    }

    /**
     * Convert Json to POJO Users
     * 
     * @param rawJson to convert
     * @return the users made from json
     */
    private ArrayList<User> deserializerUsers(String rawJson) {
        ArrayList<User> res = new ArrayList<>();

        try {
            User[] users = mapper.readValue(rawJson, User[].class);
            for (User user : users) {
                res.add(user);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Changes the access path to file
     * 
     * @param path to file
     */
    public void setPath(String path) {
        this.readWriteToFile.setPath(path);
    }

    @Override
    public ArrayList<User> loadAll() {
        try {
            String fileJson = readWriteToFile.readFromFile(fileName);
			System.out.println(fileJson);
            return this.deserializerUsers(fileJson);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound at " + fileName);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return new ArrayList<User>();
    }

    @Override
    public User get(UserId targetUser) {
        for (User user : loadAll()) {
            if (user.getUserId().equals(targetUser)) {
                
				return user;
            }
        }
        return null;
    }

    @Override
    public boolean update(UserId id, User updateUser) {
        boolean isUpdated = false;
        if (updateUser == null) {
            return isUpdated;
        }
        List<User> tempList = loadAll();

        for (int i = 0; i < tempList.size(); i++) {
            User user = tempList.get(i);

            if (user.getUserId().equals(id)) {
                tempList.set(i, updateUser);
                isUpdated = true;
            }
        }
        try {
            readWriteToFile.storeToFile(fileName, userListToJson(tempList));
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return isUpdated;
    }

    @Override
    public boolean delete(UserId id) {
        List<User> changedList = loadAll();
        int numberOfUsers = changedList.size();

        Predicate<User> deleteCondition = user -> user.getUserId().equals(id);
        changedList.removeIf(deleteCondition);
        try {
            readWriteToFile.storeToFile(fileName, userListToJson(changedList));
            return changedList.size() < numberOfUsers;
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public boolean add(User user) {
        try {
            ArrayList<User> users = loadAll();
            if (users.stream().anyMatch((u) -> u.equals(user))) {
                return false;
            }

            users.add(user);
            readWriteToFile.storeToFile(fileName, userListToJson(users));
            return true;

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return false;
    }
}

