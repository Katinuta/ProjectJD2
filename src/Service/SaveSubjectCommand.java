package Service;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

import DAOImpl.ConnectorDB;
import DAOImpl.SubjectDAOImpl;
import DTO.Subject;

/** Class contatins for creating and save new subject */

public class SaveSubjectCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String name = request.getParameter("subject");
		ConnectorDB connectorDB = null;
		Connection cn = null;
		try {
			connectorDB = ConnectorDB.getInstance();
			try {
				cn = connectorDB.getConnection();
				Subject subject = new Subject();
				SubjectDAOImpl subjectDAO = new SubjectDAOImpl(cn);
				subject.setName(name);
				subjectDAO.create(subject);
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
