package fightinggame.dbaccess;

import fightinggame.users.*;

import java.util.ArrayList;

/**
 * UserDAO is following the Database Access Object partern.
 * The interface shall make it possible to interact with the data storage system without knowing the implementation (Information hiding).
 * It shall not make the user objects.
 */
public interface UserDAO {
	/**
	 * getAllUsers will get all the users form data storage, where each string in list have all info about a user.
	 * @return list of all users
	 */
	public ArrayList<User> getAllUsers(); 
	// /**
	//  * Searches data storage for each entry with filterParam and return all lines with given value.
	//  * @param filterParam  the value it searches after in each line
	//  * @return all lines containing filterParam
	//  */
	// public List<String> filterAllUsers(String filterParam); 
	/**
	 * Finds the {@code User} that have same id in data storage.
	 * If their are no user with this id return a null object.
	 * @param id  {@code UserId}  that it uses to search data storage
	 * @return the {@code User} with given id in case there are non return null
	 */
	public User findUser(User targetUser); 
	/**
	 * Update the user with this {@code id} and changes the info into given {@code data}
	 * If there are no user with userId in data storage do nothing.
	 * @param id    that is used for search
	 * @param data  to change to
	 */
	public void updateUser(UserId id, UserData data); 
	/**
	 * Deletes the user with given {@code id}.
	 * If there are no user with userId in data storage do nothing.
	 * @param id  that is used for search
	 */
	public void deleteUser(UserId id); 
	/**
	 * Adds user with this userId and data to data storage.
	 * If there already exist a user with same id do nothing.
	 * @param id is search value and added in new data storage entry
	 * @param data added to data storage entry
	 */
	public void addUser(User user); 
}
