package com.edu.certus.msprofesor.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorCursosDto {
    
	private Long idProfesor;
	private String nombreProfesor;
	private Boolean estadoProfesor;
	private List<CursoMinDto> cursos;

}
