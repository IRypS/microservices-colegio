package com.edu.certus.msalumno.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.edu.certus.msalumno.client.CursoClient;
import com.edu.certus.msalumno.dto.CursoDto;
import com.edu.certus.msalumno.dto.ResponseDto;
import com.edu.certus.msalumno.repository.AlumnoCursoRepository;
import com.edu.certus.msalumno.repository.AlumnoRepository;
import com.edu.certus.msalumno.service.impl.AlumnoCursoServiceImpl;
import com.edu.certus.msalumno.util.Constantes;
import com.edu.certus.msalumno.util.UtilAlumnoCursoData;
import com.edu.certus.msalumno.util.UtilAlumnoData;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.RetryableException;

@ExtendWith( MockitoExtension.class )
public class AlumnoCursoServiceMockTest {


    @Mock
    private AlumnoCursoRepository alumnoCursoRepository;

    @Mock
    private AlumnoRepository alumnoRepository;

    @Mock
    private CursoClient cursoClient;

    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private AlumnoCursoServiceImpl alumnoCursoService;


    private String codeFailed = Constantes.CODE_FAILED;
    private String codeSuccess = Constantes.CODE_SUCCES;
    private String messageAlreadyExists = Constantes.ALREADY_EXISTS;
    private String messageAttrMissing = Constantes.ATTRIBUTE_MISSING;
    private String messageCreated = Constantes.RECORD_CREATED;
    private String messageDeleted = Constantes.RECORD_DELETED;
    private String messageNoRecord = Constantes.NO_RECORD_FOUND;
    private String messageNoRecords = Constantes.NO_RECORDS_FOUND;
    private String messageNoService = Constantes.NO_SERVICE_AVAILABLE;
    private String messageOpFailed = Constantes.OPERATION_FAILED;
    private String messageOpSuccess = Constantes.OPERATION_SUCCESS;
    private String messageUpdated = Constantes.RECORD_UPDATED;


    @Test
    @DisplayName( "Test de Listar alumno curso con éxito" )
    public void getAllAlumnoCursoTestSuccess() {

        when( alumnoCursoRepository.findAll() )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntityList() );

        when( alumnoRepository.findById( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilAlumnoData.dummyAlumnoEntityTrue() ) );

        when( cursoClient.getCursoTrueById( UtilAlumnoCursoData.ID_CURSO ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoClientId()  );

        when( mapper.convertValue( any(), eq( CursoDto.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoDto() );

        ResponseDto response = alumnoCursoService.getAllAlumnoCurso();

        assertAll( "Listado alumno curso", 
            () -> assertNotNull( "La respuesta es nula", 
                        response 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, response.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageOpSuccess, response.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de listar alumno curso con error" )
    public void getAllAlumnoCursoTestError() {


        // No service
        when( alumnoCursoRepository.findAll() )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntityList() );

        when( alumnoRepository.findById( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilAlumnoData.dummyAlumnoEntityTrue() ) );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenThrow( RetryableException.class );

        ResponseDto responseNoServiceAviable = alumnoCursoService.getAllAlumnoCurso();

        assertAll( "Listado alumno curso sin objetos", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNoServiceAviable 
                    ),
            () -> assertEquals( "El código de la respuesta no es 1", 
                        codeFailed, responseNoServiceAviable.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseNoServiceAviable.getMensaje().endsWith(messageNoService) 
                    )
        );



        // No record found
        when( alumnoCursoRepository.findAll() ).thenReturn( null );

        ResponseDto responseNoRecord = alumnoCursoService.getAllAlumnoCurso();

        assertAll( "Listado alumno curso sin objetos", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNoRecord 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseNoRecord.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageNoRecords, responseNoRecord.getMensaje() 
                    )
        );

        
        // Response null pointer
        when( alumnoCursoRepository.findAll() )
            .thenThrow( NullPointerException.class );

        ResponseDto responseNullPointer = alumnoCursoService.getAllAlumnoCurso();

        assertAll( "Listado alumno curso null pointer", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNullPointer
                    ),
            () -> assertEquals( "El código de la respuesta no es 1", 
                        codeFailed, responseNullPointer.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageOpFailed, responseNullPointer.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de listar alumno curso con error de nulls" )
    public void getAllAlumnoCursoTestErrorNulls() {

        // alumno not found
        when( alumnoCursoRepository.findAll() )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntityList() );

        ResponseDto responseAlumnoNotFound = alumnoCursoService.getAllAlumnoCurso();

        assertAll( "Listado alumno curso sin alumno", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseAlumnoNotFound 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseAlumnoNotFound.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageNoRecords, responseAlumnoNotFound.getMensaje() 
                    )
        );



        // curso not found
        when( alumnoRepository.findById( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilAlumnoData.dummyAlumnoEntityTrue() ) );
 
        ResponseDto responseCursoNotFound = alumnoCursoService.getAllAlumnoCurso();

        assertAll( "Listado alumno curso sin curso", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseCursoNotFound 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseCursoNotFound.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageNoRecords, responseCursoNotFound.getMensaje() 
                    )
        );
    }



    @Test
    @DisplayName( "Test de listar alumno cursos con éxito" )
    public void getAllAlumnoCursosTestSuccess() {

        when( alumnoCursoRepository.findAll() )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntityList() );

        when( alumnoRepository.findById( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilAlumnoData.dummyAlumnoEntityTrue() ) );

        when( cursoClient.getCursoTrueById( UtilAlumnoCursoData.ID_CURSO ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoClientId()  );

        when( mapper.convertValue( any(), eq( CursoDto.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoDto() );

        ResponseDto response = alumnoCursoService.getAllAlumnoCursos();

        assertAll( "Listado alumno curso", 
            () -> assertNotNull( "La respuesta es nula", 
                        response 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, response.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageOpSuccess, response.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de listar alumno cursos con error" )
    public void getAllAlumnoCursosTestError() {

        // No service 
        when( alumnoCursoRepository.findAll() )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntityList() );

        when( alumnoRepository.findById( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilAlumnoData.dummyAlumnoEntityTrue() ) );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenThrow( RetryableException.class );

        ResponseDto responseNoServiceAviable = alumnoCursoService.getAllAlumnoCursos();

        assertAll( "Listado alumno curso no service", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNoServiceAviable 
                    ),
            () -> assertEquals( "El código de la respuesta no es 1", 
                        codeFailed, responseNoServiceAviable.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseNoServiceAviable.getMensaje().endsWith(messageNoService) 
                    )
        );



        // No record 
        when( alumnoCursoRepository.findAll() )
            .thenReturn( null );

        ResponseDto responseNoRecord = alumnoCursoService.getAllAlumnoCursos();

        assertAll( "Listado alumno curso sin objetos", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNoRecord 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseNoRecord.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageNoRecords, responseNoRecord.getMensaje() 
                    )
        );



        // Null pointer 
        when( alumnoCursoRepository.findAll() )
            .thenThrow( NullPointerException.class );

        ResponseDto responseEmpty = alumnoCursoService.getAllAlumnoCursos();

        assertAll( "Listado alumno curso null pointer", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseEmpty 
                    ),
            () -> assertEquals( "El código de la respuesta no es 1", 
                        codeFailed, responseEmpty.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageOpFailed, responseEmpty.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de listar alumno cursos con error de nulls" )
    public void getAllAlumnoCursosTestErrorNulls() {

        // AlumnoNotFound
        when( alumnoCursoRepository.findAll() )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntityList() );


        ResponseDto responseAlumnoNotFound = alumnoCursoService.getAllAlumnoCursos();

        assertAll( "Listado alumno curso null pointer", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseAlumnoNotFound 
                    ),
            () -> assertEquals( "El código de la respuesta no es 1", 
                        codeSuccess, responseAlumnoNotFound.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageNoRecords, responseAlumnoNotFound.getMensaje() 
                    )
        );



        // Curso not found
        when( alumnoRepository.findById( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilAlumnoData.dummyAlumnoEntityTrue() ) );

        when( cursoClient.getCursoTrueById( UtilAlumnoCursoData.ID_CURSO ) )
            .thenReturn( null  );

        ResponseDto responseCursoNotFound = alumnoCursoService.getAllAlumnoCursos();

        assertAll( "Listado alumno curso null pointer", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseCursoNotFound 
                    ),
            () -> assertEquals( "El código de la respuesta no es 1", 
                        codeSuccess, responseCursoNotFound.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageNoRecords, responseCursoNotFound.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de buscar registro por id con exito" )
    public void getAlumnoCursoTestSuccess() {

        when( alumnoCursoRepository.findById( UtilAlumnoCursoData.ID_ALUMNO_CURSO ) )
            .thenReturn( Optional.of( UtilAlumnoCursoData.dummyAlumnoCursoEntity() ) );

        when( alumnoRepository.findById( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilAlumnoData.dummyAlumnoEntityTrue() ) );

        when( cursoClient.getCursoTrueById( UtilAlumnoCursoData.ID_CURSO ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoClientId()  );

        when( mapper.convertValue( any(), eq( CursoDto.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoDto() );

        ResponseDto response = alumnoCursoService.getAlumnoCurso( UtilAlumnoCursoData.ID_ALUMNO_CURSO );

        assertAll( "Buscar registro alumno curso", 
            () -> assertNotNull( "La respuesta es nula", 
                        response 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, response.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageOpSuccess, response.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de buscar registro por id con error" )
    public void getAlumnoCursoTestError() {


        // No record 
        ResponseDto responseNotFound = alumnoCursoService.getAlumnoCurso( UtilAlumnoCursoData.ID_ALUMNO_CURSO );

        assertAll( "Listado alumno curso sin objetos", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNotFound 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseNotFound.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageNoRecord, responseNotFound.getMensaje()
                    )
        );



        // No Alumno found
        when( alumnoCursoRepository.findById( UtilAlumnoCursoData.ID_ALUMNO_CURSO ) )
            .thenReturn( Optional.of( UtilAlumnoCursoData.dummyAlumnoCursoEntity() ) );

        ResponseDto responseNoAlumnoFound = alumnoCursoService.getAlumnoCurso( UtilAlumnoCursoData.ID_ALUMNO_CURSO );

        assertAll( "Listado alumno curso sin alumno", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNoAlumnoFound 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseNoAlumnoFound.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseNoAlumnoFound.getMensaje().startsWith( messageNoRecord ) 
                    )
        );



        // No curso found 
        when( alumnoRepository.findById( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilAlumnoData.dummyAlumnoEntityTrue() ) );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoClientNull() );

        ResponseDto responseNoCursoFound = alumnoCursoService.getAlumnoCurso( UtilAlumnoCursoData.ID_ALUMNO_CURSO );

        assertAll( "Listado alumno curso sin curso", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNoCursoFound 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseNoCursoFound.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseNoCursoFound.getMensaje().startsWith( messageNoRecord ) 
                    )
        );



        // No service 
        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenThrow( RetryableException.class );

        ResponseDto responseNoServiceAviable = alumnoCursoService.getAlumnoCurso( UtilAlumnoCursoData.ID_ALUMNO_CURSO );

        assertAll( "Buscar registro alumno curso no service", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNoServiceAviable 
                    ),
            () -> assertEquals( "El código de la respuesta no es 1", 
                        codeFailed, responseNoServiceAviable.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseNoServiceAviable.getMensaje().endsWith(messageNoService) 
                    )
        );



        // Null pointer
        when( alumnoCursoRepository.findById( UtilAlumnoCursoData.ID_ALUMNO_CURSO ) )
            .thenThrow( NullPointerException.class );

        ResponseDto responseNullPointer = alumnoCursoService.getAlumnoCurso( UtilAlumnoCursoData.ID_ALUMNO_CURSO );

        assertAll( "Buscar registro alumno curso null pointer", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNullPointer 
                    ),
            () -> assertEquals( "El código de la respuesta no es 1", 
                        codeFailed, responseNullPointer.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageOpFailed, responseNullPointer.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de buscar registro por idAlumno y IdCurso con exito" )
    public void getAlumnoCursoByAlumnoAndCursoTestSuccess() {

        when( alumnoCursoRepository.findByIdAlumnoAndIdCurso( UtilAlumnoData.ID_TRUE, UtilAlumnoCursoData.ID_CURSO ) )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntity() );

        when( alumnoRepository.findById( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilAlumnoData.dummyAlumnoEntityTrue() ) );

        when( cursoClient.getCursoTrueById( UtilAlumnoCursoData.ID_CURSO ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoClientId()  );

        when( mapper.convertValue( any(), eq( CursoDto.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoDto() );

        ResponseDto response = alumnoCursoService
            .getAlumnoCursoByAlumnoAndCurso( UtilAlumnoData.ID_TRUE, UtilAlumnoCursoData.ID_CURSO );

        assertAll( "Buscar registro alumno curso", 
            () -> assertNotNull( "La respuesta es nula", 
                        response 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, response.getCodigo()
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageOpSuccess, response.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de buscar registro por idAlumno y IdCurso con error" )
    public void getAlumnoCursoByAlumnoAndCursoTestError() {


        // No found
        ResponseDto responseNotFound = alumnoCursoService
            .getAlumnoCursoByAlumnoAndCurso( UtilAlumnoData.ID_TRUE, UtilAlumnoCursoData.ID_CURSO );

        assertAll( "Listado alumno curso sin objetos", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNotFound 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseNotFound.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageNoRecord, responseNotFound.getMensaje() 
                    )
        );



        // No alumno found
        when( alumnoCursoRepository.findByIdAlumnoAndIdCurso( UtilAlumnoData.ID_TRUE, UtilAlumnoCursoData.ID_CURSO ) )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntity() );

        ResponseDto responseNoAlumnoFound = alumnoCursoService
            .getAlumnoCursoByAlumnoAndCurso( UtilAlumnoData.ID_TRUE, UtilAlumnoCursoData.ID_CURSO );

        assertAll( "Listado alumno curso sin alumno", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNoAlumnoFound 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseNoAlumnoFound.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseNoAlumnoFound.getMensaje().startsWith( messageNoRecord ) 
                    )
        );



        // No curso found
        when( alumnoRepository.findById( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilAlumnoData.dummyAlumnoEntityTrue() ) );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoClientNull() );

        ResponseDto responseNoCursoFound = alumnoCursoService
            .getAlumnoCursoByAlumnoAndCurso( UtilAlumnoData.ID_TRUE, UtilAlumnoCursoData.ID_CURSO );

        assertAll( "Listado alumno curso sin curso", 
            () -> assertNotNull( "La respuesta es nula", responseNoCursoFound ),
            () -> assertEquals( "El código de la respuesta no es 0", codeSuccess, responseNoCursoFound.getCodigo() ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", responseNoCursoFound.getMensaje().startsWith( messageNoRecord ) )
        );
        
        

        // No service 
        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenThrow( RetryableException.class );

        ResponseDto responseNoServiceAviable = alumnoCursoService
            .getAlumnoCursoByAlumnoAndCurso( UtilAlumnoData.ID_TRUE, UtilAlumnoCursoData.ID_CURSO );

        assertAll( "Buscar registro alumno curso no service", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNoServiceAviable 
                    ),
            () -> assertEquals( "El código de la respuesta no es 1", 
                        codeFailed, responseNoServiceAviable.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseNoServiceAviable.getMensaje().endsWith(messageNoService) 
                    )
        );



        // Null pointer 
        when( alumnoCursoRepository.findByIdAlumnoAndIdCurso( UtilAlumnoData.ID_TRUE, UtilAlumnoCursoData.ID_CURSO ) )
            .thenThrow( NullPointerException.class );

        ResponseDto responseNullPointer = alumnoCursoService
            .getAlumnoCursoByAlumnoAndCurso( UtilAlumnoData.ID_TRUE, UtilAlumnoCursoData.ID_CURSO );

        assertAll( "Buscar registro alumno null pointer", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNullPointer 
                    ),
            () -> assertEquals( "El código de la respuesta no es 1", 
                        codeFailed, responseNullPointer.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageOpFailed, responseNullPointer.getMensaje() 
                    )
        );

    }
    


    @Test
    @DisplayName( "Test de buscar registro por idAlumno con exito" )
    public void getAlumnoCursosByAlumnoTestSuccess() {

        when( alumnoCursoRepository.findAllByIdAlumno( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntityList() );

        when( alumnoRepository.findById( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilAlumnoData.dummyAlumnoEntityTrue() ) );

        when( cursoClient.getCursoTrueById( UtilAlumnoCursoData.ID_CURSO ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoClientId()  );

        when( mapper.convertValue( any(), eq(CursoDto.class) ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoDto() );

        ResponseDto response = alumnoCursoService.getAlumnoCursosByAlumno( UtilAlumnoData.ID_TRUE );

        assertAll( "Buscar registro alumno curso", 
            () -> assertNotNull( "La respuesta es nula", 
                        response 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, response.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageOpSuccess, response.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de buscar registro por idAlumno con error" )
    public void getAlumnoCursosByAlumnoTestError() {


        // Not found 
        when( alumnoCursoRepository.findAllByIdAlumno( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( null );

        ResponseDto responseNotFound = alumnoCursoService.getAlumnoCursosByAlumno( UtilAlumnoData.ID_TRUE );

        assertAll( "Listado alumno curso nulo", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNotFound 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0",
                        codeSuccess, responseNotFound.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide",
                        messageNoRecords, responseNotFound.getMensaje() 
                    )
        );



        // Not alumno found
        when( alumnoCursoRepository.findAllByIdAlumno( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntityList() );

        ResponseDto responseNoAlumnoFound = alumnoCursoService.getAlumnoCursosByAlumno( UtilAlumnoData.ID_TRUE );

        assertAll( "Listado alumno curso sin alumno", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNoAlumnoFound
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseNoAlumnoFound.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseNoAlumnoFound.getMensaje().startsWith( messageNoRecord ) 
                    )
        );



        // Not any curso found
        when( alumnoCursoRepository.findAllByIdAlumno( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntityList() );

        when( alumnoRepository.findById( any( Long.class ) ) )
            .thenReturn( Optional.of( UtilAlumnoData.dummyAlumnoEntityTrue() ) );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenReturn( null );

        ResponseDto responseEmptyList = alumnoCursoService.getAlumnoCursosByAlumno( UtilAlumnoData.ID_TRUE );

        assertAll( "Listado alumno curso vacio", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseEmptyList 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseEmptyList.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseEmptyList.getMensaje().startsWith( messageNoRecords ) 
                    )
        );
        


        // No service 
        when( alumnoCursoRepository.findAllByIdAlumno( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntityList() );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenThrow( RetryableException.class );

        ResponseDto responseNoServiceAviable = alumnoCursoService.getAlumnoCursosByAlumno( UtilAlumnoData.ID_TRUE );

        assertAll( "Buscar registro alumno curso no service", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNoServiceAviable 
                    ),
            () -> assertEquals( "El código de la respuesta no es 1", 
                        codeFailed, responseNoServiceAviable.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseNoServiceAviable.getMensaje().endsWith(messageNoService) 
                    )
        );



        // Null pointer 
        when( alumnoCursoRepository.findAllByIdAlumno( UtilAlumnoData.ID_TRUE ) )
            .thenThrow( NullPointerException.class );

        ResponseDto responseNullPointer = alumnoCursoService.getAlumnoCursosByAlumno( UtilAlumnoData.ID_TRUE );

        assertAll( "Buscar registro alumno null pointer", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNullPointer 
                    ),
            () -> assertEquals( "El código de la respuesta no es 1", 
                        codeFailed, responseNullPointer.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageOpFailed, responseNullPointer.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de crear alumno con exito" )
    public void createAlumnoCursoTestSuccess() {

        when( alumnoRepository.findById( any( Long.class ) ) )
            .thenReturn( Optional.of( UtilAlumnoData.dummyAlumnoEntityTrue() ) );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoClientId() );

        ResponseDto response = alumnoCursoService
            .createAlumnoCurso( UtilAlumnoCursoData.dummyAlumnoCursoDto() );

        assertAll( "Crear nuevo registro alumno curso",
            () -> assertNotNull( "La respuesta es nula", 
                        response 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeSuccess, response.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageCreated, response.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de crear alumno con error" )
    public void createAlumnoCursoTestError() {


        // Null attributtes
        ResponseDto responseAttrNull = alumnoCursoService
            .createAlumnoCurso( UtilAlumnoCursoData.dummyAlumnoCursoDtoNullAttr() );

        assertAll( "Crear nuevo registro con atributos nulos",
            () -> assertNotNull( "La respuesta es nula", 
                        responseAttrNull 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeFailed, responseAttrNull.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseAttrNull.getMensaje().startsWith( messageAttrMissing ) 
                    )
        );


        
        // Already exists
        when( alumnoCursoRepository.findByIdAlumnoAndIdCurso( any( Long.class ), any( Long.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntity() );

        ResponseDto responseDuplicated = alumnoCursoService
            .createAlumnoCurso( UtilAlumnoCursoData.dummyAlumnoCursoDtoDuplicated() );

        assertAll( "Crear nuevo registro repetido",
            () -> assertNotNull( "La respuesta es nula", 
                        responseDuplicated 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeFailed, responseDuplicated.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageAlreadyExists, responseDuplicated.getMensaje() 
                    )
        );



        // Not alumno found 
        when( alumnoCursoRepository.findByIdAlumnoAndIdCurso( any( Long.class ), any( Long.class ) ) )
            .thenReturn( null );

        ResponseDto responseAlumnoNotFound= alumnoCursoService
            .createAlumnoCurso( UtilAlumnoCursoData.dummyAlumnoCursoDto() );

        assertAll( "Crear nuevo registro sin alumno encontrado",
            () -> assertNotNull( "La respuesta es nula", 
                        responseAlumnoNotFound 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeFailed, responseAlumnoNotFound.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseAlumnoNotFound.getMensaje().startsWith( messageNoRecord ) 
                    )
        );



        // Not curso found
        when( alumnoRepository.findById( any( Long.class ) ) )
            .thenReturn( Optional.of( UtilAlumnoData.dummyAlumnoEntityTrue() ) );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoClientNull() );

        ResponseDto responseCursoNotFound = alumnoCursoService
            .createAlumnoCurso( UtilAlumnoCursoData.dummyAlumnoCursoDto() );

        assertAll( "Crear nuevo registro sin curso encontrado",
            () -> assertNotNull( "La respuesta es nula", 
                        responseCursoNotFound 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeFailed, responseCursoNotFound.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseCursoNotFound.getMensaje().startsWith( messageNoRecord ) 
                    )
        );



        // No service 
        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenThrow( RetryableException.class );

        ResponseDto responseCursoNoService = alumnoCursoService
            .createAlumnoCurso( UtilAlumnoCursoData.dummyAlumnoCursoDto() );

        assertAll( "Crear nuevo registro sin servicio",
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



        // Null pointer 
        ResponseDto responseNullPointer = alumnoCursoService.createAlumnoCurso( null );

        assertAll( "Crear nuevo registro null pointer",
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
    @DisplayName( "Test de actualizar alumno curso con éxito" )
    public void updateAlumnoCursoTestSuccess() {

        when( alumnoCursoRepository.findById( any( Long.class ) ) )
            .thenReturn( Optional.of( UtilAlumnoCursoData.dummyAlumnoCursoEntity() ) );

        when( alumnoRepository.findById( any( Long.class ) ) )
            .thenReturn( Optional.of( UtilAlumnoData.dummyAlumnoEntityTrue() ) );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoClientId() );

        ResponseDto response = alumnoCursoService.updateAlumnoCurso( UtilAlumnoCursoData.dummyAlumnoCursoDto() );

        assertAll( "Crear nuevo registro alumno curso",
            () -> assertNotNull( "La respuesta es nula", 
                        response 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeSuccess, response.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageUpdated, response.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de actualizar alumno curso con error" )
    public void updateAlumnoCursoTestsError() {

        
        // Null attributtes 
        ResponseDto responseAttrNull = alumnoCursoService
            .updateAlumnoCurso( UtilAlumnoCursoData.dummyAlumnoCursoDtoNullAttr() );

        assertAll( "Actualziar nuevo registro con atributos nulos",
            () -> assertNotNull( "La respuesta es nula", 
                        responseAttrNull 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeFailed, responseAttrNull.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseAttrNull.getMensaje().startsWith( messageAttrMissing ) 
                    )
        );


        
        // Not found
        ResponseDto responseNoRecord = alumnoCursoService
            .updateAlumnoCurso( UtilAlumnoCursoData.dummyAlumnoCursoDtoDuplicated() );

        assertAll( "Actualziar nuevo registro repetido",
            () -> assertNotNull( "La respuesta es nula", 
                        responseNoRecord 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeFailed, responseNoRecord.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageNoRecord, responseNoRecord.getMensaje() 
                    )
        );



        // Already exists
        when( alumnoCursoRepository.findById( any( Long.class ) ) )
            .thenReturn( Optional.of( UtilAlumnoCursoData.dummyAlumnoCursoEntity() ) );

        when( alumnoCursoRepository.findByIdAlumnoAndIdCurso( any( Long.class ), any( Long.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntity() );

        ResponseDto responseAlumnoDuplicated = alumnoCursoService
            .updateAlumnoCurso( UtilAlumnoCursoData.dummyAlumnoCursoDto() );

        assertAll( "Actualizar nuevo registro sin alumno encontrado",
            () -> assertNotNull( "La respuesta es nula", 
                        responseAlumnoDuplicated 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeSuccess, responseAlumnoDuplicated.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageAlreadyExists, responseAlumnoDuplicated.getMensaje() 
                    )
        );



        // No alumno found
        when( alumnoCursoRepository.findByIdAlumnoAndIdCurso( any( Long.class ), any( Long.class ) ) )
            .thenReturn( null );

        ResponseDto responseAlumnoNotFound = alumnoCursoService
            .updateAlumnoCurso( UtilAlumnoCursoData.dummyAlumnoCursoDto() );

        assertAll( "Actualizar nuevo registro sin alumno encontrado",
            () -> assertNotNull( "La respuesta es nula", 
                        responseAlumnoNotFound 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeFailed, responseAlumnoNotFound.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseAlumnoNotFound.getMensaje().startsWith( messageNoRecord ) 
                    )
        );



        // No curso found
        when( alumnoRepository.findById( any( Long.class ) ) )
            .thenReturn( Optional.of( UtilAlumnoData.dummyAlumnoEntityTrue() ) );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyCursoClientNull() );

        ResponseDto responseCursoNotFound = alumnoCursoService
            .updateAlumnoCurso( UtilAlumnoCursoData.dummyAlumnoCursoDto() );

        assertAll( "Actualizar nuevo registro sin curso encontrado",
            () -> assertNotNull( "La respuesta es nula", 
                        responseCursoNotFound 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeFailed, responseCursoNotFound.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseCursoNotFound.getMensaje().startsWith( messageNoRecord ) 
                    )
        );



        // No service
        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenThrow( RetryableException.class );

        ResponseDto responseCursoNoService = alumnoCursoService
            .updateAlumnoCurso( UtilAlumnoCursoData.dummyAlumnoCursoDto() );

        assertAll( "Actualizar nuevo registro sin servicio",
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



        // Null pointer 
        ResponseDto responseNullPointer = alumnoCursoService.updateAlumnoCurso( null );

        assertAll( "Actualizar nuevo registro null pointer",
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
    @DisplayName( "Test de eliminar alumno por id con exito" )
    public void deleteAlumnoCursoByIdTestSuccess() {

        when( alumnoCursoRepository.existsById( any( Long.class ) ) ).thenReturn( true );

        ResponseDto response = alumnoCursoService.deleteAlumnoCursoById( UtilAlumnoCursoData.ID_ALUMNO_CURSO );

        assertAll( "Eliminar registro por id",
            () -> assertNotNull( "La respuesta es nula", 
                        response 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeSuccess, response.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide",
                        messageDeleted, response.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de eliminar alumno por id con error" )
    public void deleteAlumnoCursoByIdTestError() {

        // Not found
        when( alumnoCursoRepository.existsById( any( Long.class ) ) ).thenReturn( false );

        ResponseDto response = alumnoCursoService.deleteAlumnoCursoById( UtilAlumnoCursoData.ID_ALUMNO_CURSO );

        assertAll( "Eliminar registro inexistente" ,
            () -> assertNotNull( "La respuesta es nula", 
                        response 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeFailed, response.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageNoRecord, response.getMensaje() 
                    )
        );



        // Null pointer
        when( alumnoCursoRepository.existsById( any( Long.class ) ) ).thenReturn( true );

        ResponseDto responseNullPointer = alumnoCursoService.deleteAlumnoCursoById( null );

        assertAll( "Eliminar registro inexistente" ,
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
    @DisplayName( "Test de eliminar alumno por alumno y curso con exito" )
    public void deleteAlumnoCursoByAlumnoAndCursoTestSuccess() {

        when( alumnoCursoRepository.findByIdAlumnoAndIdCurso( any( Long.class ), any( Long.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntity() );

        ResponseDto response = alumnoCursoService
            .deleteAlumnoCursoByAlumnoAndCurso( UtilAlumnoData.ID_TRUE, UtilAlumnoCursoData.ID_CURSO );

        assertAll( "Eliminar registro por idAlumno y idCurso",
            () -> assertNotNull( "La respuesta es nula", 
                        response 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeSuccess, response.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageDeleted, response.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de eliminar alumno por alumno y curso con error" )
    public void deleteAlumnoCursoByAlumnoAndCursoTestError() {

        // Not found 
        ResponseDto responseNotFound = alumnoCursoService
            .deleteAlumnoCursoByAlumnoAndCurso( UtilAlumnoData.ID_TRUE, UtilAlumnoCursoData.ID_CURSO );

        assertAll( "Eliminar registro inexistente" ,
            () -> assertNotNull( "La respuesta es nula", 
                        responseNotFound
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeFailed, responseNotFound.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageNoRecord, responseNotFound.getMensaje() 
                    )
        );



        // Null pointer
        when( alumnoCursoRepository.findByIdAlumnoAndIdCurso( any( Long.class ), any( Long.class ) ) )
            .thenReturn( UtilAlumnoCursoData.dummyAlumnoCursoEntity() );

        ResponseDto responseNullPointer = alumnoCursoService
            .deleteAlumnoCursoByAlumnoAndCurso( null, null );

        assertAll( "Eliminar registro inexistente" ,
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

}
