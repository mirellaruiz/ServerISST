/*package es.upm.dit.isst.pcg13.servlets;

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
@WebServlet("/GuardarPensamientoServlet")
public class GuardarPensamientoServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	String nickname = req.getParameter("nick");
	int id = Integer.parseInt(req.getParameter("pensId"));
	Pensamiento aGuardar = PensamientoDAOImplementation.getInstance().readPensamiento(id);
	User user = UserDAOImplementation.getInstance().getUser(nickname);
	List<Pensamiento> guardados = user.getGuardados();
	guardados.add(aGuardar);
	user.setGuardados(guardados);
	UserDAOImplementation.getInstance().updateUser(user);
	//propios = PensamientoDAOImplementation.getInstance().getPensamientos(nickname);
	resp.setContentType("application/json");
	resp.setCharacterEncoding("utf-8");
	
	
	resp.getWriter().write("ok");
	
	
	 
	 
}
}*/

