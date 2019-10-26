package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.News;
import model.dao.CategoryDAO;



//@WebServlet("/AdminCategoryDeleteController")
public class AdminCategoryDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;
 
   
    public AdminCategoryDeleteController() {
    	super();
    	categoryDAO = new CategoryDAO();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
		}

		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/category/index?msg=0&page=" + currentPage);
			return;
		}
		if (categoryDAO.deleteParentItemAndSubItems(id) > 0) {
			//duyệt qua tất cả bài hát trong danh mục này và xóa những comment của nó
			
			ArrayList<News> list = categoryDAO.getItemsByCatId(id);
			for (News item : list) {
				categoryDAO.deleteItemByNewsId(item.getId());
			}
			//xóa những bài hát thuộc danh mục này
			categoryDAO.deleteItemByCatId(id);
			
			response.sendRedirect(request.getContextPath() + "/admin/category/delete?msg=1");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/category/delete?msg=0" + currentPage);
			return;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

}
