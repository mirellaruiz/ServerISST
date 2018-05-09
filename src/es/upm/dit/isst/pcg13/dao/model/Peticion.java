package es.upm.dit.isst.pcg13.dao.model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
public class Peticion{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="nick")
	private User solicitante;
	@ManyToOne
	@JoinColumn(name="nick")
	private User solicitado;
	
	@org.hibernate.annotations.Type(type="true_false")
	private int estado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(User solicitante) {
		this.solicitante = solicitante;
	}

	public User getSolicitado() {
		return solicitado;
	}

	public void setSolicitado(User solicitado) {
		this.solicitado = solicitado;
	}

	public int isEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	
	
	
}

