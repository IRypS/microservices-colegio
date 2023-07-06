package com.edu.certus.msalumno.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.certus.msalumno.client.CursoClient;
import com.edu.certus.msalumno.client.ProfesorClient;
import com.edu.certus.msalumno.dto.AlumnoCursoProfesorDto;
import com.edu.certus.msalumno.dto.CursoDto;
import com.edu.certus.msalumno.dto.ProfesorCursoDto;
import com.edu.certus.msalumno.dto.ResponseDto;
import com.edu.certus.msalumno.entity.AlumnoCursoEntity;
import com.edu.certus.msalumno.entity.AlumnoEntity;
import com.edu.certus.msalumno.repository.AlumnoCursoRepository;
import com.edu.certus.msalumno.repository.AlumnoRepository;
import com.edu.certus.msalumno.service.AlumnoCursoProfesorService;
import com.edu.certus.msalumno.util.Constantes;
import com.edu.certus.msalumno.util.Util;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AlumnoCursoProfesorServiceImpl implements AlumnoCursoProfesorService {



    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private AlumnoCursoRepository alumnoCursoRepository;

    @Autowired
    private CursoClient cursoClient;

    @Autowired
    private ProfesorClient profesorClient;

    @Autowired
    private ObjectMapper mapper;



    @Override
    public ResponseDto getAllAlumnoCursoProfesor() {

        try {

            List<AlumnoCursoProfesorDto> listaAlumnoCursoProfesorDtos = new ArrayList<AlumnoCursoProfesorDto>();


            List<AlumnoCursoEntity> listAlumnoCursoEntity = alumnoCursoRepository.findAll();

            for (AlumnoCursoEntity alumnoCursoEntity : listAlumnoCursoEntity) {
                
                AlumnoEntity alumnoEntity = alumnoRepository.findByIdAndEstadoTrue( alumnoCursoEntity.getIdAlumno() );
                
                if ( alumnoEntity == null ) {
                    log.error( Constantes.NO_RECORD_FOUND + " | Alumno ID: " + alumnoCursoEntity.getIdAlumno() );
                    continue;
                }

                ResponseDto responseCursoDto = cursoClient.getCursoTrueById( alumnoCursoEntity.getIdCurso() );
                CursoDto cursoDto = mapper.convertValue( responseCursoDto.getData(), CursoDto.class );
                
                if ( responseCursoDto.getData() == null ) {
                    log.error( Constantes.NO_RECORD_FOUND + " | Curso ID: " + alumnoCursoEntity.getIdCurso() );
                    continue;
                }

                ResponseDto responseProfesorDto = profesorClient.getProfesorCursosByCurso( alumnoCursoEntity.getIdCurso() );
				List<ProfesorCursoDto> listaProfesorCursoDto = mapper.convertValue( responseProfesorDto.getData(), new TypeReference< List<ProfesorCursoDto> >() {} );

                if ( listaProfesorCursoDto.size() == 0 ) {
                    log.error( Constantes.NO_RECORD_FOUND + " | No se encontraron profesores | Curso ID: " + alumnoCursoEntity.getIdCurso() );
                    continue;
                }


                listaProfesorCursoDto.forEach( profesorCurso -> {

                    listaAlumnoCursoProfesorDtos.add(

                        AlumnoCursoProfesorDto.builder()
                            .idAlumno( alumnoEntity.getId() )
                            .nombreAlumno( alumnoEntity.getNombres() + " " + alumnoEntity.getApellidos() )
                            .estadoAlumno( alumnoEntity.isEstado() )
                            .idCurso( cursoDto.getId() )
                            .nombreCurso( cursoDto.getDescripcion() )
                            .idProfesor( profesorCurso.getIdProfesor() )
                            .nombreProfesor( profesorCurso.getNombreProfesor() )
                            .build()

                    );

                });
                
            }

            return Util.getResponse(true, Constantes.OPERATION_SUCCESS, listaAlumnoCursoProfesorDtos);
            
        } catch ( RetryableException e ) {
			log.error( "[ms-curso/ms-profesor] " + Constantes.NO_SERVICE_AVAILABLE, e );
			return Util.getResponse(false, "[ms-curso/ms-profesor] " + Constantes.NO_SERVICE_AVAILABLE, null);
		
		} catch (Exception e) {
            log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
        }
        
    };

    
}
