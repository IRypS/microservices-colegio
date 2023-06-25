package com.edu.certus.msalumno.util;

import com.edu.certus.msalumno.dto.ResponseDto;

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
