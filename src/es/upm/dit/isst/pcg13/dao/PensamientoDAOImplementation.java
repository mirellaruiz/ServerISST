package es.upm.dit.isst.pcg13.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.pcg13.dao.model.Pensamiento;
import es.upm.dit.isst.pcg13.dao.model.User;
import es.upm.dit.isst.pcg13.dao.model.Valoracion;
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
	public List<Pensamiento> readNearest(double lat, double lon, double dis, User user) {
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
				boolean valoradoLike = false;
				System.out.println("nuevo pensamiento");
				
				for(Valoracion val: pensamiento.getValoraciones()) {
					System.out.println("nueva valoracion");
					System.out.println(val.getAuthor().getNick());
					if (val.getAuthor().getNick().equals(user.getNick())) {
						System.out.println("ya ha sido valorado");
						valoradoLike = true;
						break;
					}
				}
				if (!pensamiento.getAuthor().getNick().equals(user.getNick()) && !valoradoLike && Utils.distance(lat, lon, Double.valueOf(pensamiento.getLatitude()),Double.valueOf(pensamiento.getLongitude())) < dis) {
					pensamiento.setLikes(getLikes(pensamiento));
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
		for (Pensamiento pens: pensamientos) {
			pens.setLikes(getLikes(pens));
		}
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
	@Override
	public int getLikes(Pensamiento pensamiento) {
		Session session = SessionFactoryService.get().openSession();
		List<Valoracion> valorados = new ArrayList<>();
		int likes = 0;
		try {
			
			session.beginTransaction();
			valorados.addAll(session.createQuery("select p.valoraciones from Pensamiento p where p.idPens = :idPens").setParameter("idPens", pensamiento.getIdPens()).list());
			session.getTransaction().commit();
			for (Valoracion val: valorados) {
				if (val.getValor() == true){
					likes++;
				}
			}
		}
		catch(Exception e) {
			
		} finally {
			session.close();
		
			}
		System.out.println(likes);
			return likes;
		}
		
	
	@Override
	public int getDislikes(Pensamiento pensamiento) {
		Session session = SessionFactoryService.get().openSession();
		List<Valoracion> valorados = new ArrayList<>();
		int dislikes = 0;
		try {
			
			session.beginTransaction();
			valorados.addAll(session.createQuery("select p.valoraciones from Pensamiento p where p.idPens = :idPens").setParameter("idPens", pensamiento.getIdPens()).list());
			session.getTransaction().commit();
			for (Valoracion val: valorados) {
				if (val.getValor() == false){
					dislikes++;
				}
			}
		}
		catch(Exception e) {
			
		} finally {
			session.close();
		
			}
			return dislikes;
		}
	

	

}
