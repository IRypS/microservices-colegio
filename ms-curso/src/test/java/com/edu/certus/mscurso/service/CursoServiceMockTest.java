package com.edu.certus.mscurso.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.edu.certus.mscurso.dto.CursoDto;
import com.edu.certus.mscurso.dto.ResponseDto;
import com.edu.certus.mscurso.entity.CursoEntity;
import com.edu.certus.mscurso.repository.CursoRepository;
import com.edu.certus.mscurso.service.impl.CursoServiceImpl;
import com.edu.certus.mscurso.util.Constantes;
import com.edu.certus.mscurso.util.UtilCursoData;
import com.edu.certus.mscurso.util.UtilList;

@ExtendWith( MockitoExtension.class )
public class CursoServiceMockTest {
    

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoServiceImpl cursoService;


    private String codeFailed = Constantes.CODE_FAILED;
    private String codeSuccess = Constantes.CODE_SUCCES;
    private String messageAlreadyExists = Constantes.ALREADY_EXISTS;
    private String messageAttrMissing = Constantes.ATTRIBUTE_MISSING;
    private String messageCreated = Constantes.RECORD_CREATED;
    private String messageDeleted = Constantes.RECORD_DELETED;
    private String messageNoRecord = Constantes.NO_RECORD_FOUND;
    private String messageNoRecords = Constantes.NO_RECORDS_FOUND;
    private String messageOpFailed = Constantes.OPERATION_FAILED;
    private String messageUpdated = Constantes.RECORD_UPDATED;

    private Long idEstadoFalse = UtilCursoData.ID_FALSE;
    private Long idEstadoTrue = UtilCursoData.ID_TRUE;


    @Test
    @DisplayName( "Test de listar cursos con éxito" )
    public void getAllCursoTestSuccess() {

        when( cursoRepository.findAllByEstadoTrue() )
            .thenReturn( UtilCursoData.dummyCursoEntityListTrue() );

        when( cursoRepository.findAll() )
            .thenReturn( UtilCursoData.dummyCursoEntityListAll() );


        // Only true
        ResponseDto responseOnlyTrue = cursoService.getAllCurso( true );

        assertAll( "Obtener cursos con estado true",
            () -> assertNotNull( "La respuesta del listado true es nula", 
                        responseOnlyTrue 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseOnlyTrue.getCodigo() 
                    ),
            () -> assertNotNull( "Los datos son nulos", 
                        responseOnlyTrue.getData() 
                    ),
            () -> assertEquals( "El numero de items esperado no coincide", 
                        1, UtilList.getItemsCount( responseOnlyTrue.getData() ) 
                    )
        );

        // All
        ResponseDto responseAll = cursoService.getAllCurso( false );
        assertAll( "Obtener cursos con estado false",
            () -> assertNotNull( "La respuesta del listado all es nula", 
                        responseAll 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseAll.getCodigo() 
                    ),
            () -> assertNotNull( "Los datos son nulos", 
                        responseAll.getData() 
                    ),
            () -> assertEquals( "El numero de items esperado no coincide", 
                        2, UtilList.getItemsCount( responseAll.getData() ) 
                    )
        );

    }



     @Test
    @DisplayName( "Test de Listar todos los cursos con error" )
    public void getAllCursoTestFailed() {

        // null
        when( cursoRepository.findAllByEstadoTrue() ).thenReturn( null );
        when( cursoRepository.findAll() ).thenReturn( null );

        ResponseDto responseOnlyTrueNull = cursoService.getAllCurso( true );
        ResponseDto responseAllNull = cursoService.getAllCurso( false );

        assertAll( "Obtener cursos null con estado true", 
            () -> assertNotNull( "La respuesta del listado true es nula", 
                        responseOnlyTrueNull 
                    ),
            () -> assertEquals( "El código de la respuesta true no es 1", 
                        codeFailed, responseOnlyTrueNull.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta true no coincide", 
                        messageOpFailed, responseOnlyTrueNull.getMensaje() 
                    ),
            () -> assertNotNull( "La respuesta del listado all es nula", 
                        responseAllNull 
                    ),
            () -> assertEquals( "El código de la respuesta all no es 1", 
                        codeFailed, responseAllNull.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta all no coincide", 
                        messageOpFailed, responseAllNull.getMensaje() 
                    )
        );



        // empty
        when( cursoRepository.findAllByEstadoTrue() ).thenReturn( Collections.emptyList() );
        when( cursoRepository.findAll() ).thenReturn( Collections.emptyList() );

        ResponseDto responseOnlyTrueEmpty = cursoService.getAllCurso( true );
        ResponseDto responseAllEmpty = cursoService.getAllCurso( false );

        assertAll( "Obtener cursos empty con estado true", 
            () -> assertNotNull( "La respuesta del listado true es nula", 
                        responseOnlyTrueEmpty 
                    ),
            () -> assertEquals( "El código true de la respuesta no es 0", 
                        codeSuccess, responseOnlyTrueEmpty.getCodigo() 
                    ),
            () -> assertNull( "Los datos true no son nulos", 
                        responseOnlyTrueEmpty.getData() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta true no coincide", 
                        messageNoRecords, responseOnlyTrueEmpty.getMensaje() 
                    ),
            () -> assertNotNull( "La respuesta del listado all es nula", 
                        responseAllEmpty 
                    ),
            () -> assertEquals( "El código all de la respuesta no es 0", 
                        codeSuccess, responseAllEmpty.getCodigo() 
                    ),
            () -> assertNull( "Los datos all no son nulos", 
                        responseAllEmpty.getData() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta all no coincide", 
                        messageNoRecords, responseAllEmpty.getMensaje() 
                    )
        );



        // null pointer
        when( cursoRepository.findAllByEstadoTrue() ).thenThrow( NullPointerException.class );
        when( cursoRepository.findAll() ).thenThrow( NullPointerException.class );

        ResponseDto responseOnlyTrueNullPointer = cursoService.getAllCurso( true );
        ResponseDto responseAllNullPointer = cursoService.getAllCurso( false );

        assertAll( "Obtener cursos con estado true - NullPointerException", 
            () -> assertNotNull( "La respuesta del listado true es nula", 
                        responseOnlyTrueNullPointer 
                    ),
            () -> assertEquals( "El código de la respuesta true no es 1", 
                        codeFailed, responseOnlyTrueNullPointer.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta true no coincide", 
                        messageOpFailed, responseOnlyTrueNullPointer.getMensaje() 
                    ),
            () -> assertNotNull( "La respuesta del listado all es nula", 
                        responseAllNullPointer 
                    ),
            () -> assertEquals( "El código de la respuesta all no es 1", 
                        codeFailed, responseAllNullPointer.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta all no coincide", 
                        messageOpFailed, responseAllNullPointer.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de buscar curso por ID con exito" )
    public void getCursoByIdTestSuccess() {

        when( cursoRepository.findById( idEstadoFalse ) )
            .thenReturn( Optional.of( UtilCursoData.dummyCursoEntityFalse() ) );

        when( cursoRepository.findByIdAndEstadoTrue( idEstadoTrue ) )
            .thenReturn( UtilCursoData.dummyCursoEntityTrue() );


        // Only true
        ResponseDto responseOnlyTrue = cursoService.getCursoById( idEstadoTrue, true );

        assertAll( "Obtener curso con estado true",
            () -> assertNotNull( "La respuesta del listado true es nula", 
                        responseOnlyTrue 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseOnlyTrue.getCodigo() 
                    ),
            () -> assertNotNull( "Los datos son nulos", 
                        responseOnlyTrue.getData() 
                    )
        );


        // All
        ResponseDto responseAll = cursoService.getCursoById( idEstadoFalse, false );

        assertAll( "Obtener curso con estado false",
            () -> assertNotNull( "La respuesta del listado all es nula", 
                        responseAll 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, responseAll.getCodigo() 
                    ),
            () -> assertNotNull( "Los datos son nulos", 
                        responseAll.getData() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de buscar curso por ID con error" )
    public void getCursoByIdTestError() {

        // Only true
        when( cursoRepository.findByIdAndEstadoTrue( idEstadoFalse ) )
            .thenReturn( null );

        ResponseDto responseOnlyTrueNull = cursoService.getCursoById( idEstadoFalse, true );

        when( cursoRepository.findByIdAndEstadoTrue( null ) )
            .thenThrow( NullPointerException.class );

        ResponseDto responseOnlyTrueNullPointerException = cursoService.getCursoById( null, true );

        assertAll( "Obtener curso null con estado true", 
            () -> assertNotNull( "La respuesta null es nula", 
                        responseOnlyTrueNull 
                    ),
            () -> assertNull( "La data de respuesta null no es nula", 
                        responseOnlyTrueNull.getData() 
                    ),
            () -> assertEquals( "El código de la respuesta null no es 0", 
                        codeSuccess, responseOnlyTrueNull.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta null no coincide", 
                        messageNoRecord, responseOnlyTrueNull.getMensaje() 
                    ),
            () -> assertNotNull( "La respuesta null pointer es nula", 
                        responseOnlyTrueNullPointerException 
                    ),
            () -> assertNull( "La data de respuesta null pointer no es nula", 
                        responseOnlyTrueNullPointerException.getData() 
                    ),
            () -> assertEquals( "El código de la respuesta null pointer no es 1", 
                        codeFailed, responseOnlyTrueNullPointerException.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta null pointer no coincide", 
                        messageOpFailed, responseOnlyTrueNullPointerException.getMensaje() 
                    )
        );


        // All
        ResponseDto responseAllNull = cursoService.getCursoById( idEstadoFalse, false );

        when( cursoRepository.findById( null ) )
            .thenThrow( NullPointerException.class );

        ResponseDto responseAllNullPointerException = cursoService.getCursoById( null, false );

        assertAll( "Obtener curso null con estado all", 
            () -> assertNotNull( "La respuesta null es nula", 
                        responseAllNull 
                    ),
            () -> assertNull( "La data de respuesta null no es nula", 
                        responseAllNull.getData() 
                    ),
            () -> assertEquals( "El código de la respuesta null no es 0", 
                        codeSuccess, responseAllNull.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta null no coincide", 
                        messageNoRecord, responseOnlyTrueNull.getMensaje() 
                    ),
            () -> assertNotNull( "La respuesta null pointer es nula", 
                        responseAllNullPointerException 
                    ),
            () -> assertNull( "La data de respuesta null pointer no es nula", 
                        responseAllNullPointerException.getData() 
                    ),
            () -> assertEquals( "El código de la respuesta null pointer no es 1", 
                        codeFailed, responseAllNullPointerException.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta null pointer no coincide", 
                        messageOpFailed, responseAllNullPointerException.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de Crear curso con exito" )
    public void createCursoTestSuccess() {
        
        when( cursoRepository.save( any(CursoEntity.class) ) )
            .thenReturn( null );

		ResponseDto response = cursoService.createCurso( UtilCursoData.dummyCursoDto() );

        assertAll( "Crear curso", 
            () -> assertNotNull( "La respuesta es nula", 
                        response 
                    ),
            () -> assertEquals( "El código de la respuesta all no es 0", 
                        codeSuccess, response.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje no coincide", 
                        messageCreated, response.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de Crear curso con error" )
    public void createCursoTestError() {

        // Without attributes
        ResponseDto responseWithoutDescripcion = cursoService
            .createCurso( UtilCursoData.dummyCursoDtoWithoutDescripcion() );

        assertAll( "Creando curso sin atributos", 
            () -> assertNotNull( "La respuesta sin descripcion es nula", 
                        responseWithoutDescripcion 
                    ),
            () -> assertEquals( "El código de la respuesta sin descripcion no es 1", 
                        codeFailed, responseWithoutDescripcion.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta sin nombre no coincide", 
                        responseWithoutDescripcion.getMensaje().startsWith( messageAttrMissing ) 
                    )
        );



        // Already exits
        CursoDto cursoDto = UtilCursoData.dummyCursoDtoRepeated();

        when( cursoRepository.findByDescripcionAndEstadoTrue( cursoDto.getDescripcion() ) )
            .thenReturn( UtilCursoData.dummyCursoEntityTrue() );

        ResponseDto responseAlready = cursoService.createCurso( cursoDto );

        assertAll( "Creando curso existente", 
            () -> assertNotNull( "La respuesta de creacion repetida es nula", 
                        responseAlready
                    ),
            () -> assertEquals( "El código de la respuesta de creacion repetida no es 1", 
                        codeFailed, responseAlready.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageAlreadyExists, responseAlready.getMensaje() 
                    )
        );



        // null pointer
        ResponseDto responseNullPointer = cursoService.createCurso(null);

        assertAll( "Creando curso null", 
            () -> assertNotNull( "La respuesta con argumento invalido es nula", 
                        responseNullPointer 
                    ),
            () -> assertEquals( "El código de la respuesta con argumento invalido no es 1", 
                        codeFailed, responseNullPointer.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageOpFailed, responseNullPointer.getMensaje() 
                    )
        );

    }




    @Test
    @DisplayName( "Test de actualizar curso con exito" )
    public void updateCursoTestSuccess() {

        when( cursoRepository.findByIdAndEstadoTrue( UtilCursoData.ID_TRUE ) )
            .thenReturn( UtilCursoData.dummyCursoEntityTrue() );

        when( cursoRepository.save( any(CursoEntity.class) ) )
            .thenReturn( UtilCursoData.dummyCursoEntityTrue() );
        
		ResponseDto response = cursoService.updateCurso( UtilCursoData.dummyCursoDtoRepeated() );

        assertAll( "Actualizar curso con estado true", 
            () -> assertNotNull( "La respuesta es nula", 
                        response 
                    ),
            () -> assertEquals( "El código de la respuesta all no es 0",
                        codeSuccess, response.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje no coincide", 
                        messageUpdated, response.getMensaje() 
                    )
        );

    }




    @Test
    @DisplayName( "Test de actualizar curso con error" )
    public void updateCursoTestError() {


        // not found
        ResponseDto responseNotFound = cursoService
            .updateCurso( UtilCursoData.dummyCursoDtoRepeated() );

        assertAll( "Actualizar curso inexistente", 
            () -> assertNotNull( "La respuesta del curso no encontrado es nula", 
                        responseNotFound 
                    ),
            () -> assertEquals( "El código de la respuesta no encontrado no es 1", 
                        codeFailed, responseNotFound.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje no coincide", 
                        messageNoRecord, responseNotFound.getMensaje() 
                    )
        );



        // Without attributes
        when( cursoRepository.findByIdAndEstadoTrue( UtilCursoData.ID_TRUE ) )
            .thenReturn( UtilCursoData.dummyCursoEntityTrue() );

         ResponseDto responseWithoutDescripcion = cursoService
            .updateCurso( UtilCursoData.dummyCursoDtoWithoutDescripcion() );

        assertAll( "Creando curso sin atributos", 
            () -> assertNotNull( "La respuesta sin descripcion es nula", 
                        responseWithoutDescripcion 
                    ),
            () -> assertEquals( "El código de la respuesta sin descripcion no es 1", 
                        codeFailed, responseWithoutDescripcion.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta sin nombre no coincide", 
                        responseWithoutDescripcion.getMensaje().startsWith( messageAttrMissing ) 
                    )
        );



        // Repeated
        CursoDto cursoDto = UtilCursoData.dummyCursoDtoRepeated();
        
        when( cursoRepository.findByDescripcionAndEstadoTrue( cursoDto.getDescripcion() ) )
            .thenReturn( UtilCursoData.dummyCursoEntityTrue() );

        ResponseDto responseAlreadyExists = cursoService.updateCurso( cursoDto );

        assertAll( "Actualizar curso ya registrado", 
            () -> assertNotNull( "La respuesta del curso ya registrado es nula", 
                        responseAlreadyExists 
                    ),
            () -> assertEquals( "El código de la respuesta ya registrado no es 1", 
                        codeFailed, responseAlreadyExists.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje no coincide", 
                        messageAlreadyExists, responseAlreadyExists.getMensaje() 
                    )
        );


        
        // null pointer
        ResponseDto responseNullPointer = cursoService.updateCurso(null);

        assertAll( "Actualizando curso null", 
            () -> assertNotNull( "La respuesta de creacion repetida es nula", 
                        responseNullPointer 
                    ),
            () -> assertEquals( "El código de la respuesta con argumento invalido no es 1", 
                        codeFailed, responseNullPointer.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje de la respuesta no coincide", 
                        messageOpFailed, responseNullPointer.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de eliminar curso con exito" )
    public void deleteCursoByIdTestSuccess() {

        when( cursoRepository.findByIdAndEstadoTrue( UtilCursoData.ID_TRUE ) )
            .thenReturn( UtilCursoData.dummyCursoEntityTrue() );

        ResponseDto response = cursoService.deleteCursoById( UtilCursoData.ID_TRUE );

        assertAll( "Eliminar curso encontrado", 
            () -> assertNotNull( "La respuesta es nula", 
                        response 
                    ),
            () -> assertEquals( "El código de la respuesta no es 0", 
                        codeSuccess, response.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje no coincide",
                        messageDeleted, response.getMensaje() 
                    )
        );

    }



    @Test
    @DisplayName( "Test de eliminar curso con error" )
    public void deleteCursoByIdTestError() {

        // Not found
        ResponseDto response = cursoService.deleteCursoById( UtilCursoData.ID_TRUE );

        assertAll( "Eliminar curso no encontrado", 
            () -> assertNotNull( "La respuesta es nula", 
                        response 
                    ),
            () -> assertEquals( "El código de la respuesta no es 1", 
                        codeFailed, response.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje no coincide", 
                        messageNoRecord, response.getMensaje() 
                    )
        );



        // Null pointer
        when( cursoRepository.findByIdAndEstadoTrue( UtilCursoData.ID_TRUE ) )
            .thenThrow( NullPointerException.class );;

        ResponseDto responseNullPointer = cursoService.deleteCursoById( UtilCursoData.ID_TRUE );

        assertAll( "Eliminar null", 
            () -> assertNotNull( "La respuesta es nula", 
                        responseNullPointer
                    ),
            () -> assertEquals( "El código de la respuesta no es 1", 
                        codeFailed, responseNullPointer.getCodigo() 
                    ),
            () -> assertEquals( "El mensaje no coincide", 
                        messageOpFailed, responseNullPointer.getMensaje() 
                    )
        );

    }

}
