package es.upm.dit.isst.pcg13.dao;

import java.util.List;

import es.upm.dit.isst.pcg13.dao.model.Pensamiento;

public interface PensamientoDAO {

	public void createPensamiento (Pensamiento pensamiento);
	public Pensamiento readPensamiento (int idPens);
	public void updatePensamiento(Pensamiento pensamiento);
	public void deletePensamiento(Pensamiento pensamiento);
	public List<Pensamiento> readNearest(double lat, double lon);
	public int getLastId();
	void drop();
}
