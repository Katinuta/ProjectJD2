package Service;

import javax.servlet.http.HttpServletRequest;

/**Class contains page for opening form for registration*/

public class OpenFormCommand implements ActionCommand{

	@Override
	public String execute(HttpServletRequest request) {
		return "jsp/registration.jsp";
	}

}
