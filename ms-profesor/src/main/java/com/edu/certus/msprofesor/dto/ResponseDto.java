package com.edu.certus.msprofesor.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel( value = "Response DTO", description = "DTO para manejar las respuestas que provea la API Rest" )
public class ResponseDto {


	@ApiModelProperty( value = "Código de operación", example = "0", dataType = "integer" )
	private String codigo;

	@ApiModelProperty( value = "Mensaje de respuesta", example = "Operación exitosa", dataType = "string" )
	private String mensaje;

	@ApiModelProperty( value = "Datos resultantes de la operación", dataType = "Object" )
	private Object data;
	

}
