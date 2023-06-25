package com.edu.certus.msalumno.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.edu.certus.msalumno.dto.ResponseDto;


@FeignClient( name = "ms-curso", url = "http://localhost:8083" )
public interface CursoClient {
    
    @GetMapping( "/v1/curso" )
    public ResponseDto readAllCurso();

    @GetMapping( "/v1/curso/{id}" )
    public ResponseDto readCurso( @PathVariable( "id" ) Long id );

}
