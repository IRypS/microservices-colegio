package com.edu.certus.msalumno.util;

import java.util.Collections;
import java.util.List;

import com.edu.certus.msalumno.dto.AlumnoCursoSendDto;
import com.edu.certus.msalumno.dto.CursoDto;
import com.edu.certus.msalumno.dto.ResponseDto;
import com.edu.certus.msalumno.entity.AlumnoCursoEntity;


public class UtilAlumnoCursoData {
    
    
    public static Long ID_ALUMNO_CURSO = 1L;
    
    public static Long ID_CURSO = 1L;
    
    public static AlumnoCursoEntity dummyAlumnoCursoEntity() {

        return AlumnoCursoEntity.builder()
            .id( 1L )
            .idAlumno( 1L )
            .idCurso( 1L )
            .build();
    }


    public static List<AlumnoCursoEntity> dummyAlumnoCursoEntityList() {
        return Collections.singletonList( dummyAlumnoCursoEntity() );
    }


    public static AlumnoCursoSendDto dummyAlumnoCursoDto() {

        return AlumnoCursoSendDto.builder()
            .id( 2L )
            .idAlumno( 1L )
            .idCurso( 2L )
            .build();
    }


    public static AlumnoCursoSendDto dummyAlumnoCursoDtoDuplicated() {

        return AlumnoCursoSendDto.builder()
            .id( 1L )
            .idAlumno( 1L )
            .idCurso( 1L )
            .build();
    }

    public static AlumnoCursoSendDto dummyAlumnoCursoDtoNullAttr() {

        return AlumnoCursoSendDto.builder()
            .id( null )
            .idAlumno( null )
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
