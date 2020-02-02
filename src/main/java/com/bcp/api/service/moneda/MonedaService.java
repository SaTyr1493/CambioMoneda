package com.bcp.api.service.moneda;

import com.bcp.api.servicedto.response.ActualizarTipoCambioResponse;
import com.bcp.api.servicedto.response.CambiarMonedaResponse;
import com.bcp.api.webdto.request.ActualizarTipoCambioWebRequest;
import com.bcp.api.webdto.request.CambiarMonedaWebRequest;

import io.reactivex.Single;

public interface MonedaService {

	Single<CambiarMonedaResponse> cambiarMoneda(CambiarMonedaWebRequest cambiarMonedaWebRequest);
	Single<ActualizarTipoCambioResponse> actualizarTipoCambio(ActualizarTipoCambioWebRequest actualizarTipoCambioWebRequest);
	
}
