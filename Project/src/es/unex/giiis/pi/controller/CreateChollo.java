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
import es.unex.giiis.pi.model.Chollo;
import es.unex.giiis.pi.model.User;

/**
 * Servlet implementation class CreateChollo
 */
@WebServlet("/CreateChollo.do")
public class CreateChollo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateChollo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.info("Atendiendo GET");

		response.sendRedirect("CreateChollo.jsp");
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

		Chollo chollo = new Chollo();
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String link = request.getParameter("link");

		chollo.setTitle(title);
		chollo.setDescription(description);
		chollo.setLink(link);

		//TODO IN MODEL CREATE THE CHOLLO
		//PUEDE HABER CHOLLOS REPETIDOS
		if(!title.isEmpty() && !link.isEmpty()) {
			User user = (User) session.getAttribute("user");
			chollo.setIdu(user.getId());
			cholloDao.add(chollo);
		}

		List<Chollo> chollosList = cholloDao.getAll();

		request.setAttribute("chollosList", chollosList);

		RequestDispatcher view = request.getRequestDispatcher("indexUserView.jsp");
		view.forward(request, response);

	}

}
