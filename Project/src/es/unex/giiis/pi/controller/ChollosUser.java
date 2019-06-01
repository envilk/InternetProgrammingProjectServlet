package es.unex.giiis.pi.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.unex.giiis.pi.dao.JDBCCholloDAOImpl;
import es.unex.giiis.pi.dao.JDBCUserDAOImpl;
import es.unex.giiis.pi.model.Chollo;
import es.unex.giiis.pi.model.User;

/**
 * Servlet implementation class ChollosUser
 */
@WebServlet("/ChollosUser.do")
public class ChollosUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChollosUser() {
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
		HttpSession session = request.getSession();

		//CHOLLOS
		JDBCCholloDAOImpl cholloDao = new JDBCCholloDAOImpl();
		cholloDao.setConnection(conn);	
		
		List<Chollo> chollos = cholloDao.getAll();
		List<Chollo> chollosUser = new ArrayList<Chollo>();
		
		User user = (User) session.getAttribute("user");
		
		for (Chollo chollo : chollos) {
			//VER SI ESE CHOLLO ES DE ESTE USER ENTONCES SE PONE EN LISTA AUX Y ESA SE METE EN LA REQUEST
			if(user.getId()==chollo.getIdu())
				chollosUser.add(chollo);
		}
		
		request.setAttribute("chollosUser", chollosUser);
		
		RequestDispatcher view = request.getRequestDispatcher("ChollosUser.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.info("Atendiendo POST");

		//GENERIC
		ServletContext sc = request.getServletContext();
		Connection conn = (Connection) sc.getAttribute("dbConn");

		//CHOLLOS
		JDBCCholloDAOImpl cholloDao = new JDBCCholloDAOImpl();
		cholloDao.setConnection(conn);

		List<Chollo> chollosList = cholloDao.getAll();

		//request.setAttribute("chollosList", chollosList);
		request.setAttribute("chollosList", chollosList);

		RequestDispatcher view = request.getRequestDispatcher("indexUserView.jsp");
		view.forward(request, response);
		
	}

}
