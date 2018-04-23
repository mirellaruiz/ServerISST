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


@WebServlet("/ContactosServlet")
public class ContactosServlet extends HttpServlet {
	
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String nick = req.getParameter("nick");
	
	System.out.println(nick);
	
	
	
	
	
	
	 //List<String> contactos = new ArrayList<String>();
	 

	 List<User> contactos = null;
	contactos = UserDAOImplementation.getInstance().readContactos(nick);
	resp.setContentType("application/json");
	resp.setCharacterEncoding("utf-8");
	JsonArray jsonA = new JsonArray();
	if (contactos.size() > 0) {
		for (User usuario: contactos) {
			JsonObject j = new JsonObject();
			j.addProperty("nick", usuario.getNick());
			j.addProperty("pensamientoscontacto", usuario.getPensamientosPropios().toString());
			//No sé si será necesario añadir los pensamientos de los propios contactos al Json
			j.addProperty("facebook", usuario.getFacebook());
			j.addProperty("twitter", usuario.getTwitter());
			jsonA.add(j);
		}
	}
	String json;
	json =new  Gson().toJson(jsonA);	
	System.out.println(json);
	resp.getWriter().write(json);
	/*
	Gson gson= new Gson();
	PrintWriter pw=resp.getWriter();
	 pw.println( gson.toJson(contactos));
	 pw.close();
	*/
	
	
}	

}