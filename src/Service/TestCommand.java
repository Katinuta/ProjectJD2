package Service;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAOImpl.ConnectorDB;
import DAOImpl.SubjectDAOImpl;
import DAOImpl.TestDAOImpl;
import DTO.Subject;

/** Class contains command for getting list of test by choose subject */

public class TestCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		HttpSession session = request.getSession();
		String number = request.getParameter("but");
		int id = Integer.valueOf(number); // get id choose subject
		List<DTO.Test> list = new ArrayList<>();
		ConnectorDB connectorDB = null;
		Connection cn = null;
		try {
			connectorDB = ConnectorDB.getInstance();
			try {
				cn = connectorDB.getConnection();
				TestDAOImpl testDAO = new TestDAOImpl(cn);
				SubjectDAOImpl subjectDAO = new SubjectDAOImpl(cn);
				Subject subject = subjectDAO.findSubjectById(id);
				session.setAttribute("subject", subject);
				list = testDAO.findTestBySubjectId(id);
				//set in request list of tests by subject
				request.setAttribute("testList", list); 
				page = "jsp/test.jsp";
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
