package fightinggame.dbaccess;

import fightinggame.users.UserData;
import fightinggame.users.UserId;

import java.io.FileNotFoundException;
import java.util.List;
import java.nio.file.Path;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class UserDAOImpl implements UserDAO{
	private String path;

	public UserDAOImpl(){
		// TODO: make construtor
		// this.path = Paths.get("gr2201/gr2201/core/src/main/resource/fightinggame/dbaccess/users.txt");
		// this.path = "src/main/resources/fightinggame//dbaccess//users.txt";
		this.path = "users";

	}

	public UserDAOImpl(String p){
		// TODO: make construtor that points to path to save, edit data.
		this.path = p;
	}

	public List<String> getAllUsers() {
		try {
			return readFromFile(this.getPath());
		} catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return new ArrayList();
	}

	public List<String> filterAllUsers(String filterParam) {
		List<String> correnspondingUsers = new ArrayList();
		for (String userInfo : getAllUsers()) {
			if(userInfo.contains(filterParam)) {
				correnspondingUsers.add(userInfo);
			}
		}
		return correnspondingUsers;
	}
	
	public String findUser(UserId id) {
		for (String userInfo : getAllUsers()) {
			if(userInfo.split(", ")[0].equals(id.getUserId())) {
				return userInfo;
			}
		}
		return new String();
	}

	public void updateUser(UserId id, UserData data) {
		List<String> tempList = getAllUsers();
		for (int i = 0; i < tempList.size(); i++) {
			String userInfo = tempList.get(i);
			if(userInfo.split(", ")[0].equals(id.getUserId())){
				tempList.set(i, id.toString() + ", " + data.toString());
				break;
			}
		}
		try {
			storeToFile(this.getPath(), String.join("\n", tempList), true);
			
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());

		}
	}

	public void deleteUser(UserId id) {
		List<String> tempList = getAllUsers();
		for (int i = 0; i < tempList.size(); i++) {
			String userInfo = tempList.get(i);
			if(userInfo.split(", ")[0].equals(id.getUserId())){
				tempList.remove(userInfo);
				break;
			}
		}
		try {
			storeToFile(this.getPath(), String.join("\n", tempList), true);
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	public void addUser(UserId userId, UserData data) {
		try {
			storeToFile(this.getPath(), userId.toString() + ", " + data.toString(), false);
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	public void setPath(String p) {
		this.path = p;
	}
	public String getPath() {
		return this.path;
	}

	private static List<String> readFromFile(String filename) throws FileNotFoundException {		
		List<String> usersInfo = new ArrayList<>();

		ClassLoader classLoader = UserDAOImpl.class.getClassLoader();
		File userFile = new File(classLoader.getResource(filename + ".txt").getFile());

		// File userFile = new File(filename);
		if (userFile.exists()){
			Scanner userFileReader = new Scanner(userFile);

			while(userFileReader.hasNextLine()) {
				String line = userFileReader.nextLine();
				for (String dataFromFile : line.split(", ")){
					usersInfo.add(dataFromFile);
				}
			}
			userFileReader.close();
		}
		else throw new FileNotFoundException("The settings file could not be found.");
		return usersInfo;
	}

	private static void storeToFile(String filename, String data, Boolean shallOverwrite) throws IOException{
		// File currentFile = new File(filename);

		// File currentFile = new File(classLoader.getResource("users.txt").getFile());

		// File currentFile = new File(filename + ".txt");
		// FileWriter currentWriter = new FileWriter(currentFile, !shallOverwrite);
		File currentFile;
		try {
			ClassLoader classLoader = UserDAO.class.getClassLoader();
			currentFile = new File(classLoader.getResource(filename + ".txt").toURI());
			
		} catch (Exception e) {
			currentFile = new File("");
			// TODO: handle exception
		}
		// currentFile.createNewFile();
		// if (file.createNewFile()) 
		
		FileWriter currentWriter = new FileWriter(currentFile, !shallOverwrite);
		String headLine = "id, password";
		
		if (shallOverwrite){
			currentWriter.write(headLine + "\n" + data);
		}
		else {
			if (!currentFile.exists()){
				currentWriter.write(headLine);
			}
			currentWriter.append(data);
		}
		currentWriter.close();
	}


	public static void main(String[] args) {
		UserDAO dao = new UserDAOImpl();
		dao.addUser(new UserId("userId1"), new UserData("data1"));
		dao.addUser(new UserId("userId1"), new UserData("data1"));

	}
}
