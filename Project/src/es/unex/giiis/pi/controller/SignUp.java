package es.unex.giiis.pi.controller;

import es.unex.giiis.pi.model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp.do")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		String passconf = request.getParameter("password_confirmation");
		PrintWriter out = response.getWriter();
		
		/* CONFIRMACION DE PASSWORD */
		if(!SignInUpValidate.validateSignUpPasswords(password, passconf)) {
			out.println("<html><body>WRONG PASS CONFIRMATION</body></html>");
			return;
		}
		out.println("<html><body>"+username+" "+email+" "+password+" "+passconf+" </br>REGISTERED!</body></html>");
		
		/* HACER EN CLASE MODELO, ES EL REGISTRO EN UN JSON DE UN USER */
		String outputPath = new String("");
		Writer writer;
		writer = new FileWriter(outputPath,true); // outputPath is the the Path where you want to save the file
		Gson gson = new Gson();
		gson.toJson(username, writer);
		writer.write("\n");
		writer.flush();
		writer.close();
	} 

}
