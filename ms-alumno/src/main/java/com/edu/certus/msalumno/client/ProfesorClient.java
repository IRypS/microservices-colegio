package com.edu.certus.msalumno.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.edu.certus.msalumno.dto.ResponseDto;

/**
 * Feign client para la interacción con el microservicio <b>ms-profesor</b>
 */
@FeignClient( name = "ms-profesor" )
public interface ProfesorClient {

    @GetMapping( "/v1/profesor" )
    public ResponseDto getAllProfesorTrue();

    @GetMapping( "/v1/profesor/{id}" )
    public ResponseDto getProfesorTrueById( @PathVariable("id") Long idProfesor );

    @GetMapping( "/v1/profesor-curso" )
    public ResponseDto getAllProfesorCurso();

    @GetMapping( "/v1/profesor-curso/profesores/{idProfesor}" )
    public ResponseDto getProfesorCursosByProfesor( @PathVariable("idProfesor") Long idProfesor );

    @GetMapping( "/v1/profesor-curso/cursos/{idCurso}" )
    public ResponseDto getProfesorCursosByCurso( @PathVariable("idCurso") Long idCurso );

}
