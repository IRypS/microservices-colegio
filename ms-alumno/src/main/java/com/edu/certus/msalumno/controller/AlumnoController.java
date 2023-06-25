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

import com.edu.certus.msalumno.dto.AlumnoDto;
import com.edu.certus.msalumno.dto.ResponseDto;
import com.edu.certus.msalumno.service.AlumnoService;

@RestController
@RequestMapping( "/v1/alumno" )
public class AlumnoController {


    
    @Autowired
    private AlumnoService alumnoService;
    


    @GetMapping
    public ResponseEntity< ResponseDto > getAllAlumnoTrue() {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoService.getAllAlumno( true ) );
    }
    


    @GetMapping( "/all" )
    public ResponseEntity< ResponseDto > getAllAlumno() {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoService.getAllAlumno( false ) );
    }



    @GetMapping( "/{id}" )
    public ResponseEntity< ResponseDto > getAlumnoTrueById( @PathVariable("id") Long idAlumno ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoService.getAlumnoById( idAlumno, true ) );
    }



    @GetMapping( "/all/{id}" )
    public ResponseEntity< ResponseDto > getAlumnoById( @PathVariable("id") Long idAlumno ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoService.getAlumnoById( idAlumno, false ) );
    }



    @PostMapping
    public ResponseEntity< ResponseDto > createAlumno( @RequestBody AlumnoDto alumnoDto ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoService.createAlumno( alumnoDto ) );
    }



    @PutMapping
    public ResponseEntity< ResponseDto > updateAlumno( @RequestBody AlumnoDto alumnoDto ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoService.updateAlumno( alumnoDto ) );
    }



    @DeleteMapping( "/{id}" )
    public ResponseEntity< ResponseDto > deleteAlumnoById( @PathVariable("id") Long idAlumno ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoService.deleteAlumnoById( idAlumno ) );
    }

    
}
