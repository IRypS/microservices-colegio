package com.edu.certus.msalumno.controller;

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

import com.edu.certus.msalumno.dto.AlumnoCursoDto;
import com.edu.certus.msalumno.dto.ResponseDto;
import com.edu.certus.msalumno.service.AlumnoCursoService;

@RestController
@RequestMapping( "/v1/alumno-curso" )
public class AlumnoCursoController {



    @Autowired
    private AlumnoCursoService alumnoCursoService;
    


    @GetMapping
    public ResponseEntity< ResponseDto > getAllAlumnoCurso() {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoService.getAllAlumnoCurso() );
    }
    


    @GetMapping( "/alumnos" )
    public ResponseEntity< ResponseDto > getAllAlumnoCursos() {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoService.getAllAlumnoCursos() );
    }
    


    @GetMapping( "/{id}" )
    public ResponseEntity< ResponseDto > getAlumnoCurso( @PathVariable("id") Long id ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoService.getAlumnoCurso( id ) );
    }
    


    @GetMapping( "/alumnos/{idAlumno}/{idCurso}" )
    public ResponseEntity< ResponseDto > getAlumnoCursoByAlumnoAndCurso( 
            @PathVariable("idAlumno") Long idAlumno, 
            @PathVariable("idCurso") Long idCurso  ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoService.getAlumnoCursoByAlumnoAndCurso( idAlumno, idCurso ) );
    }
    


    @GetMapping( "/alumnos/{idAlumno}" )
    public ResponseEntity< ResponseDto > getAlumnoCursosByAlumno( @PathVariable("idAlumno") Long idAlumno ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoService.getAlumnoCursosByAlumno( idAlumno ) );
    }
    


    @PostMapping
    public ResponseEntity< ResponseDto > createAlumnoCurso(@RequestBody AlumnoCursoDto alumnoCursoDto ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoService.createAlumnoCurso( alumnoCursoDto ) );
    }
    


    @PutMapping( "/{id}" )
    public ResponseEntity< ResponseDto > updateAlumnoCurso(
            @PathVariable("id") Long id,
            @RequestBody AlumnoCursoDto alumnoCursoDto ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoService.updateAlumnoCurso( id, alumnoCursoDto ) );
    }
    


    @DeleteMapping( "/{id}" )
    public ResponseEntity< ResponseDto > deleteAlumnoCursoById(@PathVariable("id") Long id ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoService.deleteAlumnoCursoById( id ) );
    }
    


    @DeleteMapping( "/alumnos/{idAlumno}/{idCurso}" )
    public ResponseEntity< ResponseDto > deleteAlumnoCursoByAlumnoAndCurso( 
            @PathVariable("idAlumno") Long idAlumno, 
            @PathVariable("idCurso") Long idCurso  ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoService.deleteAlumnoCursoByAlumnoAndCurso( idAlumno, idCurso ) );
    }

    
}
