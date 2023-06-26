package com.edu.certus.msprofesor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDto {

	private Long id;
	private String descripcion;
	private Boolean estado;

}
