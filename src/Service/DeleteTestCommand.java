package Service;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAOImpl.AnswerDAOImpl;
import DAOImpl.ConnectorDB;
import DAOImpl.QuestionDAOImpl;
import DAOImpl.TestDAOImpl;
import DTO.Question;
import DTO.Subject;
import DTO.Test;

public class DeleteTestCommand implements ActionCommand{

	@Override
	public String execute(HttpServletRequest request) {
		String page=null;
		HttpSession session=request.getSession();
		int testId=Integer.parseInt(request.getParameter("button"));
		Subject subject=(Subject) session.getAttribute("subject");
		ConnectorDB connectorDB=null;
		Connection cn=null;
		try {
			connectorDB=ConnectorDB.getInstance();
			try {
				cn=connectorDB.getConnection();
				TestDAOImpl testDAO=new TestDAOImpl(cn);
				QuestionDAOImpl questionDAO=new QuestionDAOImpl(cn);
				AnswerDAOImpl answerDAO=new AnswerDAOImpl(cn);
				cn.setAutoCommit(false);
				List<Question> listQuestion=questionDAO.findTestQuestions(testId);
				Iterator<Question> it=listQuestion.iterator();
				while(it.hasNext()){
					Question question=it.next();
					answerDAO.delete(question.getQuestionId());
					questionDAO.delete(question.getQuestionId());
				}
				testDAO.delete(testId);
				cn.commit();
				List<Test> testList=testDAO.findTestBySubjectId(subject.getSubjectId());
				session.setAttribute("testList", testList);
				page="jsp/test.jsp";
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					cn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}finally{
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return page;
	}

}
