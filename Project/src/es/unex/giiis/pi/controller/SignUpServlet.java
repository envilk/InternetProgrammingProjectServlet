package es.unex.giiis.pi.controller;

import java.io.IOException;
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
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet.do")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Atendiendo GET");
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password_confirmation = request.getParameter("password_confirmation");

		user.setUsername(name);
		user.setEmail(email);
		user.setPassword(password);

		Map<String, String> messages = new HashMap<String, String>();

		JDBCUserDAOImpl dao = new JDBCUserDAOImpl();
		dao.setConnection(conn);

		//Checks if the user "user" is in the database, saving an "User" object inside the "User" type variable "userDao"
		User userDao = dao.getUsername(user.getUsername());

		//Only if there's no user(null) in the database with the data introduced before the user can be created in the database
		if (user.validateName(messages) && user.validatePasswords(password_confirmation) && userDao == null) {
			dao.add(user);
			User newU = dao.getUsername(user.getUsername());
			session.setAttribute("user", newU);

			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		}
		else {
			response.sendRedirect("/Project");
		}

	}

}
