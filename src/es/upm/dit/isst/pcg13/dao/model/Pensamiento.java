package es.upm.dit.isst.pcg13.dao.model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
public class Pensamiento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPens;
	@ManyToOne
	@JoinColumn(name="nick")
	private User author;
	private String text;
	private String topic;
	private Date date;
	private float latitude;
	private float longitude;
	//@ManyToMany(cascade = {CascadeType.ALL}, mappedBy="guardados", fetch = FetchType.EAGER)
	//private List<User> userGuardan;
	@OneToMany(mappedBy = "pensamiento", fetch = FetchType.EAGER, orphanRemoval=true)//relacion para BBDD, orphan removal para que borre todas las relaciones
	private List<Valoracion> valoraciones; 
	
	public List<Valoracion> getValoraciones() {
		return valoraciones;
	}
	public void setValoraciones(List<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}
	private int likes;
	private int dislikes;
	
	public int getIdPens() {
		return idPens;
	}
	public void setIdPens(int idPens) {
		this.idPens = idPens;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date fecha) {
		this.date = fecha;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	/*public List<User> getUserGuardan() {
		return userGuardan;
	}
	public void setUserGuardan(List<User> userGuardan) {
		this.userGuardan = userGuardan;
	}*/
	
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	
}
