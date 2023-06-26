package com.edu.certus.mscurso.dto;

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
@ApiModel( value = "Curso DTO", description = "DTO para manejar los datos de la entidad Curso" )
public class CursoDto {
    
    @ApiModelProperty( value = "ID del curso", example = "1", dataType = "integer" )
    private Long id;

    @ApiModelProperty( value = "Descripcion del curso", example = "Algoritmos I", dataType = "string" )
    private String descripcion;

    @ApiModelProperty( value = "Visibilidad curso",  example = "true", dataType = "boolean" )
    private boolean estado;
    
}