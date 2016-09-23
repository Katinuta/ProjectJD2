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
import DTO.Answer;
import DTO.Question;
import DTO.Test;

/** Class contatins command for deleting question and answer from test */

public class DeleteQuestionCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		int questionId = Integer.parseInt(request.getParameter("idQuestion"));
		HttpSession session = request.getSession();
		Test test = (Test) session.getAttribute("test");
		ConnectorDB connectorDb = null;
		Connection cn = null;
		try {
			connectorDb = ConnectorDB.getInstance();
			try {
				cn = connectorDb.getConnection();
				AnswerDAOImpl answerDAO = new AnswerDAOImpl(cn);
				QuestionDAOImpl questionDAO = new QuestionDAOImpl(cn);
				cn.setAutoCommit(false);
				answerDAO.delete(questionId);
				questionDAO.delete(questionId);
				cn.commit();
				// get new list of questions of the test
				List<Question> listQuestion = questionDAO.findTestQuestions(test.getTestId());
				List<Answer> listAnswer = new ArrayList<>();
				Iterator<Question> it = listQuestion.iterator();
				while (it.hasNext()) {
					Question question = it.next();
					// get answer for question of test
					listAnswer.addAll(answerDAO.findQuestionAnswers(question.getQuestionId()));
				}
				session.setAttribute("listQuestion", listQuestion);
				session.setAttribute("listAnswer", listAnswer);
				page = "jsp/contenttest.jsp";
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
