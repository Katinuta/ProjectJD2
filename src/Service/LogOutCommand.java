package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**Class contains command for exit from programm*/

public class LogOutCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		String page = "login.jsp";
		return page;
	}

}
