package fightinggame.dbaccess;

import fightinggame.users.UserData;
import fightinggame.users.UserId;

import java.io.FileNotFoundException;
import java.util.List;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class UserDAOImpl implements UserDAO{
	private String path;

	public UserDAOImpl(){
		// this.path = Paths.get("gr2201/gr2201/core/src/main/resource/fightinggame/dbaccess/users.txt");
		// this.path = "src/main/resources/fightinggame//dbaccess//users.txt";
		
		this.path = "gr2201/core/src/main/resources/fightinggame/dbaccess/";
				 //  gr2201/core/src/main/resources/fightinggame/dbaccess/users.txt
		// this.path = "gr2201/core/src/test/recources/fightinggame/dbaccess/";
					
	}

	public UserDAOImpl(String p){
		// TODO: make construtor that points to path to save, edit data.
		this.path = p;
	}

	public List<String> getAllUsers() {
		try {
			return readFromFile(this.getPath());
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound at " + this.getPath());
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
			storeToFile(this.getPath(), userId.toString() + ", " + data.toString() + "\n", false);
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

		// ClassLoader classLoader = UserDAOImpl.class.getClassLoader();
		// File userFile = new File(classLoader.getResource(filename + "users.txt").getFile());

		File userFile = new File(filename + "users.txt");
		if (userFile.exists()){
			Scanner userFileReader = new Scanner(userFile);

			while(userFileReader.hasNextLine()) {
				String line = userFileReader.nextLine();
				usersInfo.add(line);
				// System.out.println(line);
				// for (String dataFromFile : line.split(", ")){
					// usersInfo.add(dataFromFile);
				// }
			}
			userFileReader.close();
		}
		else throw new FileNotFoundException("The settings file could not be found.");
		return usersInfo;
	}

	private static void storeToFile(String filename, String data, Boolean shallOverwrite) throws IOException{
		// File currentFile = new File("gr2201/gr2201/core/src/main/resources/fightinggame/dbaccess/" + file);
		String file = filename + "users.txt";
		File currentFile = new File(file);

		// if (currentFile.createNewFile()) System.out.println("Created new file");
		FileWriter currentWriter = new FileWriter(currentFile, !shallOverwrite);
		String headLine = "id, password";
		
		if (shallOverwrite){
			currentWriter.write(headLine + "\n" + data);
		}
		else {
			if (currentFile.createNewFile()){
				currentWriter.write(headLine);
			}
			currentWriter.append(data);
		}
		currentWriter.close();
	}


	// public static void main(String[] args) {
	// 	UserDAO dao = new UserDAOImpl();
	// 	dao.addUser(new UserId("userId1"), new UserData("data1"));
	// 	dao.addUser(new UserId("userId1"), new UserData("data1"));
	// 	dao.addUser(new UserId("userId3"), new UserData("data1"));

		
	// 	// System.out.println(dao.findUser(new UserId("Sverre1")));
	// 	System.out.println(dao.findUser(new UserId("userId3")));

	// }
}
