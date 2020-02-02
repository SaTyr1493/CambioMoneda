package com.bcp.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Moneda")
public class Moneda {

	@Id
	@Column(name = "idmoneda")
	private int idmoneda;
	@Column(name = "monedaorigen")
	private String monedaorigen;
	@Column(name = "monedadestino")
	private String monedadestino;
	@Column(name = "tipocambio")
	private Double tipocambio;
	
	public int getIdmoneda() {
		return idmoneda;
	}
	public void setIdmoneda(int idmoneda) {
		this.idmoneda = idmoneda;
	}
	public String getMonedaorigen() {
		return monedaorigen;
	}
	public void setMonedaorigen(String monedaorigen) {
		this.monedaorigen = monedaorigen;
	}
	public String getMonedadestino() {
		return monedadestino;
	}
	public void setMonedadestino(String monedadestino) {
		this.monedadestino = monedadestino;
	}
	public Double getTipocambio() {
		return tipocambio;
	}
	public void setTipocambio(Double tipocambio) {
		this.tipocambio = tipocambio;
	}
	
}
