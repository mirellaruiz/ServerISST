package es.upm.dit.isst.pcg13.dao;

import es.upm.dit.isst.pcg13.dao.model.Pensamiento;
import es.upm.dit.isst.pcg13.dao.model.User;
import es.upm.dit.isst.pcg13.dao.model.Valoracion;

public interface ValoracionDAO {
	
	public void createValoracion (Valoracion valoracion);
	public void deleteValoracion (Valoracion valoracion);
	public void updateValoracion (Valoracion valoracion);
	public boolean comprobarValoracion (User user, Pensamiento pens);

}
