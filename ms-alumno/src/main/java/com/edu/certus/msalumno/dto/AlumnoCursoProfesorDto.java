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
@ApiModel( value = "Alumno - Curso - Profesor DTO", description = "DTO para crear una respuesta de un alumno, un curso y un profesor" )
public class AlumnoCursoProfesorDto {

    @ApiModelProperty( value = "ID alumno", example = "15", dataType = "integer" )
    private Long idAlumno;

    @ApiModelProperty( value = "Nombres de alumno", example = "Ramón Alberto Perez Lima", dataType = "string" )
    private String nombreAlumno;

    @ApiModelProperty( value = "Visibilidad del alumno", example = "true", dataType = "boolean" )
    private boolean estadoAlumno;

    @ApiModelProperty( value = "ID curso", example = "20", dataType = "integer" )
    private Long idCurso;

    @ApiModelProperty( value = "Nombre del curso", example = "Bases de datos", dataType = "string" )
    private String nombreCurso;

    @ApiModelProperty( value = "ID profesor", example = "15", dataType = "integer" )
    private Long idProfesor;

    @ApiModelProperty( value = "Nombres de profesor", example = "Juan Alberto Soliz Arrenda", dataType = "string" )
    private String nombreProfesor;
    
}
