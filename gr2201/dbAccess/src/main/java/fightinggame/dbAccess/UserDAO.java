package fightinggame.dbAccess;


/**
 * UserDAO is following the Database Access Object partern.
 * The interface shall make it possible to interact with the data storage system without knowing the implementation (Information hiding).
 */
public interface UserDAO {
	public List<String> getAllUsers(); 
	public List<String> filterAllUsers(String filterParam); 
	public String findUser(UserId id); 
	public void updateUser(UserId id, UserData data); 
	public void deleteUser(UserId id); 
	public void addUser(UserData data); 
}
