package DTO;

/**Class describes result, contains constructors,set and get methods
 *  for all fields of class and overring method  toString*/

public class Result extends Entity {
	private int testId;
	private int userId;
	private int result;

	public Result() {
	}

	public Result(int testId, int userId, int result) {
		super();
		this.testId = testId;
		this.userId = userId;
		this.result = result;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Result [testId=" + testId + ", userId=" + userId + ", result=" + result + "]";
	}

}
