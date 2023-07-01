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
@ApiModel( value = "Profesor - Curso Send", 
    description = "Dto para el envío de datos con el objetivo de creación y actualización de registros" )
public class ProfesorCursoSendDto {
    
    @ApiModelProperty( value = "ID de registro", example = "1", dataType = "integer" )
    private Long id;
    
    @ApiModelProperty( value = "ID profesor", example = "15", dataType = "integer" )
    private Long idProfesor;
    
    @ApiModelProperty( value = "ID curso", example = "20", dataType = "integer" )
    private Long idCurso;

}
