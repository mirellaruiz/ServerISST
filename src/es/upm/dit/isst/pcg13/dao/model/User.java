package es.upm.dit.isst.pcg13.dao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
@Entity
public class User {
	@Id
	private String nick;
	private String password;
	private String facebook;
	private String twitter;
	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER)//relacion para BBDD
	private List<Pensamiento> pensamientosPropios; 
	
	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER)//relacion para BBDD
	private List<Valoracion> valorados; 

	//@ManyToMany(cascade = {CascadeType.ALL},  fetch = FetchType.EAGER)
	//@JoinTable(name="Guardados", joinColumns= {@JoinColumn (name="nick")}, inverseJoinColumns= {@JoinColumn(name="idPens")})
	//private List<Pensamiento> guardados;
	
	@ManyToMany(cascade = {CascadeType.ALL},  fetch = FetchType.EAGER)
	@JoinTable(name="Amigos", joinColumns= {@JoinColumn (name="myNick")}, inverseJoinColumns= {@JoinColumn(name="nickFriend")})
	private List<User> myFriends;
	
	@ManyToMany(cascade = {CascadeType.ALL}, mappedBy="myFriends",  fetch = FetchType.EAGER)
	private List<User> friendsWithMe;

	public String getNick() {
		return nick;
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

	public List<User> getFriendsWithMe() {
		return friendsWithMe;
	}

	public void setFriendsWithMe(List<User> friendsWithMe) {
		this.friendsWithMe = friendsWithMe;
	}
}
