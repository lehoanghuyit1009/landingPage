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
		

		int id =0;
		id = Integer.parseInt(request.getParameter("id"));
		if(categoryDAO.delete( id) > 0) {
			categoryDAO.deleteNewsByCid(id);
			response.sendRedirect(request.getContextPath() + "/admin/category/index?msg=1");
			
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/category/index?msg=0");

		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

}
