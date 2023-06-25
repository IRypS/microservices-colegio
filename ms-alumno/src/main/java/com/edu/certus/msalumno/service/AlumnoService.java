package com.edu.certus.msalumno.service;

import com.edu.certus.msalumno.dto.AlumnoDto;
import com.edu.certus.msalumno.dto.ResponseDto;

public interface AlumnoService {

    public ResponseDto getAllAlumno( boolean onlyTrue );
    public ResponseDto getAlumnoById( Long idAlumno, boolean onlyTrue );
    public ResponseDto createAlumno( AlumnoDto alumnoDto );
    public ResponseDto updateAlumno( AlumnoDto alumnoDto );
    public ResponseDto deleteAlumnoById( Long idAlumno );

}
