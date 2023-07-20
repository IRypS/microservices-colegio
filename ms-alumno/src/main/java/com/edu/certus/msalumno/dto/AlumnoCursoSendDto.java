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
@ApiModel( value = "Alumno - Curso Send", 
    description = "Dto para el envío de datos con el objetivo de creación y actualización de registros" )
public class AlumnoCursoSendDto {


    @ApiModelProperty( value = "ID de registro", example = "1", dataType = "integer" )
    private Long id;
    
    @ApiModelProperty( value = "ID alumno", example = "15", dataType = "integer" )
	private Long idAlumno;
    
    @ApiModelProperty( value = "ID curso", example = "20", dataType = "integer" )
    private Long idCurso;
    
    
}
