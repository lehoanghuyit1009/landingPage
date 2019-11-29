package filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import util.AuthUtil;

public class ProfileFilter implements Filter {

	public ProfileFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if (!AuthUtil.checkDoneLogin(request)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		} else {
			User user = AuthUtil.getUserLogin(request);
			int userPermissionId = user.getRole().getId();
			switch (userPermissionId) {
			case 3:
				chain.doFilter(req, resp);
				break;
			default:
				response.sendRedirect(request.getContextPath() + "/admin");
				break;
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
