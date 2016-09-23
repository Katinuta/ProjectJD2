package Service;

import javax.servlet.http.HttpServletRequest;

/** Intarface executes command */

public interface ActionCommand {
	String execute(HttpServletRequest request);
}
