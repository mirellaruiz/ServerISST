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
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

/*
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
*/
	// TODO Auto-generated method stub
	String nick = req.getParameter("nick");
	String password = req.getParameter("password");
	System.out.println(nick+" "+password);
	User exist = UserDAOImplementation.getInstance().getUser(nick);
	String hello;
	if (exist != null) {
		hello = "already exist";

	}
	else {
	User user = new User();
	user.setNick(nick);
	user.setPassword(password);
	//persistimos el dato
	UserDAOImplementation.getInstance().createUser(user);
	hello = "hello from server";
	}
	resp.setContentType("application/json");
	resp.setCharacterEncoding("utf-8");

	String json =new  Gson().toJson(hello);
	resp.getWriter().write(json);



}

}
