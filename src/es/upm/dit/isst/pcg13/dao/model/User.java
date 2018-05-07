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

	//@ManyToMany(cascade = {CascadeType.ALL},  fetch = FetchType.EAGER)
	//@JoinTable(name="Guardados", joinColumns= {@JoinColumn (name="nick")}, inverseJoinColumns= {@JoinColumn(name="idPens")})
	//private List<Pensamiento> guardados;
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private List<User> myFriends;
//	
//	@ManyToMany(cascade = {CascadeType.ALL},  fetch = FetchType.EAGER) 
//	@JoinTable(name="Amigos", joinColumns= {@JoinColumn (name="myNick")}, inverseJoinColumns= {@JoinColumn(name="nickFriend")})
//	private List<User> myFriends;
//	
//	@ManyToMany(cascade = {CascadeType.ALL}, mappedBy="myFriends",  fetch = FetchType.EAGER)
//	private List<User> friendsWithMe;
//	
//	@OneToMany(mappedBy="userId2")
//	  private List<Contactos> contactos1;
//	
//	@OneToMany(mappedBy="userId1")
//	  private List<Contactos> contactos2;
//	
	/* public void addContacto(User user2) {
	    Contactos association = new Contactos();
	    association.setUser2(user2);
	    association.setUser1(this);
	    association.setUserId2(user2.getNick());
	    association.setUserId1(this.getNick());
	    
	    if(this.contactos2 == null)
	       this.contactos2 = new ArrayList<>();

	    this.contactos2.add(association);
	    // Also add the association object to the employee.
	    user2.getContactos().add(association);
	  }
	
	public List<Contactos> getContactos() {
		return contactos1;
	}
	
	public void setContactos(List<Contactos> Contactos) {
		this.contactos1 = Contactos;
	} */
	
	public String getNick() {
		return nick;
	}

	public User() {
		myFriends = new ArrayList();
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

	/*public List<Pensamiento> getGuardados() {
		return guardados;
	}

	public void setGuardados(List<Pensamiento> guardados) {
		this.guardados = guardados;
	}*/

	public List<Valoracion> getValorados() {
		return valorados;
	}

	public void setValorados(List<Valoracion> valorados) {
		this.valorados = valorados;
	}

	public List<User> getMyFriends() {
		return myFriends;
	}

	public void setMyFriends(List<User> myFriends) {
		this.myFriends = myFriends;
	}

	/*public List<User> getFriendsWithMe() {
		return friendsWithMe;
	}

	public void setFriendsWithMe(List<User> friendsWithMe) {
		this.friendsWithMe = friendsWithMe;
	} */
}
