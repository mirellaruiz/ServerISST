package es.upm.dit.isst.pcg13.servlets;

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
import es.upm.dit.isst.pcg13.dao.ValoracionDAOImplementation;
import es.upm.dit.isst.pcg13.dao.model.Pensamiento;
import es.upm.dit.isst.pcg13.dao.model.User;
@WebServlet("/PensamientosValoradosServlet")
public class PensamientosValoradosServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	String nickname = req.getParameter("nick");
	User user = UserDAOImplementation.getInstance().getUser(nickname);

	List<Pensamiento> guardados = null;
	guardados = ValoracionDAOImplementation.getInstance().getPensamientosValorados(user);
	
	//propios = PensamientoDAOImplementation.getInstance().getPensamientos(nickname);
	resp.setContentType("application/json");
	resp.setCharacterEncoding("utf-8");
	JsonArray jsonA = new JsonArray();
	if (guardados.size() > 0) {
	for (Pensamiento pens: guardados) {
	JsonObject j = new JsonObject();
	j.addProperty("id", String.valueOf(pens.getIdPens()));
	
	j.addProperty("date", pens.getDate().toString());
	
	j.addProperty("latitude", String.valueOf(pens.getLatitude()));
	
	j.addProperty("longitude",String.valueOf(pens.getLongitude()));
	System.out.println(pens.getDate().toString());
	j.addProperty("topic",  pens.getTopic());
	j.addProperty("text", pens.getText());
	if (pens.getAuthor() != null)
	j.addProperty("autor", pens.getAuthor().getNick());
	jsonA.add(j);
	}
	}
	String json;
	json =new  Gson().toJson(jsonA);	
	System.out.println(json);
	resp.getWriter().write(json);
	
	/* ESTO ES UNA PRUEBA DE OTRA FORMA DE MANDAR UN JSON*/
	 
	 
}
}
