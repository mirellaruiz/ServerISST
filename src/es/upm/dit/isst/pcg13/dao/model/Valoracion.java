package es.upm.dit.isst.pcg13.dao.model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
public class Valoracion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idVal;
	@ManyToOne
	private User author;
	@ManyToOne
	private Pensamiento pensamiento;
	
	@org.hibernate.annotations.Type(type="true_false")
	private boolean valor;

	public int getIdVal() {
		return idVal;
	}

	public void setIdVal(int idVal) {
		this.idVal = idVal;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Pensamiento getPensamiento() {
		return pensamiento;
	}

	public void setPensamiento(Pensamiento pensamiento) {
		this.pensamiento = pensamiento;
	}

	public boolean getValor() {
		return valor;
	}

	public void setValor(boolean like) {
		this.valor = like;
	}
	
	
	
}

