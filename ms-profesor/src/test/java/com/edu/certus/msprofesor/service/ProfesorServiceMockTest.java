package com.edu.certus.msprofesor.service;

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

import com.edu.certus.msprofesor.dto.ProfesorDto;
import com.edu.certus.msprofesor.dto.ResponseDto;
import com.edu.certus.msprofesor.entity.ProfesorEntity;
import com.edu.certus.msprofesor.repository.ProfesorRepository;
import com.edu.certus.msprofesor.service.impl.ProfesorServiceImpl;
import com.edu.certus.msprofesor.util.Constantes;
import com.edu.certus.msprofesor.util.UtilList;
import com.edu.certus.msprofesor.util.UtilProfesorData;

@ExtendWith( MockitoExtension.class )
public class ProfesorServiceMockTest {
    
    @Mock
    private ProfesorRepository profesorRepository;

    @InjectMocks
    private ProfesorServiceImpl profesorService;

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

    private Long idEstadoFalse = UtilProfesorData.ID_FALSE;
    private Long idEstadoTrue = UtilProfesorData.ID_TRUE;


    @Test
    @DisplayName( "Test de Listar todos los profesores con exito" )
    public void getAllProfesorTestSuccess() {

        when( profesorRepository.findAll() )
            .thenReturn( UtilProfesorData.dummyProfesorEntityListAll() );

        when( profesorRepository.findAllByEstadoTrue() )
            .thenReturn( UtilProfesorData.dummyProfesorEntityListTrue() );
        
        // Only true
        ResponseDto responseOnlyTrue = profesorService.getAllProfesor( true );

        assertAll( "Obtener profesores con estado true",
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
        ResponseDto responseAll = profesorService.getAllProfesor( false );
        assertAll( "Obtener profesores con estado false",
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
    @DisplayName( "Test de Listar todos los profesores con error" )
    public void getAllProfesorTestError() {

        // null
        when( profesorRepository.findAllByEstadoTrue() ).thenReturn( null );
        when( profesorRepository.findAll() ).thenReturn( null );

        ResponseDto responseOnlyTrueNull = profesorService.getAllProfesor( true );
        ResponseDto responseAllNull = profesorService.getAllProfesor( false );

        assertAll( "Obtener profesores null con estado true", 
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
        when( profesorRepository.findAllByEstadoTrue() ).thenReturn( Collections.emptyList() );
        when( profesorRepository.findAll() ).thenReturn( Collections.emptyList() );

        ResponseDto responseOnlyTrueEmpty = profesorService.getAllProfesor( true );
        ResponseDto responseAllEmpty = profesorService.getAllProfesor( false );

        assertAll( "Obtener profesores empty con estado true", 
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
        when( profesorRepository.findAllByEstadoTrue() ).thenThrow( NullPointerException.class );
        when( profesorRepository.findAll() ).thenThrow( NullPointerException.class );

        ResponseDto responseOnlyTrueNullPointer = profesorService.getAllProfesor( true );
        ResponseDto responseAllNullPointer = profesorService.getAllProfesor( false );

        assertAll( "Obtener profesores con estado true - NullPointerException", 
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
    @DisplayName( "Test de buscar profesor por ID con exito" )
    public void getProfesorByIdTestSuccess() {

        when( profesorRepository.findById( idEstadoFalse ) )
            .thenReturn( Optional.of( UtilProfesorData.dummyProfesorEntityFalse() ) );

        when( profesorRepository.findByIdAndEstadoTrue( idEstadoTrue ) )
            .thenReturn( UtilProfesorData.dummyProfesorEntityTrue() );


        // Only true
        ResponseDto responseOnlyTrue = profesorService.getProfesorById( idEstadoTrue, true );

        assertAll( "Obtener profesor con estado true",
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
        ResponseDto responseAll = profesorService.getProfesorById( idEstadoFalse, false );

        assertAll( "Obtener profesor con estado false",
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
    @DisplayName( "Test de buscar profesor por ID con error" )
    public void getProfesorByIdTestError() {

        // Only true
        when( profesorRepository.findByIdAndEstadoTrue( idEstadoFalse ) )
            .thenReturn( null );

        ResponseDto responseOnlyTrueNull = profesorService.getProfesorById( idEstadoFalse, true );

        when( profesorRepository.findByIdAndEstadoTrue( null ) )
            .thenThrow( NullPointerException.class );

        ResponseDto responseOnlyTrueNullPointerException = profesorService.getProfesorById( null, true );

        assertAll( "Obtener profesor null con estado true", 
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
        ResponseDto responseAllNull = profesorService.getProfesorById( idEstadoFalse, false );

        when( profesorRepository.findById( null ) )
            .thenThrow( NullPointerException.class );

        ResponseDto responseAllNullPointerException = profesorService.getProfesorById( null, false );

        assertAll( "Obtener profesor null con estado all", 
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
    @DisplayName( "Test de Crear profesor con exito" )
    public void createProfesorTestSuccess() {
        
        when( profesorRepository.save( any(ProfesorEntity.class) ) )
            .thenReturn( null );

		ResponseDto response = profesorService.createProfesor( UtilProfesorData.dummyProfesorDto() );

        assertAll( "Crear profesor", 
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
    @DisplayName( "Test de Crear profesor con error" )
    public void createProfesorTestError() {

        // Without attributes
        ResponseDto responseWithoutNombre = profesorService.createProfesor( UtilProfesorData.dummyProfesorDtoWithoutNombres() );
        ResponseDto responseWithoutApellidos = profesorService.createProfesor( UtilProfesorData.dummyProfesorDtoWithoutApellidos() );
        ResponseDto responseWithoutSexo = profesorService.createProfesor( UtilProfesorData.dummyProfesorDtoWithoutSexo() );
        ResponseDto responseInvalidArgument = profesorService.createProfesor( UtilProfesorData.dummyProfesorDtoInvalidArgument() );

        assertAll( "Creando profesor sin atributos / atributos inválidos", 
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
        ProfesorDto profesorDto = UtilProfesorData.dummyProfesorDtoRepeated();

        when( profesorRepository.findByNombresAndApellidosAndEstadoTrue( profesorDto.getNombres(), profesorDto.getApellidos() ) )
            .thenReturn( UtilProfesorData.dummyProfesorEntityTrue() );

        ResponseDto responseAlready = profesorService.createProfesor( profesorDto );

        assertAll( "Creando profesor existente", 
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
        ResponseDto responseNullPointer = profesorService.createProfesor(null);

        assertAll( "Creando profesor null", 
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
    @DisplayName( "Test de actualizar profesor con exito" )
    public void updateProfesorTestSuccess() {

        when( profesorRepository.findByIdAndEstadoTrue( UtilProfesorData.ID_TRUE ) )
            .thenReturn( UtilProfesorData.dummyProfesorEntityTrue() );

        when( profesorRepository.save( any(ProfesorEntity.class) ) )
            .thenReturn( UtilProfesorData.dummyProfesorEntityTrue() );
        
		ResponseDto response = profesorService.updateProfesor( UtilProfesorData.dummyProfesorDtoRepeated() );

        assertAll( "Actualizar profesor con estado true", 
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
    @DisplayName( "Test de actualizar profesor con error" )
    public void updateProfesorTestError() {


        // not found
        ResponseDto responseNotFound = profesorService.updateProfesor( UtilProfesorData.dummyProfesorDtoRepeated() );

        assertAll( "Actualizar profesor inexistente", 
            () -> assertNotNull( "La respuesta del profesor no encontrado es nula", 
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
        when( profesorRepository.findByIdAndEstadoTrue( UtilProfesorData.ID_TRUE ) )
            .thenReturn( UtilProfesorData.dummyProfesorEntityTrue() );

        ResponseDto responseWithoutNombre = profesorService.updateProfesor( UtilProfesorData.dummyProfesorDtoWithoutNombres() );
        ResponseDto responseWithoutApellidos = profesorService.updateProfesor( UtilProfesorData.dummyProfesorDtoWithoutApellidos() );
        ResponseDto responseWithoutSexo = profesorService.updateProfesor( UtilProfesorData.dummyProfesorDtoWithoutSexo() );
        ResponseDto responseInvalidArgument = profesorService.updateProfesor( UtilProfesorData.dummyProfesorDtoInvalidArgument() );

        assertAll( "Actualizando profesor sin atributos / atributos inválidos", 
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
        ProfesorDto profesorDto = UtilProfesorData.dummyProfesorDtoRepeated();
        
        when( profesorRepository.findByNombresAndApellidosAndEstadoTrue( profesorDto.getNombres(), profesorDto.getApellidos() ) )
            .thenReturn( UtilProfesorData.dummyProfesorEntityTrue() );

        ResponseDto responseAlreadyExists = profesorService.updateProfesor( profesorDto );

        assertAll( "Actualizar profesor ya registrado", 
            () -> assertNotNull( "La respuesta del profesor ya registrado es nula", 
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
        ResponseDto responseNullPointer = profesorService.updateProfesor(null);

        assertAll( "Actualizando profesor null", 
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
    @DisplayName( "Test de eliminar profesor con exito" )
    public void deleteProfesorByIdTestSuccess() {

        when( profesorRepository.findByIdAndEstadoTrue( UtilProfesorData.ID_TRUE ) )
            .thenReturn( UtilProfesorData.dummyProfesorEntityTrue() );

        ResponseDto response = profesorService.deleteProfesorById( UtilProfesorData.ID_TRUE );

        assertAll( "Eliminar profesor encontrado", 
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
    @DisplayName( "Test de eliminar profesor con error" )
    public void deleteProfesorByIdTestError() {

        // Not found
        ResponseDto response = profesorService.deleteProfesorById( UtilProfesorData.ID_TRUE );

        assertAll( "Eliminar profesor no encontrado", 
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
        when( profesorRepository.findByIdAndEstadoTrue( UtilProfesorData.ID_TRUE ) )
            .thenThrow( NullPointerException.class );;

        ResponseDto responseNullPointer = profesorService.deleteProfesorById( UtilProfesorData.ID_TRUE );

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
