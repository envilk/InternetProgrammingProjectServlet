package es.unex.giiis.pi.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
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
import es.unex.giiis.pi.dao.JDBCLikesDAOImpl;
import es.unex.giiis.pi.model.Chollo;
import es.unex.giiis.pi.model.Like;

/**
 * Servlet implementation class Likes
 */
@WebServlet("/Likes.do")
public class Likes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Likes() {
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
		
		Collections.sort(chollosList, new Comparator<Chollo>() {
		    public int compare(Chollo one, Chollo other) {
		    	Integer likesOne, likesTwo;
		    	likesOne = one.getLikes();
		    	likesTwo = other.getLikes(); 
		        return likesOne.compareTo(likesTwo);
		    }
		}); 
		
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
		
		logger.info("Atendiendo POST");

		//GENERIC
		ServletContext sc = request.getServletContext();
		Connection conn = (Connection) sc.getAttribute("dbConn");

		//CHOLLOS
		JDBCCholloDAOImpl cholloDao = new JDBCCholloDAOImpl();
		cholloDao.setConnection(conn);

		//LIKES
		JDBCLikesDAOImpl likesDao = new JDBCLikesDAOImpl();
		likesDao.setConnection(conn);
		
		int idu, idc;
		idu = Integer.parseInt(request.getParameter("idu"));
		idc = Integer.parseInt(request.getParameter("idc"));
		logger.info("-----------------------"+idu+" "+idc);
		
		Like like = new Like();
		like.setIdu(idu);
		like.setIdc(idc);
		
		Chollo chollo = cholloDao.get(idc);
		
		if(likesDao.isLike(idu, idc)){
			logger.info("----------------ARRIVES IF-------------------");
			likesDao.delete(idu, idc);
			chollo.setLikes(chollo.getLikes()-1);
		}
		else {
			logger.info("----------------ARRIVES ELSE-------------------");
			likesDao.add(like);
			chollo.setLikes(chollo.getLikes()+1);
		}
		
		cholloDao.save(chollo);
		
		List<Chollo> chollosList = cholloDao.getAll();
		
		//request.setAttribute("chollosList", chollosList);
		request.setAttribute("chollosList", chollosList);
	
		RequestDispatcher view = request.getRequestDispatcher("indexUserView.jsp");
		view.forward(request, response);
	}

}
