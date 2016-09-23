package DAO;

import java.util.List;
import DTO.Answer;

/** Interface contains methods for access to table Answer */

public interface AnswerDAO extends AbstractDAO<Answer> {

	public List<Answer> findQuestionAnswers(int questionId);

	public int updateAnswer(Answer answer, String content, Boolean rightAnswer);

}
