package com.edu.certus.msalumno.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: AÑadir sexo en alumno
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel( value = "Alumno - Curso DTO", description = "DTO para crear una respuesta de un alumno y un curso" )
public class AlumnoCursoDto {

	
	@ApiModelProperty( value = "ID de registro", example = "1", dataType = "integer" )
	private Long id;

	@ApiModelProperty( value = "ID alumno", example = "15", dataType = "integer" )
	private Long idAlumno;

	@ApiModelProperty( value = "Nombres de alumno", example = "Ramón Alberto Perez Lima", dataType = "string" )
	private String nombreAlumno;

	@ApiModelProperty( value = "Sexo alumno", example = "M", dataType = "string" )
	private String sexoAlumno;

	@ApiModelProperty( value = "Visibilidad del alumno", example = "true", dataType = "boolean" )
	private Boolean estadoAlumno;

	@ApiModelProperty( value = "ID curso", example = "20", dataType = "integer" )
	private Long idCurso;

	@ApiModelProperty( value = "Nombre del curso", example = "Bases de datos", dataType = "string" )
	private String nombreCurso;
	
	
}
