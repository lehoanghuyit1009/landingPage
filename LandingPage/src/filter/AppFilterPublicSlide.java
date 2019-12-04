package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.News;
import model.bean.Slide;
import model.service.CategoryService;
import model.service.CommentService;
import model.service.NewsService;
import model.service.SlideService;
import util.DefineUtil;

public class AppFilterPublicSlide implements Filter {
	public AppFilterPublicSlide() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String uri = httpServletRequest.getRequestURI();
		if ("/LandingPage/".equals(uri) || uri.startsWith("/LandingPage/category/")
				|| uri.startsWith("/LandingPage/search") || uri.startsWith("/LandingPage/detail/")
				|| uri.startsWith("/LandingPage/contact") || uri.startsWith("/LandingPage/profile") || uri.startsWith("/LandingPage/page/")) {
			SlideService slideService = new SlideService();
			ArrayList<Slide> listSlideShow = slideService.getItems(DefineUtil.NUMBER_SLIDE);
			httpServletRequest.setAttribute("listSlideShow", listSlideShow);
			NewsService newsService = new NewsService();
			CategoryService categoryService = new CategoryService();
			ArrayList<Category> listCat = categoryService.getAllListCat();
			ArrayList<News> listPopular = newsService.getPopularItems(DefineUtil.NUMBER_PER_PAGE);
			request.setAttribute("listCat", listCat);
			request.setAttribute("listPopular", listPopular);
			CommentService commentService = new CommentService();
			HashMap<Integer, Integer> listCountCommentPopular = commentService.getListCountComment(listPopular);
			request.setAttribute("listCountCommentPopular", listCountCommentPopular);
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
