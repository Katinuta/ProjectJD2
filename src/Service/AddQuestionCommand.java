package Service;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAOImpl.ConnectorDB;
import DAOImpl.TestDAOImpl;
import DTO.Subject;
import DTO.Test;

/**
 * Class contains command for transition to create new question with answers for
 * new or old test
 */

public class AddQuestionCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		HttpSession session = request.getSession();
		Enumeration<String> attrSession = session.getAttributeNames();
		Boolean hasEl = false;
		// check if session has attribute "test"
		if (attrSession.hasMoreElements()) {
			while (attrSession.hasMoreElements()) {
				String attr = attrSession.nextElement();
				if (attr.equals("test")) {
					hasEl = true;
					break;
				}
			}
		}
		// actions if create new test
		if (!hasEl) {
			Test test = new Test();
			String nameTest = request.getParameter("nametest");
			System.out.println(nameTest);
			test.setName(nameTest);
			Subject subject = (Subject) session.getAttribute("subject");
			test.setSubjectId(subject.getSubjectId());
			ConnectorDB connectorDB = null;
			Connection cn = null;
			try {
				connectorDB = ConnectorDB.getInstance();
				try {
					cn = connectorDB.getConnection();
					TestDAOImpl testDAO = new TestDAOImpl(cn);
					testDAO.create(test);
					test = testDAO.findTestBySubjectIdAndName(subject.getSubjectId(), nameTest);
					session.setAttribute("test", test);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
		page = "jsp/question.jsp";
		return page;
	}

}
