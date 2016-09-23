package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAO.TestDAO;
import DTO.Test;

/**
 * Class implements methods of interface UserDAO, ovveride this, contains
 * constructor
 */


public class TestDAOImpl implements TestDAO {
	private final static String SQL_INSERT_TEST="INSERT  INTO test (subject_id,name) VALUES(?,?)"; 
	private final static String SQL_SELECT_TEST_BY_SUBJECTID="SELECT * FROM test WHERE subject_id=?";
	private final static String SQL_SELECT_TEST_BY_NAME_SUBJECT_ID="SELECT * FROM test WHERE name=? and subject_id=?";
	private final static String SQL_SELECT_TEST_BY_ID="SELECT * FROM test WHERE test_id=?";
	private final static String SQL_UPDATE_NAME_TEST_BY_ID="UPDATE test SET test.name=? WHERE test_id=?";
	private final static String SQL_DELETE_TEST_BY_ID="DELETE FROM test WHERE test_id=?";
	private Connection connection;

	public TestDAOImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Test> findAll() {
		return null;
	}

	@Override
	public boolean delete(int id) {
		boolean flag=false;
		PreparedStatement ps=null;
		try {
			ps=connection.prepareStatement(SQL_DELETE_TEST_BY_ID);
			ps.setInt(1,id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Statement не создан");
		}finally{
			close(ps);
		}
		return flag;
	}

	public boolean create(Test entity) {
		boolean flag=false;
		PreparedStatement ps=null;
		try {
			ps=connection.prepareStatement(SQL_INSERT_TEST);
			ps.setInt(1, entity.getSubjectId());
			ps.setString(2, entity.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Statement не создан");
		}finally{
			close(ps);
		}
		return flag;
	}

	@Override
	public Test update(Test entity) {
		return null;
	}

	public List<Test> findTestBySubjectId(int subjectId){
		List<Test> list=new ArrayList<>();
		PreparedStatement ps=null;
		try {
			ps=connection.prepareStatement(SQL_SELECT_TEST_BY_SUBJECTID);
			ps.setInt(1, subjectId);
			ResultSet resultSet=ps.executeQuery();
			while(resultSet.next()){
				Test test=new Test();
				test.setTestId(resultSet.getInt("test_id"));
				test.setSubjectId(resultSet.getInt("subject_id"));
				test.setName(resultSet.getString("name"));
				list.add(test);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Statement не создан");
		}finally{
			close(ps);
		}
		return list;
	}
	
	public Test findTestBySubjectIdAndName(int subjectId,String name){
		Test test =new Test();
		PreparedStatement ps=null;
		try {
			ps=connection.prepareStatement(SQL_SELECT_TEST_BY_NAME_SUBJECT_ID);
			ps.setString(1, name);
			ps.setInt(2, subjectId);
			ResultSet resultSet=ps.executeQuery();
			while(resultSet.next()){
				test.setName( resultSet.getString("name"));
				test.setSubjectId( resultSet.getInt("subject_id"));
				test.setTestId(resultSet.getInt("test_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Statement не создан");
		}finally{
			close(ps);
		}
		return test;
	}
	public Test findTestByTestId(int testId){
		Test test=new Test();
		PreparedStatement ps=null;
		try {
			ps=connection.prepareStatement(SQL_SELECT_TEST_BY_ID);
			ps.setInt(1, testId);
			ResultSet resultSet=ps.executeQuery();
			while(resultSet.next()){
				test.setTestId(resultSet.getInt("test_id"));
				test.setSubjectId(resultSet.getInt("subject_id"));
				test.setName(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(ps);
		}
		return test;
	}
	
	public int updateNameTestById(Test test, String name){
		PreparedStatement ps=null;
		int colCount=0;
		try {
			ps=connection.prepareStatement(SQL_UPDATE_NAME_TEST_BY_ID);
			ps.setString(1, name);
			ps.setInt(2, test.getTestId());
			colCount=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(ps);
		}
		return  colCount;
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
