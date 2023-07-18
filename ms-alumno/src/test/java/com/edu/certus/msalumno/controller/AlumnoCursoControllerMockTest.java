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

import com.edu.certus.msalumno.dto.AlumnoCursoSendDto;
import com.edu.certus.msalumno.dto.ResponseDto;
import com.edu.certus.msalumno.service.impl.AlumnoCursoServiceImpl;
import com.edu.certus.msalumno.util.Constantes;
import com.edu.certus.msalumno.util.UtilAlumnoCursoData;
import com.edu.certus.msalumno.util.UtilAlumnoData;

@ExtendWith( MockitoExtension.class )
public class AlumnoCursoControllerMockTest {
    
    @Mock
	private AlumnoCursoServiceImpl alumnoCursoServiceImpl;
	
	@InjectMocks
	private AlumnoCursoController alumnoCursoController;


	private String codeSuccess = Constantes.CODE_SUCCES;
	private String messageOpSuccess = Constantes.OPERATION_SUCCESS;

    private Long ID_ALUMNO = UtilAlumnoData.ID_TRUE;
    private Long ID_CURSO = UtilAlumnoCursoData.ID_CURSO;
    private Long ID_ALUMNO_CURSO = UtilAlumnoCursoData.ID_ALUMNO_CURSO;


    @Test
	@DisplayName( "Test de listar registros alumno curso con exito" )
	public void getAllAlumnoCursoTestSuccess() {

		when( alumnoCursoServiceImpl.getAllAlumnoCurso() )
            .thenReturn( UtilAlumnoData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = alumnoCursoController.getAllAlumnoCurso();
		
		assertAll( "Listar registros" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

	}



    @Test
    @DisplayName( "Test de listar registros alumno y sus cursos con éxito" )
    public void getAllAlumnoCursosTestSuccess() {

        when( alumnoCursoServiceImpl.getAllAlumnoCursos() )
            .thenReturn( UtilAlumnoData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = alumnoCursoController.getAllAlumnoCursos();
		
		assertAll( "Listar registros alumno cursos" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

    }



    @Test
    @DisplayName( "Test de buscar un registro por id con éxito" )
    public void getAlumnoCursoTestSuccess() {

        when( alumnoCursoServiceImpl.getAlumnoCurso( ID_ALUMNO_CURSO ) )
            .thenReturn( UtilAlumnoData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = alumnoCursoController.getAlumnoCurso( ID_ALUMNO_CURSO  );
		
		assertAll( "Buscar registro por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

    }



    @Test
    @DisplayName( "Test de buscar un registro por idAlumno y idCurso con éxito" )
    public void getAlumnoCursoByAlumnoAndCursoTestSuccess() {

        when( alumnoCursoServiceImpl.getAlumnoCursoByAlumnoAndCurso( ID_ALUMNO, ID_CURSO ) )
            .thenReturn( UtilAlumnoData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = alumnoCursoController
            .getAlumnoCursoByAlumnoAndCurso( ID_ALUMNO, ID_CURSO );
		
		assertAll( "Buscar registro por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

    }



    @Test
    @DisplayName( "Test de registros por idAlumno con éxito" )
    public void getAlumnoCursosByAlumnoTestSuccess() {

        when( alumnoCursoServiceImpl.getAlumnoCursosByAlumno( ID_ALUMNO ) )
            .thenReturn( UtilAlumnoData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = alumnoCursoController
            .getAlumnoCursosByAlumno( ID_ALUMNO );
		
		assertAll( "Buscar registro por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

    }



    @Test
    @DisplayName( "Test de crear alumno con éxito" )
    public void createAlumnoCursoTestSuccess() {

        when( alumnoCursoServiceImpl.createAlumnoCurso( any( AlumnoCursoSendDto.class ) ) )
            .thenReturn( UtilAlumnoData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = alumnoCursoController
            .createAlumnoCurso( UtilAlumnoCursoData.dummyAlumnoCursoDto() );
		
		assertAll( "Buscar registro por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

    }



    @Test
    @DisplayName( "Test de actualizar alumno con éxito" )
    public void updateAlumnoCursoTestSuccess() {

        when( alumnoCursoServiceImpl.updateAlumnoCurso( any( AlumnoCursoSendDto.class ) ) )
            .thenReturn( UtilAlumnoData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = alumnoCursoController
            .updateAlumnoCurso( UtilAlumnoCursoData.dummyAlumnoCursoDto() );
		
		assertAll( "Buscar registro por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

    }



    @Test
    @DisplayName( "Test de eliminar alumno por id con éxito" )
    public void deleteAlumnoCursoByIdTestSuccess() {

        when( alumnoCursoServiceImpl.deleteAlumnoCursoById( any( Long.class ) ) )
            .thenReturn( UtilAlumnoData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = alumnoCursoController
            .deleteAlumnoCursoById( ID_ALUMNO_CURSO );
		
		assertAll( "Buscar registro por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

    }



    @Test
    @DisplayName( "Test de eliminar alumno por id con éxito" )
    public void deleteAlumnoCursoByAlumnoAndCursoTestSuccess() {

        when( alumnoCursoServiceImpl.deleteAlumnoCursoByAlumnoAndCurso( ID_ALUMNO, ID_CURSO ) )
            .thenReturn( UtilAlumnoData.dummyResponseDto( codeSuccess, messageOpSuccess ) );

		ResponseEntity<ResponseDto> response = alumnoCursoController
            .deleteAlumnoCursoByAlumnoAndCurso( ID_ALUMNO, ID_CURSO );
		
		assertAll( "Buscar registro por id" , 
			() -> assertEquals( "El código Http no es 200", HttpStatus.OK, response.getStatusCode() ),
			() -> assertNotNull( response.getBody(), "El cuerpo de la respuesta es nulo" )
		);

    }


}
