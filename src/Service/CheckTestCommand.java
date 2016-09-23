package Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DTO.Answer;
import DTO.Question;

public class CheckTestCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page=null;
		HttpSession session=request.getSession();
		List<Answer> listAnswerRight=(List<Answer>) session.getAttribute("listAnswer");
		List<Question> listQuestion=(List<Question>) session.getAttribute("listQuestion");
		int countQuestion=listQuestion.size();
		int countRightQuestion=0;
		Iterator<Question> itQuestion=listQuestion.iterator();
		while(itQuestion.hasNext()){
			Question question=itQuestion.next();
			Iterator<Answer> itAnswer=listAnswerRight.iterator();
			int countAnswerQuestion=0;
			int countRightAnswer=0;
			
			while(itAnswer.hasNext()){
				Answer answer=itAnswer.next();
				if(answer.getQuestionId()==question.getQuestionId()){
					countAnswerQuestion++;
					String checkedBox=request.getParameter(Integer.toString(answer.getAnswerId()));
					if(checkedBox!=null&&answer.isRightAnswer()||checkedBox==null&&!(answer.isRightAnswer())){
						countRightAnswer++;
					}
				}
			}
			if(countAnswerQuestion==countRightAnswer){
				countRightQuestion++;
			}
		}
		session.removeAttribute("listAnswer");
		session.removeAttribute("listQuestion");
		countRightQuestion=countRightQuestion*100/countQuestion;
		countQuestion=countQuestion*100/countQuestion;
		request.setAttribute("countRightQuestion", countRightQuestion);
		request.setAttribute("countQuestion", countQuestion);
		page="jsp/result.jsp";
		return page;
	}

}
