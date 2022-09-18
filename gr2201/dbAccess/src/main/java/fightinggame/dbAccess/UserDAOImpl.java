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

	public List<String> getAllUsers() {
		// TODO: 
	}
	public List<String> filterAllUsers(String filterParam) {
		// TODO:
	}
	
	public String findUser(UserId id) {
		// TODO:
	}

	public void updateUser(UserId id, UserData data) {
		// TODO:
	}
	public void deleteUser(UserId id) {
		// TODO:
	}
	public void addUser(UserId userId, UserData data) {
		// TODO:
	}

	public void setPath(Path p) {
		this.path = p;
	}
	
}
