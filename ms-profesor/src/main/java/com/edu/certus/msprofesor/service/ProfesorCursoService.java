package com.edu.certus.msprofesor.service;

import com.edu.certus.msprofesor.dto.ProfesorCursoDto;
import com.edu.certus.msprofesor.dto.ResponseDto;

public interface ProfesorCursoService {
    
    public ResponseDto getAllProfesorCurso();
	public ResponseDto getAllProfesorCursos();
	public ResponseDto getProfesorCursoById( Long id );
	public ResponseDto getProfesorCursoByProfesorAndCurso( Long idProfesor, Long idCurso );
	public ResponseDto getProfesorCursosByProfesor( Long idProfesor );
	public ResponseDto createProfesorCurso( ProfesorCursoDto profesorCursoDto );
	public ResponseDto updateProfesorCurso( Long id, ProfesorCursoDto profesorCursoDto );
	public ResponseDto deleteProfesorCursoById( Long id );
	public ResponseDto deleteProfesorCursoByProfesorAndCurso( Long idProfesor, Long idCurso );

}
