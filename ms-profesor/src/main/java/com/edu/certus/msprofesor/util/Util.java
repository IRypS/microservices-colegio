package com.edu.certus.msprofesor.util;

import com.edu.certus.msprofesor.dto.ResponseDto;

public class Util {

	public static ResponseDto getResponse(boolean success, String mensaje, Object data) {

		ResponseDto response = new ResponseDto();
		String cod = (!success) ? Constantes.CODE_FAILED : Constantes.CODE_SUCCES;
		response.setCodigo(cod);
		response.setMensaje(mensaje);
		response.setData(data);
		return response;
		
	}
}
