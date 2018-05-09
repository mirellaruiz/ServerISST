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

import es.upm.dit.isst.pcg13.dao.UserDAOImplementation;
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
	List<User> contactos = UserDAOImplementation.getInstance().createContactos(user1, user2);
	resp.setContentType("application/json");
	resp.setCharacterEncoding("utf-8");
	String json;
	if (user1==null || user2==null) {
		json =new  Gson().toJson("wrong");
}
else {
json =new  Gson().toJson("ok");
}
	resp.getWriter().write(json);
	
}	

}