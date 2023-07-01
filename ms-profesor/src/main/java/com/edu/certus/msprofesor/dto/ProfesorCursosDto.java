package com.edu.certus.msprofesor.dto;

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
@ApiModel( value = "Profesor - Cursos DTO", description = "DTO para crear una respuesta de un profesor y sus cursos" )
public class ProfesorCursosDto {


	@ApiModelProperty( value = "ID profesor", example = "15", dataType = "integer" )
	private Long idProfesor;

	@ApiModelProperty( value = "Nombres de profesor", example = "Juan Alberto Soliz Arrenda", dataType = "string" )
	private String nombreProfesor;

	@ApiModelProperty( value = "Sexo profesor", example = "M", dataType = "string" )
	private String sexoProfesor;

	@ApiModelProperty( value = "Visibilidad", example = "true", dataType = "boolean" )
	private boolean estadoProfesor;

	@ApiModelProperty(value = "Lista de cursos", dataType = "java.util.List<CursoMinDto>")
	private List<CursoMinDto> cursos;

}
