package DTO;

/**Class describes subject, contains constructors,set and get methods
 *  for all fields of class and overring method  toString*/

public class Subject extends Entity {

	private int subjectId;
	private String name;

	public Subject() {

	}

	public Subject(int subjectId, String name) {
		this.subjectId = subjectId;
		this.name = name;
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
		return "Subject [subjectId=" + subjectId + ", name=" + name + "]";
	}

}
