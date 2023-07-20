package com.edu.certus.msalumno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.certus.msalumno.dto.ResponseDto;
import com.edu.certus.msalumno.service.AlumnoCursoProfesorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping( "/v1/alumno-curso-profesor" )
@Api( tags = "Controlador Alumno - Curso - Profesor", description = "Controlador para las interacciones con los objetos alumno , curso y profesor" )
public class AlumnoCursoProfesorController {



    @Autowired
    private AlumnoCursoProfesorService alumnoCursoProfesorService;


    @ApiOperation( value = "Método para listar objetos alumno-curso-profesor",
        notes = "- Lista individualmente registros de un alumno, un curso y un profesor" )
    @GetMapping
    public ResponseEntity< ResponseDto > getAllAlumnoCursoProfesor() {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoProfesorService.getAllAlumnoCursoProfesor() );
    }
    
}
