package es.upm.dit.isst.pcg13.servlets;

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

import es.upm.dit.isst.pcg13.dao.PensamientoDAOImplementation;
import es.upm.dit.isst.pcg13.dao.UserDAOImplementation;
import es.upm.dit.isst.pcg13.dao.model.Pensamiento;
import es.upm.dit.isst.pcg13.dao.model.User;
@WebServlet("/PublicarServlet")
public class PublicarServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Pensamiento pensamiento = new Pensamiento();
	String nickname = req.getParameter("nick");
	String text = req.getParameter("text");
	Float lat= Float.parseFloat(req.getParameter("lat"));
	
	Float lon = Float.parseFloat(req.getParameter("lon"));
	String topic = req.getParameter("topic");
	System.out.println(nickname);
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
