package com.edu.certus.mscurso.controller;

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

import com.edu.certus.mscurso.dto.CursoDto;
import com.edu.certus.mscurso.dto.ResponseDto;
import com.edu.certus.mscurso.service.impl.CursoServiceImpl;
import com.edu.certus.mscurso.util.Constantes;
import com.edu.certus.mscurso.util.UtilCursoData;

@ExtendWith( MockitoExtension.class )
public class CursoControllerMockTest {

    @Mock
	private CursoServiceImpl cursoService;
	
	@InjectMocks
	private CursoController cursoController;


	private String codeSuccess = Constantes.CODE_SUCCES;
	private String messageOpSuccess = Constantes.OPERATION_SUCCESS;


    @Test
	@DisplayName( "Test de listar cursos visibles con exito" )
	public void getAllCursoTrueTestSuccess() {

		when( cursoService.getAllCurso( true ) )
            .thenReturn( UtilCursoData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = cursoController.getAllCursoTrue();
		
		assertAll( "Listar cursos visibles" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}


    @Test
	@DisplayName( "Test de listar cursos all con exito" )
	public void getAllCursoTestSuccess() {

		when( cursoService.getAllCurso( false ) )
            .thenReturn( UtilCursoData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = cursoController.getAllCurso();
		
		assertAll( "Listar cursos" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}


	
	@Test
	@DisplayName( "Test de buscar curso true by id con exito" )
	public void getCursoTrueByIdTestSuccess() {

		when( cursoService.getCursoById( UtilCursoData.ID_TRUE, true ) )
            .thenReturn( UtilCursoData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = cursoController.getCursoTrueById( UtilCursoData.ID_TRUE );
		
		assertAll( "Buscar curso por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}


	
	@Test
	@DisplayName( "Test de buscar curso all by id con exito" )
	public void getCursoByIdTestSuccess() {

		when( cursoService.getCursoById( UtilCursoData.ID_TRUE, false ) )
            .thenReturn( UtilCursoData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = cursoController.getCursoById( UtilCursoData.ID_TRUE );
		
		assertAll( "Buscar curso por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}



	@Test
	@DisplayName( "Test de crear curso con exito" )
	public void createCursoTestSuccess() {

		when( cursoService.createCurso( any(CursoDto.class) ) )
            .thenReturn( UtilCursoData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = cursoController.createCurso( UtilCursoData.dummyCursoDto() );
		
		assertAll( "Crear curso" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}



	@Test
	@DisplayName( "Test de actualizar curso con exito" )
	public void updateCursoTestSuccess() {

		when( cursoService.updateCurso( any( CursoDto.class ) ) )
            .thenReturn( UtilCursoData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = cursoController
            .updateCurso( UtilCursoData.dummyCursoDto() );
		
		assertAll( "Actualizar curso" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}



	@Test
	@DisplayName( "Test de eliminar curso con exito" )
	public void deleteCursoByIdTestSuccess() {

		when( cursoService.deleteCursoById( UtilCursoData.ID_TRUE ) )
            .thenReturn( UtilCursoData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = cursoController.deleteCursoById( UtilCursoData.ID_TRUE );
		
		assertAll( "Eliminar curso" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}
    
}
