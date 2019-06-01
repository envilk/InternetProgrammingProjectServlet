package es.unex.giiis.pi.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
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
import javax.servlet.http.HttpSession;

import es.unex.giiis.pi.dao.CholloDAO;
import es.unex.giiis.pi.dao.JDBCCholloDAOImpl;
import es.unex.giiis.pi.model.Chollo;
import es.unex.giiis.pi.model.User;

/**
 * Servlet implementation class Soldout
 */
@WebServlet("/Soldout.do")
public class Soldout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Soldout() {
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

		List<Chollo> chollosList = cholloDao.getAll();
		List<Chollo> chollosAvailable = new ArrayList<Chollo>();

		for (Chollo chollo : chollosList) {
			if(chollo.getSoldout()==0)
				chollosAvailable.add(chollo);
		}

		request.setAttribute("chollosList", chollosAvailable);

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
		logger.info("Atendiendo POST");

		//GENERIC
		ServletContext sc = request.getServletContext();
		Connection conn = (Connection) sc.getAttribute("dbConn");

		//CHOLLOS
		JDBCCholloDAOImpl cholloDao = new JDBCCholloDAOImpl();
		cholloDao.setConnection(conn);

		List<Chollo> chollosUser = new ArrayList<Chollo>();

		String id = (String) request.getParameter("id");
		Long idC = Long.parseLong(id);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		//We check that the chollo exists
		Chollo chollo = cholloDao.get(idC);
		if(chollo.getSoldout() == 0)
			chollo.setSoldout(1);
		else
			chollo.setSoldout(0);

		cholloDao.save(chollo);

		List<Chollo> chollos = cholloDao.getAll();
		for (Chollo aux : chollos) {
			//VER SI ESE CHOLLO ES DE ESTE USER ENTONCES SE PONE EN LISTA AUX Y ESA SE METE EN LA REQUEST
			if(user.getId()==aux.getIdu())
				chollosUser.add(aux);
		}

		request.setAttribute("chollosUser", chollosUser);

		RequestDispatcher view = request.getRequestDispatcher("ChollosUser.jsp");
		view.forward(request, response);
	}

}
