package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.UserDAO;
import DTO.Subject;
import DTO.User;

/**
 * Class implements methods of interface UserDAO, ovveride this, contains
 * constructor
 */

public class UserDAOImpl implements UserDAO {

	private final static String SQL_SELECT_USERS = "SELECT * FROM user";
	private final static String SQL_SELECT_USERS_SUBJECTS = "SELECT subject.name , subject.subject_id FROM student_to_subject "
			+ "JOIN subject USING (subject_id) WHERE student_to_subject.user_id=?";
	private final static String SQL_INSERT_USER = "INSERT INTO user(name, surname,login, password, access) VALUES(?,?,?,?,?)";
	private final static String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE user.login=?";
	private Connection connection;

	public UserDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SQL_SELECT_USERS);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setUserId(resultSet.getInt("user_id"));
				user.setName(resultSet.getString("name"));
				user.setSurname(resultSet.getString("surname"));
				list.add(user);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(ps);
		}
		return list;

	}

	public boolean delete(int id) {
		return false;
	}

	public boolean create(User entity) {
		boolean flag = false;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SQL_INSERT_USER);
			ps.setString(1, entity.getName());
			ps.setString(2, entity.getSurname());
			ps.setString(3, entity.getLogin());
			ps.setString(4, entity.getPassword());
			ps.setBoolean(5, entity.isAccess());
			ps.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Statement не создан");
		} finally {
			close(ps);
		}
		return flag;
	}

	public User update(User entity) {
		return null;
	}

	public List<Subject> findUserSubject(int userId) {
		List<Subject> list = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SQL_SELECT_USERS_SUBJECTS);
			ps.setInt(1, userId);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Subject subject = new Subject();
				subject.setSubjectId(resultSet.getInt("subject_id"));
				subject.setName(resultSet.getString("name"));
				list.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Statement не создан");
		} finally {
			close(ps);
		}
		return list;
	}

	public User findUserByLogin(String login) {
		User user = new User();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
			ps.setString(1, login);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				user.setUserId(resultSet.getInt("user_id"));
				user.setName(resultSet.getString("name"));
				user.setSurname(resultSet.getString("surname"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setAccess(resultSet.getBoolean("access"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Statement не создан");
		} finally {
			close(ps);
		}
		return user;
	}

	public void close(PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
