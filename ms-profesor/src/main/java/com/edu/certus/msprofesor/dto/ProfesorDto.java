package com.edu.certus.msprofesor.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel( value = "Profesor DTO", description = "DTO para crear una respuesta de profesor" )
public class ProfesorDto {


	@ApiModelProperty( value = "ID profesor", example = "1", dataType = "integer" )
	private Long id;

	@ApiModelProperty( value = "Nombre profesor", example = "Juan Alberto", dataType = "string" )
	private String nombres;

	@ApiModelProperty( value = "Apellido profesor", example = "Soliz Arrenda", dataType = "string" )
	private String apellidos;

	@ApiModelProperty( value = "Sexo profesor", example = "M", dataType = "string" )
	private String sexo;

	@ApiModelProperty( value = "Visibilidad", example = "true", dataType = "boolean" )
	private boolean estado;
	

}
