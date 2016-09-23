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

/**Class contains command for getting the test questions with answers for changing */

public class ChangeTestCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page=null;
		HttpSession session=request.getSession();
		String number=request.getParameter("button"); //get choose test id
		int testId=Integer.valueOf(number);
		Test test=null;
		List<Question> listQuestion=new ArrayList<>();
		List<Answer> listAnswer=new ArrayList<>();
		ConnectorDB connectorDB=null;
		Connection cn=null;
		try {
			connectorDB=ConnectorDB.getInstance();
			try {
				cn=connectorDB.getConnection();
				TestDAOImpl testDAO=new TestDAOImpl(cn);
				QuestionDAOImpl questionDAO=new QuestionDAOImpl(cn);
				AnswerDAOImpl answerDAO=new AnswerDAOImpl(cn);
				test=testDAO.findTestByTestId(testId);
				session.setAttribute("test", test);
				//get list of questions of test
				listQuestion=questionDAO.findTestQuestions(test.getTestId()); 
				session.setAttribute("listQuestion", listQuestion);
				Iterator<Question> it=listQuestion.iterator();
				while(it.hasNext()){
					Question question=(Question) it.next();
					//get answers for question of test
					List<Answer> list=answerDAO.findQuestionAnswers(question.getQuestionId());
					listAnswer.addAll(list);
				}
				session.setAttribute("listAnswer", listAnswer);
				session.setAttribute("change", true);
				page = "jsp/contenttest.jsp";
			} catch (SQLException e) {
				e.printStackTrace();
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
