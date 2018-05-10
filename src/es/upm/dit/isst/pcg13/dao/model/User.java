package es.upm.dit.isst.pcg13.dao.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
@Entity
public class User {
	@Id
	private String nick;
	private String password;
	private String facebook;
	private String twitter;
	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER, orphanRemoval=true)//relacion para BBDD
	private List<Pensamiento> pensamientosPropios; 
	
	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER, orphanRemoval=true)//relacion para BBDD
	private List<Valoracion> valorados; 

	@OneToMany(mappedBy = "solicitante", fetch = FetchType.EAGER, orphanRemoval=true)//relacion para BBDD, orphan removal para que borre todas las relaciones
	private List<Peticion> peticionesPropias; 
	
	@OneToMany(mappedBy = "solicitado", fetch = FetchType.EAGER, orphanRemoval=true)//relacion para BBDD, orphan removal para que borre todas las relaciones
	private List<Peticion> peticiones; 
	
	    

	

	public String getNick() {
		return nick;
	}

	public User() {
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}


	public List<Pensamiento> getPensamientosPropios() {
		return pensamientosPropios;
	}

	public void setPensamientosPropios(List<Pensamiento> pensamientosPropios) {
		this.pensamientosPropios = pensamientosPropios;
	}

	
	public List<Peticion> getPeticionesPropias() {
		return peticionesPropias;
	}

	public void setPeticionesPropias(List<Peticion> peticionesPropias) {
		this.peticionesPropias = peticionesPropias;
	}

	public List<Peticion> getPeticiones() {
		return peticiones;
	}

	public void setPeticiones(List<Peticion> peticiones) {
		this.peticiones = peticiones;
	}

	

	public List<Valoracion> getValorados() {
		return valorados;
	}

	public void setValorados(List<Valoracion> valorados) {
		this.valorados = valorados;
	}

	
}
