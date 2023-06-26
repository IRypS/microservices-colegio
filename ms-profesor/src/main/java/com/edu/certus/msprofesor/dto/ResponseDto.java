package com.edu.certus.msprofesor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

	private String codigo;
	private String mensaje;
	private Object data;
	
}
