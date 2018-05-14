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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import es.upm.dit.isst.pcg13.dao.PensamientoDAOImplementation;
import es.upm.dit.isst.pcg13.dao.UserDAOImplementation;
import es.upm.dit.isst.pcg13.dao.model.Pensamiento;
import es.upm.dit.isst.pcg13.dao.model.User;

@WebServlet("/BorrarPensamientosPropiosServlet")
public class BorrarPensamientosPropiosServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = req.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { System.out.println("error al parsear peticion"); }

		    JsonObject jsonObject =  new Gson().fromJson(jb.toString(), JsonObject.class);

			String nickname = jsonObject.get("nick").getAsString();
		int id = jsonObject.get("pensId").getAsInt();
		User user = UserDAOImplementation.getInstance().getUser(nickname);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		if(user != null) {
			List <Pensamiento> propios = UserDAOImplementation.getInstance().getPropios(nickname);
		for (Pensamiento p: propios) {
			if (p.getIdPens() == id) {
				PensamientoDAOImplementation.getInstance().deletePensamiento(id);
				resp.getWriter().write("ok");
				break;
				
			}
		}
		resp.getWriter().write("no se ha borrado nada");	
			
		}
		resp.getWriter().write("error de autenticacion");
	}	
}
