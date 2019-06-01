package es.unex.giiis.pi.controller;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.unex.giiis.pi.dao.CategoryDAO;
import es.unex.giiis.pi.dao.CholloDAO;
import es.unex.giiis.pi.dao.ChollosCategoryDAO;
import es.unex.giiis.pi.dao.JDBCCategoryDAOImpl;
import es.unex.giiis.pi.dao.JDBCCholloDAOImpl;
import es.unex.giiis.pi.dao.JDBCChollosCategoryDAOImpl;
import es.unex.giiis.pi.dao.JDBCShopDAOImpl;
import es.unex.giiis.pi.dao.JDBCUserDAOImpl;
import es.unex.giiis.pi.dao.ShopDAO;
import es.unex.giiis.pi.dao.UserDAO;
import es.unex.giiis.pi.model.Chollo;
import es.unex.giiis.pi.model.Shop;
import es.unex.giiis.pi.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import java.sql.Connection;



/**
 * Servlet implementation class ListUsersNotesServlet
 */
@WebServlet("/ListChollosServlet.do")
public class ListChollosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListChollosServlet() {
        super();
    }

	/**
	 * Initial GET method which links to the index.html main page
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("Atendiendo GET");
		
		//Obtain the connection to the database from the ServletContext
		ServletContext sc = request.getServletContext();
		Connection conn = (Connection) sc.getAttribute("dbConn");
		
//		//Instantiate the DAO object implementation and set the connection to the database
//		JDBCUserDAOImpl usrDao = new JDBCUserDAOImpl();
//		usrDao.setConnection(conn);
		
		JDBCCholloDAOImpl cholloDao = new JDBCCholloDAOImpl();
		cholloDao.setConnection(conn);
		
		//Obtain all the elements of the cholloDAO object
		List<Chollo> cholloList = cholloDao.getAll();
		
		//Set an attribute with the object that contains all these elements
		request.setAttribute("chollosList", cholloList);
		
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	
	}

	
}
