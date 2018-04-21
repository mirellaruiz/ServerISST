package es.upm.dit.isst.pcg13.dao;

import org.hibernate.Session;

import es.upm.dit.isst.pcg13.dao.model.Pensamiento;
public class PensamientoDAOImplementation implements PensamientoDAO{
	
	private static PensamientoDAOImplementation instance;
	private PensamientoDAOImplementation() {};
	public static PensamientoDAOImplementation getInstance() {
		if(null == instance) instance = new PensamientoDAOImplementation();
		return instance;
	}

	@Override
	public void createPensamiento(Pensamiento pensamiento) {
		Session session = SessionFactoryService.get().openSession();//Se abre la sesion
		try {
			session.beginTransaction();
			session.save(pensamiento);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
		}
		
		
	}

	@Override
	public Pensamiento readPensamiento(int idPens) {
		Pensamiento pens = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			pens = session.get(Pensamiento.class, idPens);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
		}
		return pens;
	}

	@Override
	public void updatePensamiento(Pensamiento pensamiento) {

		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(pensamiento);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
		}		
	}

	@Override
	public void deletePensamiento(Pensamiento pensamiento) {

		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(pensamiento);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
		}
		
	}

}
