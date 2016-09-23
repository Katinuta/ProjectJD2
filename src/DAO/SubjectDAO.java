package DAO;

import DTO.Subject;

/** Interface contains methods for access to table Subject */

public interface SubjectDAO extends AbstractDAO<Subject> {

	public Subject findSubjectById(int subjectId);

}
