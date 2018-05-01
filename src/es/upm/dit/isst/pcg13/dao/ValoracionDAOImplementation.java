package es.upm.dit.isst.pcg13.dao;

import org.hibernate.Session;

import es.upm.dit.isst.pcg13.dao.model.Valoracion;

public class ValoracionDAOImplementation implements ValoracionDAO{

	@Override
	public void createValoracion(Valoracion valoracion) {
		Session session = SessionFactoryService.get().openSession();//Se abre la sesion
		try {
			session.beginTransaction();
			session.save(valoracion);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
		}
		
	}

	@Override
	public void deleteValoracion(Valoracion valoracion) {
		Session session = SessionFactoryService.get().openSession();//Se abre la sesion
		try {
			session.beginTransaction();
			session.delete(valoracion);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
		}
		
	}

}
