package com.edu.certus.msprofesor.util;

import java.util.Collections;
import java.util.List;

import com.edu.certus.msprofesor.dto.ProfesorDto;
import com.edu.certus.msprofesor.dto.ResponseDto;
import com.edu.certus.msprofesor.entity.ProfesorEntity;

public class UtilProfesorData {

    public static final Long ID_TRUE = 1L;
    public static final Long ID_FALSE = 2L;


    public static ProfesorEntity dummyProfesorEntityTrue() {

        return ProfesorEntity.builder()
            .id( 1L )
            .nombres( "profesor 1" )
            .apellidos( "apellidos 1" )
            .sexo( "M" )
            .estado( true )
            .build();

    }


    public static ProfesorEntity dummyProfesorEntityFalse() {

        return ProfesorEntity.builder()
            .id( 2L )
            .nombres( "profesor 2" )
            .apellidos( "apellidos 2" )
            .sexo( "F" )
            .estado( false )
            .build();

    }


    public static List<ProfesorEntity> dummyProfesorEntityListTrue() {
        return Collections.singletonList( dummyProfesorEntityTrue() );
    }


    public static List<ProfesorEntity> dummyProfesorEntityListAll() {
        return Collections.unmodifiableList( List.of( dummyProfesorEntityTrue(), dummyProfesorEntityFalse() ) );
    }


    public static ProfesorDto dummyProfesorDto() {

        return ProfesorDto.builder()
            .id( 3L )
            .nombres( "profesor 3" )
            .apellidos( "apellidos 3" )
            .sexo( "F" )
            .estado( true )
            .build();

    }

    public static ProfesorDto dummyProfesorDtoRepeated() {

        return ProfesorDto.builder()
            .id( 1L )
            .nombres( "profesor 1" )
            .apellidos( "apellidos 1" )
            .sexo( "M" )
            .estado( false )
            .build();

    }

    
    public static ProfesorDto dummyProfesorDtoWithoutNombres() {
        return ProfesorDto.builder().id( 1L ).nombres( "" ).build();
    }
    

    public static ProfesorDto dummyProfesorDtoWithoutApellidos() {
        return ProfesorDto.builder().id( 1L ).nombres( "profesor 1" ).apellidos( "" ).build();
    }

    public static ProfesorDto dummyProfesorDtoWithoutSexo() {

        return ProfesorDto.builder()
            .id( 1L )
            .nombres( "profesor 1" )
            .apellidos( "apellidos 1" )
            .sexo( "" )
            .build();

    }

    public static ProfesorDto dummyProfesorDtoInvalidArgument() {

        return ProfesorDto.builder()
            .id( 1L )
            .nombres( "profesor 1" )
            .apellidos( "apellidos 1" )
            .sexo( "INVALID" )
            .build();

    }


    public static ResponseDto dummyResponseDto( String code, String message) {

        ResponseDto responseDto = new ResponseDto();
        responseDto.setCodigo( code );
        responseDto.setMensaje( message );
        responseDto.setData( new Object() );
        return responseDto;

    }
}
