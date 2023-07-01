package com.edu.certus.msalumno.dto;

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
@ApiModel( value = "Curso DTO", description = "DTO para crear una respuesta de curso" )
public class CursoDto {


	@ApiModelProperty( value = "ID curso", example = "20", dataType = "integer" )
	private Long id;

	@ApiModelProperty( value = "Nombre del curso", example = "Bases de datos", dataType = "string" )
	private String descripcion;

	@ApiModelProperty( value = "Visibilidad del curso", example = "true", dataType = "boolean" )
	private boolean estado;

	
}
