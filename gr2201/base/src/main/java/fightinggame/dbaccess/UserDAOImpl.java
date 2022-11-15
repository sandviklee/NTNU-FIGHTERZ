package fightinggame.dbaccess;

import fightinggame.json.UserModule;
import fightinggame.users.User;
import fightinggame.users.UserData;
import fightinggame.users.UserId;

import java.io.FileNotFoundException;
import java.util.List;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.function.Predicate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;


public class UserDAOImpl implements UserDAO {
	private String path;
	private ObjectMapper mapper = new ObjectMapper();

	public UserDAOImpl(){
		this.path = "../../base/src/main/resources/fightinggame/dbaccess/";
		mapper.registerModule(new UserModule());
	}

	public UserDAOImpl(String path){
		this.path = path;
		mapper.registerModule(new UserModule());
	}

	@Override
	public ArrayList<User> getAllUsers() {
		try {
			String fileJson = readFromFile(this.getPath());
			return this.deserializerUsers(fileJson);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound at " + this.getPath());
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return new ArrayList<User>();
	}
	
	@Override
	public User findUser(User targetUser) {
		for (User user : getAllUsers()) {
			if(user.equals(targetUser)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public boolean updateUser(UserId id, UserData data) {
		List<User> tempList = getAllUsers();

		for (int i = 0; i < tempList.size(); i++) {
			User user = tempList.get(i);

			if (user.getUserId().equals(id)) {
				tempList.set(i, new User(id, data));
				break;
			}
		}
		try {
			storeToFile(this.getPath(), userListToJson(tempList));
			return true;
			
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return false;
	}

	@Override
	public boolean deleteUser(UserId id) {
		List<User> changedList = getAllUsers();

		Predicate<User> deleteCondition = user -> user.getUserId().equals(id);
		changedList.removeIf(deleteCondition);
		try {
			storeToFile(this.getPath(), userListToJson(changedList));
			return true;
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return false;
	}

	@Override
	public boolean addUser(User user) {
		try {
			ArrayList<User> users = getAllUsers();
			if (users.stream().anyMatch((u) -> u.equals(user))) return false;

			users.add(user);
			storeToFile(this.getPath(), userListToJson(users));
			return true;
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return false;
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
	 * Convert Json to Users
	 * @param rawJson  to convert
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
	 * Will read string from file
	 * @param filename  of the file to read from
	 * @return the entire text file
	 * @throws IOException
	 */
	private static String readFromFile(String filename) throws IOException {		
		String usersInfo = "";
		File userFile = new File(filename + "users.json");
		if (userFile.exists()){
			Scanner userFileReader = new Scanner(userFile);

			while(userFileReader.hasNextLine()) {
				String line = userFileReader.nextLine();
				usersInfo += line;
			}
			userFileReader.close();
		}
		else throw new FileNotFoundException("The file could not be found.");
		return usersInfo;
	}


	/**
	 * Writes data to file and when no file exist create a new file.
	 * @param filename        to write file to
	 * @param data            to write to file
	 * @param shallOverwrite  if method shall overwrite
	 * @throws IOException
	 */
	private static void storeToFile(String filename, String data) throws IOException{
		String file = filename + "users.json";
		File currentFile = new File(file);

		FileWriter currentWriter = new FileWriter(currentFile);
		currentFile.createNewFile();
		currentWriter.write("[" + data + "]");
		currentWriter.close();
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}
}
