package es.upm.dit.isst.pcg13.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import es.upm.dit.isst.pcg13.dao.PeticionDAOImplementation;
import es.upm.dit.isst.pcg13.dao.UserDAOImplementation;
import es.upm.dit.isst.pcg13.dao.model.Peticion;
import es.upm.dit.isst.pcg13.dao.model.User;

@WebServlet("/AceptarServlet")
public class AceptarServlet extends HttpServlet {
	
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	StringBuffer jb = new StringBuffer();
	  String line = null;
	  try {
	    BufferedReader reader = req.getReader();
	    while ((line = reader.readLine()) != null)
	      jb.append(line);
	  } catch (Exception e) { System.out.println("error al parsear peticion"); }

	    JsonObject jsonObject =  new Gson().fromJson(jb.toString(), JsonObject.class);

		String nick1 = jsonObject.get("nick1").getAsString();
		String nick2 = jsonObject.get("nick2").getAsString();
		Boolean action = jsonObject.get("action").getAsBoolean();

	 User user1 = UserDAOImplementation.getInstance().getUser(nick1);
	 User user2 = UserDAOImplementation.getInstance().getUser(nick2);
	 String json;
		if (user1==null || user2==null) {
			json =new  Gson().toJson("wrong");
	}
		else {
			Peticion p = PeticionDAOImplementation.getInstance().getPeticion(user1, user2);
			
			if (action == true) {
				json =new  Gson().toJson("aceptada");
				p.setEstado(1);
				}
			else {
				json =new  Gson().toJson("denegada");
				 
				p.setEstado(2);
				
			}
			PeticionDAOImplementation.getInstance().updatePeticion(p);
		
		}
	
	resp.setContentType("application/json");
	resp.setCharacterEncoding("utf-8");

	resp.getWriter().write(json);
	
}	

}

