package DAO;

import java.util.List;
import DTO.Subject;
import DTO.User;

/**Interface contains methods for access to table User*/

public interface UserDAO extends AbstractDAO<User> {

	public List<Subject> findUserSubject(int userId);

	public User findUserByLogin(String login);
}
