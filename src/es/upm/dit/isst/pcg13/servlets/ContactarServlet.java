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

@WebServlet("/ContactarServlet")
public class ContactarServlet extends HttpServlet {
	
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

	 User user1 = UserDAOImplementation.getInstance().getUser(nick1);
	 User user2 = UserDAOImplementation.getInstance().getUser(nick2);
	//creamos la peticion
	 //primero miramos si no hemos pedido amistad antes
	 String json;
	 boolean twice = false;
	 
		if (user1==null || user2==null) {
			json =new  Gson().toJson("wrong");
			
	}
		else {
			//si ya la has solicitado esta repetida
			for (Peticion p: UserDAOImplementation.getInstance().readMisPeticiones(nick1)) {
				
				 if (p.getSolicitado().getNick().equals(user2.getNick())) {
					 System.out.println("llego aqui");
					 twice = true;
				 }
			 }
			//o si ya la has aceptado esta repetida
			for (Peticion p: UserDAOImplementation.getInstance().readPeticiones(nick1)) {
				 if (p.getSolicitante().getNick().equals(user2.getNick()) && p.getEstado()==1) {
					 twice = true;
				 }
				 
			 }
	 //si no esta repetida, creamos la peticion
	 if (!twice) {
		 System.out.println("contactado");
		Peticion p = new Peticion();
		p.setEstado(0);
		p.setSolicitado(user2);
		p.setSolicitante(user1);
		PeticionDAOImplementation.getInstance().createPeticion(p);
		json=new  Gson().toJson("creada");
	 }
	 else {
		 System.out.println("repetido");
		 json =new  Gson().toJson("operacion repetida");
		  
	 }
		} 
	resp.setContentType("application/json");
	resp.setCharacterEncoding("utf-8");
	


	resp.getWriter().write(json);
	
}	

}
