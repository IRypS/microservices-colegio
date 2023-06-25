package com.edu.certus.msalumno.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoResponseDto {

	private List<AlumnoDto> alumno;
	private Object curso;
	
}
