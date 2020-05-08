package com.nicolas.app.ruleta.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "ruletas")
public class Ruleta implements Serializable{

	private static final long serialVersionUID = 2531877258446656774L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
        )
	private List<Apuesta> apuestas;
	private Boolean abierta = false;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Apuesta> getApuestas() {
		return apuestas;
	}
	public void setApuestas(List<Apuesta> apuestas) {
		this.apuestas = apuestas;
	}
	public Boolean getAbierta() {
		return abierta;
	}
	public void setAbierta(Boolean abierta) {
		this.abierta = abierta;
	}
	
	
	
}
