package com.edu.certus.msalumno.dto;

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
@ApiModel( value = "Alumno DTO", description = "DTO para crear una respuesta de alumno" )
public class AlumnoDto {


	@ApiModelProperty( value = "ID alumno", example = "1", dataType = "integer" )
	private Long id;

	@ApiModelProperty( value = "Nombre alumno", example = "Angel Alonso", dataType = "string" )
	private String nombres;

	@ApiModelProperty( value = "Apellido alumno", example = "Rosas Ramos", dataType = "string" )
	private String apellidos;

	@ApiModelProperty( value = "Sexo alumno", example = "M", dataType = "string" )
	private String sexo;

	@ApiModelProperty( value = "Visibilidad", example = "true", dataType = "boolean" )
	private boolean estado;

	
}
