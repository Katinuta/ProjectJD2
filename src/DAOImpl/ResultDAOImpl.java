package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import DAO.ResultDAO;
import DTO.Result;

/**
 * Class implements methods of interface ResultDAO, ovveride this, contains
 * constructor
 */

public class ResultDAOImpl implements ResultDAO {
	final static String SQL_INSERT_RESULT = "INSERT INTO result(test_id, user_id, result)VALUES(?,?,?)";
	final static String SQL_SELECT_RESULT_BY_TEST_USER = "SELECT * FROM result WHERE test_id=? and user_id=?";
	private Connection connection;

	public ResultDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public List<Result> findAll() {
		return null;
	}

	public boolean delete(int id) {
		return false;
	}

	public boolean create(Result entity) {
		boolean flag = false;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareCall(SQL_INSERT_RESULT);
			ps.setInt(1, entity.getTestId());
			ps.setInt(2, entity.getUserId());
			ps.setInt(3, entity.getResult());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}

		return flag;
	}

	public Result update(Result entity) {
		return null;
	}

	public Result findResultByTestUser(int testId, int userId) {
		Result result = new Result();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SQL_SELECT_RESULT_BY_TEST_USER);
			ps.setInt(1, testId);
			ps.setInt(2, userId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				result.setTestId(resultSet.getInt("test_id"));
				result.setUserId(resultSet.getInt("user_id"));
				result.setResult(resultSet.getInt("result"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return result;
	}

	public void close(PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {

		}
	}
}
