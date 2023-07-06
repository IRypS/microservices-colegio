package com.edu.certus.msalumno.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoCursoProfesorDto {

    private Long idAlumno;

    private String nombreAlumno;

    private boolean estadoAlumno;

    private Long idCurso;

    private String nombreCurso;

    private Long idProfesor;

    private String nombreProfesor;
    
}
