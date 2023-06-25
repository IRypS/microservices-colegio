package com.edu.certus.msalumno.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoCursoDto {

	private Long id;
	private Long idAlumno;
	private String nombreAlumno;
	private Boolean estadoAlumno;
	private Long idCurso;
	private String nombreCurso;
	
}
