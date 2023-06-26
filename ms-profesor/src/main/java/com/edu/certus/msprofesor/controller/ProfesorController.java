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

import com.edu.certus.msprofesor.dto.ProfesorDto;
import com.edu.certus.msprofesor.dto.ResponseDto;
import com.edu.certus.msprofesor.service.ProfesorService;

@RestController
@RequestMapping( "/v1/profesor" )
public class ProfesorController {
    
    

    
    @Autowired
    private ProfesorService profesorService;
    


    
    @GetMapping
    public ResponseEntity< ResponseDto > getAllProfesorTrue() {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorService.getAllProfesor( true ) );
    }
    


    @GetMapping( "/all" )
    public ResponseEntity< ResponseDto > getAllProfesor() {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorService.getAllProfesor( false ) );
    }



    @GetMapping( "/{id}" )
    public ResponseEntity< ResponseDto > getProfesorTrueById( @PathVariable("id") Long idProfesor ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorService.getProfesorById( idProfesor, true ) );
    }



    @GetMapping( "/all/{id}" )
    public ResponseEntity< ResponseDto > getProfesorById( @PathVariable("id") Long idProfesor ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorService.getProfesorById( idProfesor, false ) );
    }



    @PostMapping
    public ResponseEntity< ResponseDto > createProfesor( @RequestBody ProfesorDto profesorDto ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorService.createProfesor( profesorDto ) );
    }



    @PutMapping
    public ResponseEntity< ResponseDto > updateProfesor( @RequestBody ProfesorDto profesorDto ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorService.updateProfesor( profesorDto ) );
    }



    @DeleteMapping( "/{id}" )
    public ResponseEntity< ResponseDto > deleteAlumnoById( @PathVariable("id") Long idProfesor ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorService.deleteProfesorById( idProfesor ) );
    }

}
