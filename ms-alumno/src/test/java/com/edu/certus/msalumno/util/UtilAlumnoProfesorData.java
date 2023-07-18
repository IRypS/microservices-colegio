package com.edu.certus.msalumno.util;

import java.util.Collections;
import java.util.List;

import com.edu.certus.msalumno.dto.ProfesorCursoDto;
import com.edu.certus.msalumno.dto.ResponseDto;

public class UtilAlumnoProfesorData {
    

    public static ProfesorCursoDto dummyProfesorCursoDto () {
        
        return ProfesorCursoDto.builder()
            .id( 1L )
            .idProfesor( 1L )
            .nombreProfesor( "profesor 1" )
            .sexoProfesor( "M" )
            .estadoProfesor( true )
            .idCurso( 1L )
            .nombreCurso( "curso 1" )
            .build();
    }
    

    public static List<ProfesorCursoDto> dummyProfesorCursoListDto () {
        return Collections.singletonList( dummyProfesorCursoDto() );
    }


    public static ResponseDto dummyProfesorClient() {

        ResponseDto responseDto = new ResponseDto();
        responseDto.setCodigo( Constantes.CODE_SUCCES );
        responseDto.setMensaje( Constantes.OPERATION_SUCCESS );
        responseDto.setData( dummyProfesorCursoListDto() );
        
        return responseDto;

    }

}
