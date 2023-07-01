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

import com.edu.certus.msprofesor.dto.ProfesorCursoSendDto;
import com.edu.certus.msprofesor.dto.ResponseDto;
import com.edu.certus.msprofesor.service.ProfesorCursoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping( "/v1/profesor-curso" )
@Api( tags = "Controlador Profesor - Curso", description = "Controlador para las interacciones con los objetos profesor y curso" )
public class ProfesorCursoController {


    @Autowired
    private ProfesorCursoService profesorCursoService;
    


    @ApiOperation( value = "Método para listar objetos profesor-curso",
        notes = "- Lista individualmente registros de un profesor y un curso" )
    @GetMapping
    public ResponseEntity< ResponseDto > getAllProfesorCurso() {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorCursoService.getAllProfesorCurso() );
    }
    


    @ApiOperation( value = "Método para listar objetos profesor-cursos",
        notes = "- Lista individualmente un profesor junto a sus cursos asignados de manera agrupada   " +
                "- **No listará a los profesores que no tengan cursos para mostrar**" )
    @GetMapping( "/profesores" )
    public ResponseEntity< ResponseDto > getAllProfesorCursos() {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorCursoService.getAllProfesorCursos() );
    }
    


    @ApiOperation( value = "Método para buscar un registro ( profesor - curso ) mediante un ID de registro",
        notes = "- Buscará un registro con el ID ingresado" )
    @GetMapping( "/{id}" )
    public ResponseEntity< ResponseDto > getProfesorCursoById( @PathVariable("id") Long id ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorCursoService.getProfesorCursoById( id ) );
    }
    


    @ApiOperation( value = "Método para buscar un registro ( profesor - curso ) mediante profesor y curso",
        notes = "- Buscará un registro coincidente con el ID del profesor y el ID del curso ingresado" )
    @GetMapping( "/profesores/{idProfesor}/{idCurso}" )
    public ResponseEntity< ResponseDto > getProfesorCursoByProfesorAndCurso( 
            @PathVariable("idProfesor") Long idProfesor, 
            @PathVariable("idCurso") Long idCurso  ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorCursoService.getProfesorCursoByProfesorAndCurso( idProfesor, idCurso ) );
    }
    


    @ApiOperation( value = "Método para buscar los cursos de un profesor",
        notes = "- Buscará todos los cursos pertenecientes al ID del profesor ingresado   " + 
                "- No mostrará al profesor si no tiene al menos un curso para mostrar")
    @GetMapping( "/profesores/{idProfesor}" )
    public ResponseEntity< ResponseDto > getProfesorCursosByProfesor( @PathVariable("idProfesor") Long idProfesor ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorCursoService.getProfesorCursosByProfesor( idProfesor ) );
    }
    


    @ApiOperation( value = "Método para crear un registro ( profesor - curso ) ",
        notes = "- Se evaluará si existe un registro con los mismos datos y de no encontrarlo creará el registro   " + 
                "- El atributo ID se crea una vez se registra en la base de datos" )
    @PostMapping
    public ResponseEntity< ResponseDto > createProfesorCurso( @RequestBody ProfesorCursoSendDto profesorCursoSendDto ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorCursoService.createProfesorCurso( profesorCursoSendDto ) );
    }
    


    @ApiOperation( value = "Método para actualizar un registro ( profesor - curso ) ",
        notes = "- Antes de actualizar evaluará si existe el registro   " +
                "- También evaluará si los nuevos datos del registro ( profesor - curso ) no se encuentra en otro registro existente" )
    @PutMapping
    public ResponseEntity< ResponseDto > updateProfesorCurso( @RequestBody ProfesorCursoSendDto profesorCursoSendDto ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorCursoService.updateProfesorCurso( profesorCursoSendDto ) );
    }
    


    @ApiOperation( value = "Método para eliminar un registro ( profesor - curso ) mediante un ID de registro",
        notes = "- Se evaluará si existe un registro con el ID ingresado y en caso de encontrarlo, lo borrará" )
    @DeleteMapping( "/{id}" )
    public ResponseEntity< ResponseDto > deleteProfesorCursoById(@PathVariable("id") Long id ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorCursoService.deleteProfesorCursoById( id ) );
    }
    


    @ApiOperation( value = "Método para eliminar un registro ( profesor - curso ) mediante un ID de profesor y un ID de curso",
        notes = "- Se evaluará si existe un registro con los ID's ingresados y en caso de encontrarlo, lo borrará" )
    @DeleteMapping( "/profesores/{idProfesor}/{idCurso}" )
    public ResponseEntity< ResponseDto > deleteProfesorCursoByProfesorAndCurso( 
            @PathVariable("idProfesor") Long idProfesor, 
            @PathVariable("idCurso") Long idCurso  ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorCursoService.deleteProfesorCursoByProfesorAndCurso( idProfesor, idCurso ) );
    }

    
}
