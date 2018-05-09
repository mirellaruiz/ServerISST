package es.upm.dit.isst.pcg13.dao;

import org.hibernate.Session;

import es.upm.dit.isst.pcg13.dao.model.Peticion;

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
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
		}
		
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
