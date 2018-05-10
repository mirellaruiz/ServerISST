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
	@JoinColumn(name="nick2", referencedColumnName="nick")
	private User solicitado;
	
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

	public int getEstado() {
		return estado;
	}

	
	
	
}

