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
import DTO.Subject;
import DTO.Test;


/**Class contains command for changing questions and answers of test*/

public class SaveTestCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		HttpSession session = request.getSession();
		Subject subject = (Subject) session.getAttribute("subject");
		ConnectorDB connectorDB = null;
		Connection cn = null;
		try {
			connectorDB = ConnectorDB.getInstance();
			try {
				cn = connectorDB.getConnection();
				TestDAOImpl testDAO = new TestDAOImpl(cn);
				QuestionDAOImpl questionDAO = new QuestionDAOImpl(cn);
				AnswerDAOImpl answerDAO = new AnswerDAOImpl(cn);
				//actions if test change
				if (session.getAttribute("change") != null) {
					Test test = (Test) session.getAttribute("test");
					testDAO.updateNameTestById(test, request.getParameter("changeName"));
					List<Question> listQuestion = (List<Question>) session.getAttribute("listQuestion");
					List<Answer> listAnswer = (List<Answer>) session.getAttribute("listAnswer");
					Iterator<Question> itQuestion = listQuestion.iterator();
					while (itQuestion.hasNext()) {
						Question question = itQuestion.next();
						String content = request.getParameter(Integer.toString(question.getQuestionId()));
						questionDAO.updateContextQuestion(question, content);
					}
					Iterator<Answer> itAnswer = listAnswer.iterator();
					while (itAnswer.hasNext()) {
						Answer answer = itAnswer.next();
						String content = request.getParameter(Integer.toString(answer.getAnswerId()));
						String checkbox = request.getParameter("checkbox" + Integer.toString(answer.getAnswerId()));
						Boolean rightAnswer = false;
						if (checkbox != null && checkbox.equals("on")) {
							rightAnswer = true;
						}
						answerDAO.updateAnswer(answer, content, rightAnswer);
					}
					session.removeAttribute("change");
				}
				//actions for save test cteating or changing
				session.removeAttribute("listAnswer");
				session.removeAttribute("listQuestion");
				session.removeAttribute("test");
				List<DTO.Test> list = new ArrayList<>();
				list = testDAO.findTestBySubjectId(subject.getSubjectId()); //get new list of test by subject
				request.setAttribute("testList", list);
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
		page = "jsp/test.jsp";
		return page;
	}

}
