package es.upm.dit.isst.pcg13.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import es.upm.dit.isst.pcg13.dao.UserDAOImplementation;
import es.upm.dit.isst.pcg13.dao.model.User;


@WebServlet("/PeticionServlet")
public class PeticionServlet extends HttpServlet {
	
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String nick1 = req.getParameter("nick1");
	String nick2 = req.getParameter("nick2");
	
	
	
	
	
	
	
	 //List<String> contactos = new ArrayList<String>();

	 User user1 = UserDAOImplementation.getInstance().getUser(nick1);
	 User user2 = UserDAOImplementation.getInstance().getUser(nick2);
	List<User> contactos = UserDAOImplementation.getInstance().createContactos(user1, user2);
	resp.setContentType("application/json");
	resp.setCharacterEncoding("utf-8");
	
	resp.getWriter().write("ok");
	/*
	Gson gson= new Gson();
	PrintWriter pw=resp.getWriter();
	 pw.println( gson.toJson(contactos));
	 pw.close();
	*/
	
	
}	

}
