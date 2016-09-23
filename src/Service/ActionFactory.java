package Service;

import javax.servlet.http.HttpServletRequest;

/**Class contains method for createing command */

public class ActionFactory {
	public ActionCommand defineCommand(HttpServletRequest request) {
		ActionCommand current;
		String action = request.getParameter("command");
		CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
		current = currentEnum.getCurrentCommand();
		return current;
	}
}
