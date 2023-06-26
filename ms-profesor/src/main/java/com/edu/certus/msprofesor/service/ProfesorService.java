package com.edu.certus.msprofesor.service;

import com.edu.certus.msprofesor.dto.ProfesorDto;
import com.edu.certus.msprofesor.dto.ResponseDto;

public interface ProfesorService {

    public ResponseDto getAllProfesor( boolean onlyTrue );
    public ResponseDto getProfesorById( Long idProfesor, boolean onlyTrue );
    public ResponseDto createProfesor( ProfesorDto profesorDto );
    public ResponseDto updateProfesor( ProfesorDto profesorDto );
    public ResponseDto deleteProfesorById( Long idProfesor );
    
}
