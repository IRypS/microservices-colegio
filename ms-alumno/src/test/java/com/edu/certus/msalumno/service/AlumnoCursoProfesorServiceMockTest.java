package com.edu.certus.msalumno.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.edu.certus.msalumno.client.CursoClient;
import com.edu.certus.msalumno.client.ProfesorClient;
import com.edu.certus.msalumno.dto.CursoDto;
import com.edu.certus.msalumno.dto.ResponseDto;
import com.edu.certus.msalumno.repository.AlumnoCursoRepository;
import com.edu.certus.msalumno.repository.AlumnoRepository;
import com.edu.certus.msalumno.service.impl.AlumnoCursoProfesorServiceImpl;
import com.edu.certus.msalumno.util.Constantes;
import com.edu.certus.msalumno.util.UtilAlumnoCursoData;
import com.edu.certus.msalumno.util.UtilAlumnoData;
import com.edu.certus.msalumno.util.UtilAlumnoProfesorData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.RetryableException;

@ExtendWith( MockitoExtension.class )
public class AlumnoCursoProfesorServiceMockTest {
    

    @Mock
    private AlumnoCursoRepository alumnoCursoRepository;

    @Mock
    private AlumnoRepository alumnoRepository;

    @Mock
    private CursoClient cursoClient;

    @Mock
    private ProfesorClient profesorClient;

    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    AlumnoCursoProfesorServiceImpl alumnoCursoProfesorService;


    private String codeFailed = Constantes.CODE_FAILED;
    private String codeSuccess = Constantes.CODE_SUCCES;
    private String messageNoRecords = Constantes.NO_RECORDS_FOUND;
    private String messageNoService = Constantes.NO_SERVICE_AVAILABLE;
    private String messageOpFailed = Constantes.OPERATION_FAILED;
    private String messageOpSuccess = Constantes.OPERATION_SUCCESS;


    @Test
    @DisplayName( "Test de listar alumno, profesor y curso con éxito" )
    @SuppressWarnings("unchecked")
    public void getAllAlumnoCursoProfesorTestSuccess() {

        when( alumnoCursoRepository.findAll() )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntityList() );

        when( alumnoRepository.findByIdAndEstadoTrue( any( Long.class ) ) )
            .thenReturn( UtilAlumnoData.dummyAlumnoEntityTrue() );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoClientId() );

        when( mapper.convertValue( any(), eq( CursoDto.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoDto() );

        when( profesorClient.getProfesorCursosByCurso( any( Long.class ) ) )
            .thenReturn( UtilAlumnoProfesorData.dummyProfesorClient() );

        when( mapper.convertValue( any(), any(TypeReference.class) ) )
            .thenReturn( UtilAlumnoProfesorData.dummyProfesorCursoListDto() );


        ResponseDto response = alumnoCursoProfesorService.getAllAlumnoCursoProfesor();


        assertAll( "Listando alumno curso profesor", 
            () -> assertNotNull( "La respuesta es nula", 
                        response 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeSuccess, response.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageOpSuccess, response.getMensaje() 
                    ),
            () -> assertNotNull( "Los datos de la respuesta son nulos", 
                        response.getData() 
                    )
        );

    }

    

    @Test
    @DisplayName( "Test de listar alumno, profesor y curso con error" )
    public void getAllAlumnoCursoProfesorTestError() {


        // No records found
        ResponseDto responseNotFound = alumnoCursoProfesorService.getAllAlumnoCursoProfesor();

        assertAll( "Listando alumno curso profesor vacios", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNotFound 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeSuccess, responseNotFound.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageNoRecords, responseNotFound.getMensaje() 
                    )
        );

        

        // no service profesor
        when( alumnoCursoRepository.findAll() )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntityList() );

        when( alumnoRepository.findByIdAndEstadoTrue( any( Long.class ) ) )
            .thenReturn( UtilAlumnoData.dummyAlumnoEntityTrue() );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoClientId() );

        when( mapper.convertValue( any(), eq( CursoDto.class  ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoDto() );

        when( profesorClient.getProfesorCursosByCurso( any( Long.class ) ) )
            .thenThrow( RetryableException.class );


        ResponseDto responseProfesorNoService = alumnoCursoProfesorService.getAllAlumnoCursoProfesor();


        assertAll( "Listando alumno curso profesor - curso no service", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseProfesorNoService 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeFailed, responseProfesorNoService.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseProfesorNoService.getMensaje().endsWith( messageNoService )
                    )
        );



        // no service curso
        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenThrow( RetryableException.class );

        ResponseDto responseCursoNoService = alumnoCursoProfesorService.getAllAlumnoCursoProfesor();
        
        assertAll( "Listando alumno curso profesor - curso no service", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseCursoNoService 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeFailed, responseCursoNoService.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseCursoNoService.getMensaje().endsWith( messageNoService )
                    )
        );



        // null pointer
        when( alumnoCursoRepository.findAll() )
            .thenThrow( NullPointerException.class );

        ResponseDto responseNullPointer = alumnoCursoProfesorService.getAllAlumnoCursoProfesor();

        assertAll( "Listando alumno curso profesor - curso no service", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNullPointer 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeFailed, responseNullPointer.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageOpFailed, responseNullPointer.getMensaje()
                    )
        );


    }



    @Test
    @DisplayName( "Test de listar alumno, profesor y curso con error de nulos" )
    public void getAllAlumnoCursoProfesorTestNulls() {

        // alumno null
        when( alumnoCursoRepository.findAll() )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntityList() );
        
        when( alumnoRepository.findByIdAndEstadoTrue( any( Long.class ) ) )
            .thenReturn( null );

        ResponseDto responseAlumnoNull = alumnoCursoProfesorService.getAllAlumnoCursoProfesor();

        assertAll( "Listando alumno curso profesor - alumno null", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseAlumnoNull 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeSuccess, responseAlumnoNull.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageNoRecords, responseAlumnoNull.getMensaje()
                    )
        );



        // curso null 
        when( alumnoRepository.findByIdAndEstadoTrue( any( Long.class ) ) )
            .thenReturn( UtilAlumnoData.dummyAlumnoEntityTrue() );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenReturn( null );

        ResponseDto responseCursoNull = alumnoCursoProfesorService.getAllAlumnoCursoProfesor();

        assertAll( "Listando alumno curso profesor - curso null", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseCursoNull 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeSuccess, responseCursoNull.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageNoRecords, responseCursoNull.getMensaje()
                    )
        );



        // profesor null
        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoClientId() );

        when( mapper.convertValue( any(), eq( CursoDto.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoDto() );

        when( profesorClient.getProfesorCursosByCurso( any( Long.class ) ) )
            .thenReturn( null );

        ResponseDto responseProfesorNull = alumnoCursoProfesorService.getAllAlumnoCursoProfesor();

        assertAll( "Listando alumno curso profesor - profesor null", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseProfesorNull 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeSuccess, responseProfesorNull.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageNoRecords, responseProfesorNull.getMensaje()
                    )
        );

    }
    


}
