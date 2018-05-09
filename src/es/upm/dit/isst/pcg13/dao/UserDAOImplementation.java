package es.upm.dit.isst.pcg13.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.pcg13.dao.model.Pensamiento;
import es.upm.dit.isst.pcg13.dao.model.User;
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
	public List<Pensamiento> getPropios(String nick) {
		User user = null;
		Session session = SessionFactoryService.get().openSession();
		List<Pensamiento> pens = new ArrayList<>();
		try {
			session.beginTransaction();
			pens.addAll(session.createQuery("select u.pensamientosPropios from User u where u.nick = :nick").setParameter("nick", nick).list());
			session.getTransaction().commit();			
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		return pens;
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
	@Override
	public List<User> createContactos (User user1, User user2){
		List<User> contactos = new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
			user1.getContactos().add(user2);
			user2.getContactos().add(user1);
			session.beginTransaction();
			//No sé si la createquery es la correcta
		
			session.saveOrUpdate(user1);
			session.saveOrUpdate(user2);
			
			session.getTransaction().commit();
		}
		catch (Exception e){
			System.out.println(e);
		}finally {
			session.close();
		}
		return contactos;
	}
	
	@Override
	public List<User> deleteContactos (User user1, User user2){
		List<User> contactos = new ArrayList<>();
		List<User> contactos2 = new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
			contactos = user1.getContactos();
			
			Iterator<User> it= contactos.iterator();
			 
			while(it.hasNext()) {
			 
			String nombre= it.next().getNick();
			if (nombre.equals(user2.getNick())) {
			 
			it.remove();
			}
			}
			
			contactos2 = user2.getContactos();
			
			Iterator<User> it2= contactos2.iterator();
			 
			while(it2.hasNext()) {
			 
			String nombre2= it2.next().getNick();
			if (nombre2.equals(user2.getNick())) {
			 
			it2.remove();
			}
			}
			
			session.beginTransaction();
			
			user1.setContactos(contactos);
			user2.setContactos(contactos2);
			
			
			session.saveOrUpdate(user1);
			session.saveOrUpdate(user2);
			
			session.getTransaction().commit();
		}
		catch (Exception e){
			System.out.println(e);
		}finally {
			session.close();
		}
		return contactos;
	}
	
	@Override
	public List<User> readPeticiones(String nickname){
		List<User> contactos = new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			//No sé si la createquery es la correcta
			contactos.addAll(session.createQuery("select u.myFriends from User u " + 
			"where u.nick = :nick")
				.setParameter("nick", nickname)
				.list());
			session.getTransaction().commit();
		}
		catch (Exception e){
			System.out.println(e);
		}finally {
			session.close();
		}
		return contactos;
	}
		@Override
		public List<User> readMisPeticiones(String nickname){
			List<User> contactos = new ArrayList<>();
			Session session = SessionFactoryService.get().openSession();
			try {
				session.beginTransaction();
				//No sé si la createquery es la correcta
				contactos.addAll(session.createQuery("select u.myFriends from User u " + 
				"where u.nick = :nick")
					.setParameter("nick", nickname)
					.list());
				session.getTransaction().commit();
			}
			catch (Exception e){
				System.out.println(e);
			}finally {
				session.close();
			}
			return contactos;
		}
		@Override
		public List<User> readContactos(String nickname){
			List<User> contactos = new ArrayList<>();
			Session session = SessionFactoryService.get().openSession();
			try {
				session.beginTransaction();
				//No sé si la createquery es la correcta
				contactos.addAll(session.createQuery("select u.myContacts from User u " + 
				"where u.nick = :nick")
					.setParameter("nick", nickname)
					.list());
				session.getTransaction().commit();
			}
			catch (Exception e){
				System.out.println(e);
			}finally {
				session.close();
			}
			return contactos;
		}
		public void aceptarContacto (User user1, User user2) {
			Session session = SessionFactoryService.get().openSession();
			try {
				readContactos(user1.getNick());
				session.beginTransaction();
				//No sé si la createquery es la correcta
				
				session.getTransaction().commit();
			}
			catch (Exception e){
				System.out.println(e);
			}finally {
				session.close();
			}
				}
	}


