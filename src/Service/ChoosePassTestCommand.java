package Service;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAOImpl.AnswerDAOImpl;
import DAOImpl.ConnectorDB;
import DAOImpl.QuestionDAOImpl;
import DAOImpl.TestDAOImpl;
import DTO.Answer;
import DTO.Question;
import DTO.Test;

/**Class contatins command for getiing questions of the test with answer to pass*/

public class ChoosePassTestCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		int testId = Integer.parseInt(request.getParameter("button"));
		HttpSession session = request.getSession();
		ConnectorDB connectorDB = null;
		Connection cn = null;
		try {
			connectorDB = ConnectorDB.getInstance();
			try {
				cn = connectorDB.getConnection();
				TestDAOImpl testDAO = new TestDAOImpl(cn);
				QuestionDAOImpl questionDAO = new QuestionDAOImpl(cn);
				AnswerDAOImpl answerDAO = new AnswerDAOImpl(cn);
				Test test = testDAO.findTestByTestId(testId);
				session.setAttribute("test", test);
				//find questions of the test
				List<Question> listQuestion = questionDAO.findTestQuestions(testId);
				List<Answer> listAnswer = new ArrayList<>();
				Iterator<Question> it = listQuestion.iterator();
				while (it.hasNext()) {
					Question question = it.next();
					//find answers for the question
					listAnswer.addAll(answerDAO.findQuestionAnswers(question.getQuestionId()));
				}
				session.setAttribute("listAnswer", listAnswer);
				session.setAttribute("listQuestion", listQuestion);
				page = "jsp/passtest.jsp";
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
