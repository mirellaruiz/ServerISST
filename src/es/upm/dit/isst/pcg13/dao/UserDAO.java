package es.upm.dit.isst.pcg13.dao;

import java.util.List;

import es.upm.dit.isst.pcg13.dao.model.User;

public interface UserDAO {
	public void createUser(User user);
	public User getUser(String nick);
	public void updateUser (User user);
	public void deleteUser (User user);
	
	public User loginUser(String nick, String password);

}
