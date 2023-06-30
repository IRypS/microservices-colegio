package com.edu.certus.msprofesor.dto;

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
@ApiModel( value = "Profesor - Curso DTO", description = "DTO para crear una respuesta de un profesor y un curso" )
public class ProfesorCursoDto {


	@ApiModelProperty( value = "ID de registro", example = "1", dataType = "integer" )
	private Long id;

	@ApiModelProperty( value = "ID profesor", example = "15", dataType = "integer" )
	private Long idProfesor;

	@ApiModelProperty( value = "Nombres de profesor", example = "Juan Alberto Soliz Arrenda", dataType = "string" )
	private String nombreProfesor;

	@ApiModelProperty( value = "Sexo profesor", example = "M", dataType = "string" )
	private String sexoProfesor;

	@ApiModelProperty( value = "Visibilidad del profesor", example = "true", dataType = "boolean" )
	private Boolean estadoProfesor;

	@ApiModelProperty( value = "ID curso", example = "20", dataType = "integer" )
	private Long idCurso;

	@ApiModelProperty( value = "Nombre del curso", example = "Bases de datos", dataType = "string" )
	private String nombreCurso;
    
	
}
