package com.edu.certus.mscurso.util;

import java.util.Collections;
import java.util.List;

import com.edu.certus.mscurso.dto.CursoDto;
import com.edu.certus.mscurso.dto.ResponseDto;
import com.edu.certus.mscurso.entity.CursoEntity;

public class UtilCursoData {
    
    public static final Long ID_TRUE = 1L;
    public static final Long ID_FALSE = 2L;


    public static CursoEntity dummyCursoEntityTrue() {

        return CursoEntity.builder()
            .id( 1L )
            .descripcion( "curso 1" )
            .estado( true )
            .build();

    }


    public static CursoEntity dummyCursoEntityFalse() {

        return CursoEntity.builder()
            .id( 2L )
            .descripcion( "curso 2" )
            .estado( false )
            .build();

    }


    public static List<CursoEntity> dummyCursoEntityListTrue() {
        return Collections.singletonList( dummyCursoEntityTrue() );
    }


    public static List<CursoEntity> dummyCursoEntityListAll() {
        return Collections.unmodifiableList( List.of( dummyCursoEntityTrue(), dummyCursoEntityFalse() ) );
    }


    public static CursoDto dummyCursoDto() {

        return CursoDto.builder()
            .id( 3L )
            .descripcion( "curso 3" )
            .estado( true )
            .build();

    }

    public static CursoDto dummyCursoDtoRepeated() {

        return CursoDto.builder()
            .id( 1L )
            .descripcion( "curso 1" )
            .estado( true )
            .build();

    }

    
    public static CursoDto dummyCursoDtoWithoutDescripcion() {
        return CursoDto.builder().id( 1L ).build();

    }


    public static ResponseDto dummyResponseDto( String code, String message) {

        ResponseDto responseDto = new ResponseDto();
        responseDto.setCodigo( code );
        responseDto.setMensaje( message );
        responseDto.setData( new Object() );
        return responseDto;

    }

}
