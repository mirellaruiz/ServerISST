package es.upm.dit.isst.pcg13.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.pcg13.dao.UserDAOImplementation;
import es.upm.dit.isst.pcg13.dao.model.User;



@WebServlet("/BorrarContactoServlet")
public class BorrarContactoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nick1 = req.getParameter("nick1");
		String nick2 = req.getParameter("nick2");
		
		User user1 = UserDAOImplementation.getInstance().getUser(nick1);
		 User user2 = UserDAOImplementation.getInstance().getUser(nick2);
		 
	List<User> contactos = UserDAOImplementation.getInstance().deleteContactos(user1, user2);
		
	resp.setContentType("application/json");
	resp.setCharacterEncoding("utf-8");
	
	resp.getWriter().write("ok");
		
		/*String json;
		
		json =new  Gson().toJson("borrada");	
		
		resp.getWriter().write(json); */
}
	
}
