package es.upm.dit.isst.pcg13.dao;

import es.upm.dit.isst.pcg13.dao.model.User;
import org.hibernate.Session;
public class UserDAOImplementation implements UserDAO {
	
	private static UserDAOImplementation instance;//Para modelo singleton
	private UserDAOImplementation() {};
	public static UserDAOImplementation getInstance() {
		if(null==instance) instance = new UserDAOImplementation();
		return instance;
	}

	@Override
	public void createUser(User user) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}
		catch (Exception e){
			
		}finally {
			session.close();
		}
	}
	
		
		
	

	@Override
	public User getUser(String nick) {
		User user = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			user = session.get(User.class, nick);
			session.getTransaction().commit();			
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public void updateUser(User user) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(user);
			session.getTransaction().commit();
		}
		catch (Exception e){
			
		}finally {
			session.close();
		}
		
	}

	@Override
	public void deleteUser(User user) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		}
		catch (Exception e){
			
		}finally {
			session.close();
		}
		
	}

	@Override
	public User loginUser(String nick, String password) {
	User user = null;
			Session session = SessionFactoryService.get().openSession();
			try {
				session.beginTransaction();
				user = (User) session.createQuery("select u from User u " + 
				"where u.nick = :nick and u.password = :password")
					.setParameter("nick", nick)
					.setParameter("password", password)
					.getSingleResult();
				session.getTransaction().commit();
			}
			catch (Exception e){
				System.out.println(e);
			}finally {
				session.close();
			}
			return user;
		}
		
	}


