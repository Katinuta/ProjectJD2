package Service;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAOImpl.AnswerDAOImpl;
import DAOImpl.ConnectorDB;
import DAOImpl.QuestionDAOImpl;
import DTO.Answer;
import DTO.Question;
import DTO.Test;

/**Class contains command addition question with answers for new and old test */

public class AddAnswersCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		HttpSession session = request.getSession();
		Test test = (Test) session.getAttribute("test");
		String content = request.getParameter("question");
		Enumeration<String> attrSession=session.getAttributeNames();
		Boolean hasListQuestion=false;
		Boolean hasListAnswer=false;
		List<Answer> listAnswer = null;
		List<Question> listQuestion=null;
		if(attrSession.hasMoreElements()){
			while(attrSession.hasMoreElements()){
				String attr=attrSession.nextElement();
				//check if session has attribute "listQuestion" and "listAnswer"
				if(attr.equals("listQuestion")){
					hasListQuestion=true;
				}
				if(attr.equals("listAnswer")){
					hasListAnswer=true;
				}
			}
		}
		if(hasListAnswer&&hasListQuestion){
			//for old test
			listAnswer=(List<Answer>) session.getAttribute("listAnswer");
			listQuestion=(List<Question>) session.getAttribute("listQuestion");
		}else if(!hasListAnswer&&!hasListQuestion){
			//for new test
			listAnswer = new ArrayList<>();
			listQuestion = new ArrayList<>();
		}
		ConnectorDB connectorDB = null;
		Connection cn = null;
		try {
			connectorDB = ConnectorDB.getInstance();
			try {
				cn = connectorDB.getConnection();
				QuestionDAOImpl questionDAO = new QuestionDAOImpl(cn);
				AnswerDAOImpl answerDAO = new AnswerDAOImpl(cn);
				Question question = new Question();
				question.setTestId(test.getTestId());
				question.setContent(content);
				cn.setAutoCommit(false);
				questionDAO.create(question);
				question = questionDAO.findQuestionByContentAndTest(content, test.getTestId());
				listQuestion.add(question); //addition new question
				session.setAttribute("listQuestion", listQuestion);
				for (int i = 1; i <= 5; i++) {
					Answer answer = new Answer();
					answer.setContent(request.getParameter("answer" + i));
					answer.setQuestionId(question.getQuestionId());
					String checkbox=request.getParameter("checkbox" + i);
					if (checkbox!=null&&checkbox.equals("on")) {
						answer.setRightAnswer(true);
					} else {
						answer.setRightAnswer(false);
					}
					answerDAO.create(answer);
					listAnswer.add(answer);//addition answer to question
				}
				cn.commit();
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
