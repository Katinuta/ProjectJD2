package Service;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAOImpl.ConnectorDB;
import DAOImpl.SubjectDAOImpl;
import DAOImpl.UserDAOImpl;
import DTO.User;

/**Class contains command for getting all user subjects*/

public class SubjectCommand implements ActionCommand{

	@Override
	public String execute(HttpServletRequest request) {
		String page=null;
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		Boolean role=user.isAccess();
		List<DTO.Subject> list=new ArrayList<>();
		ConnectorDB cnDB=null;
		Connection cn=null;
			try {
				cnDB=ConnectorDB.getInstance();
				try {
					cn=cnDB.getConnection();
					SubjectDAOImpl subjectDAO=new SubjectDAOImpl(cn);
					UserDAOImpl userDAO=new UserDAOImpl(cn);
					if(role){
						list=subjectDAO.findAll();		//find all subjects for admin
					}else if(!role){
						list=userDAO.findUserSubject(user.getUserId());  //find all subject for student
					}
					request.setAttribute("listSubject", list);  //set list of subject in the request
					page="jsp/subject.jsp";
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}finally{
				try {
					cn.close();				// return connection to pool
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return page;
	}

}
