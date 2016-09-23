package DTO;

/**Class describes question, contains constructors,set and get methods
 *  for all fields of class and overring method  toString*/

public class Question extends Entity {
	private int questionId;
	private int testId;
	private String content;

	public Question() {

	}

	public Question(int questionId, int testId, String content) {
		super();
		this.questionId = questionId;
		this.testId = testId;
		this.content = content;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", testId=" + testId + ", content=" + content + "]";
	}

}
