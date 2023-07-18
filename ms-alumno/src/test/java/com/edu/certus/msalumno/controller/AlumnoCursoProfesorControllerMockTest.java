package com.edu.certus.msalumno.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.edu.certus.msalumno.dto.ResponseDto;
import com.edu.certus.msalumno.service.impl.AlumnoCursoProfesorServiceImpl;
import com.edu.certus.msalumno.util.Constantes;
import com.edu.certus.msalumno.util.UtilAlumnoData;

@ExtendWith( MockitoExtension.class )
public class AlumnoCursoProfesorControllerMockTest {
    
    @Mock
	private AlumnoCursoProfesorServiceImpl alumnoCursoProfesorService;
	
	@InjectMocks
	private AlumnoCursoProfesorController alumnoCursoProfesorController;


	private String codeSuccess = Constantes.CODE_SUCCES;
	private String messageOpSuccess = Constantes.OPERATION_SUCCESS;


    @Test
    @DisplayName( "Test de listar alumno, curso y profesor" )
    public void a() {
        
        when( alumnoCursoProfesorService.getAllAlumnoCursoProfesor() )
            .thenReturn( UtilAlumnoData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = alumnoCursoProfesorController.getAllAlumnoCursoProfesor();
		
		assertAll( "Listar registros" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

    }


}
