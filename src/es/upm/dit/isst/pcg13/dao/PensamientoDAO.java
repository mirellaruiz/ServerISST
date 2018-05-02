package es.upm.dit.isst.pcg13.dao;

import java.util.List;

import es.upm.dit.isst.pcg13.dao.model.Pensamiento;
import es.upm.dit.isst.pcg13.dao.model.User;

public interface PensamientoDAO {

	public void createPensamiento (Pensamiento pensamiento);
	public Pensamiento readPensamiento (int idPens);
	public void updatePensamiento(Pensamiento pensamiento);
	public void deletePensamiento(int idPens);
	public List<Pensamiento> readNearest(double lat, double lon, double dis, User user);

	public int getLikes(Pensamiento pensamiento);
	public int getDislikes(Pensamiento pensamiento);
	public int getLastId();
	public void drop();
	public List<Pensamiento> readAll(String nick);
}
