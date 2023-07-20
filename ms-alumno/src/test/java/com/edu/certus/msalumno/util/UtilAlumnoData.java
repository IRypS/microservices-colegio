package com.edu.certus.msalumno.util;

import java.util.Collections;
import java.util.List;

import com.edu.certus.msalumno.dto.AlumnoDto;
import com.edu.certus.msalumno.dto.ResponseDto;
import com.edu.certus.msalumno.entity.AlumnoEntity;



public class UtilAlumnoData {


    public static final Long ID_TRUE = 1L;
    public static final Long ID_FALSE = 2L;


    public static AlumnoEntity dummyAlumnoEntityTrue() {

        return AlumnoEntity.builder()
            .id( 1L )
            .nombres( "alumno 1" )
            .apellidos( "apellidos 1" )
            .sexo( "M" )
            .estado( true )
            .build();

    }


    public static AlumnoEntity dummyAlumnoEntityFalse() {

        return AlumnoEntity.builder()
            .id( 2L )
            .nombres( "alumno 2" )
            .apellidos( "apellidos 2" )
            .sexo( "F" )
            .estado( false )
            .build();

    }


    public static List<AlumnoEntity> dummyAlumnoEntityListTrue() {
        return Collections.singletonList( dummyAlumnoEntityTrue() );
    }


    public static List<AlumnoEntity> dummyAlumnoEntityListAll() {
        return Collections.unmodifiableList( List.of( dummyAlumnoEntityTrue(), dummyAlumnoEntityFalse() ) );
    }


    public static AlumnoDto dummyAlumnoDto() {

        return AlumnoDto.builder()
            .id( 3L )
            .nombres( "alumno 3" )
            .apellidos( "apellidos 3" )
            .sexo( "F" )
            .estado( true )
            .build();

    }

    public static AlumnoDto dummyAlumnoDtoRepeated() {

        return AlumnoDto.builder()
            .id( 1L )
            .nombres( "alumno 1" )
            .apellidos( "apellidos 1" )
            .sexo( "M" )
            .estado( false )
            .build();

    }

    
    public static AlumnoDto dummyAlumnoDtoWithoutNombres() {
        return AlumnoDto.builder().id( 1L ).nombres( "" ).build();
    }
    

    public static AlumnoDto dummyAlumnoDtoWithoutApellidos() {
        return AlumnoDto.builder().id( 1L ).nombres( "alumno 1" ).apellidos( "" ).build();
    }

    public static AlumnoDto dummyAlumnoDtoWithoutSexo() {

        return AlumnoDto.builder()
            .id( 1L )
            .nombres( "alumno 1" )
            .apellidos( "apellidos 1" )
            .sexo( "" )
            .build();

    }

    public static AlumnoDto dummyAlumnoDtoInvalidArgument() {

        return AlumnoDto.builder()
            .id( 1L )
            .nombres( "alumno 1" )
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
