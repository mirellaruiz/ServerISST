package es.upm.dit.isst.pcg13.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
@WebServlet("/PensamientosCercanosServlet")
public class PensamientosCercanosServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Double lat=Double.parseDouble(req.getParameter("lat"));
	Double lon = Double.parseDouble(req.getParameter("lon"));
	Double dis = Double.parseDouble(req.getParameter("dist"));
	User user = UserDAOImplementation.getInstance().getUser(req.getParameter("nick"));

	List<Pensamiento> cercanos = null;
	cercanos = PensamientoDAOImplementation.getInstance().readNearest(lat, lon, dis, user);
	resp.setContentType("application/json");
	resp.setCharacterEncoding("utf-8");
	JsonArray jsonA = new JsonArray();
	Collections.sort(cercanos, new Comparator<Pensamiento>() {
		

		@Override
		public int compare(Pensamiento p1, Pensamiento p2) {
			
			return (new Integer(p2.getLikes()).compareTo(new Integer(p1.getLikes())));
		}
	});
	for (Pensamiento pens: cercanos) {
	JsonObject j = new JsonObject();
	j.addProperty("id", String.valueOf(pens.getIdPens()));
	
	String dateFormat = pens.getDate().toString().substring(0, 19);
	String dateSent = new String();
	SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat formateador = new SimpleDateFormat("dd/MM HH:mm");
try {
	Date date = parseador.parse(dateFormat);
	dateSent= formateador.format(date);
}
catch (Exception e){
	
}
	j.addProperty("date", dateSent);
	
	j.addProperty("latitude", String.valueOf(pens.getLatitude()));
	
	j.addProperty("longitude",String.valueOf(pens.getLongitude()));
	System.out.println(pens.getDate().toString());
	j.addProperty("topic",  pens.getTopic());
	j.addProperty("text", pens.getText());
	System.out.println(pens.getText());
	if (pens.getAuthor() != null)
	j.addProperty("autor", pens.getAuthor().getNick());
	j.addProperty("likes", String.valueOf(pens.getLikes()));
	jsonA.add(j);
	}
	
	String json;
	json =new  Gson().toJson(jsonA);	
	System.out.println(json);
	resp.getWriter().write(json);
	
	/* ESTO ES UNA PRUEBA DE OTRA FORMA DE MANDAR UN JSON*/
	 
	 
}
}
