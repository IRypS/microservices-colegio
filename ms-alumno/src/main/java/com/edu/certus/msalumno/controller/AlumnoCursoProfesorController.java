package com.edu.certus.msalumno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.certus.msalumno.dto.ResponseDto;
import com.edu.certus.msalumno.service.AlumnoCursoProfesorService;

@RestController
@RequestMapping( "/v1/alumno-curso-profesor" )
public class AlumnoCursoProfesorController {


    @Autowired
    private AlumnoCursoProfesorService alumnoCursoProfesorService;


    @GetMapping
    public ResponseEntity< ResponseDto > getAllAlumnoCursoProfesor() {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoProfesorService.getAllAlumnoCursoProfesor() );
    }
    
}
