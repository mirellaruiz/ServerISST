package es.upm.dit.isst.pcg13.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import es.upm.dit.isst.pcg13.dao.UserDAOImplementation;
import es.upm.dit.isst.pcg13.dao.model.User;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String nick = req.getParameter("nick");
	String password = req.getParameter("password");
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