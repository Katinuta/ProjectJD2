package Service;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAOImpl.ConnectorDB;
import DAOImpl.ResultDAOImpl;
import DTO.Result;
import DTO.Test;
import DTO.User;

/**Class contains connamd for save result of the test*/

public class SaveResultCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		int result = Integer.parseInt(request.getParameter("countRightQuestion"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Test test = (Test) session.getAttribute("test");
		ConnectorDB connectorDB = null;
		Connection cn = null;
		try {
			connectorDB = ConnectorDB.getInstance();
			try {
				cn = connectorDB.getConnection();
				Result res = new Result(test.getTestId(), user.getUserId(), result);
				ResultDAOImpl resultDAO = new ResultDAOImpl(cn);
				resultDAO.create(res);
				session.removeAttribute("test");
				session.removeAttribute("subject");
				page = "jsp/main.jsp";
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
