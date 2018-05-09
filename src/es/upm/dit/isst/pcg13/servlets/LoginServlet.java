package es.upm.dit.isst.pcg13.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import es.upm.dit.isst.pcg13.dao.UserDAOImplementation;
import es.upm.dit.isst.pcg13.dao.model.User;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
*/

	StringBuffer jb = new StringBuffer();
	  String line = null;
	  try {
	    BufferedReader reader = req.getReader();
	    while ((line = reader.readLine()) != null)
	      jb.append(line);
	  } catch (Exception e) { System.out.println("error al parsear peticion"); }

	    JsonObject jsonObject =  new Gson().fromJson(jb.toString(), JsonObject.class);

	    System.out.println(jsonObject);
	    
	String nick = jsonObject.get("nick").getAsString();
	String password = jsonObject.get("password").getAsString();
	
	//String nick = req.getParameter("nick");
	//String password = req.getParameter("password");
	System.out.println(nick+" "+password);
	User user = null;

	//Persistimos el dato

	user = UserDAOImplementation.getInstance().loginUser(nick, password);
	resp.setContentType("application/json");
	resp.setCharacterEncoding("utf-8");
	String json;
	if (user == null) {
	json =new  Gson().toJson("wrong");
	}
	else {
	json =new  Gson().toJson("ok");
	}
	resp.getWriter().write(json);

	/* ESTO ES UNA PRUEBA DE OTRA FORMA DE MANDAR UN JSON
	 List<String> lista = new ArrayList<String>();
	 lista.add(nick+"hola");
	 lista.add(password);

	 Gson gson= new Gson();
	 PrintWriter pw=resp.getWriter();
	 pw.println( gson.toJson(lista));
	 pw.close();
	 */



}

}
