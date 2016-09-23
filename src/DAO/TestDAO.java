package DAO;

import DTO.Test;

/** Interface contains methods for access to table Test */

public interface TestDAO extends AbstractDAO<Test> {

	public Test findTestBySubjectIdAndName(int subjectId, String name);

	public Test findTestByTestId(int testId);

	public int updateNameTestById(Test test, String name);
}
