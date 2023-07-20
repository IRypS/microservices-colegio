package com.edu.certus.mscurso.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.certus.mscurso.dto.CursoDto;
import com.edu.certus.mscurso.dto.ResponseDto;
import com.edu.certus.mscurso.entity.CursoEntity;
import com.edu.certus.mscurso.repository.CursoRepository;
import com.edu.certus.mscurso.service.CursoService;
import com.edu.certus.mscurso.util.Constantes;
import com.edu.certus.mscurso.util.Util;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CursoServiceImpl implements CursoService {
    


    @Autowired
    private CursoRepository cursoRepository;



    @Override
    public ResponseDto getAllCurso( boolean onlyTrue ) {

        try {

            List< CursoEntity > listaCursoEntity = onlyTrue
                ? cursoRepository.findAllByEstadoTrue()
                : cursoRepository.findAll();

            if ( listaCursoEntity.isEmpty() ) {
                log.error( Constantes.NO_RECORDS_FOUND );
                return Util.getResponse( true, Constantes.NO_RECORDS_FOUND, null );
            }


            List< CursoDto > listaCursoDto = new ArrayList<>();

            for( CursoEntity cursoEntity : listaCursoEntity ) {

                listaCursoDto.add(
                    CursoDto.builder()
                        .id( cursoEntity.getId() )
                        .descripcion( cursoEntity.getDescripcion() )
                        .estado( cursoEntity.isEstado() )
                        .build()
                );

            }

            return Util.getResponse( true, Constantes.OPERATION_SUCCESS, listaCursoDto );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }

    };



    @Override
    public ResponseDto getCursoById( Long idCurso, boolean onlyTrue ){

        try {

            CursoEntity cursoEntityFound = onlyTrue
                ? cursoRepository.findByIdAndEstadoTrue( idCurso )
                : cursoRepository.findById( idCurso ).orElse(null);

            if ( cursoEntityFound == null ) {
                log.error( Constantes.NO_RECORD_FOUND );
                return Util.getResponse( true, Constantes.NO_RECORD_FOUND, null );
            }


            CursoDto cursoDto =  CursoDto.builder()
                .id( cursoEntityFound.getId() )
                .descripcion( cursoEntityFound.getDescripcion() )
                .estado( cursoEntityFound.isEstado() )
                .build();
            
            return Util.getResponse( true, Constantes.OPERATION_SUCCESS, cursoDto );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }

    }



    @Override
    public ResponseDto createCurso( CursoDto cursoDto ) {

        try {

            if ( cursoDto.getDescripcion() == null || cursoDto.getDescripcion().trim().length() == 0) {
                log.error( Constantes.ATTRIBUTE_MISSING + " [descripcion]" );
                return Util.getResponse( false, Constantes.ATTRIBUTE_MISSING + " [descripcion]", null );
            }
            
            String descripcion = cursoDto.getDescripcion().trim();

            CursoEntity cursoEntityFound = cursoRepository.findByDescripcionAndEstadoTrue( descripcion );

            if ( cursoEntityFound != null ) {
                log.error( Constantes.ALREADY_EXISTS );
                return Util.getResponse( false, Constantes.ALREADY_EXISTS, cursoEntityFound );
            }
            

            CursoEntity cursoEntity = CursoEntity.builder()
                .descripcion( descripcion )
                .estado( true )
                .build();
            
            cursoRepository.save( cursoEntity );
            cursoDto.setId( cursoEntity.getId() );
            return Util.getResponse( true, Constantes.RECORD_CREATED, cursoDto );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }

    }



    @Override
    public ResponseDto updateCurso( CursoDto cursoDto ) {

        try {
            
            CursoEntity cursoEntityFound = cursoRepository.findByIdAndEstadoTrue( cursoDto.getId() );

            if ( cursoEntityFound == null ) {
                log.error( Constantes.NO_RECORD_FOUND );
                return Util.getResponse( false, Constantes.NO_RECORD_FOUND, null );
            }

            
            if ( cursoDto.getDescripcion() == null || cursoDto.getDescripcion().trim().length() == 0) {
                log.error( Constantes.ATTRIBUTE_MISSING + " [descripcion]" );
                return Util.getResponse( false, Constantes.ATTRIBUTE_MISSING + " [descripcion]", null );
            }
            
            String descripcion = cursoDto.getDescripcion().trim();

            CursoEntity cursoEntityDuplicatedFound = cursoRepository.findByDescripcionAndEstadoTrue( descripcion );

            if ( cursoEntityDuplicatedFound != null ) {
                log.error( Constantes.ALREADY_EXISTS );
                return Util.getResponse( false, Constantes.ALREADY_EXISTS, cursoEntityDuplicatedFound );
            }
            

            cursoEntityFound.setDescripcion( descripcion );
            cursoRepository.save( cursoEntityFound );
            return Util.getResponse( true , Constantes.RECORD_UPDATED, cursoEntityFound );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }

    }



    @Override
    public ResponseDto deleteCursoById( Long idCurso ) {

        try {

            CursoEntity cursoEntityFound = cursoRepository.findByIdAndEstadoTrue( idCurso );

            if ( cursoEntityFound == null ) {
                log.error( Constantes.NO_RECORD_FOUND );
                return Util.getResponse( false, Constantes.NO_RECORD_FOUND, null );
            }
            
            cursoEntityFound.setEstado( false );
            cursoRepository.save( cursoEntityFound );
            return Util.getResponse( true, Constantes.RECORD_DELETED, null );
            
        } catch ( Exception e ) {
            log.error( Constantes.OPERATION_FAILED, e );
            return Util.getResponse( false, Constantes.OPERATION_FAILED, null );
        }

    };

}
