package com.edu.certus.msalumno.service;

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

import com.edu.certus.msalumno.dto.AlumnoDto;
import com.edu.certus.msalumno.dto.ResponseDto;
import com.edu.certus.msalumno.entity.AlumnoEntity;
import com.edu.certus.msalumno.repository.AlumnoRepository;
import com.edu.certus.msalumno.service.impl.AlumnoServiceImpl;
import com.edu.certus.msalumno.util.Constantes;
import com.edu.certus.msalumno.util.UtilList;
import com.edu.certus.msalumno.util.UtilAlumnoData;


@ExtendWith( MockitoExtension.class )
public class AlumnoServiceMockTest {
    
    
    @Mock
    private AlumnoRepository alumnoRepository;

    @InjectMocks
    private AlumnoServiceImpl alumnoService;

    private String codeFailed = Constantes.CODE_FAILED;
    private String codeSuccess = Constantes.CODE_SUCCES;
    private String messageAlreadyExists = Constantes.ALREADY_EXISTS;
    private String messageAttrBad = Constantes.ATTRIBUTE_BAD;
    private String messageAttrMissing = Constantes.ATTRIBUTE_MISSING;
    private String messageCreated = Constantes.RECORD_CREATED;
    private String messageDeleted = Constantes.RECORD_DELETED;
    private String messageNoRecord = Constantes.NO_RECORD_FOUND;
    private String messageNoRecords = Constantes.NO_RECORDS_FOUND;
    private String messageOpFailed = Constantes.OPERATION_FAILED;
    private String messageUpdated = Constantes.RECORD_UPDATED;

    private Long idEstadoFalse = UtilAlumnoData.ID_FALSE;
    private Long idEstadoTrue = UtilAlumnoData.ID_TRUE;


    @Test
    @DisplayName( "Test de Listar todos los alumnos con exito" )
    public void getAllAlumnoTestSuccess() {

        when( alumnoRepository.findAll() )
            .thenReturn( UtilAlumnoData.dummyAlumnoEntityListAll() );

        when( alumnoRepository.findAllByEstadoTrue() )
            .thenReturn( UtilAlumnoData.dummyAlumnoEntityListTrue() );
        
        // Only true
        ResponseDto responseOnlyTrue = alumnoService.getAllAlumno( true );

        assertAll( "Obtener alumnos con estado true",
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
        ResponseDto responseAll = alumnoService.getAllAlumno( false );
        assertAll( "Obtener alumnos con estado false",
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
    @DisplayName( "Test de Listar todos los alumnos con error" )
    public void getAllAlumnoTestError() {

        // null
        when( alumnoRepository.findAllByEstadoTrue() ).thenReturn( null );
        when( alumnoRepository.findAll() ).thenReturn( null );

        ResponseDto responseOnlyTrueNull = alumnoService.getAllAlumno( true );
        ResponseDto responseAllNull = alumnoService.getAllAlumno( false );

        assertAll( "Obtener alumnos null con estado true", 
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
        when( alumnoRepository.findAllByEstadoTrue() ).thenReturn( Collections.emptyList() );
        when( alumnoRepository.findAll() ).thenReturn( Collections.emptyList() );

        ResponseDto responseOnlyTrueEmpty = alumnoService.getAllAlumno( true );
        ResponseDto responseAllEmpty = alumnoService.getAllAlumno( false );

        assertAll( "Obtener alumnos empty con estado true", 
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
        when( alumnoRepository.findAllByEstadoTrue() ).thenThrow( NullPointerException.class );
        when( alumnoRepository.findAll() ).thenThrow( NullPointerException.class );

        ResponseDto responseOnlyTrueNullPointer = alumnoService.getAllAlumno( true );
        ResponseDto responseAllNullPointer = alumnoService.getAllAlumno( false );

        assertAll( "Obtener alumnos con estado true - NullPointerException", 
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
    @DisplayName( "Test de buscar alumno por ID con exito" )
    public void getAlumnoByIdTestSuccess() {

        when( alumnoRepository.findById( idEstadoFalse ) )
            .thenReturn( Optional.of( UtilAlumnoData.dummyAlumnoEntityFalse() ) );

        when( alumnoRepository.findByIdAndEstadoTrue( idEstadoTrue ) )
            .thenReturn( UtilAlumnoData.dummyAlumnoEntityTrue() );


        // Only true
        ResponseDto responseOnlyTrue = alumnoService.getAlumnoById( idEstadoTrue, true );

        assertAll( "Obtener alumno con estado true",
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
        ResponseDto responseAll = alumnoService.getAlumnoById( idEstadoFalse, false );

        assertAll( "Obtener alumnos con estado false",
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
    @DisplayName( "Test de buscar alumno por ID con error" )
    public void getAlumnoByIdTestError() {

        // Only true
        when( alumnoRepository.findByIdAndEstadoTrue( idEstadoFalse ) )
            .thenReturn( null );

        ResponseDto responseOnlyTrueNull = alumnoService.getAlumnoById( idEstadoFalse, true );

        when( alumnoRepository.findByIdAndEstadoTrue( null ) )
            .thenThrow( NullPointerException.class );

        ResponseDto responseOnlyTrueNullPointerException = alumnoService.getAlumnoById( null, true );

        assertAll( "Obtener alumno null con estado true", 
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
        ResponseDto responseAllNull = alumnoService.getAlumnoById( idEstadoFalse, false );

        when( alumnoRepository.findById( null ) )
            .thenThrow( NullPointerException.class );

        ResponseDto responseAllNullPointerException = alumnoService.getAlumnoById( null, false );

        assertAll( "Obtener alumno null con estado all", 
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
    @DisplayName( "Test de Crear alumno con exito" )
    public void createAlumnoTestSuccess() {
        
        when( alumnoRepository.save( any(AlumnoEntity.class) ) )
            .thenReturn( null );

		ResponseDto response = alumnoService.createAlumno( UtilAlumnoData.dummyAlumnoDto() );

        assertAll( "Crear alumno", 
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
    @DisplayName( "Test de Crear alumno con error" )
    public void createAlumnoTestError() {

        // Without attributes
        ResponseDto responseWithoutNombre = alumnoService.createAlumno( UtilAlumnoData.dummyAlumnoDtoWithoutNombres() );
        ResponseDto responseWithoutApellidos = alumnoService.createAlumno( UtilAlumnoData.dummyAlumnoDtoWithoutApellidos() );
        ResponseDto responseWithoutSexo = alumnoService.createAlumno( UtilAlumnoData.dummyAlumnoDtoWithoutSexo() );
        ResponseDto responseInvalidArgument = alumnoService.createAlumno( UtilAlumnoData.dummyAlumnoDtoInvalidArgument() );

        assertAll( "Creando alumno sin atributos / atributos inválidos", 
            () -> assertNotNull( "La respuesta sin nombre es nula", 
                        responseWithoutNombre 
                    ),
            () -> assertNotNull( "La respuesta sin apellidos es nula", 
                        responseWithoutApellidos 
                    ),
            () -> assertNotNull( "La respuesta sin sexo es nula", 
                        responseWithoutSexo 
                    ),
            () -> assertNotNull( "La respuesta con argumento invalido es nula", 
                        responseInvalidArgument 
                    ),
            () -> assertEquals( "El código de la respuesta sin nombre no es 1", 
                        codeFailed, responseWithoutNombre.getCodigo() 
                    ),
            () -> assertEquals( "El código de la respuesta sin apellidos no es 1", 
                        codeFailed, responseWithoutApellidos.getCodigo() 
                    ),
            () -> assertEquals( "El código de la respuesta sin sexo es nula no es 1", 
                        codeFailed, responseWithoutSexo.getCodigo() 
                    ),
            () -> assertEquals( "El código de la respuesta con argumento invalido no es 1", 
                        codeFailed, responseInvalidArgument.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta sin nombre no coincide", 
                        responseWithoutNombre.getMensaje().startsWith(messageAttrMissing) 
                    ),
            () -> assertTrue( "El mensaje de la respuesta sin apellidos no coincide", 
                        responseWithoutApellidos.getMensaje().startsWith(messageAttrMissing)
                    ),
            () -> assertTrue( "El mensaje de la respuesta sin sexo no coincide", 
                        responseWithoutSexo.getMensaje().startsWith(messageAttrMissing) 
                    ),
            () -> assertTrue( "El mensaje de la respuesta con argumento invalido no coincide", 
                        responseInvalidArgument.getMensaje().startsWith(messageAttrBad) 
                    )
        );



        // Already exits
        AlumnoDto alumnoDto = UtilAlumnoData.dummyAlumnoDtoRepeated();

        when( alumnoRepository.findByNombresAndApellidosAndEstadoTrue( alumnoDto.getNombres(), alumnoDto.getApellidos() ) )
            .thenReturn( UtilAlumnoData.dummyAlumnoEntityTrue() );

        ResponseDto responseAlready = alumnoService.createAlumno( alumnoDto );

        assertAll( "Creando alumno existente", 
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
        ResponseDto responseNullPointer = alumnoService.createAlumno(null);

        assertAll( "Creando alumno null", 
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
    @DisplayName( "Test de actualizar alumno con exito" )
    public void updateAlumnoTestSuccess() {

        when( alumnoRepository.findByIdAndEstadoTrue( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( UtilAlumnoData.dummyAlumnoEntityTrue() );

        when( alumnoRepository.save( any(AlumnoEntity.class) ) )
            .thenReturn( UtilAlumnoData.dummyAlumnoEntityTrue() );
        
		ResponseDto response = alumnoService.updateAlumno( UtilAlumnoData.dummyAlumnoDtoRepeated() );

        assertAll( "Actualizar alumno con estado true", 
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
    @DisplayName( "Test de actualizar alumno con error" )
    public void updateAlumnoTestError() {


        // not found
        ResponseDto responseNotFound = alumnoService.updateAlumno( UtilAlumnoData.dummyAlumnoDtoRepeated() );

        assertAll( "Actualizar alumno inexistente", 
            () -> assertNotNull( "La respuesta del alumno no encontrado es nula", 
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
        when( alumnoRepository.findByIdAndEstadoTrue( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( UtilAlumnoData.dummyAlumnoEntityTrue() );

        ResponseDto responseWithoutNombre = alumnoService.updateAlumno( UtilAlumnoData.dummyAlumnoDtoWithoutNombres() );
        ResponseDto responseWithoutApellidos = alumnoService.updateAlumno( UtilAlumnoData.dummyAlumnoDtoWithoutApellidos() );
        ResponseDto responseWithoutSexo = alumnoService.updateAlumno( UtilAlumnoData.dummyAlumnoDtoWithoutSexo() );
        ResponseDto responseInvalidArgument = alumnoService.updateAlumno( UtilAlumnoData.dummyAlumnoDtoInvalidArgument() );

        assertAll( "Alctualizando alumno sin atributos / atributos inválidos", 
            () -> assertNotNull( "La respuesta sin nombre es nula", 
                        responseWithoutNombre 
                    ),
            () -> assertNotNull( "La respuesta sin apellidos es nula", 
                        responseWithoutApellidos 
                    ),
            () -> assertNotNull( "La respuesta sin sexo es nula", 
                        responseWithoutSexo 
                    ),
            () -> assertNotNull( "La respuesta con argumento invalido es nula", 
                        responseInvalidArgument 
                    ),
            () -> assertEquals( "El código de la respuesta sin nombre no es 1", 
                        codeFailed, responseWithoutNombre.getCodigo() 
                    ),
            () -> assertEquals( "El código de la respuesta sin apellidos no es 1", 
                        codeFailed, responseWithoutApellidos.getCodigo() 
                    ),
            () -> assertEquals( "El código de la respuesta sin sexo es nula no es 1", 
                        codeFailed, responseWithoutSexo.getCodigo() 
                    ),
            () -> assertEquals( "El código de la respuesta con argumento invalido no es 1", 
                        codeFailed, responseInvalidArgument.getCodigo() 
                    ),
            () -> assertTrue( "El mensaje de la respuesta sin nombre no coincide", 
                        responseWithoutNombre.getMensaje().startsWith(messageAttrMissing) 
                    ),
            () -> assertTrue( "El mensaje de la respuesta sin apellidos no coincide", 
                        responseWithoutApellidos.getMensaje().startsWith(messageAttrMissing) 
                    ),
            () -> assertTrue( "El mensaje de la respuesta sin sexo no coincide", 
                        responseWithoutSexo.getMensaje().startsWith(messageAttrMissing) 
                    ),
            () -> assertTrue( "El mensaje de la respuesta con argumento invalido no coincide", 
                        responseInvalidArgument.getMensaje().startsWith(messageAttrBad) 
                    )
        );



        // Repeated
        AlumnoDto alumnoDto = UtilAlumnoData.dummyAlumnoDtoRepeated();
        
        when( alumnoRepository.findByNombresAndApellidosAndEstadoTrue( alumnoDto.getNombres(), alumnoDto.getApellidos() ) )
            .thenReturn( UtilAlumnoData.dummyAlumnoEntityTrue() );

        ResponseDto responseAlreadyExists = alumnoService.updateAlumno( alumnoDto );

        assertAll( "Actualizar alumno ya registrado", 
            () -> assertNotNull( "La respuesta del alumno ya registrado es nula", 
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
        ResponseDto responseNullPointer = alumnoService.updateAlumno(null);

        assertAll( "Actualizando alumno null", 
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
    @DisplayName( "Test de eliminar alumno con exito" )
    public void deleteAlumnoByIdTestSuccess() {

        when( alumnoRepository.findByIdAndEstadoTrue( UtilAlumnoData.ID_TRUE ) )
            .thenReturn( UtilAlumnoData.dummyAlumnoEntityTrue() );

        ResponseDto response = alumnoService.deleteAlumnoById( UtilAlumnoData.ID_TRUE );

        assertAll( "Eliminar alumno encontrado", 
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
    @DisplayName( "Test de eliminar alumno con error" )
    public void deleteAlumnoByIdTestError() {

        // Not found
        ResponseDto response = alumnoService.deleteAlumnoById( UtilAlumnoData.ID_TRUE );

        assertAll( "Eliminar alumno no encontrado", 
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
        when( alumnoRepository.findByIdAndEstadoTrue( UtilAlumnoData.ID_TRUE ) )
            .thenThrow( NullPointerException.class );;

        ResponseDto responseNullPointer = alumnoService.deleteAlumnoById( UtilAlumnoData.ID_TRUE );

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