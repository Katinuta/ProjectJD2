package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAO.QuestionDAO;
import DTO.Question;

/**
 * Class implements methods of interface QuestionDAO, ovveride this, contains
 * constructor
 */

public class QuestionDAOImpl implements QuestionDAO {
	private final static String SQL_INSERT_QUESTUION = "INSERT  INTO question (test_id,content) VALUES(?,?)";;
	private final static String SQL_SELECT_TEST_QUESTIONS = "SELECT * FROM question WHERE test_id=?";
	private final static String SQL_SELECT_TEST_BY_CONTENT_TEST_ID = "SELECT * FROM question WHERE content=? and test_id=?";
	private final static String SQL_UPDATE_CONTENT_BY_ID = "UPDATE question SET question.content=? WHERE question.question_id=?";
	private final static String SQL_DELETE_QUESTION_BY_ID = "DELETE FROM question WHERE question.question_id=?";
	private Connection connection;

	public QuestionDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public List<Question> findAll() {
		return null;
	}

	public boolean delete(int id) {
		boolean flag = false;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SQL_DELETE_QUESTION_BY_ID);
			ps.setInt(1, id);
			ps.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Statement не создан");
		} finally {
			close(ps);
		}
		return flag;
	}

	public boolean create(Question entity) {
		boolean flag = false;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SQL_INSERT_QUESTUION);
			ps.setInt(1, entity.getTestId());
			ps.setString(2, entity.getContent());
			ps.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Statement не создан");
		} finally {
			close(ps);
		}
		return flag;
	}

	public Question update(Question entity) {
		return null;
	}

	public List<Question> findTestQuestions(int testId) {
		List<Question> list = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SQL_SELECT_TEST_QUESTIONS);
			ps.setInt(1, testId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Question question = new Question();
				question.setTestId(resultSet.getInt("test_id"));
				question.setQuestionId(resultSet.getInt("question_id"));
				question.setContent(resultSet.getString("content"));
				list.add(question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Statement не создан");
		} finally {
			close(ps);
		}
		return list;
	}

	public Question findQuestionByContentAndTest(String content, int testId) {
		Question question = new Question();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SQL_SELECT_TEST_BY_CONTENT_TEST_ID);
			ps.setString(1, content);
			ps.setInt(2, testId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				question.setQuestionId(resultSet.getInt("question_id"));
				question.setTestId(resultSet.getInt("test_id"));
				question.setContent(resultSet.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return question;
	}

	public int updateContextQuestion(Question question, String content) {
		PreparedStatement ps = null;
		int colCount = 0;
		try {
			ps = connection.prepareStatement(SQL_UPDATE_CONTENT_BY_ID);
			ps.setString(1, content);
			ps.setInt(2, question.getQuestionId());
			colCount = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return colCount;
	}

	public void close(PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
