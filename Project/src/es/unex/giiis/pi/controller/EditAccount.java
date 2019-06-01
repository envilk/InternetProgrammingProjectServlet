package es.unex.giiis.pi.controller;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class EditAccount
 */
@WebServlet("/EditAccount.do")
public class EditAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditAccount() {
		super();
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

		List<Chollo> chollosList = cholloDao.getAll();

		//request.setAttribute("chollosList", chollosList);
		request.setAttribute("chollosList", chollosList);

		//USERS
		JDBCUserDAOImpl userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);
		User user = new User();

		user = (User) session.getAttribute("user");//GETTING USER FORM CONTEXT

		String possibleEmail = request.getParameter("email");
		String possiblePass = request.getParameter("password");

		boolean flag = false;//SIGNIFICA QUE NO ESTA REPETIDO EN FALSE
		
		//CHECK IF THE EMAIL IS NOT REPEATED
		if(!possibleEmail.isEmpty()) {
			List<User> userList = userDao.getAll();
			for (User aux : userList) {
				String auxEmail = aux.getEmail();
				if(auxEmail.equals(possibleEmail) && (user.getId() != aux.getId())) {
					flag = true;
					break;
				}
			}	
		}

		RequestDispatcher view;
		if(!possiblePass.isEmpty())
			user.setPassword(possiblePass);

		//IF THERE ARE OTHER EMAILS LIKE "POSSIBLEEMAIL THEN GO TO indexUserView, OTHERWISE GOES TO EditAccountConfirmation"
		if(!flag) {
			if(!possibleEmail.isEmpty())
				user.setEmail(possibleEmail);

			session.setAttribute("user", user);

			view = request.getRequestDispatcher("EditAccountConfirmation.jsp");
		}
		else {
			session.setAttribute("user", user);

			view = request.getRequestDispatcher("indexUserView.jsp");
		}

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
		HttpSession session = request.getSession();

		//CHOLLOS
		JDBCCholloDAOImpl cholloDao = new JDBCCholloDAOImpl();
		cholloDao.setConnection(conn);

		List<Chollo> chollosList = cholloDao.getAll();

		//request.setAttribute("chollosList", chollosList);
		request.setAttribute("chollosList", chollosList);

		//USERS
		User user = new User();

		JDBCUserDAOImpl userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);
		user = (User) session.getAttribute("user");//GETTING USER FROM PAGE

		userDao.save(user);

		//request.setAttribute("user", user);
		session.setAttribute("user", user);

		RequestDispatcher view = request.getRequestDispatcher("indexUserView.jsp");
		view.forward(request, response);

	}

}
