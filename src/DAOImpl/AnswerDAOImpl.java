package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAO.AnswerDAO;
import DTO.Answer;

/**Class implements methods of interface AnswerDAO, ovveride this, contains constructor */

public class AnswerDAOImpl implements AnswerDAO {

	final static String SQL_INSERT_ANSWER = "INSERT INTO answer(question_id,content,right_answer) VALUES(?,?,?)";
	final static String SQL_SELECT_QUESTION_ANSWERS = "SELECT * FROM answer WHERE question_id=?";
	final static String SQL_UPDATE_ANSWER = "UPDATE answer SET answer.content=?,answer.right_answer=? WHERE answer.answer_id=?";
	final static String SQl_DELETE_ANSWER_BY_QUESTION_ID = "DELETE FROM answer WHERE question_id=?";
	private Connection connection;

	public AnswerDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public List<Answer> findAll() {
		return null;
	}

	public boolean delete(int id) {
		boolean flag = false;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SQl_DELETE_ANSWER_BY_QUESTION_ID);
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

	public boolean create(Answer entity) {
		boolean flag = false;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SQL_INSERT_ANSWER);
			ps.setInt(1, entity.getQuestionId());
			ps.setString(2, entity.getContent());
			ps.setBoolean(3, entity.isRightAnswer());
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

	public Answer update(Answer entity) {
		return null;
	}

	public List<Answer> findQuestionAnswers(int questionId) {
		List<Answer> list = new ArrayList<>();
		PreparedStatement ps = null;
		try {

			ps = connection.prepareStatement(SQL_SELECT_QUESTION_ANSWERS);
			ps.setInt(1, questionId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Answer answer = new Answer();
				answer.setAnswerId(resultSet.getInt("answer_id"));
				answer.setQuestionId(resultSet.getInt("question_id"));
				answer.setContent(resultSet.getString("content"));
				answer.setRightAnswer(resultSet.getBoolean("right_answer"));
				list.add(answer);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Statement не создан");
		} finally {
			close(ps);
		}
		return list;
	}

	public int updateAnswer(Answer answer, String content, Boolean rightAnswer) {
		PreparedStatement ps = null;
		int colCount = 0;
		try {
			ps = connection.prepareStatement(SQL_UPDATE_ANSWER);
			ps.setString(1, content);
			ps.setBoolean(2, rightAnswer);
			ps.setInt(3, answer.getAnswerId());
			colCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Statement не создан");
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
