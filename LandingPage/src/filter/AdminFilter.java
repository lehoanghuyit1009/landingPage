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

public class AdminFilter implements Filter {

	public AdminFilter() {
	}

	public void destroy() {
	}

	// admin/*
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		/*
		 * Login before go to admin page. url-pattern: admin/*
		 */
		if (!AuthUtil.checkDoneLogin(request)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		} else {

			/* Phân lọai user vào trang amdin */
			User user = AuthUtil.getUserLogin(request);
			int userPermissionId = user.getRole().getId(); /* phân lọa theo theo id như trong database */
			switch (userPermissionId) {
			case 3:
				String uri = request.getRequestURI();
				ArrayList<String> notEditorPermissions = new ArrayList<>();
				String admin = "/admin/";
				notEditorPermissions.add(admin + "slide");
				notEditorPermissions.add(admin + "category");
				notEditorPermissions.add(admin + "contact");
				notEditorPermissions.add(admin + "user");

				boolean check = true;
				for (String item : notEditorPermissions) {
					if (uri.contains(item)) {
						System.out.println(uri.contains(item));
						check = false;
						break;
					}
				}
				if (check) {
					chain.doFilter(req, resp);
				} else {
					response.sendRedirect(request.getContextPath() + "/");
					return;
				}
				break;
			case 2:/* Nếu user là editor thì */
				String uri1 = request.getRequestURI();
				/*
				 * start: tạo ra list các đường dẫn mà editor không được vào để so sảnh với uri
				 * và chặn lại
				 */
				ArrayList<String> notEditorPermissions1 = new ArrayList<>();
				String admin1 = "/admin/";
				notEditorPermissions1.add(admin1 + "slide");
				notEditorPermissions1.add(admin1 + "category");
				notEditorPermissions1.add(admin1 + "contact");

				/*
				 * end: tạo ra list các đường dẫn mà editor không được vào để so sảnh với uri và
				 * chặn lại
				 */
				/* Kiểm tra quyền */
				boolean check1 = true;
				for (String item : notEditorPermissions1) {
					if (uri1.contains(item)) {
						System.out.println(uri1.contains(item));
						check = false;
						break;
					}
				}
				if (check1) {
					chain.doFilter(req, resp);
				} else {
					response.sendRedirect(request.getContextPath() + "/admin");
					return;
				}
				break;
			case 1:/* Nếu là admin thì có thể vào tất cả các phần của amdin */
				chain.doFilter(req, resp);
				break;
			default:
				break;
			}
		}
		// chain.doFilter(req, resp);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
