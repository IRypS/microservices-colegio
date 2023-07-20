package com.edu.certus.msalumno.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.certus.msalumno.dto.AlumnoDto;
import com.edu.certus.msalumno.dto.ResponseDto;
import com.edu.certus.msalumno.entity.AlumnoEntity;
import com.edu.certus.msalumno.repository.AlumnoRepository;
import com.edu.certus.msalumno.service.AlumnoService;
import com.edu.certus.msalumno.util.Constantes;
import com.edu.certus.msalumno.util.Util;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AlumnoServiceImpl implements AlumnoService {
    
    

    @Autowired
    private AlumnoRepository alumnoRepository;



    @Override
    public ResponseDto getAllAlumno( boolean onlyTrue ) {

        try {

            List< AlumnoEntity > listaAlumnoEntity = onlyTrue
                ? alumnoRepository.findAllByEstadoTrue()
                : alumnoRepository.findAll();

            if ( listaAlumnoEntity.isEmpty() ) {
                log.error( Constantes.NO_RECORDS_FOUND );
                return Util.getResponse( true, Constantes.NO_RECORDS_FOUND, null );
            }


            List<AlumnoDto> listaAlumnoDto = new ArrayList<>();

            for( AlumnoEntity alumnoEntity : listaAlumnoEntity ) {

                listaAlumnoDto.add(
                    AlumnoDto.builder()
                        .id( alumnoEntity.getId() )
                        .nombres( alumnoEntity.getNombres() )
                        .apellidos( alumnoEntity.getApellidos() )
                        .sexo( alumnoEntity.getSexo() )
                        .estado( alumnoEntity.isEstado() )
                        .build()
                );

            }

            return Util.getResponse( true, Constantes.OPERATION_SUCCESS, listaAlumnoDto );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }

    }


    
    @Override
    public ResponseDto getAlumnoById( Long idAlumno, boolean onlyTrue ) {

        try {

            AlumnoEntity alumnoEntityFound = onlyTrue
                ? alumnoRepository.findByIdAndEstadoTrue( idAlumno )
                : alumnoRepository.findById( idAlumno ).orElse(null);

            if ( alumnoEntityFound == null ) {
                log.error( Constantes.NO_RECORD_FOUND );
                return Util.getResponse( true, Constantes.NO_RECORD_FOUND, null );
            }


            AlumnoDto alumnoDto =  AlumnoDto.builder()
                .id( alumnoEntityFound.getId() )
                .nombres( alumnoEntityFound.getNombres() )
                .apellidos( alumnoEntityFound.getApellidos() )
                .sexo( alumnoEntityFound.getSexo() )
                .estado( alumnoEntityFound.isEstado() )
                .build();
            
            return Util.getResponse( true, Constantes.OPERATION_SUCCESS, alumnoDto );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }

    }



    @Override
    public ResponseDto createAlumno( AlumnoDto alumnoDto ) {

        try {

            String nombres = alumnoDto.getNombres().trim();

            if( nombres.length() == 0 ) {
                log.error( Constantes.ATTRIBUTE_MISSING + " [nombres]" );
                return Util.getResponse( false, Constantes.ATTRIBUTE_MISSING + " [nombres]", null );
            }

            String apellidos = alumnoDto.getApellidos().trim();
            
            if( apellidos.length() == 0 ) {
                log.error( Constantes.ATTRIBUTE_MISSING + " [apellidos]" );
                return Util.getResponse( false, Constantes.ATTRIBUTE_MISSING + " [apellidos]", null );
            }

            String sexo = alumnoDto.getSexo();

            if( sexo.length() == 0 ) {
                log.error( Constantes.ATTRIBUTE_MISSING + " [sexo]" );
                return Util.getResponse( false, Constantes.ATTRIBUTE_MISSING + " [sexo]", null );
            }

            if ( !sexo.equals("M") && !sexo.equals("F") ) {
                log.error( Constantes.ATTRIBUTE_BAD + " [sexo] debe ser F o M" );
                return Util.getResponse( false, Constantes.ATTRIBUTE_BAD + " [sexo] debe ser F o M", null );
            }


            AlumnoEntity alumnoEntityFound = alumnoRepository
                .findByNombresAndApellidosAndEstadoTrue( nombres, apellidos );

            if ( alumnoEntityFound != null ) {
                log.error( Constantes.ALREADY_EXISTS );
                return Util.getResponse( false, Constantes.ALREADY_EXISTS, alumnoEntityFound );
            }
            

            AlumnoEntity alumnoEntity = AlumnoEntity.builder()
                .nombres( nombres )
                .apellidos( apellidos )
                .sexo( sexo )
                .estado( true )
                .build();
            
            alumnoRepository.save( alumnoEntity );
            alumnoDto.setId( alumnoEntity.getId() );
            return Util.getResponse( true, Constantes.RECORD_CREATED, alumnoDto );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }

    };



    @Override
    public ResponseDto updateAlumno( AlumnoDto alumnoDto ) {

        try {
            
            AlumnoEntity alumnoEntityFound = alumnoRepository.findByIdAndEstadoTrue( alumnoDto.getId() );

            if ( alumnoEntityFound == null ) {
                log.error( Constantes.NO_RECORD_FOUND );
                return Util.getResponse( false, Constantes.NO_RECORD_FOUND, null );
            }

            
            String nombres = alumnoDto.getNombres().trim();

            if( nombres.length() == 0 ) {
                log.error( Constantes.ATTRIBUTE_MISSING + " [nombres]" );
                return Util.getResponse( false, Constantes.ATTRIBUTE_MISSING + " [nombres]", null );
            }

            String apellidos = alumnoDto.getApellidos().trim();
            
            if( apellidos.length() == 0 ) {
                log.error( Constantes.ATTRIBUTE_MISSING + " [apellidos]" );
                return Util.getResponse( false, Constantes.ATTRIBUTE_MISSING + " [apellidos]", null );
            }

            String sexo = alumnoDto.getSexo();

            if( sexo.length() == 0 ) {
                log.error( Constantes.ATTRIBUTE_MISSING + " [sexo]" );
                return Util.getResponse( false, Constantes.ATTRIBUTE_MISSING + " [sexo]", null );
            }

            if ( !sexo.equals("M") && !sexo.equals("F") ) {
                log.error( Constantes.ATTRIBUTE_BAD + " [sexo] debe ser F o M" );
                return Util.getResponse( false, Constantes.ATTRIBUTE_BAD + " [sexo] debe ser F o M", null );
            }


            AlumnoEntity alumnoEntityDuplicatedFound = alumnoRepository
                .findByNombresAndApellidosAndEstadoTrue( nombres, apellidos );

            if ( alumnoEntityDuplicatedFound != null ) {
                log.error( Constantes.ALREADY_EXISTS );
                return Util.getResponse( false, Constantes.ALREADY_EXISTS, alumnoEntityDuplicatedFound );
            }
            

            alumnoEntityFound.setNombres( nombres );
            alumnoEntityFound.setApellidos( apellidos );
            alumnoEntityFound.setSexo( sexo );
            alumnoRepository.save( alumnoEntityFound );
            return Util.getResponse( true , Constantes.RECORD_UPDATED, alumnoEntityFound );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }

    };



    @Override
    public ResponseDto deleteAlumnoById( Long idAlumno ) {

        try {

            AlumnoEntity alumnoEntityFound = alumnoRepository.findByIdAndEstadoTrue( idAlumno );

            if ( alumnoEntityFound == null ) {
                log.error( Constantes.NO_RECORD_FOUND );
                return Util.getResponse( false, Constantes.NO_RECORD_FOUND, null );
            }
            
            alumnoEntityFound.setEstado( false );
            alumnoRepository.save( alumnoEntityFound );
            return Util.getResponse( true, Constantes.RECORD_DELETED, null );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }

    };

}
