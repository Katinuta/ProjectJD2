package Service;

import org.apache.commons.codec.digest.DigestUtils;
import DTO.User;

/** Class contains method checking password */

public class LoginLogic {
	public static boolean checkLogin(User user, String enterLogin, String enterPassword) {
		String pass = enterPassword + "java";
		enterPassword = DigestUtils.md5Hex(pass);
		return user.getLogin().equals(enterLogin) && user.getPassword().equals(enterPassword);
	}
}
