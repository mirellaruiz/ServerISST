package es.upm.dit.isst.pcg13.dao;

import java.sql.PseudoColumnUsage;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.pcg13.dao.model.Pensamiento;
import es.upm.dit.isst.pcg13.dao.model.User;
import es.upm.dit.isst.pcg13.dao.model.Valoracion;

public class ValoracionDAOImplementation implements ValoracionDAO{
	
	private static ValoracionDAOImplementation instance;
	private ValoracionDAOImplementation() {};
	public static ValoracionDAOImplementation getInstance() {
		if(null == instance) instance = new ValoracionDAOImplementation();
		return instance;
	}


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
	@Override
	public void updateValoracion(Valoracion valoracion) {
		Session session = SessionFactoryService.get().openSession();//Se abre la sesion
		try {
			session.beginTransaction();
			session.saveOrUpdate(valoracion);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
		}
		
	}
	@Override
	public List<Pensamiento> getPensamientosValorados (User user) {
		List<Pensamiento> pens = new ArrayList<>();
			Session session = SessionFactoryService.get().openSession();//Se abre la sesion
		try {
			session.beginTransaction();
			pens.addAll(session.createQuery("select v.pensamiento from Valoracion v where v.author.nick = :nick and v.valor = :valor").setParameter("valor", true).list());
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
			
		}
		return pens;
	
	}

	
	@Override
	public Valoracion getValoracion(User user, Pensamiento pens) {
		Valoracion valoracion = new Valoracion();
		Session session = SessionFactoryService.get().openSession();//Se abre la sesion
		try {
			session.beginTransaction();
			valoracion = (Valoracion) session.createQuery("select v from Valoracion v where v.author.nick = :nick and v.pensamiento.idPens = :idPens").setParameter("nick", user.getNick()).setParameter("idPens", pens.getIdPens()).uniqueResult();
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
			
		}
		return valoracion;
	
	}


}
