package Service;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;

import DAOImpl.ConnectorDB;
import DAOImpl.UserDAOImpl;
import DTO.User;

/**Class contains command for registration new student*/

public class RegistrationCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page=null;
		String name=request.getParameter("name");
		String surname=request.getParameter("surname");
		String login=request.getParameter("login");
		String password=DigestUtils.md5Hex(request.getParameter("password")+"java");
		User user=new User();
		user.setAccess(false);
		user.setName(name);
		user.setSurname(surname);
		user.setPassword(password);
		user.setLogin(login);
		ConnectorDB connectorDB=null;
		Connection cn=null;
		try {
			connectorDB=ConnectorDB.getInstance();
			try {
				cn=connectorDB.getConnection();
				UserDAOImpl userDAO=new UserDAOImpl(cn);
				boolean flag=userDAO.create(user);
				if(flag){
					page="login.jsp";
				}else{
					request.setAttribute("errorRegMessage", "Try to book again");
					page="jsp/registration.jsp";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}finally{
			try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return page;
	}

}
