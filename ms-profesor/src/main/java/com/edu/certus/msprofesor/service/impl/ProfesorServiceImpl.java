package com.edu.certus.msprofesor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.certus.msprofesor.dto.ProfesorDto;
import com.edu.certus.msprofesor.dto.ResponseDto;
import com.edu.certus.msprofesor.entity.ProfesorEntity;
import com.edu.certus.msprofesor.repository.ProfesorRepository;
import com.edu.certus.msprofesor.service.ProfesorService;
import com.edu.certus.msprofesor.util.Constantes;
import com.edu.certus.msprofesor.util.Util;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProfesorServiceImpl implements ProfesorService {

    

    @Autowired
    private ProfesorRepository profesorRepository;



    @Override
    public ResponseDto getAllProfesor( boolean onlyTrue ) {
        
        try {

            List< ProfesorEntity > listaProfesorEntity = onlyTrue
                ? profesorRepository.findAllByEstadoTrue()
                : profesorRepository.findAll();

            if ( listaProfesorEntity.isEmpty() ) {
                log.error( Constantes.NO_RECORDS_FOUND );
                return Util.getResponse( true, Constantes.NO_RECORDS_FOUND, null );
            }


            List<ProfesorDto> listaProfesorDto = new ArrayList<>();

            for( ProfesorEntity profesorEntity : listaProfesorEntity ) {

                listaProfesorDto.add(
                    ProfesorDto.builder()
                        .id( profesorEntity.getId() )
                        .nombres( profesorEntity.getNombres() )
                        .apellidos( profesorEntity.getApellidos() )
                        .sexo( profesorEntity.getSexo() )
                        .estado( profesorEntity.isEstado() )
                        .build()
                );

            }

            return Util.getResponse( true, Constantes.OPERATION_SUCCESS, listaProfesorDto );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }

    };



    @Override
    public ResponseDto getProfesorById( Long idProfesor, boolean onlyTrue ) {

        try {

            ProfesorEntity profesorEntityFound = onlyTrue
                ? profesorRepository.findByIdAndEstadoTrue( idProfesor )
                : profesorRepository.findById( idProfesor ).orElse(null);

            if ( profesorEntityFound == null ) {
                log.error( Constantes.NO_RECORD_FOUND );
                return Util.getResponse( true, Constantes.NO_RECORD_FOUND, null );
            }


            ProfesorDto profesorDto =  ProfesorDto.builder()
                .id( profesorEntityFound.getId() )
                .nombres( profesorEntityFound.getNombres() )
                .apellidos( profesorEntityFound.getApellidos() )
                .sexo( profesorEntityFound.getSexo() )
                .estado( profesorEntityFound.isEstado() )
                .build();
            
            return Util.getResponse( true, Constantes.OPERATION_SUCCESS, profesorDto );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }

    };



    @Override
    public ResponseDto createProfesor( ProfesorDto profesorDto ) {

        try {

            String nombres = profesorDto.getNombres().trim();
            String apellidos = profesorDto.getApellidos().trim();
            String sexo = profesorDto.getSexo();

            if ( !sexo.equals("M") && !sexo.equals("F") ) {
                log.error( Constantes.ATTRIBUTE_BAD + " [sexo]" );
                return Util.getResponse( false, Constantes.ATTRIBUTE_BAD + " [sexo]", null );
            }


            ProfesorEntity profesorEntityFound = profesorRepository
                .findByNombresAndApellidosAndEstadoTrue( nombres, apellidos );

            if ( profesorEntityFound != null ) {
                log.error( Constantes.ALREADY_EXISTS );
                return Util.getResponse( false, Constantes.ALREADY_EXISTS, profesorEntityFound );
            }
            

            ProfesorEntity profesorEntity = ProfesorEntity.builder()
                .nombres( nombres )
                .apellidos( apellidos )
                .sexo( sexo )
                .estado( true )
                .build();
            
            profesorRepository.save( profesorEntity );
            profesorDto.setId( profesorEntity.getId() );
            return Util.getResponse( true, Constantes.RECORD_CREATED, profesorDto );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }

    };



    @Override
    public ResponseDto updateProfesor( ProfesorDto profesorDto ) {

        try {
            
            ProfesorEntity profesorEntityFound = profesorRepository.findByIdAndEstadoTrue( profesorDto.getId() );

            if ( profesorEntityFound == null ) {
                log.error( Constantes.NO_RECORD_FOUND );
                return Util.getResponse( false, Constantes.NO_RECORD_FOUND, null );
            }

            
            String nombres = profesorDto.getNombres().trim();
            String apellidos = profesorDto.getApellidos().trim();
            String sexo = profesorDto.getSexo();

            if ( !sexo.equals("M") && !sexo.equals("F") ) {
                log.error( Constantes.ATTRIBUTE_BAD + " [sexo]" );
                return Util.getResponse( false, Constantes.ATTRIBUTE_BAD + " [sexo]", null );
            }


            ProfesorEntity profesorEntityDuplicatedFound = profesorRepository
                .findByNombresAndApellidosAndEstadoTrue( nombres, apellidos );

            if ( profesorEntityDuplicatedFound != null ) {
                log.error( Constantes.ALREADY_EXISTS );
                return Util.getResponse( false, Constantes.ALREADY_EXISTS, profesorEntityDuplicatedFound );
            }
            

            profesorEntityFound.setNombres( nombres );
            profesorEntityFound.setApellidos( apellidos );
            profesorEntityFound.setSexo( sexo );
            profesorRepository.save( profesorEntityFound );
            return Util.getResponse( true , Constantes.RECORD_UPDATED, profesorEntityFound );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }

    };



    @Override
    public ResponseDto deleteProfesorById( Long idProfesor ) {

        try {

            ProfesorEntity profesorEntityFound = profesorRepository.findByIdAndEstadoTrue( idProfesor );

            if ( profesorEntityFound == null ) {
                log.error( Constantes.NO_RECORD_FOUND );
                return Util.getResponse( false, Constantes.NO_RECORD_FOUND, null );
            }
            
            profesorEntityFound.setEstado( false );
            profesorRepository.save( profesorEntityFound );
            return Util.getResponse( true, Constantes.RECORD_DELETED, null );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }

    };
    
}
