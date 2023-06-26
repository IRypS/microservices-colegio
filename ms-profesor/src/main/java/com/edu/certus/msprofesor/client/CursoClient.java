package com.edu.certus.msprofesor.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.edu.certus.msprofesor.dto.ResponseDto;


@FeignClient( name = "ms-curso" )
public interface CursoClient {
    
    @GetMapping( "/v1/curso" )
    public ResponseDto getAllCursoTrue();

    @GetMapping( "/v1/curso/{id}" )
    public ResponseDto getCursoTrueById( @PathVariable( "id" ) Long id );

}
