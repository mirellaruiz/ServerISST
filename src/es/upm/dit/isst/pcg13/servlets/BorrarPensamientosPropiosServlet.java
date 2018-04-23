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
import es.upm.dit.isst.pcg13.dao.model.Pensamiento;
import es.upm.dit.isst.pcg13.dao.model.User;

@WebServlet("/BorrarPensamientosPropiosServlet")
public class BorrarPensamientosPropiosServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nickname = req.getParameter("nick");
		int id = Integer.parseInt(req.getParameter("pensId"));
		User user = UserDAOImplementation.getInstance().getUser(nickname);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		if(PensamientoDAOImplementation.getInstance().pensamientoEstaGuardado(id)) {
			PensamientoDAOImplementation.getInstance().deletePensamiento(id);
			
			resp.getWriter().write("ok");
		}
		resp.getWriter().write("no se ha borrado nada");
	}	
}
