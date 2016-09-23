package DAO;

import DTO.Question;

/** Interface contains methods for access to table Question */

public interface QuestionDAO extends AbstractDAO<Question> {

	public Question findQuestionByContentAndTest(String content, int testId);

	public int updateContextQuestion(Question question, String content);
}
