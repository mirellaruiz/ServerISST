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


import es.upm.dit.isst.pcg13.dao.*;
import es.upm.dit.isst.pcg13.dao.model.Pensamiento;
import es.upm.dit.isst.pcg13.dao.model.User;
import es.upm.dit.isst.pcg13.dao.model.Valoracion;
@WebServlet("/BorrarValoracionServlet")
public class BorrarValoracionServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Valoracion valoracion = new Valoracion();
		String nickname = req.getParameter("nick");
		int idPens = Integer.parseInt(req.getParameter("pens"));
		
		User user = UserDAOImplementation.getInstance().getUser(nickname);
		Pensamiento pens = PensamientoDAOImplementation.getInstance().readPensamiento(idPens);
		
		valoracion = ValoracionDAOImplementation.getInstance().getValoracion(user, pens);
		System.out.println(valoracion.getIdVal());
		ValoracionDAOImplementation.getInstance().deleteValoracion(valoracion);
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		
		String json;
		
		json =new  Gson().toJson("borrada");	
		
		resp.getWriter().write(json);
		
		/* ESTO ES UNA PRUEBA DE OTRA FORMA DE MANDAR UN JSON*/
		 
		 
	}
	}
