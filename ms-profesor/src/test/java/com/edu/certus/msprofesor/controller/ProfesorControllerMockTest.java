package com.edu.certus.msprofesor.controller;

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

import com.edu.certus.msprofesor.dto.ProfesorDto;
import com.edu.certus.msprofesor.dto.ResponseDto;
import com.edu.certus.msprofesor.service.impl.ProfesorServiceImpl;
import com.edu.certus.msprofesor.util.Constantes;
import com.edu.certus.msprofesor.util.UtilProfesorData;

@ExtendWith( MockitoExtension.class )
public class ProfesorControllerMockTest {
    
    @Mock
	private ProfesorServiceImpl profesorService;
	
	@InjectMocks
	private ProfesorController profesorController;


	private String codeSuccess = Constantes.CODE_SUCCES;
	private String messageOpSuccess = Constantes.OPERATION_SUCCESS;


    @Test
	@DisplayName( "Test de listar profesores visibles con exito" )
	public void getAllProfesorTrueTestSuccess() {

		when( profesorService.getAllProfesor( true ) ).thenReturn( 
			UtilProfesorData.dummyResponseDto( codeSuccess, messageOpSuccess ) 
		);

		ResponseEntity<ResponseDto> response = profesorController.getAllProfesorTrue();
		
		assertAll( "Listar profesores visibles" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}


	@Test
	@DisplayName( "Test de listar profesores all con exito" )
	public void getAllProfesorTestSuccess() {

		when( profesorService.getAllProfesor( false ) ).thenReturn( 
			UtilProfesorData.dummyResponseDto( codeSuccess, messageOpSuccess ) 
		);

		ResponseEntity<ResponseDto> response = profesorController.getAllProfesor();
		
		assertAll( "Listar profesores" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}


	
	@Test
	@DisplayName( "Test de buscar profesor true by id con exito" )
	public void getProfesorTrueByIdTestSuccess() {

		when( profesorService.getProfesorById( UtilProfesorData.ID_TRUE, true ) ).thenReturn( 
			UtilProfesorData.dummyResponseDto( codeSuccess, messageOpSuccess ) 
		);

		ResponseEntity<ResponseDto> response = profesorController.getProfesorTrueById( UtilProfesorData.ID_TRUE );
		
		assertAll( "Buscar profesor por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}


	
	@Test
	@DisplayName( "Test de buscar profesor all by id con exito" )
	public void getProfesorByIdTestSuccess() {

		when( profesorService.getProfesorById( UtilProfesorData.ID_TRUE, false ) ).thenReturn( 
			UtilProfesorData.dummyResponseDto( codeSuccess, messageOpSuccess ) 
		);

		ResponseEntity<ResponseDto> response = profesorController.getProfesorById( UtilProfesorData.ID_TRUE );
		
		assertAll( "Buscar profesor por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}



	@Test
	@DisplayName( "Test de crear profesor con exito" )
	public void createProfesorTestSuccess() {

		when( profesorService.createProfesor( any(ProfesorDto.class) ) ).thenReturn( 
			UtilProfesorData.dummyResponseDto( codeSuccess, messageOpSuccess ) 
		);

		ResponseEntity<ResponseDto> response = profesorController.createProfesor( UtilProfesorData.dummyProfesorDto() );
		
		assertAll( "Crear profesor" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}



	@Test
	@DisplayName( "Test de actualizar profesor con exito" )
	public void updateProfesorTestSuccess() {

		when( profesorService.updateProfesor( any( ProfesorDto.class ) ) ).thenReturn( 
			UtilProfesorData.dummyResponseDto( codeSuccess, messageOpSuccess ) 
		);

		ResponseEntity<ResponseDto> response = profesorController.updateProfesor( UtilProfesorData.dummyProfesorDto() );
		
		assertAll( "Actualizando profesor" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}



	@Test
	@DisplayName( "Test de eliminar profesor con exito" )
	public void deleteProfesorByIdTestSuccess() {

		when( profesorService.deleteProfesorById( UtilProfesorData.ID_TRUE ) ).thenReturn( 
			UtilProfesorData.dummyResponseDto( codeSuccess, messageOpSuccess ) 
		);

		ResponseEntity<ResponseDto> response = profesorController.deleteProfesorById( UtilProfesorData.ID_TRUE );
		
		assertAll( "Borrando profesor por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}
}
