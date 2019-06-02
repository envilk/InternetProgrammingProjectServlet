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
 * Servlet implementation class DeleteAccount
 */
@WebServlet("/DeleteAccount.do")
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.info("Atendiendo GET");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null) {
			RequestDispatcher view = request.getRequestDispatcher("DeleteAccountConfirmation.jsp");
			view.forward(request, response);
		}
		else
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

		//USERS
		User user = new User();

		JDBCUserDAOImpl userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);
		user = (User) session.getAttribute("user");//GETTING USER FROM PAGE


		if(user != null) {
			userDao.delete(user.getId());
			session.invalidate();
		}

		response.sendRedirect("/Project");
	}

}
