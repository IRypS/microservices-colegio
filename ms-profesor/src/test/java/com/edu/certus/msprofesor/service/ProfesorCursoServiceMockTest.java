package com.edu.certus.msprofesor.service;

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

import com.edu.certus.msprofesor.client.CursoClient;
import com.edu.certus.msprofesor.dto.CursoDto;
import com.edu.certus.msprofesor.dto.ResponseDto;
import com.edu.certus.msprofesor.repository.ProfesorCursoRepository;
import com.edu.certus.msprofesor.repository.ProfesorRepository;
import com.edu.certus.msprofesor.service.impl.ProfesorCursoServiceImpl;
import com.edu.certus.msprofesor.util.Constantes;
import com.edu.certus.msprofesor.util.UtilProfesorCursoData;
import com.edu.certus.msprofesor.util.UtilProfesorData;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.RetryableException;

@ExtendWith( MockitoExtension.class )
public class ProfesorCursoServiceMockTest {

    @Mock
    private ProfesorCursoRepository profesorCursoRepository;

    @Mock
    private ProfesorRepository profesorRepository;

    @Mock
    private CursoClient cursoClient;

    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private ProfesorCursoServiceImpl profesorCursoService;


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
    @DisplayName( "Test de Listar profesor curso con éxito" )
    public void getAllProfesorCursoTestSuccess() {

        when( profesorCursoRepository.findAll() )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntityList() );

        when( profesorRepository.findById( UtilProfesorData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilProfesorData.dummyProfesorEntityTrue() ) );

        when( cursoClient.getCursoTrueById( UtilProfesorCursoData.ID_CURSO ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoClientId()  );

        when( mapper.convertValue( any(), eq( CursoDto.class ) ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoDto() );

        ResponseDto response = profesorCursoService.getAllProfesorCurso();

        assertAll( "Listado profesor curso", 
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
    @DisplayName( "Test de listar profesor curso con error" )
    public void getAllProfesorCursoTestError() {


        // No service
        when( profesorCursoRepository.findAll() )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntityList() );

        when( profesorRepository.findById( UtilProfesorData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilProfesorData.dummyProfesorEntityTrue() ) );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenThrow( RetryableException.class );

        ResponseDto responseNoServiceAviable = profesorCursoService.getAllProfesorCurso();

        assertAll( "Listado profesor curso sin objetos", 
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
        when( profesorCursoRepository.findAll() ).thenReturn( null );

        ResponseDto responseNoRecord = profesorCursoService.getAllProfesorCurso();

        assertAll( "Listado profesor curso sin objetos", 
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
        when( profesorCursoRepository.findAll() )
            .thenThrow( NullPointerException.class );

        ResponseDto responseNullPointer = profesorCursoService.getAllProfesorCurso();

        assertAll( "Listado profesor curso null pointer", 
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
    @DisplayName( "Test de listar profesor curso con error de nulls" )
    public void getAllProfesorCursoTestErrorNulls() {

        // profesor not found
        when( profesorCursoRepository.findAll() )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntityList() );

        ResponseDto responseProfesorNotFound = profesorCursoService.getAllProfesorCurso();

        assertAll( "Listado profesor curso sin profesor", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseProfesorNotFound 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseProfesorNotFound.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageNoRecords, responseProfesorNotFound.getMensaje() 
                    )
        );



        // curso not found
        when( profesorRepository.findById( UtilProfesorData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilProfesorData.dummyProfesorEntityTrue() ) );
 
        ResponseDto responseCursoNotFound = profesorCursoService.getAllProfesorCurso();

        assertAll( "Listado profesor curso sin curso", 
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
    @DisplayName( "Test de listar profesor cursos con éxito" )
    public void getAllProfesorCursosTestSuccess() {

        when( profesorCursoRepository.findAll() )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntityList() );

        when( profesorRepository.findById( UtilProfesorData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilProfesorData.dummyProfesorEntityTrue() ) );

        when( cursoClient.getCursoTrueById( UtilProfesorCursoData.ID_CURSO ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoClientId()  );

        when( mapper.convertValue( any(), eq( CursoDto.class ) ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoDto() );

        ResponseDto response = profesorCursoService.getAllProfesorCursos();

        assertAll( "Listado profesor curso", 
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
    @DisplayName( "Test de listar profesor cursos con error" )
    public void getAllProfesorCursosTestError() {

        // No service 
        when( profesorCursoRepository.findAll() )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntityList() );

        when( profesorRepository.findById( UtilProfesorData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilProfesorData.dummyProfesorEntityTrue() ) );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenThrow( RetryableException.class );

        ResponseDto responseNoServiceAviable = profesorCursoService.getAllProfesorCursos();

        assertAll( "Listado profesor curso no service", 
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
        when( profesorCursoRepository.findAll() )
            .thenReturn( null );

        ResponseDto responseNoRecord = profesorCursoService.getAllProfesorCursos();

        assertAll( "Listado profesor curso sin objetos", 
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
        when( profesorCursoRepository.findAll() )
            .thenThrow( NullPointerException.class );

        ResponseDto responseEmpty = profesorCursoService.getAllProfesorCursos();

        assertAll( "Listado profesor curso null pointer", 
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
    @DisplayName( "Test de listar profesor cursos con error de nulls" )
    public void getAllProfesorCursosTestErrorNulls() {

        // profesor not found
        when( profesorCursoRepository.findAll() )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntityList() );


        ResponseDto responseProfesorNotFound = profesorCursoService.getAllProfesorCursos();

        assertAll( "Listado profesor curso null pointer", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseProfesorNotFound 
                    ),
            () -> assertEquals( "El código de la respuesta no es 1", 
                        codeSuccess, responseProfesorNotFound.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageNoRecords, responseProfesorNotFound.getMensaje() 
                    )
        );



        // Curso not found
        when( profesorRepository.findById( UtilProfesorData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilProfesorData.dummyProfesorEntityTrue() ) );

        when( cursoClient.getCursoTrueById( UtilProfesorCursoData.ID_CURSO ) )
            .thenReturn( null  );

        ResponseDto responseCursoNotFound = profesorCursoService.getAllProfesorCursos();

        assertAll( "Listado profesor curso null pointer", 
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
    public void getProfesorCursoByIdTestSuccess() {

        when( profesorCursoRepository.findById( UtilProfesorCursoData.ID_PROFESOR_CURSO ) )
            .thenReturn( Optional.of( UtilProfesorCursoData.dummyProfesorCursoEntity() ) );

        when( profesorRepository.findById( UtilProfesorData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilProfesorData.dummyProfesorEntityTrue() ) );

        when( cursoClient.getCursoTrueById( UtilProfesorCursoData.ID_CURSO ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoClientId()  );

        when( mapper.convertValue( any(), eq( CursoDto.class ) ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoDto() );

        ResponseDto response = profesorCursoService.getProfesorCursoById( UtilProfesorCursoData.ID_PROFESOR_CURSO );

        assertAll( "Buscar registro profesor curso", 
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
    public void getProfesorCursoByIdTestError() {


        // No record 
        ResponseDto responseNotFound = profesorCursoService.getProfesorCursoById( UtilProfesorCursoData.ID_PROFESOR_CURSO );

        assertAll( "Listado profesor curso sin objetos", 
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



        // No profesor found
        when( profesorCursoRepository.findById( UtilProfesorCursoData.ID_PROFESOR_CURSO ) )
            .thenReturn( Optional.of( UtilProfesorCursoData.dummyProfesorCursoEntity() ) );

        ResponseDto responseNoProfesorFound = profesorCursoService.getProfesorCursoById( UtilProfesorCursoData.ID_PROFESOR_CURSO );

        assertAll( "Listado profesor curso sin profesor", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNoProfesorFound 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseNoProfesorFound.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseNoProfesorFound.getMensaje().startsWith( messageNoRecord ) 
                    )
        );



        // No curso found 
        when( profesorRepository.findById( UtilProfesorData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilProfesorData.dummyProfesorEntityTrue() ) );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoClientNull() );

        ResponseDto responseNoCursoFound = profesorCursoService.getProfesorCursoById( UtilProfesorCursoData.ID_PROFESOR_CURSO );

        assertAll( "Listado profesor curso sin curso", 
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

        ResponseDto responseNoServiceAviable = profesorCursoService.getProfesorCursoById( UtilProfesorCursoData.ID_PROFESOR_CURSO );

        assertAll( "Buscar registro profesor curso no service", 
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
        when( profesorCursoRepository.findById( UtilProfesorCursoData.ID_PROFESOR_CURSO ) )
            .thenThrow( NullPointerException.class );

        ResponseDto responseNullPointer = profesorCursoService.getProfesorCursoById( UtilProfesorCursoData.ID_PROFESOR_CURSO );

        assertAll( "Buscar registro profesor curso null pointer", 
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
    @DisplayName( "Test de buscar registro por idProfesor y IdCurso con exito" )
    public void getProfesorCursoByProfesorAndCursoTestSuccess() {

        when( profesorCursoRepository.findByIdProfesorAndIdCurso( UtilProfesorData.ID_TRUE, UtilProfesorCursoData.ID_CURSO ) )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntity() );

        when( profesorRepository.findById( UtilProfesorData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilProfesorData.dummyProfesorEntityTrue() ) );

        when( cursoClient.getCursoTrueById( UtilProfesorCursoData.ID_CURSO ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoClientId()  );

        when( mapper.convertValue( any(), eq( CursoDto.class ) ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoDto() );

        ResponseDto response = profesorCursoService
            .getProfesorCursoByProfesorAndCurso( UtilProfesorData.ID_TRUE, UtilProfesorCursoData.ID_CURSO );

        assertAll( "Buscar registro profesor curso", 
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
    @DisplayName( "Test de buscar registro por idProfesor y IdCurso con error" )
    public void getProfesorCursoByProfesorAndCursoTestError() {


        // No found
        ResponseDto responseNotFound = profesorCursoService
            .getProfesorCursoByProfesorAndCurso( UtilProfesorData.ID_TRUE, UtilProfesorCursoData.ID_CURSO );

        assertAll( "Listado profesor curso sin objetos", 
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



        // No profesor found
        when( profesorCursoRepository.findByIdProfesorAndIdCurso( UtilProfesorData.ID_TRUE, UtilProfesorCursoData.ID_CURSO ) )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntity() );

        ResponseDto responseNoProfesorFound = profesorCursoService
            .getProfesorCursoByProfesorAndCurso( UtilProfesorData.ID_TRUE, UtilProfesorCursoData.ID_CURSO );

        assertAll( "Listado profesor curso sin profesor", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNoProfesorFound 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseNoProfesorFound.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseNoProfesorFound.getMensaje().startsWith( messageNoRecord ) 
                    )
        );



        // No curso found
        when( profesorRepository.findById( UtilProfesorData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilProfesorData.dummyProfesorEntityTrue() ) );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoClientNull() );

        ResponseDto responseNoCursoFound = profesorCursoService
            .getProfesorCursoByProfesorAndCurso( UtilProfesorData.ID_TRUE, UtilProfesorCursoData.ID_CURSO );

        assertAll( "Listado profesor curso sin curso", 
            () -> assertNotNull( "La respuesta es nula", responseNoCursoFound ),
            () -> assertEquals( "El código de la respuesta no es 0", codeSuccess, responseNoCursoFound.getCodigo() ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", responseNoCursoFound.getMensaje().startsWith( messageNoRecord ) )
        );
        
        

        // No service 
        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenThrow( RetryableException.class );

        ResponseDto responseNoServiceAviable = profesorCursoService
            .getProfesorCursoByProfesorAndCurso( UtilProfesorData.ID_TRUE, UtilProfesorCursoData.ID_CURSO );

        assertAll( "Buscar registro profesor curso no service", 
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
        when( profesorCursoRepository.findByIdProfesorAndIdCurso( UtilProfesorData.ID_TRUE, UtilProfesorCursoData.ID_CURSO ) )
            .thenThrow( NullPointerException.class );

        ResponseDto responseNullPointer = profesorCursoService
            .getProfesorCursoByProfesorAndCurso( UtilProfesorData.ID_TRUE, UtilProfesorCursoData.ID_CURSO );

        assertAll( "Buscar registro profesor null pointer", 
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
    @DisplayName( "Test de buscar registro por idProfesor con exito" )
    public void getProfesorCursosByProfesorTestSucces() {

        when( profesorCursoRepository.findAllByIdProfesor( UtilProfesorData.ID_TRUE ) )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntityList() );

        when( profesorRepository.findById( UtilProfesorData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilProfesorData.dummyProfesorEntityTrue() ) );

        when( cursoClient.getCursoTrueById( UtilProfesorCursoData.ID_CURSO ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoClientId()  );

        when( mapper.convertValue( any(), eq(CursoDto.class) ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoDto() );

        ResponseDto response = profesorCursoService.getProfesorCursosByProfesor( UtilProfesorData.ID_TRUE );

        assertAll( "Buscar registro profesor curso", 
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
    @DisplayName( "Test de buscar registro por idProfesor con error" )
    public void getProfesorCursosByProfesorTestError() {


        // Not found 
        when( profesorCursoRepository.findAllByIdProfesor( UtilProfesorData.ID_TRUE ) )
            .thenReturn( null );

        ResponseDto responseNotFound = profesorCursoService.getProfesorCursosByProfesor( UtilProfesorData.ID_TRUE );

        assertAll( "Listado profesor curso nulo", 
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



        // Not profesor found
        when( profesorCursoRepository.findAllByIdProfesor( UtilProfesorData.ID_TRUE ) )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntityList() );

        ResponseDto responseNoProfesorFound = profesorCursoService.getProfesorCursosByProfesor( UtilProfesorData.ID_TRUE );

        assertAll( "Listado profesor curso sin profesor", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNoProfesorFound
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseNoProfesorFound.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseNoProfesorFound.getMensaje().startsWith( messageNoRecord ) 
                    )
        );



        // Not any curso found
        when( profesorCursoRepository.findAllByIdProfesor( UtilProfesorData.ID_TRUE ) )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntityList() );

        when( profesorRepository.findById( any( Long.class ) ) )
            .thenReturn( Optional.of( UtilProfesorData.dummyProfesorEntityTrue() ) );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenReturn( null );

        ResponseDto responseEmptyList = profesorCursoService.getProfesorCursosByProfesor( UtilProfesorData.ID_TRUE );

        assertAll( "Listado profesor curso vacio", 
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
        when( profesorCursoRepository.findAllByIdProfesor( UtilProfesorData.ID_TRUE ) )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntityList() );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenThrow( RetryableException.class );

        ResponseDto responseNoServiceAviable = profesorCursoService.getProfesorCursosByProfesor( UtilProfesorData.ID_TRUE );

        assertAll( "Buscar registro profesor curso no service", 
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
        when( profesorCursoRepository.findAllByIdProfesor( UtilProfesorData.ID_TRUE ) )
            .thenThrow( NullPointerException.class );

        ResponseDto responseNullPointer = profesorCursoService.getProfesorCursosByProfesor( UtilProfesorData.ID_TRUE );

        assertAll( "Buscar registro profesor null pointer", 
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
    @DisplayName( "Test de buscar registro por idCurso con exito" )
    public void getProfesorCursosByCursoTestSuccess() {

        when( profesorCursoRepository.findAllByIdCurso( UtilProfesorCursoData.ID_CURSO ) )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntityList() );

        when( cursoClient.getCursoTrueById( UtilProfesorCursoData.ID_CURSO ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoClientId()  );

        when( mapper.convertValue( any(), eq(CursoDto.class) ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoDto() );

        when( profesorRepository.findById( UtilProfesorData.ID_TRUE ) )
            .thenReturn( Optional.of( UtilProfesorData.dummyProfesorEntityTrue() ) );


        ResponseDto response = profesorCursoService.getProfesorCursosByCurso( UtilProfesorCursoData.ID_CURSO );

        assertAll( "Buscar registro profesor curso", 
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
    @DisplayName( "Test de buscar registro por idCurso con error" )
    public void getProfesorCursosByCursoTestError() {


        // Not found 
        when( profesorCursoRepository.findAllByIdCurso( UtilProfesorCursoData.ID_CURSO ) )
            .thenReturn( null );

        ResponseDto responseNotFound = profesorCursoService.getProfesorCursosByCurso( UtilProfesorCursoData.ID_CURSO );

        assertAll( "Listado profesor curso nulo", 
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



        // Not curso found
        when( profesorCursoRepository.findAllByIdCurso( UtilProfesorCursoData.ID_CURSO ) )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntityList() );

        ResponseDto responseNoCursoFound = profesorCursoService.getProfesorCursosByCurso( UtilProfesorCursoData.ID_CURSO );

        assertAll( "Listado profesor curso sin profesor", 
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



        // Not any profesor found
        when( profesorCursoRepository.findAllByIdCurso( UtilProfesorCursoData.ID_CURSO ) )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntityList() );

        when( cursoClient.getCursoTrueById( UtilProfesorCursoData.ID_CURSO ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoClientId()  );

        when( mapper.convertValue( any(), eq(CursoDto.class) ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoDto() );

        ResponseDto responseEmptyList = profesorCursoService.getProfesorCursosByCurso( UtilProfesorCursoData.ID_CURSO );

        assertAll( "Listado profesor curso vacio", 
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
        when( profesorCursoRepository.findAllByIdCurso( UtilProfesorCursoData.ID_CURSO ) )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntityList() );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenThrow( RetryableException.class );

        ResponseDto responseNoServiceAviable = profesorCursoService.getProfesorCursosByCurso( UtilProfesorCursoData.ID_CURSO );

        assertAll( "Buscar registro profesor curso no service", 
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
        when( profesorCursoRepository.findAllByIdCurso( UtilProfesorCursoData.ID_CURSO ) )
            .thenThrow( NullPointerException.class );

        ResponseDto responseNullPointer = profesorCursoService.getProfesorCursosByCurso( UtilProfesorCursoData.ID_CURSO );

        assertAll( "Buscar registro profesor null pointer", 
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
    @DisplayName( "Test de crear profesor con exito" )
    public void createProfesorCursoTestSuccess() {

        when( profesorRepository.findById( any( Long.class ) ) )
            .thenReturn( Optional.of( UtilProfesorData.dummyProfesorEntityTrue() ) );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoClientId() );

        ResponseDto response = profesorCursoService
            .createProfesorCurso( UtilProfesorCursoData.dummyProfesorCursoDto() );

        assertAll( "Crear nuevo registro profesor curso",
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
    @DisplayName( "Test de crear profesor con error" )
    public void createProfesorCursoTestError() {


        // Null attributtes
        ResponseDto responseAttrNull = profesorCursoService
            .createProfesorCurso( UtilProfesorCursoData.dummyProfesorCursoDtoNullAttr() );

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
        when( profesorCursoRepository.findByIdProfesorAndIdCurso( any( Long.class ), any( Long.class ) ) )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntity() );

        ResponseDto responseDuplicated = profesorCursoService
            .createProfesorCurso( UtilProfesorCursoData.dummyProfesorCursoDtoDuplicated() );

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



        // Not profesor found 
        when( profesorCursoRepository.findByIdProfesorAndIdCurso( any( Long.class ), any( Long.class ) ) )
            .thenReturn( null );

        ResponseDto responseProfesorNotFound= profesorCursoService
            .createProfesorCurso( UtilProfesorCursoData.dummyProfesorCursoDto() );

        assertAll( "Crear nuevo registro sin profesor encontrado",
            () -> assertNotNull( "La respuesta es nula", 
                        responseProfesorNotFound 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeFailed, responseProfesorNotFound.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseProfesorNotFound.getMensaje().startsWith( messageNoRecord ) 
                    )
        );



        // Not curso found
        when( profesorRepository.findById( any( Long.class ) ) )
            .thenReturn( Optional.of( UtilProfesorData.dummyProfesorEntityTrue() ) );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoClientNull() );

        ResponseDto responseCursoNotFound = profesorCursoService
            .createProfesorCurso( UtilProfesorCursoData.dummyProfesorCursoDto() );

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

        ResponseDto responseCursoNoService = profesorCursoService
            .createProfesorCurso( UtilProfesorCursoData.dummyProfesorCursoDto() );

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
        ResponseDto responseNullPointer = profesorCursoService.createProfesorCurso( null );

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
    @DisplayName( "Test de actualizar profesor curso con éxito" )
    public void updateProfesorCursoTestSuccess() {

        when( profesorCursoRepository.findById( any( Long.class ) ) )
            .thenReturn( Optional.of( UtilProfesorCursoData.dummyProfesorCursoEntity() ) );

        when( profesorRepository.findById( any( Long.class ) ) )
            .thenReturn( Optional.of( UtilProfesorData.dummyProfesorEntityTrue() ) );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoClientId() );

        ResponseDto response = profesorCursoService.updateProfesorCurso( UtilProfesorCursoData.dummyProfesorCursoDto() );

        assertAll( "Crear nuevo registro profesor curso",
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
    @DisplayName( "Test de actualizar profesor curso con error" )
    public void updateProfesorCursoTestError() {

        
        // Null attributtes 
        ResponseDto responseAttrNull = profesorCursoService
            .updateProfesorCurso( UtilProfesorCursoData.dummyProfesorCursoDtoNullAttr() );

        assertAll( "Actualizar nuevo registro con atributos nulos",
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
        ResponseDto responseNoRecord = profesorCursoService
            .updateProfesorCurso( UtilProfesorCursoData.dummyProfesorCursoDtoDuplicated() );

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
        when( profesorCursoRepository.findById( any( Long.class ) ) )
            .thenReturn( Optional.of( UtilProfesorCursoData.dummyProfesorCursoEntity() ) );

        when( profesorCursoRepository.findByIdProfesorAndIdCurso( any( Long.class ), any( Long.class ) ) )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntity() );

        ResponseDto responseProfesorDuplicated = profesorCursoService
            .updateProfesorCurso( UtilProfesorCursoData.dummyProfesorCursoDto() );

        assertAll( "Actualizar nuevo registro sin profesor encontrado",
            () -> assertNotNull( "La respuesta es nula", 
                        responseProfesorDuplicated 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeSuccess, responseProfesorDuplicated.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageAlreadyExists, responseProfesorDuplicated.getMensaje() 
                    )
        );



        // No profesor found
        when( profesorCursoRepository.findByIdProfesorAndIdCurso( any( Long.class ), any( Long.class ) ) )
            .thenReturn( null );

        ResponseDto responseProfesorNotFound = profesorCursoService
            .updateProfesorCurso( UtilProfesorCursoData.dummyProfesorCursoDto() );

        assertAll( "Actualizar nuevo registro sin profesor encontrado",
            () -> assertNotNull( "La respuesta es nula", 
                        responseProfesorNotFound 
                    ),
            () -> assertEquals( "El código de la respuesta no coincide", 
                        codeFailed, responseProfesorNotFound.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta no coincide", 
                        responseProfesorNotFound.getMensaje().startsWith( messageNoRecord ) 
                    )
        );



        // No curso found
        when( profesorRepository.findById( any( Long.class ) ) )
            .thenReturn( Optional.of( UtilProfesorData.dummyProfesorEntityTrue() ) );

        when( cursoClient.getCursoTrueById( any( Long.class ) ) )
            .thenReturn( UtilProfesorCursoData.dummyCursoClientNull() );

        ResponseDto responseCursoNotFound = profesorCursoService
            .updateProfesorCurso( UtilProfesorCursoData.dummyProfesorCursoDto() );

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

        ResponseDto responseCursoNoService = profesorCursoService
            .updateProfesorCurso( UtilProfesorCursoData.dummyProfesorCursoDto() );

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
        ResponseDto responseNullPointer = profesorCursoService.updateProfesorCurso( null );

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
    @DisplayName( "Test de eliminar profesor por id con exito" )
    public void deleteProfesorCursoByIdTestSuccess() {

        when( profesorCursoRepository.existsById( any( Long.class ) ) ).thenReturn( true );

        ResponseDto response = profesorCursoService.deleteProfesorCursoById( UtilProfesorCursoData.ID_PROFESOR_CURSO );

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
    @DisplayName( "Test de eliminar profesor por id con error" )
    public void deleteProfesorCursoByIdTestError() {

        // Not found
        when( profesorCursoRepository.existsById( any( Long.class ) ) ).thenReturn( false );

        ResponseDto response = profesorCursoService.deleteProfesorCursoById( UtilProfesorCursoData.ID_PROFESOR_CURSO );

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
        when( profesorCursoRepository.existsById( any( Long.class ) ) ).thenReturn( true );

        ResponseDto responseNullPointer = profesorCursoService.deleteProfesorCursoById( null );

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
    @DisplayName( "Test de eliminar profesor por profesor y curso con exito" )
    public void deleteProfesorCursoByProfesorAndCursoTestSuccess() {

        when( profesorCursoRepository.findByIdProfesorAndIdCurso( any( Long.class ), any( Long.class ) ) )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntity() );

        ResponseDto response = profesorCursoService
            .deleteProfesorCursoByProfesorAndCurso( UtilProfesorData.ID_TRUE, UtilProfesorCursoData.ID_CURSO );

        assertAll( "Eliminar registro por idProfesor y idCurso",
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
    @DisplayName( "Test de eliminar profesor por profesor y curso con error" )
    public void deleteProfesorCursoByProfesorAndCursoTestError() {

        // Not found 
        ResponseDto responseNotFound = profesorCursoService
            .deleteProfesorCursoByProfesorAndCurso( UtilProfesorData.ID_TRUE, UtilProfesorCursoData.ID_CURSO );

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
        when( profesorCursoRepository.findByIdProfesorAndIdCurso( any( Long.class ), any( Long.class ) ) )
            .thenReturn( UtilProfesorCursoData.dummyProfesorCursoEntity() );

        ResponseDto responseNullPointer = profesorCursoService
            .deleteProfesorCursoByProfesorAndCurso( null, null );

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
