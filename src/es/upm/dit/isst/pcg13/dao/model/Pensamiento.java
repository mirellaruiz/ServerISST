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
	private User author;
	private String text;
	private String topic;
	private Date date;
	private float latitude;
	private float longitude;
	//@ManyToMany(cascade = {CascadeType.ALL}, mappedBy="guardados", fetch = FetchType.EAGER)
	//private List<User> userGuardan;
	@OneToMany(mappedBy = "pensamiento", fetch = FetchType.EAGER)//relacion para BBDD
	private List<Valoracion> valoraciones; 
	
	public List<Valoracion> getValoraciones() {
		return valoraciones;
	}
	public void setValoraciones(List<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}
	private float likes;
	private float dislikes;
	
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
	
	public float getDislikes() {
		return dislikes;
	}
	public void setDislikes(float dislikes) {
		this.dislikes = dislikes;
	}
	public float getLikes() {
		return likes;
	}
	public void setLikes(float likes) {
		this.likes = likes;
	}
	
	
}
