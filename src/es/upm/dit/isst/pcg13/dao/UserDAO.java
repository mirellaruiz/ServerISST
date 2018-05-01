package es.upm.dit.isst.pcg13.dao;

import java.util.List;

import es.upm.dit.isst.pcg13.dao.model.Pensamiento;
import es.upm.dit.isst.pcg13.dao.model.User;

public interface UserDAO {
	public void createUser(User user);
	public User getUser(String nick);
	public void updateUser (User user);
	public void deleteUser (User user);
	public List<Pensamiento> readGuardados (String nickname);
	//public List<Pensamiento> readConLike (String nickname);
	public List<User> readContactos (String nickname);
	public User loginUser(String nick, String password);

}
