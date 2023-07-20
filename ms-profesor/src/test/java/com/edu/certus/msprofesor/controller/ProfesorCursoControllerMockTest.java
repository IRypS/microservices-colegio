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

import com.edu.certus.msprofesor.dto.ProfesorCursoSendDto;
import com.edu.certus.msprofesor.dto.ResponseDto;
import com.edu.certus.msprofesor.service.impl.ProfesorCursoServiceImpl;
import com.edu.certus.msprofesor.util.Constantes;
import com.edu.certus.msprofesor.util.UtilProfesorCursoData;
import com.edu.certus.msprofesor.util.UtilProfesorData;

@ExtendWith( MockitoExtension.class )
public class ProfesorCursoControllerMockTest {
    
    @Mock
	private ProfesorCursoServiceImpl profesorCursoService;
	
	@InjectMocks
	private ProfesorCursoController profesorCursoController;


	private String codeSuccess = Constantes.CODE_SUCCES;
	private String messageOpSuccess = Constantes.OPERATION_SUCCESS;

    private Long ID_PROFESOR = UtilProfesorData.ID_TRUE;
    private Long ID_CURSO = UtilProfesorCursoData.ID_CURSO;
    private Long ID_PROFESOR_CURSO = UtilProfesorCursoData.ID_PROFESOR_CURSO;


    @Test
	@DisplayName( "Test de listar registros profesor curso con exito" )
	public void getAllProfesorCursoTestSuccess() {

		when( profesorCursoService.getAllProfesorCurso() )
            .thenReturn( UtilProfesorData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = profesorCursoController.getAllProfesorCurso();
		
		assertAll( "Listar registros" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}



    @Test
    @DisplayName( "Test de listar registros profesor y sus cursos con éxito" )
    public void getAllProfesorCursosTestSucces() {

        when( profesorCursoService.getAllProfesorCursos() )
            .thenReturn( UtilProfesorData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = profesorCursoController.getAllProfesorCursos();
		
		assertAll( "Listar registros profesor cursos" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

    }



    @Test
    @DisplayName( "Test de buscar un registro por id con éxito" )
    public void getProfesorCursoByIdTestSuccess() {

        when( profesorCursoService.getProfesorCursoById( ID_PROFESOR_CURSO ) )
            .thenReturn( UtilProfesorData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = profesorCursoController.getProfesorCursoById( ID_PROFESOR_CURSO  );
		
		assertAll( "Buscar registro por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

    }



    @Test
    @DisplayName( "Test de buscar un registro por idAlumno y idCurso con éxito" )
    public void getProfesorCursoByProfesorAndCursoTestSuccess() {

        when( profesorCursoService.getProfesorCursoByProfesorAndCurso( ID_PROFESOR, ID_CURSO ) )
            .thenReturn( UtilProfesorData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = profesorCursoController
            .getProfesorCursoByProfesorAndCurso( ID_PROFESOR, ID_CURSO );
		
		assertAll( "Buscar registro por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

    }



    @Test
    @DisplayName( "Test de registros por idProfesor con éxito" )
    public void getProfesorCursosByProfesorTestSuccess() {

        when( profesorCursoService.getProfesorCursosByProfesor( ID_PROFESOR ) )
            .thenReturn( UtilProfesorData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = profesorCursoController
            .getProfesorCursosByProfesor( ID_PROFESOR );
		
		assertAll( "Buscar registro por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

    }



    @Test
    @DisplayName( "Test de registros por idCurso con éxito" )
    public void getProfesorCursosByCursoTestSuccess() {

        when( profesorCursoService.getProfesorCursosByCurso( ID_CURSO ) )
            .thenReturn( UtilProfesorData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = profesorCursoController
            .getProfesorCursosByCurso( ID_CURSO );
		
		assertAll( "Buscar registro por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

    }



    @Test
    @DisplayName( "Test de crear profesor con éxito" )
    public void createProfesorCursoTestSuccess() {

        when( profesorCursoService.createProfesorCurso( any( ProfesorCursoSendDto.class ) ) )
            .thenReturn( UtilProfesorData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = profesorCursoController
            .createProfesorCurso( UtilProfesorCursoData.dummyProfesorCursoDto() );
		
		assertAll( "Buscar registro por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

    }



    @Test
    @DisplayName( "Test de actualizar profesor con éxito" )
    public void updateProfesorCursoTestSuccess() {

        when( profesorCursoService.updateProfesorCurso( any( ProfesorCursoSendDto.class ) ) )
            .thenReturn( UtilProfesorData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = profesorCursoController
            .updateProfesorCurso( UtilProfesorCursoData.dummyProfesorCursoDto() );
		
		assertAll( "Buscar registro por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

    }



    @Test
    @DisplayName( "Test de eliminar profesor por id con éxito" )
    public void deleteProfesorCursoByIdTestSuccess() {

        when( profesorCursoService.deleteProfesorCursoById( any( Long.class ) ) )
            .thenReturn( UtilProfesorData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = profesorCursoController
            .deleteProfesorCursoById( ID_PROFESOR_CURSO );
		
		assertAll( "Buscar registro por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

    }



    @Test
    @DisplayName( "Test de eliminar profesor por idProfesor & idCurso con éxito" )
    public void deleteProfesorCursoByProfesorAndCursoTestSuccess() {

        when( profesorCursoService.deleteProfesorCursoByProfesorAndCurso( ID_PROFESOR, ID_CURSO ) )
            .thenReturn( UtilProfesorData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = profesorCursoController
            .deleteProfesorCursoByProfesorAndCurso( ID_PROFESOR, ID_CURSO );
		
		assertAll( "Buscar registro por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

    }

}
