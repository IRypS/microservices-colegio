package com.edu.certus.mscurso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.certus.mscurso.dto.CursoDto;
import com.edu.certus.mscurso.dto.ResponseDto;
import com.edu.certus.mscurso.service.CursoService;


@RestController
@RequestMapping( "/v1/curso" )
public class CursoController {
    
    
    
    @Autowired
    private CursoService cursoService;



    @GetMapping
    public ResponseEntity< ResponseDto > getAllCursoTrue() {
        return ResponseEntity.status( HttpStatus.OK ).body( cursoService.getAllCurso( true ) );
    }



    @GetMapping( "/all" )
    public ResponseEntity< ResponseDto > getAllCurso() {
        return ResponseEntity.status( HttpStatus.OK ).body( cursoService.getAllCurso( false ) );
    }



    @GetMapping( "/{id}" )
    public ResponseEntity< ResponseDto > getProfesorTrueById( @PathVariable( "id" ) Long id ) {
        return ResponseEntity.status( HttpStatus.OK ).body( cursoService.getCursoById( id, true ) );
    }



    @GetMapping( "/all/{id}" )
    public ResponseEntity< ResponseDto > getProfesorById( @PathVariable( "id" ) Long id ) {
        return ResponseEntity.status( HttpStatus.OK ).body( cursoService.getCursoById( id, false ) );
    }



    @PostMapping
    public ResponseEntity< ResponseDto > createCurso( @RequestBody CursoDto curso ) {
        return ResponseEntity.status( HttpStatus.OK ).body( cursoService.createCurso(curso) );
    }



    @PutMapping
    public ResponseEntity< ResponseDto > updateCurso( @RequestBody CursoDto curso ) {
        return ResponseEntity.status( HttpStatus.OK ).body( cursoService.updateCurso(curso) );
    }

    

    @DeleteMapping( "/{id}" )
    public ResponseEntity< ResponseDto > deleteCursoById( @PathVariable( "id" ) Long id ) {
        return ResponseEntity.status( HttpStatus.OK ).body( cursoService.deleteCursoById( id ) );
    }

}
