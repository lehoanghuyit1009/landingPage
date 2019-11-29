package util;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDAO;
import model.service.UserService;

public class AuthUtil {
	public static boolean checkDoneLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(DefineUtil.USER_LOGIN) != null) {
			return true;
		}
		Cookie[] arCookies = request.getCookies();
		for (Cookie cookie : arCookies) {
			if (cookie.getName().equals("userCookie") && cookie.getMaxAge() != 0) {
				UserService userService = new UserService();
				session.setAttribute(DefineUtil.USER_LOGIN,
						userService.getItemById(Integer.parseInt(cookie.getValue())));
				session.setAttribute("loginTime", new Date());
				return true;
			}
		}
		return false;
	}

	public static User getUserLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(DefineUtil.USER_LOGIN) != null) {
			return (User) session.getAttribute(DefineUtil.USER_LOGIN);
		} else {
			return null;
		}
	}

	public static User getUserLoginPublic(HttpServletRequest request) {
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute(DefineUtil.USER_LOGIN);
		if (user != null)
			return user;
		if (request.getCookies() != null) {
			Cookie[] arCookies = request.getCookies();
			if (arCookies != null || arCookies.length > 0) {
				for (Cookie cookie : arCookies) {
					if (cookie.getName().equals("userCookie") && cookie.getMaxAge() != 0) {
						UserService userService = new UserService();
						return userService.getItemById(Integer.parseInt(cookie.getValue()));
					}
				}
			}
		}

		return null;
	}
}
