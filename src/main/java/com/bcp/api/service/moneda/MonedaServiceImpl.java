package com.bcp.api.service.moneda;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcp.api.repository.MonedaRepository;
import com.bcp.api.servicedto.response.ActualizarTipoCambioResponse;
import com.bcp.api.servicedto.response.CambiarMonedaResponse;
import com.bcp.api.webdto.request.ActualizarTipoCambioWebRequest;
import com.bcp.api.webdto.request.CambiarMonedaWebRequest;

import io.reactivex.Single;

@Service
public class MonedaServiceImpl implements MonedaService{
	
	@Autowired
	private MonedaRepository monedaRepository;
		
	@Override
	public Single<CambiarMonedaResponse> cambiarMoneda(CambiarMonedaWebRequest cambiarMonedaWebRequest) {
		return cambiarMonedaRepository(cambiarMonedaWebRequest);
	}
	
	private Single<CambiarMonedaResponse> cambiarMonedaRepository(CambiarMonedaWebRequest cambiarMonedaWebRequest){
		
		return Single.create(singleSubscriber -> {
			Optional<Double> tipoCambio = monedaRepository.findTipoCambio(cambiarMonedaWebRequest.getMonedaOrigen(),
																			cambiarMonedaWebRequest.getMonedaDestino());
			if(!tipoCambio.isPresent()) {
				singleSubscriber.onError(new EntityNotFoundException());
			}else {
				BigDecimal montoCambiado = new BigDecimal(tipoCambio.get()*cambiarMonedaWebRequest.getMonto());
				montoCambiado = montoCambiado.setScale(3, RoundingMode.HALF_UP);
				
				CambiarMonedaResponse cambioMonedaResponse = new CambiarMonedaResponse();			
				cambioMonedaResponse.setMonto(cambiarMonedaWebRequest.getMonto());
				cambioMonedaResponse.setMonedaOrigen(cambiarMonedaWebRequest.getMonedaOrigen());
				cambioMonedaResponse.setMonedaDestino(cambiarMonedaWebRequest.getMonedaDestino());
				cambioMonedaResponse.setMontoCambiado(montoCambiado.doubleValue());
				cambioMonedaResponse.setTipoCambio(tipoCambio.get());
				
				singleSubscriber.onSuccess(cambioMonedaResponse);
			}
		});
	}

	@Override
	public Single<ActualizarTipoCambioResponse> actualizarTipoCambio(ActualizarTipoCambioWebRequest actualizarTipoCambioWebRequest) {
		return actualizarTipoCambioRepository(actualizarTipoCambioWebRequest);
	}
	
	private Single<ActualizarTipoCambioResponse> actualizarTipoCambioRepository(ActualizarTipoCambioWebRequest actualizarTipoCambioWebRequest) {
		
		return Single.create(singleSubscriber ->{
			Optional<Double> tipoCambioAnterior = monedaRepository.findTipoCambio(actualizarTipoCambioWebRequest.getMonedaOrigen(),
																		actualizarTipoCambioWebRequest.getMonedaDestino());
			if(!tipoCambioAnterior.isPresent()) {
				singleSubscriber.onError(new EntityNotFoundException());
			}else {
				monedaRepository.updateTipoCambio(actualizarTipoCambioWebRequest.getTipoCambioNuevo(), 
						actualizarTipoCambioWebRequest.getMonedaOrigen(), 
						actualizarTipoCambioWebRequest.getMonedaDestino());
				
				ActualizarTipoCambioResponse actualizarTipoCambioResponse = new ActualizarTipoCambioResponse();
				actualizarTipoCambioResponse.setMonedaOrigen(actualizarTipoCambioWebRequest.getMonedaOrigen());
				actualizarTipoCambioResponse.setMonedaDestino(actualizarTipoCambioWebRequest.getMonedaDestino());
				actualizarTipoCambioResponse.setTipoCambioAnterior(tipoCambioAnterior.get());
				actualizarTipoCambioResponse.setTipoCambioNuevo(actualizarTipoCambioWebRequest.getTipoCambioNuevo());
				
				singleSubscriber.onSuccess(actualizarTipoCambioResponse);
			}
		});
	}
}
