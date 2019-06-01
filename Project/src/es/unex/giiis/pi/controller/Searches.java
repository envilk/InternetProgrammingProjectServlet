package es.unex.giiis.pi.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.unex.giiis.pi.dao.JDBCCholloDAOImpl;
import es.unex.giiis.pi.model.Chollo;

/**
 * Servlet implementation class Searches
 */
@WebServlet("/Searches.do")
public class Searches extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Searches() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Atendiendo GET");

		//GENERIC
		ServletContext sc = request.getServletContext();
		Connection conn = (Connection) sc.getAttribute("dbConn");

		//CHOLLOS
		JDBCCholloDAOImpl cholloDao = new JDBCCholloDAOImpl();
		cholloDao.setConnection(conn);
		
		List<Chollo> chollosList = cholloDao.getAllBySearchAll((String)request.getParameter("search"));
		
		//request.setAttribute("chollosList", chollosList);
		request.setAttribute("chollosList", chollosList);
	
		String referer = request.getHeader("referer");
		RequestDispatcher view;
		if(referer.equals("http://localhost:8080/Project/"))
			view = request.getRequestDispatcher("index.jsp");
		else
			view = request.getRequestDispatcher("indexUserView.jsp");
		
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
