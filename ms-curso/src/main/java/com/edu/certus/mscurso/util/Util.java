package com.edu.certus.mscurso.util;

import com.edu.certus.mscurso.dto.ResponseDto;

/**
 * Utilidad para crear objetos <b>ResponseDto</b>
 */
public class Util {


	/**
	 * Crea un objeto <b>ResponseDto</b>
	 * @param success Valor true / false para indicar el resultado de la respuesta
	 * @param mensaje Mensaje sobre la respuesta
	 * @param data Contenido de la respuesta
	 * @return Objeto <b>ResponseDto</b> con los parámetros ingresados
	 */
	public static ResponseDto getResponse(boolean success, String mensaje, Object data) {

		ResponseDto response = new ResponseDto();
		String cod = (!success) ? Constantes.CODE_FAILED : Constantes.CODE_SUCCES;
		response.setCodigo(cod);
		response.setMensaje(mensaje);
		response.setData(data);
		return response;
		
	}
}
