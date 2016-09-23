package Service;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAOImpl.ConnectorDB;
import DAOImpl.UserDAOImpl;
import DTO.User;

/** Class describe command of authentication user */

public class LoginCommand implements ActionCommand {
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String password = request.getParameter(PARAM_NAME_PASSWORD);
		User user = new User();
		ConnectorDB cnDB = null;
		Connection cn = null;
		try {
			cnDB = ConnectorDB.getInstance();
			try {
				cn = cnDB.getConnection();
				UserDAOImpl userDAO = new UserDAOImpl(cn);
				user = userDAO.findUserByLogin(login);
				if (LoginLogic.checkLogin(user, login, password)) {
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					page = "jsp/main.jsp";
				} else {
					request.setAttribute("errorLoginMessage", "Incorrect login or password");
					page = "jsp/login.jsp";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (PropertyVetoException e) {
			e.printStackTrace();
		} finally {
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return page;
	}

}
