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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping( "/v1/alumno" )
@Api( tags = "Controlador Alumno", description = "Controlador para las interacciones con el objeto Alumno" )
public class AlumnoController {


    @Autowired
    private AlumnoService alumnoService;
    


    @ApiOperation( value = "Método para listar los alumnos visibles",
        notes = "- Lista todos los alumnos cuyo estado sea **true**" )
    @GetMapping
    public ResponseEntity< ResponseDto > getAllAlumnoTrue() {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoService.getAllAlumno( true ) );
    }
    


    @ApiOperation( value = "Método para listar todos los alumnos",
        notes = "- Lista todos los alumnos sin importar su visibilidad (estado)" )
    @GetMapping( "/all" )
    public ResponseEntity< ResponseDto > getAllAlumno() {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoService.getAllAlumno( false ) );
    }



    @ApiOperation( value = "Método para obtener un alumno visible mediante su ID", 
        notes = "- Solo encuentra el alumno si tiene el estado con el valor **true**" )
    @GetMapping( "/{id}" )
    public ResponseEntity< ResponseDto > getAlumnoTrueById( @PathVariable("id") Long idAlumno ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoService.getAlumnoById( idAlumno, true ) );
    }



    @ApiOperation( value = "Método para obtener un alumno mediante su ID", 
        notes = "- Busca un alumno sin importar su visibilidad" )
    @GetMapping( "/all/{id}" )
    public ResponseEntity< ResponseDto > getAlumnoById( @PathVariable("id") Long idAlumno ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoService.getAlumnoById( idAlumno, false ) );
    }



    // TODO: [OPCIONAL] CREAR UN DTO CON SOLO ATRIBUTOS NECESARIOS PARA LA CREACION
    @ApiOperation( value = "Método para crear un alumno",
        notes = "- Al crearse, el atributo \"estado\" se inicializa en [True]   " + 
                "- El atributo ID se crea una vez se registra en la base de datos   " + 
                "- **No se creará el alumno si ya existe uno visible con los mismos nombres y apellidos**   " )
    @PostMapping
    public ResponseEntity< ResponseDto > createAlumno( @RequestBody AlumnoDto alumnoDto ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoService.createAlumno( alumnoDto ) );
    }



    @ApiOperation( value = "Método para actualizar un alumno",
        notes = "- Se debe incluir todo el objeto alumno, incluyendo el ID y los atributos que no se actualizan   " +
                "- El ID del objeto debe existir en la base de datos y estar visible   " +
                "- **No se actualiza el estado**" )
    @PutMapping
    public ResponseEntity< ResponseDto > updateAlumno( @RequestBody AlumnoDto alumnoDto ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoService.updateAlumno( alumnoDto ) );
    }



    @ApiOperation( value = "Método para eliminar un alumno mediante su id",
        notes = "- El borrado es lógico (cambia el estado de **true** a **false**)   " +
                "- Solo borrará los alumnos visibles (no encontrá el registro si ya se encuentra borrado)" )
    @DeleteMapping( "/{id}" )
    public ResponseEntity< ResponseDto > deleteAlumnoById( @PathVariable("id") Long idAlumno ) {
        return ResponseEntity.status( HttpStatus.OK ).body( alumnoService.deleteAlumnoById( idAlumno ) );
    }

    
}
