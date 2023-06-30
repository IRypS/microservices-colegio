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
@ApiModel( value = "Curso Min DTO", description = "DTO para crear una respuesta mínima de curso" )
public class CursoMinDto {


	@ApiModelProperty( value = "ID curso", example = "20", dataType = "integer" )
	private Long id;

	@ApiModelProperty( value = "Nombre del curso", example = "Bases de datos", dataType = "string" )
	private String descripcion;


}