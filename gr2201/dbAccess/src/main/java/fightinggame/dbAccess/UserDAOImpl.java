package fightinggame.dbAccess;

import java.util.List;

public class UserDAOImpl implements UserDAO{

	private List<E> Users = new List<E>();
	private Path path;

	public UserDAOImpl(){
		// TODO: make construtor
	}

	public UserDAOImpl(Path p){
		// TODO: make construtor that points to path to save, edit data.
		// this.path
	}

	/**
	 * getAllUsers will get all the users form data storage.
	 * @return
	 */
	public List<String> getAllUsers() {
		// TODO: 
	}
	public List<String> filterAllUsers(String filterParam) {
		// TODO:
	}
	/**
	 * Finds the {@code User} that have same id in data storage.
	 * If their are no user with this id return a null object.
	 * @param id {@code UserId}  that it uses to search data storage
	 * @return the {@code User} with given id in case there are non return null
	 */
	public String findUser(UserId id) {
		// TODO:
	}

	public void updateUser(UserId id, UserData data) {
		// TODO:
	}
	public void deleteUser(UserId id) {
		// TODO:
	}
	public void addUser(String name, UserData data) {
		// TODO:
	}

	public void setPath(Path p) {
		// better of being private?
		this.path = p;
	}
	
}
