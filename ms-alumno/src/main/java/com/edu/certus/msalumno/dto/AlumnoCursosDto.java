package com.edu.certus.msalumno.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel( value = "Alumno - Cursos DTO", description = "DTO para crear una respuesta de un alumno y sus cursos" )
public class AlumnoCursosDto {


	@ApiModelProperty( value = "ID alumno", example = "15", dataType = "integer" )
	private Long idAlumno;

	@ApiModelProperty( value = "Nombres de alumno", example = "Ramón Alberto Perez Lima", dataType = "string" )
	private String nombreAlumno;

	@ApiModelProperty( value = "Sexo alumno", example = "M", dataType = "string" )
	private String sexoAlumno;

	@ApiModelProperty( value = "Visibilidad del alumno", example = "true", dataType = "boolean" )
	private Boolean estadoAlumno;

	@ApiModelProperty(value = "Lista de cursos", dataType = "java.util.List<CursoMinDto>")
	private List<CursoMinDto> cursos;
	
	
}