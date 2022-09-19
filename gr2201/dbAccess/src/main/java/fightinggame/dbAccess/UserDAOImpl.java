package fightinggame.dbAccess;

import java.io.FileNotFoundException;
import java.util.List;

public class UserDAOImpl implements UserDAO{
	private Path path;

	public UserDAOImpl(){
		// TODO: make construtor
		this.path = new Path("gr2201/gr2201/dbAccess/src/main/resource/fightinggame/dbAccess/users.txt");
	}

	public UserDAOImpl(Path p){
		// TODO: make construtor that points to path to save, edit data.
		this.path = p;
	}

	public List<String> getAllUsers() {
		try {
			return readFromFile(this.getPath());
		} catch (FileNotFoundException e) {
			System.err.println(e.getLocalizedMessage());
		}
		return new List();
	}
	public List<String> filterAllUsers(String filterParam) {
		List<String> correnspondingUsers = new List();
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
		for (int i = 0; i < tempList.length; i++) {
			String userInfo = tempList.get(i);
			if(userInfo.split(", ")[0].equals(id.getUserId())){
				tempList.set(i, id.toString() + ", " + data.toString());
				break;
			}
		}
		storeToFile(this.getPath(), String.join("\n", tempList), true);

	}

	public void deleteUser(UserId id) {
		// TODO:
		List<String> tempList = getAllUsers();
		for (int i = 0; i < tempList.length; i++) {
			String userInfo = tempList.get(i);
			if(userInfo.split(", ")[0].equals(id.getUserId())){
				tempList.remove(userInfo);
				break;
			}
		}
		storeToFile(this.getPath(), String.join("\n", tempList), true);


	}

	public void addUser(UserId userId, UserData data) {
		try {
			storeToFile(this.getPath(), userId.toString() + ", " + data.toString(), false);
		} catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	public void setPath(Path p) {
		this.path = p;
	}
	public Path getPath() {
		return this.path;
	}

	


	private static List<String> readFromFile(Path filename) throws FileNotFoundException {		
		List<String> users = new ArrayList<>();
		
		File userFile = new File(filename);
		if (userFile.exists()){
			Scanner userFileReader = new Scanner(userFile);

			while(userFileReader.hasNextLine()) {
				String line = userFileReader.nextLine();
				for (String dataFromFile : line.split(", ")){
					users.add(dataFromFile);
				}
			}
			userFileReader.close();
		}
		else throw new FileNotFoundException("The settings file could not be found.");
		return users;
	}

	private static void storeToFile(Path filename, String data, Boolean shallOverwrite) throws IOException{
		File currentFile = new File(filename);
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
}
