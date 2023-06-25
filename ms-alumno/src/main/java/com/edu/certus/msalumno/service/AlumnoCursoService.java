package com.edu.certus.msalumno.service;

import com.edu.certus.msalumno.dto.AlumnoCursoDto;
import com.edu.certus.msalumno.dto.ResponseDto;

public interface AlumnoCursoService {
    
    public ResponseDto getAllAlumnoCurso();
	public ResponseDto getAllAlumnoCursos();
	public ResponseDto getAlumnoCurso( Long id );
	public ResponseDto getAlumnoCursoByAlumnoAndCurso( Long idAlumno, Long idCurso );
	public ResponseDto getAlumnoCursosByAlumno( Long idAlumno );
	public ResponseDto createAlumnoCurso( AlumnoCursoDto alumnoCursoDto );
	public ResponseDto updateAlumnoCurso( Long id, AlumnoCursoDto alumnoCursoDto );
	public ResponseDto deleteAlumnoCursoById( Long id );
	public ResponseDto deleteAlumnoCursoByAlumnoAndCurso( Long idAlumno, Long idCurso );

}
