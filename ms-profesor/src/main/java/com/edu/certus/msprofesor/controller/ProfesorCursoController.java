package com.edu.certus.msprofesor.controller;

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

import com.edu.certus.msprofesor.dto.ProfesorCursoDto;
import com.edu.certus.msprofesor.dto.ResponseDto;
import com.edu.certus.msprofesor.service.ProfesorCursoService;

@RestController
@RequestMapping( "/v1/profesor-curso" )
public class ProfesorCursoController {


    @Autowired
    private ProfesorCursoService profesorCursoService;
    


    @GetMapping
    public ResponseEntity< ResponseDto > getAllProfesorCurso() {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorCursoService.getAllProfesorCurso() );
    }
    


    @GetMapping( "/profesores" )
    public ResponseEntity< ResponseDto > getAllProfesorCursos() {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorCursoService.getAllProfesorCursos() );
    }
    


    @GetMapping( "/{id}" )
    public ResponseEntity< ResponseDto > getProfesorCursoById( @PathVariable("id") Long id ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorCursoService.getProfesorCursoById( id ) );
    }
    


    @GetMapping( "/profesores/{idProfesor}/{idCurso}" )
    public ResponseEntity< ResponseDto > getProfesorCursoByProfesorAndCurso( 
            @PathVariable("idProfesor") Long idProfesor, 
            @PathVariable("idCurso") Long idCurso  ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorCursoService.getProfesorCursoByProfesorAndCurso( idProfesor, idCurso ) );
    }
    


    @GetMapping( "/profesores/{idProfesor}" )
    public ResponseEntity< ResponseDto > getProfesorCursosByProfesor( @PathVariable("idProfesor") Long idProfesor ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorCursoService.getProfesorCursosByProfesor( idProfesor ) );
    }
    


    @PostMapping
    public ResponseEntity< ResponseDto > createProfesorCurso( @RequestBody ProfesorCursoDto profesorCursoDto ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorCursoService.createProfesorCurso( profesorCursoDto ) );
    }
    


    @PutMapping( "/{id}" )
    public ResponseEntity< ResponseDto > updateProfesorCurso(
            @PathVariable("id") Long id,
            @RequestBody ProfesorCursoDto profesorCursoDto ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorCursoService.updateProfesorCurso( id, profesorCursoDto ) );
    }
    


    @DeleteMapping( "/{id}" )
    public ResponseEntity< ResponseDto > deleteProfesorCursoById(@PathVariable("id") Long id ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorCursoService.deleteProfesorCursoById( id ) );
    }
    


    @DeleteMapping( "/profesores/{idProfesor}/{idCurso}" )
    public ResponseEntity< ResponseDto > deleteProfesorCursoByProfesorAndCurso( 
            @PathVariable("idProfesor") Long idProfesor, 
            @PathVariable("idCurso") Long idCurso  ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorCursoService.deleteProfesorCursoByProfesorAndCurso( idProfesor, idCurso ) );
    }

    
}
