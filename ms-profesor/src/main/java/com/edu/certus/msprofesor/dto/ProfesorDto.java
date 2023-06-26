package com.edu.certus.msprofesor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorDto {
    
	private Long id;
	private String nombres;
	private String apellidos;
	private String sexo;
	private boolean estado;

}
