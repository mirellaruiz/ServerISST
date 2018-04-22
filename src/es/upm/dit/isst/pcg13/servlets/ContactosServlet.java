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

import es.upm.dit.isst.pcg13.dao.UserDAOImplementation;
import es.upm.dit.isst.pcg13.dao.model.User;


@WebServlet("/ContactosServlet")
public class ContactosServlet extends HttpServlet {
	
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String nick = req.getParameter("nick");
	
	System.out.println(nick);
	
	
	
	resp.setContentType("application/json");
	resp.setCharacterEncoding("utf-8");
	
	
	 List<String> contactos = new ArrayList<String>();
	 

	 //FALTA DEFINIR PETICION A BASE DE DATOS
	 
	//contactos = UserDAOImplementation.getInstance().loginUser(nick, password);

	 
	Gson gson= new Gson();
	PrintWriter pw=resp.getWriter();
	 pw.println( gson.toJson(contactos));
	 pw.close();
	
	
	
}	

}