package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAO.SubjectDAO;
import DTO.Subject;

/**
 * Class implements methods of interface SubjectDAO, ovveride this, contains
 * constructor
 */

public class SubjectDAOImpl implements SubjectDAO{
	
	public final static String SQL_INSERT_SUBJECT="INSERT INTO subject (name) VALUES(?)";
	public final static String SQL_SELECT_SUBJECT="SELECT subject_id,name FROM subject";
	public final static String SQL_SELECT_SUBJECT_BY_ID="SELECT * FROM subject WHERE  subject_id=?";
	private Connection connection;

	public SubjectDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public List<Subject> findAll() {
		List<Subject> list=new ArrayList<>();
		PreparedStatement ps=null;
		try {
			ps=connection.prepareStatement(SQL_SELECT_SUBJECT);
			ResultSet resultSet=ps.executeQuery();
			while(resultSet.next()){
				Subject subject=new Subject();
				subject.setSubjectId(resultSet.getInt("subject_id"));
				subject.setName(resultSet.getString("name"));
				list.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(ps);
		}
		return list;
	}

	public boolean delete(int id) {
		return false;
	}

	public boolean create(Subject entity) {
		boolean flag=false;
		PreparedStatement ps=null;
		try {
			ps=connection.prepareStatement(SQL_INSERT_SUBJECT);
			System.out.println(entity.getName());
			ps.setString(1, entity.getName());
			ps.executeUpdate();
			flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Statement не создан");
		}finally{
			close(ps);
		}
		return flag;
	}

	@Override
	public Subject update(Subject entity) {
		return null;
	}
	
	public Subject findSubjectById(int subjectId){
		Subject subject=new Subject();
		PreparedStatement ps=null;
		try {
			ps=connection.prepareStatement(SQL_SELECT_SUBJECT_BY_ID);
			ps.setInt(1,subjectId );
			ResultSet resultSet=ps.executeQuery();
			while(resultSet.next()){
				subject.setSubjectId(resultSet.getInt("subject_id"));
				subject.setName(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();System.err.println("Statement не создан");
		}finally{
			close(ps);
		}
		return subject;
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
