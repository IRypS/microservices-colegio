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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping( "/v1/alumno-curso" )
@Api( tags = "Controlador Alumno - Curso", description = "Controlador para las interacciones con los objetos alumno y curso" )
public class AlumnoCursoController {


    @Autowired
    private AlumnoCursoService alumnoCursoService;
    


    @ApiOperation( value = "Método para listar objetos alumno-curso",
        notes = "- Lista individualmente registros de un alumno y un curso" )
    @GetMapping
    public ResponseEntity< ResponseDto > getAllAlumnoCurso() {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoService.getAllAlumnoCurso() );
    }
    


    @ApiOperation( value = "Método para listar objetos alumno-cursos",
        notes = "- Lista individualmente un alumno junto a sus cursos asignados de manera agrupada   " +
                "- **No listará a los alumno que no tengan cursos para mostrar**" )
    @GetMapping( "/alumnos" )
    public ResponseEntity< ResponseDto > getAllAlumnoCursos() {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoService.getAllAlumnoCursos() );
    }
    


    @ApiOperation( value = "Método para buscar un registro ( alumno - curso ) mediante un ID de registro",
        notes = "- Buscará un registro con el ID ingresado" )
    @GetMapping( "/{id}" )
    public ResponseEntity< ResponseDto > getAlumnoCurso( @PathVariable("id") Long id ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoService.getAlumnoCurso( id ) );
    }
    


    @ApiOperation( value = "Método para buscar un registro ( alumno - curso ) mediante alumno y curso",
        notes = "- Buscará un registro coincidente con el ID del alumno y el ID del curso ingresado" )
    @GetMapping( "/alumnos/{idAlumno}/{idCurso}" )
    public ResponseEntity< ResponseDto > getAlumnoCursoByAlumnoAndCurso( 
            @PathVariable("idAlumno") Long idAlumno, 
            @PathVariable("idCurso") Long idCurso  ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoService.getAlumnoCursoByAlumnoAndCurso( idAlumno, idCurso ) );
    }
    


    @ApiOperation( value = "Método para buscar los cursos de un alumno",
        notes = "- Buscará todos los cursos pertenecientes al ID del alumno ingresado   " + 
                "- No mostrará al alumno si no tiene al menos un curso para mostrar")
    @GetMapping( "/alumnos/{idAlumno}" )
    public ResponseEntity< ResponseDto > getAlumnoCursosByAlumno( @PathVariable("idAlumno") Long idAlumno ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoService.getAlumnoCursosByAlumno( idAlumno ) );
    }
    


    // TODO: CREAR UN DTO UNICAMENTE CON ID´S PARA CREAR Y ACTUALZIAR
    @ApiOperation( value = "Método para crear un registro ( alumno - curso ) ",
        notes = "- Se evaluará si existe un registro con los mismos datos y de no encontrarlo creará la relación" )
    @PostMapping
    public ResponseEntity< ResponseDto > createAlumnoCurso(@RequestBody AlumnoCursoDto alumnoCursoDto ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoService.createAlumnoCurso( alumnoCursoDto ) );
    }
    


    @ApiOperation( value = "Método para actualizar un registro ( alumno - curso ) ",
        notes = "- Antes de actualizar evaluará si existe el registro   " +
                "- También evaluará si los nuevos datos del registro ( alumno - curso ) no se encuentra en otro registro existente" )
    @PutMapping( "/{id}" )
    public ResponseEntity< ResponseDto > updateAlumnoCurso(
            @PathVariable("id") Long id,
            @RequestBody AlumnoCursoDto alumnoCursoDto ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoService.updateAlumnoCurso( id, alumnoCursoDto ) );
    }
    


    @ApiOperation( value = "Método para eliminar un registro ( alumno - curso ) mediante un ID de registro",
        notes = "- Se evaluará si existe un registro con el ID ingresado y en caso de encontrarlo, lo borrará" )
    @DeleteMapping( "/{id}" )
    public ResponseEntity< ResponseDto > deleteAlumnoCursoById(@PathVariable("id") Long id ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoService.deleteAlumnoCursoById( id ) );
    }
    


    @ApiOperation( value = "Método para eliminar un registro ( alumno - curso ) mediante un ID de alumno y un ID de curso",
        notes = "- Se evaluará si existe un registro con los ID's ingresados y en caso de encontrarlo, lo borrará" )
    @DeleteMapping( "/alumnos/{idAlumno}/{idCurso}" )
    public ResponseEntity< ResponseDto > deleteAlumnoCursoByAlumnoAndCurso( 
            @PathVariable("idAlumno") Long idAlumno, 
            @PathVariable("idCurso") Long idCurso  ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoCursoService.deleteAlumnoCursoByAlumnoAndCurso( idAlumno, idCurso ) );
    }

    
}
