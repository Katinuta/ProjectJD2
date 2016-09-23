package DTO;

/**Class describes test, contains constructors,set and get methods
 *  for all fields of class and overring method  toString*/

public class Test extends Entity {
	private int testId;
	private int subjectId;
	private String name;

	public Test() {
	}

	public Test(int testId, int subjectId, String name) {
		super();
		this.testId = testId;
		this.subjectId = subjectId;
		this.name = name;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Test [testId=" + testId + ", subjectId=" + subjectId + ", name=" + name + "]";
	}

}
