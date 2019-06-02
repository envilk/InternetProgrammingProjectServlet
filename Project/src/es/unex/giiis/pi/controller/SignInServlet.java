package es.unex.giiis.pi.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * Servlet implementation class SignInServlet
 */
@WebServlet("/SignInServlet.do")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user")== null) 
			logger.info("------------------------NONE USER LOGGED-----------------------");
		response.sendRedirect("/Project");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("Atendiendo POST");
		
		//GENERIC
		ServletContext sc = request.getServletContext();
		Connection conn = (Connection) sc.getAttribute("dbConn");
		HttpSession session = request.getSession();
		
		//CHOLLOS
		JDBCCholloDAOImpl cholloDao = new JDBCCholloDAOImpl();
		cholloDao.setConnection(conn);
		
		List<Chollo> chollosList = cholloDao.getAll();
		
		//request.setAttribute("chollosList", chollosList);
		request.setAttribute("chollosList", chollosList);
		
		//USERS
		
		User user = new User();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
					
		JDBCUserDAOImpl userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);
		user = userDao.getEmail(email);//GETTING USER FROM DB
		
		if (user != null && (user.getPassword().equals(password))) {
			
			logger.info("-----------------------NO ERROR-----------------------");
			logger.info(user.getEmail());
			logger.info(user.getPassword());
			//request.setAttribute("user", user);
			session.setAttribute("user", user);

			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		}
		else {
			logger.info("-----------------------ERROR-----------------------");
			response.sendRedirect("/Project");
		}
		
	}

}
