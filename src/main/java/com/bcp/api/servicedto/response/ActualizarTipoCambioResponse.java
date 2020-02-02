package com.bcp.api.servicedto.response;

public class ActualizarTipoCambioResponse {

	private Double tipoCambioAnterior;
	private Double tipoCambioNuevo;
	private String monedaOrigen;
	private String monedaDestino;
	
	public Double getTipoCambioAnterior() {
		return tipoCambioAnterior;
	}
	public void setTipoCambioAnterior(Double tipoCambioAnterior) {
		this.tipoCambioAnterior = tipoCambioAnterior;
	}
	public Double getTipoCambioNuevo() {
		return tipoCambioNuevo;
	}
	public void setTipoCambioNuevo(Double tipoCambioNuevo) {
		this.tipoCambioNuevo = tipoCambioNuevo;
	}
	public String getMonedaOrigen() {
		return monedaOrigen;
	}
	public void setMonedaOrigen(String monedaOrigen) {
		this.monedaOrigen = monedaOrigen;
	}
	public String getMonedaDestino() {
		return monedaDestino;
	}
	public void setMonedaDestino(String monedaDestino) {
		this.monedaDestino = monedaDestino;
	}
	
	
}
