package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CategoryDAO;


//@WebServlet("/AdminCategoryEditController")
public class AdminCategoryEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    int id =0;   
    private CategoryDAO categoryDAO;
    public AdminCategoryEditController() {
        super();
        categoryDAO = new CategoryDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		id = Integer.parseInt(request.getParameter("id"));
		RequestDispatcher rDispatcher = request.getRequestDispatcher("/admin/category/edit.jsp");
		rDispatcher.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String name = request.getParameter("name");
		
		if(categoryDAO.update(name, id) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/category/index?msg=1");
			
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/category/index?msg=0");

		}
	
	}

}
