package es.upm.dit.isst.pcg13.dao;

import java.util.List;

import es.upm.dit.isst.pcg13.dao.model.Pensamiento;

public interface PensamientoDAO {

	public void createPensamiento (Pensamiento pensamiento);
	public Pensamiento readPensamiento (int idPens);
	public void updatePensamiento(Pensamiento pensamiento);
	public void deletePensamiento(int idPens);
	public List<Pensamiento> readNearest(double lat, double lon);
	public boolean pensamientoEsPropio (int idPens);
	public boolean pensamientoEstaGuardado (int idPens, String nick);
	public int getLastId();
	public void drop();
	public List<Pensamiento> readAll(String nick);
}
