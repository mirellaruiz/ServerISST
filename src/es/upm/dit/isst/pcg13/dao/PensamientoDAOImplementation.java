package es.upm.dit.isst.pcg13.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.pcg13.dao.model.Pensamiento;
import es.upm.dit.isst.pcg13.dao.model.User;
import es.upm.dit.isst.pcg13.utils.Utils;
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
	public int getLastId(){
		int id = 0;
		
		Session session = SessionFactoryService.get().openSession();//Se abre la sesion
		try {
			session.beginTransaction();
			id = ((Pensamiento) session.createQuery("from Pensamiento ORDER BY idPens DESC").uniqueResult())!=null? ((Pensamiento) session.createQuery("from Pensamiento ORDER BY idPens DESC").uniqueResult()).getIdPens():0;
			System.out.println(id);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
			
		}
		return id;
		
	}
	
	@Override
	public boolean pensamientoEsPropio(int idPens){
		
		boolean result = false;
		Session session = SessionFactoryService.get().openSession();//Se abre la sesion
		try {
			session.beginTransaction();
			//Hay que valorar  si el comando SQL es el correcto.
			result = (session.createQuery("select u.pensamientosPropios from User u where u.id = :id").setParameter("id", idPens).uniqueResult()!=null);
			System.out.println("El pensamiento con ID "+idPens+ "es un pensamiento propio");
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
			
		}
		return result;
		
	}
	
	@Override
	public boolean pensamientoEstaGuardado(int idPens, String nick){
		
		boolean result = false;
		Session session = SessionFactoryService.get().openSession();//Se abre la sesion
		try {
			session.beginTransaction();
			//Hay que valorar  si el comando SQL es el correcto.
			//Sin acabar
			result = (session.createQuery("select u.guardados from User u where u.nick = :nick").setParameter("nick", nick).uniqueResult()!=null);
			System.out.println("El pensamiento con ID "+idPens+ "esta guardado");
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
			
		}
		return result;
		
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
	public List<Pensamiento> readNearest(double lat, double lon) {
		List<Pensamiento> pensamientos = new ArrayList<>();
		List<Pensamiento> cercanos = new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			pensamientos.addAll(
					session.createQuery("from Pensamiento").list()
					);
			session.getTransaction().commit();			
		} catch (Exception e) {
			
		} finally {
			session.close();
			for (Pensamiento pensamiento: pensamientos) {
				if (Utils.distance(lat, lon, Double.valueOf(pensamiento.getLatitude()),Double.valueOf(pensamiento.getLongitude())) < 20) {
					System.out.println(pensamiento.getAuthor());
					cercanos.add(pensamiento);
				}
			}
		}
		return cercanos;
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
	public void deletePensamiento(int idPens) {
		Pensamiento pens = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			pens = session.get(Pensamiento.class, idPens);
			session.delete(pens);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
		}
		
	}
@Override
public List<Pensamiento> readAll(String nick){
	Session session = SessionFactoryService.get().openSession();
	List<Pensamiento> pensamientos = new ArrayList<>();
	try {
		session.beginTransaction();
		pensamientos.addAll( session.createQuery("select p from Pensamiento p where p.author.nick = :nick")
						.setParameter("nick", nick)			
				.list());
		session.getTransaction().commit();			
	} catch (Exception e) {
		
	} finally {
		for (Pensamiento p: pensamientos) {
			System.out.println(p.getText());
		}
		session.close();
		
	}
	return pensamientos;
}
	@Override
	public void drop() {

		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.createQuery("delete from Pensamiento");
			session.getTransaction().commit();
		}
		catch(Exception e) {
			
		} finally {
			session.close();
		}
		
	}

	

}
