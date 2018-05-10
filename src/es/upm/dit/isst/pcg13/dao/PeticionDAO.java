package es.upm.dit.isst.pcg13.dao;

import java.util.List;

import es.upm.dit.isst.pcg13.dao.model.Peticion;
import es.upm.dit.isst.pcg13.dao.model.User;

public interface PeticionDAO {
	public void createPeticion (Peticion peticion);
	public void deletePeticion (Peticion peticion);
	public void updatePeticion (Peticion peticion);
	public Peticion getPeticion(User user1, User user2);
	
}
