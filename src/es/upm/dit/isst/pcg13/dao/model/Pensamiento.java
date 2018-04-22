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
	@ManyToMany(cascade = {CascadeType.ALL}, mappedBy="guardados", fetch = FetchType.EAGER)
	private List<User> userGuardan;
	@ManyToMany(cascade = {CascadeType.ALL}, mappedBy="valorados",  fetch = FetchType.EAGER)
	private List<User> userValoran;
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
	public List<User> getUserGuardan() {
		return userGuardan;
	}
	public void setUserGuardan(List<User> userGuardan) {
		this.userGuardan = userGuardan;
	}
	public List<User> getUserValoran() {
		return userValoran;
	}
	public void setUserValoran(List<User> userValoran) {
		this.userValoran = userValoran;
	}
	
	
}
