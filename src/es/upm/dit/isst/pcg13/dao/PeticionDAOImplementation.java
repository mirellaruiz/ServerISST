package es.upm.dit.isst.pcg13.dao;

import org.hibernate.Session;

import es.upm.dit.isst.pcg13.dao.model.Pensamiento;
import es.upm.dit.isst.pcg13.dao.model.Peticion;
import es.upm.dit.isst.pcg13.dao.model.User;
import es.upm.dit.isst.pcg13.dao.model.Valoracion;

public class PeticionDAOImplementation implements PeticionDAO{

	private static PeticionDAOImplementation instance;
	private PeticionDAOImplementation() {};
	public static PeticionDAOImplementation getInstance() {
		if(null == instance) instance = new PeticionDAOImplementation();
		return instance;
	}


	@Override
	public void createPeticion(Peticion peticion) {
		Session session = SessionFactoryService.get().openSession();//Se abre la sesion
		try {
			session.beginTransaction();
			session.save(peticion);
			System.out.println("nullls");
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
		}
		
	}
	//este metodo no funciona
	@Override
	public Peticion getPeticion(User user1, User user2) {
		Peticion peticion = new Peticion();
		Session session = SessionFactoryService.get().openSession();//Se abre la sesion
		try {
			session.beginTransaction();
			System.out.println(user1.getNick()+" "+user2.getNick());
			peticion= (Peticion) session.createQuery("select p from Peticion p where p.solicitante.nick = :nick1 and p.solicitado.nick = :nick2").setParameter("nick1", user1.getNick()).setParameter("nick2", user2.getNick()).uniqueResult();
			System.out.println(peticion.getId());
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
			
		}
		return peticion;
	
	}


	@Override
	public void deletePeticion(Peticion peticion) {
		Session session = SessionFactoryService.get().openSession();//Se abre la sesion
		try {
			session.beginTransaction();
			session.delete(peticion);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
		}
		
	}
	@Override
	public void updatePeticion(Peticion peticion) {
		Session session = SessionFactoryService.get().openSession();//Se abre la sesion
		try {
			session.beginTransaction();
			session.saveOrUpdate(peticion);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
		}
		
	}
	
}
