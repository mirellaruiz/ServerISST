package es.upm.dit.isst.pcg13.servlets;

import java.io.IOException;
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
import es.upm.dit.isst.pcg13.dao.model.Peticion;
import es.upm.dit.isst.pcg13.dao.model.User;

@WebServlet("/PeticionesServlet")
public class PeticionesServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String nick = req.getParameter("nick");
 User user = UserDAOImplementation.getInstance().getUser(nick);
 List<User> contactos = new ArrayList();

	if (user==null) {
		//no hacemos nada
}
	else {
		
		//cogemos las que no hemos contstado
		for (Peticion p: UserDAOImplementation.getInstance().readPeticiones(nick)) {
			 if ( p.getEstado()==0) {
				 contactos.add(p.getSolicitante());
			 }
		 }
	}
	resp.setContentType("application/json");
	resp.setCharacterEncoding("utf-8");
	JsonArray jsonA = new JsonArray();
	if (contactos.size() > 0) {
		for (User usuario: contactos) {
			JsonObject j = new JsonObject();
			j.addProperty("nick", usuario.getNick());
			jsonA.add(j);
		}
	}
	String json;
	json =new  Gson().toJson(jsonA);
	resp.getWriter().write(json);
	
}
	
}