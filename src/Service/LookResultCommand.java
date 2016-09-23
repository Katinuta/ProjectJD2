package Service;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAOImpl.ConnectorDB;
import DAOImpl.ResultDAOImpl;
import DTO.Result;
import DTO.User;

/**Class contains command for show result of the test for student*/

public class LookResultCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int testId = Integer.parseInt(request.getParameter("testId"));
		ConnectorDB connectorDB = null;
		Connection cn = null;
		try {
			connectorDB = ConnectorDB.getInstance();
			try {
				cn = connectorDB.getConnection();
				Result result = new Result();
				ResultDAOImpl resultDAO = new ResultDAOImpl(cn);
				result = resultDAO.findResultByTestUser(testId, user.getUserId());
				request.setAttribute("result", result);
				page = "jsp/showresult.jsp";
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
