package DTO;

/**Class describes user, contains constructors,set and get methods
 *  for all fields of class and overring method  toString*/

public class User extends Entity {
	private int userId;
	private String name;
	private String surname;
	private String login;
	private String password;
	private boolean access;

	public User() {

	}

	public User(int userId, String name, String surname, String login, String password, boolean access) {
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.access = access;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAccess() {
		return access;
	}

	public void setAccess(boolean access) {
		this.access = access;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", surname=" + surname + ", login=" + login + ", password="
				+ password + ", access=" + access + "]";
	}

}
