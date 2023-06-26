package com.edu.certus.mscurso.service;

import com.edu.certus.mscurso.dto.CursoDto;
import com.edu.certus.mscurso.dto.ResponseDto;

public interface CursoService {

    public ResponseDto getAllCurso( boolean onlyTrue );
    public ResponseDto getCursoById( Long idCurso, boolean onlyTrue );
    public ResponseDto createCurso( CursoDto cursoDto );
    public ResponseDto updateCurso( CursoDto cursoDto );
    public ResponseDto deleteCursoById( Long idCurso );
    
}