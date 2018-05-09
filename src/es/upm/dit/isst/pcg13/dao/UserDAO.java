package es.upm.dit.isst.pcg13.dao;

import java.util.List;

import es.upm.dit.isst.pcg13.dao.model.Pensamiento;
import es.upm.dit.isst.pcg13.dao.model.User;

public interface UserDAO {
	public void createUser(User user);
	public User getUser(String nick);
	public void updateUser (User user);
	public void deleteUser (User user);
	//public List<Pensamiento> readConLike (String nickname);
	public List<User> readContactos (String nickname);
	public List<User> readPeticiones (String nickname);
	public User loginUser(String nick, String password);
	public List<Pensamiento> getPropios(String nick);
	List<User> createContactos(User user1, User user2);
	List<User> deleteContactos(User user1, User user2);
	List<User> readMisPeticiones(String nickname);
	
}
