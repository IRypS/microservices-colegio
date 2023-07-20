package com.edu.certus.msalumno.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.edu.certus.msalumno.dto.AlumnoDto;
import com.edu.certus.msalumno.dto.ResponseDto;
import com.edu.certus.msalumno.service.impl.AlumnoServiceImpl;
import com.edu.certus.msalumno.util.Constantes;
import com.edu.certus.msalumno.util.UtilAlumnoData;

@ExtendWith( MockitoExtension.class )
public class AlumnoControllerMockTest {

    @Mock
	private AlumnoServiceImpl alumnoService;
	
	@InjectMocks
	private AlumnoController alumnoController;


	private String codeSuccess = Constantes.CODE_SUCCES;
	private String messageOpSuccess = Constantes.OPERATION_SUCCESS;


    @Test
	@DisplayName( "Test de listar alumnos visibles con exito" )
	public void getAllAlumnoTrueTestSuccess() {

		when( alumnoService.getAllAlumno( true ) ).thenReturn( 
			UtilAlumnoData.dummyResponseDto( codeSuccess, messageOpSuccess ) 
		);

		ResponseEntity<ResponseDto> response = alumnoController.getAllAlumnoTrue();
		
		assertAll( "Listar alumnos visibles" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}


	@Test
	@DisplayName( "Test de listar alumnos all con exito" )
	public void getAllAlumnoTestSuccess() {

		when( alumnoService.getAllAlumno( false ) ).thenReturn( 
			UtilAlumnoData.dummyResponseDto( codeSuccess, messageOpSuccess ) 
		);

		ResponseEntity<ResponseDto> response = alumnoController.getAllAlumno();
		
		assertAll( "Listar alumnos" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}


	
	@Test
	@DisplayName( "Test de buscar alumno true by id con exito" )
	public void getAlumnoTrueByIdTestSuccess() {

		when( alumnoService.getAlumnoById( UtilAlumnoData.ID_TRUE, true ) ).thenReturn( 
			UtilAlumnoData.dummyResponseDto( codeSuccess, messageOpSuccess ) 
		);

		ResponseEntity<ResponseDto> response = alumnoController.getAlumnoTrueById( UtilAlumnoData.ID_TRUE );
		
		assertAll( "Buscar alumnos por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}


	
	@Test
	@DisplayName( "Test de buscar alumno all by id con exito" )
	public void getAlumnoByIdTestSuccess() {

		when( alumnoService.getAlumnoById( UtilAlumnoData.ID_TRUE, false ) ).thenReturn( 
			UtilAlumnoData.dummyResponseDto( codeSuccess, messageOpSuccess ) 
		);

		ResponseEntity<ResponseDto> response = alumnoController.getAlumnoById( UtilAlumnoData.ID_TRUE );
		
		assertAll( "Buscar alumnos por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}



	@Test
	@DisplayName( "Test de crear alumno con exito" )
	public void createAlumnoTestSuccess() {

		when( alumnoService.createAlumno( any(AlumnoDto.class) ) ).thenReturn( 
			UtilAlumnoData.dummyResponseDto( codeSuccess, messageOpSuccess ) 
		);

		ResponseEntity<ResponseDto> response = alumnoController.createAlumno( UtilAlumnoData.dummyAlumnoDto() );
		
		assertAll( "Crear alumno" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}



	@Test
	@DisplayName( "Test de actualizar alumno con exito" )
	public void updateAlumnoTestSuccess() {

		when( alumnoService.updateAlumno( any( AlumnoDto.class ) ) ).thenReturn( 
			UtilAlumnoData.dummyResponseDto( codeSuccess, messageOpSuccess ) 
		);

		ResponseEntity<ResponseDto> response = alumnoController.updateAlumno( UtilAlumnoData.dummyAlumnoDto() );
		
		assertAll( "a" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}



	@Test
	@DisplayName( "Test de eliminar alumno con exito" )
	public void deleteAlumnoByIdTestSuccess() {

		when( alumnoService.deleteAlumnoById( UtilAlumnoData.ID_TRUE ) ).thenReturn( 
			UtilAlumnoData.dummyResponseDto( codeSuccess, messageOpSuccess ) 
		);

		ResponseEntity<ResponseDto> response = alumnoController.deleteAlumnoById( UtilAlumnoData.ID_TRUE );
		
		assertAll( "a" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}
    
}
