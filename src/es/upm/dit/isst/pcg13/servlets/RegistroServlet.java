package es.upm.dit.isst.pcg13.servlets;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.pcg13.dao.UserDAOImplementation;
import es.upm.dit.isst.pcg13.dao.model.User;
import com.google.gson.*;


@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {
	
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String nick = req.getParameter("nick");
	String password = req.getParameter("password");
	System.out.println(nick+" "+password);
	User user = new User();
	user.setNick(nick);
	user.setPassword(password);
	//persistimos el dato
	UserDAOImplementation.getInstance().createUser(user);
	resp.setContentType("application/json");
	resp.setCharacterEncoding("utf-8");
	String hello = "hello from server";
	String json =new  Gson().toJson(hello);
	resp.getWriter().write(json);
	
	
	
}	

}
