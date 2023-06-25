package com.edu.certus.msalumno.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDto {

	private Long id;
	private String nombres;
	private String apellidos;
	private String sexo;
	private Boolean estado;

}
