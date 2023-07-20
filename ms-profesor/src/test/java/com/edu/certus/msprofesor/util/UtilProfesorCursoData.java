package com.edu.certus.msprofesor.util;

import java.util.Collections;
import java.util.List;

import com.edu.certus.msprofesor.dto.CursoDto;
import com.edu.certus.msprofesor.dto.ProfesorCursoSendDto;
import com.edu.certus.msprofesor.dto.ResponseDto;
import com.edu.certus.msprofesor.entity.ProfesorCursoEntity;

public class UtilProfesorCursoData {
    
    public static Long ID_PROFESOR_CURSO = 1L;
    
    public static Long ID_CURSO = 1L;
    
    public static ProfesorCursoEntity dummyProfesorCursoEntity() {

        return ProfesorCursoEntity.builder()
            .id( 1L )
            .idProfesor( 1L )
            .idCurso( 1L )
            .build();
    }


    public static List<ProfesorCursoEntity> dummyProfesorCursoEntityList() {
        return Collections.singletonList( dummyProfesorCursoEntity() );
    }


    public static ProfesorCursoSendDto dummyProfesorCursoDto() {

        return ProfesorCursoSendDto.builder()
            .id( 2L )
            .idProfesor( 1L )
            .idCurso( 2L )
            .build();
    }


    public static ProfesorCursoSendDto dummyProfesorCursoDtoDuplicated() {

        return ProfesorCursoSendDto.builder()
            .id( 1L )
            .idProfesor( 1L )
            .idCurso( 1L )
            .build();
    }

    public static ProfesorCursoSendDto dummyProfesorCursoDtoNullAttr() {

        return ProfesorCursoSendDto.builder()
            .id( null )
            .idProfesor( null )
            .idCurso( null )
            .build();
    }



    public static CursoDto dummyCursoDto () {
        
        return CursoDto.builder()
            .id( 1L )
            .descripcion( "Curso 1" )
            .estado( true )
            .build();
    }


    public static ResponseDto dummyCursoClientId() {

        ResponseDto responseDto = new ResponseDto();
        responseDto.setCodigo( Constantes.CODE_SUCCES );
        responseDto.setMensaje( Constantes.OPERATION_SUCCESS );
        responseDto.setData( dummyCursoDto() );
        
        return responseDto;
    }


    public static ResponseDto dummyCursoClientNull() {

        ResponseDto responseDto = new ResponseDto();
        responseDto.setCodigo( Constantes.CODE_SUCCES );
        responseDto.setMensaje( Constantes.OPERATION_SUCCESS );
        responseDto.setData( null );
        
        return responseDto;
    }

}
