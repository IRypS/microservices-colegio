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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping( "/v1/profesor" )
@Api( tags = "Controlador Profesor", description = "Controlador para las interacciones con el objeto Profesor" )
public class ProfesorController {
    
    
    @Autowired
    private ProfesorService profesorService;
    


    @ApiOperation( value = "Método para listar los profesores visibles",
        notes = "- Lista todos los profesores cuyo estado sea **true**" )
    @GetMapping
    public ResponseEntity< ResponseDto > getAllProfesorTrue() {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorService.getAllProfesor( true ) );
    }
    


    @ApiOperation( value = "Método para listar todos los profesores",
        notes = "- Lista todos los profesores sin importar su visibilidad (estado)" )
    @GetMapping( "/all" )
    public ResponseEntity< ResponseDto > getAllProfesor() {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorService.getAllProfesor( false ) );
    }



    @ApiOperation( value = "Método para obtener un profesor visible mediante su ID", 
        notes = "- Solo encuentra el profesor si tiene el estado con el valor **true**" )
    @GetMapping( "/{id}" )
    public ResponseEntity< ResponseDto > getProfesorTrueById( @PathVariable("id") Long idProfesor ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorService.getProfesorById( idProfesor, true ) );
    }



    @ApiOperation( value = "Método para obtener un profesor mediante su ID", 
        notes = "- Busca un profesor sin importar su visibilidad" )
    @GetMapping( "/all/{id}" )
    public ResponseEntity< ResponseDto > getProfesorById( @PathVariable("id") Long idProfesor ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorService.getProfesorById( idProfesor, false ) );
    }



    // TODO: [OPCIONAL] CREAR UN DTO CON SOLO ATRIBUTOS NECESARIOS PARA LA CREACION
    @ApiOperation( value = "Método para crear un profesor",
        notes = "- Al crearse, el atributo \"estado\" se inicializa en [True]   " + 
                "- El atributo ID se crea una vez se registra en la base de datos   " + 
                "- **No se creará el profesor si ya existe uno visible con los mismos nombres y apellidos**   " )
    @PostMapping
    public ResponseEntity< ResponseDto > createProfesor( @RequestBody ProfesorDto profesorDto ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorService.createProfesor( profesorDto ) );
    }



    @ApiOperation( value = "Método para actualizar un profesor",
        notes = "- Se debe incluir todo el objeto profesor, incluyendo el ID y los atributos que no se actualizan   " +
                "- El ID del objeto debe existir en la base de datos y estar visible   " +
                "- **No se actualiza el estado**" )
    @PutMapping
    public ResponseEntity< ResponseDto > updateProfesor( @RequestBody ProfesorDto profesorDto ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorService.updateProfesor( profesorDto ) );
    }



    @ApiOperation( value = "Método para eliminar un profesor mediante su id",
        notes = "- El borrado es lógico (cambia el estado de **true** a **false**)   " +
                "- Solo borrará los profesores visibles (no encontrá el registro si ya se encuentra borrado)" )
    @DeleteMapping( "/{id}" )
    public ResponseEntity< ResponseDto > deleteAlumnoById( @PathVariable("id") Long idProfesor ) {
        return ResponseEntity.status( HttpStatus.OK ).body( profesorService.deleteProfesorById( idProfesor ) );
    }

}
