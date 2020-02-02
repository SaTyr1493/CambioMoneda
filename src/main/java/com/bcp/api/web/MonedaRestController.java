package com.bcp.api.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcp.api.service.moneda.MonedaService;
import com.bcp.api.servicedto.response.ActualizarTipoCambioResponse;
import com.bcp.api.servicedto.response.CambiarMonedaResponse;
import com.bcp.api.webdto.request.ActualizarTipoCambioWebRequest;
import com.bcp.api.webdto.request.CambiarMonedaWebRequest;
import com.bcp.api.webdto.response.ActualizarTipoCambioWebResponse;
import com.bcp.api.webdto.response.BaseWebResponse;
import com.bcp.api.webdto.response.CambiarMonedaWebResponse;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/cambiomoneda")
public class MonedaRestController {

	@Autowired
	private MonedaService monedaService;
	
	private CambiarMonedaWebResponse toCambiarMonedaWebResponse(CambiarMonedaResponse cambioMonedaResponse) {
		CambiarMonedaWebResponse cambioMonedaWebResponse = new CambiarMonedaWebResponse();
		BeanUtils.copyProperties(cambioMonedaResponse, cambioMonedaWebResponse);
		return cambioMonedaWebResponse;
	}
	
	 private ActualizarTipoCambioWebResponse toActualizarTipoCambioWebResponse(ActualizarTipoCambioResponse actualizarTipoCambioResponse) {
		ActualizarTipoCambioWebResponse actualizarTipoCambioWebResponse = new ActualizarTipoCambioWebResponse();
		BeanUtils.copyProperties(actualizarTipoCambioResponse, actualizarTipoCambioWebResponse);
		return actualizarTipoCambioWebResponse;
	 }
	
	@GetMapping("/cambiarmoneda/{monto}/{monedaOrigen}/{monedaDestino}")
	public Single<ResponseEntity<BaseWebResponse<CambiarMonedaWebResponse>>> cambiarMoneda(
									@PathVariable(value = "monto") String monto, 
									@PathVariable(value = "monedaOrigen") String monedaOrigen,
									@PathVariable(value = "monedaDestino") String monedaDestino) {
		CambiarMonedaWebRequest getCambioMonedaWebRequest = new CambiarMonedaWebRequest();
		getCambioMonedaWebRequest.setMonedaDestino(monedaDestino);
		getCambioMonedaWebRequest.setMonedaOrigen(monedaOrigen);
		getCambioMonedaWebRequest.setMonto(Double.parseDouble(monto));

		return monedaService.cambiarMoneda(getCambioMonedaWebRequest)
							.subscribeOn(Schedulers.io())
							.map(cambioMonedaResponse -> ResponseEntity
									.ok(BaseWebResponse.successWithData(
											toCambiarMonedaWebResponse(cambioMonedaResponse))));
	}
	
	@PutMapping("/actualizartipocambio")
	public Single<ResponseEntity<BaseWebResponse<ActualizarTipoCambioWebResponse>>> actualizarTipoCambio(
								@RequestBody ActualizarTipoCambioWebRequest actualizarTipoCambioWebRequest){
		
		return monedaService.actualizarTipoCambio(actualizarTipoCambioWebRequest)
							.subscribeOn(Schedulers.io())
							.map(actualizarTipoCambioResponse -> ResponseEntity
									.ok(BaseWebResponse.successWithData(
											toActualizarTipoCambioWebResponse(actualizarTipoCambioResponse))));
	}
	
}
