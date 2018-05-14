package es.upm.dit.isst.pcg13.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import es.upm.dit.isst.pcg13.dao.PensamientoDAOImplementation;
import es.upm.dit.isst.pcg13.dao.UserDAOImplementation;
import es.upm.dit.isst.pcg13.dao.model.Pensamiento;
import es.upm.dit.isst.pcg13.dao.model.User;
@WebServlet("/PublicarServlet")
public class PublicarServlet extends HttpServlet {
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
		String text = jsonObject.get("text").getAsString();
		Float lat = jsonObject.get("lat").getAsFloat();
		String topic = jsonObject.get("topic").getAsString();
		Float lon = jsonObject.get("lon").getAsFloat();
		System.out.println(lat);
	Pensamiento pensamiento = new Pensamiento();
	//Float lat= Float.parseFloat(req.getParameter("lat"));
	
	//Float lon = Float.parseFloat(req.getParameter("lon"));
	
	
	User user = UserDAOImplementation.getInstance().getUser(nickname);
	pensamiento.setAuthor(user);
	pensamiento.setText(text);
	pensamiento.setLatitude(lat);
	pensamiento.setLongitude(lon);
	pensamiento.setTopic(topic);
	
	
	pensamiento.setDate(new Date());

	resp.setContentType("application/json");
	resp.setCharacterEncoding("utf-8");
	String json;
	
	PensamientoDAOImplementation.getInstance().createPensamiento(pensamiento);
	
	json =new  Gson().toJson("ok");	
	
	resp.getWriter().write(json);
	
	/* ESTO ES UNA PRUEBA DE OTRA FORMA DE MANDAR UN JSON*/
	 
	 
}
}
