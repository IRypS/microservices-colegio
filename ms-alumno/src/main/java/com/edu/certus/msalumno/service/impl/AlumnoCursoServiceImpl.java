package com.edu.certus.msalumno.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.certus.msalumno.client.CursoClient;
import com.edu.certus.msalumno.dto.AlumnoCursoDto;
import com.edu.certus.msalumno.dto.AlumnoCursoSendDto;
import com.edu.certus.msalumno.dto.AlumnoCursosDto;
import com.edu.certus.msalumno.dto.CursoDto;
import com.edu.certus.msalumno.dto.CursoMinDto;
import com.edu.certus.msalumno.dto.ResponseDto;
import com.edu.certus.msalumno.entity.AlumnoCursoEntity;
import com.edu.certus.msalumno.entity.AlumnoEntity;
import com.edu.certus.msalumno.repository.AlumnoCursoRepository;
import com.edu.certus.msalumno.repository.AlumnoRepository;
import com.edu.certus.msalumno.service.AlumnoCursoService;
import com.edu.certus.msalumno.util.Constantes;
import com.edu.certus.msalumno.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AlumnoCursoServiceImpl implements AlumnoCursoService {
    


    @Autowired
    private AlumnoCursoRepository alumnoCursoRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CursoClient cursoClient;

    @Autowired
    private ObjectMapper mapper;



    @Override
    public ResponseDto getAllAlumnoCurso() {

        try {

			List<AlumnoCursoEntity> listAlumnoCursoEntity = alumnoCursoRepository.findAll();

			if ( listAlumnoCursoEntity == null ) {
				return Util.getResponse( true, Constantes.NO_RECORDS_FOUND, null );
			}

			List<AlumnoCursoDto> listAlumnoCurso = new ArrayList<AlumnoCursoDto>();
			
			for ( AlumnoCursoEntity record : listAlumnoCursoEntity ) {

				AlumnoEntity alumnoEntity = alumnoRepository.findById( record.getIdAlumno() ).orElse(null);

				if ( alumnoEntity == null ) {
					log.error( Constantes.NO_RECORD_FOUND + " | Alumno ID: " + record.getIdAlumno() );
					continue;
				}
	
				ResponseDto responseDto = cursoClient.getCursoTrueById( record.getIdCurso() );
				CursoDto cursoDto = mapper.convertValue( responseDto.getData(), CursoDto.class );

				if ( cursoDto == null ) {
					log.error( Constantes.NO_RECORD_FOUND + " | Curso ID: " + record.getIdCurso() );
					continue;
				}

				listAlumnoCurso.add(
					AlumnoCursoDto.builder()
                        .id( record.getId() )
						.idAlumno( alumnoEntity.getId() )
						.nombreAlumno( alumnoEntity.getNombres() + " " + alumnoEntity.getApellidos() )
						.sexoAlumno( alumnoEntity.getSexo() )
						.estadoAlumno( alumnoEntity.isEstado() )
						.idCurso( cursoDto.getId() )
						.nombreCurso( cursoDto.getDescripcion() )
						.build()
				);

			}

			return Util.getResponse( true, Constantes.OPERATION_SUCCESS, listAlumnoCurso );

		} catch ( RetryableException e ) {
			log.error( "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, e );
			return Util.getResponse(false, "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, null);
		
		} catch ( Exception e ) {
			log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}

    };



    @Override
	public ResponseDto getAllAlumnoCursos() {

		try {
			
			List<AlumnoCursoEntity> listAlumnoCursoEntity = alumnoCursoRepository.findAll();

			if ( listAlumnoCursoEntity == null ) {
				return Util.getResponse( true, Constantes.NO_RECORDS_FOUND, null );
			}


			Map<Long, List<AlumnoCursoEntity>> cursosPorAlumno = listAlumnoCursoEntity.stream()
				.collect( Collectors.groupingBy( record -> record.getIdAlumno() ) );


			List<AlumnoCursosDto> listAlumnoCursos = new ArrayList<AlumnoCursosDto>();

			for ( Map.Entry<Long, List<AlumnoCursoEntity>> entry : cursosPorAlumno.entrySet() ) {

				Long idAlumno = entry.getKey();
				List<AlumnoCursoEntity> listaCursos = entry.getValue();

				AlumnoEntity alumnoEntity = alumnoRepository.findById( idAlumno ).orElse(null);

				if ( alumnoEntity == null ) {
					log.error( Constantes.NO_RECORD_FOUND + " | Alumno ID: " + idAlumno );
					continue;
				}

				List<CursoMinDto> listaCursosDtos = new ArrayList<>();
				
				for ( AlumnoCursoEntity record : listaCursos ) {

					ResponseDto cursoResponse = cursoClient.getCursoTrueById( record.getIdCurso() );
					CursoDto cursoDto = mapper.convertValue( cursoResponse.getData(), CursoDto.class );

					if ( cursoResponse.getData() == null ) {
						log.error( Constantes.NO_RECORD_FOUND + " | Curso ID: " + record.getIdCurso() );
						continue;
                    }

                    listaCursosDtos.add(
                        CursoMinDto.builder()
                            .id( cursoDto.getId() )
                            .descripcion( cursoDto.getDescripcion() )
                            .build()
                    );

                }

                if ( listaCursosDtos.size() == 0 ) {
					log.error( Constantes.NO_RECORDS_FOUND + 
						": [ No hay cursos por mostrar para el alumno con ID " + idAlumno + "]" );
					continue;
				};

				listAlumnoCursos.add(
					AlumnoCursosDto.builder()
						.idAlumno( alumnoEntity.getId() )
						.nombreAlumno( alumnoEntity.getNombres() + " " + alumnoEntity.getApellidos() )
						.sexoAlumno( alumnoEntity.getSexo() )
						.estadoAlumno( alumnoEntity.isEstado() )
						.cursos( listaCursosDtos )
						.build()
				);

			}

			return Util.getResponse( true, Constantes.OPERATION_SUCCESS, listAlumnoCursos );

		} catch ( RetryableException e ) {
			log.error( "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, e );
			return Util.getResponse(false, "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, null);
		
		} catch ( Exception e ) {
			log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}

	}



    @Override
	public ResponseDto getAlumnoCurso( Long id ) {

		try {

			AlumnoCursoEntity alumnoCursoEntity = alumnoCursoRepository.findById(id).orElse(null);

			if( alumnoCursoEntity == null ) {
				log.error( Constantes.NO_RECORDS_FOUND );
				return Util.getResponse(true, Constantes.NO_RECORD_FOUND, null);
			}
			
			AlumnoEntity alumnoEntity = alumnoRepository.findById( alumnoCursoEntity.getIdAlumno() ).orElse(null);

			if ( alumnoEntity == null ) {
				log.error( Constantes.NO_RECORD_FOUND + " | Alumno ID: " + alumnoCursoEntity.getIdAlumno() );
				return Util.getResponse(true, Constantes.NO_RECORD_FOUND + " | Alumno ID: " + alumnoCursoEntity.getIdAlumno() , null);
			}

			ResponseDto cursoResponse = cursoClient.getCursoTrueById( alumnoCursoEntity.getIdCurso() );
			CursoDto cursoDto = mapper.convertValue( cursoResponse.getData(), CursoDto.class );
			
			if ( cursoResponse.getData() == null ) {
				log.error( Constantes.NO_RECORD_FOUND + " | Curso ID: " + alumnoCursoEntity.getIdCurso() );
				return Util.getResponse(true, Constantes.NO_RECORD_FOUND + " | Curso ID: " + alumnoCursoEntity.getIdCurso() , null);
			}

			AlumnoCursoDto alumnoCursoDto = AlumnoCursoDto.builder()
                .id( id )
				.idAlumno( alumnoEntity.getId() )
				.nombreAlumno( alumnoEntity.getNombres() + " " + alumnoEntity.getApellidos() )
				.sexoAlumno( alumnoEntity.getSexo() )
				.estadoAlumno( alumnoEntity.isEstado() )
				.idCurso( cursoDto.getId() )
				.nombreCurso( cursoDto.getDescripcion() )
				.build();
			
			return Util.getResponse(true, Constantes.OPERATION_SUCCESS, alumnoCursoDto);

		} catch ( RetryableException e ) {
			log.error( "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, e );
			return Util.getResponse(false, "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, null);
		
		} catch ( Exception e ) {
			log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}

	}



    @Override
	public ResponseDto getAlumnoCursoByAlumnoAndCurso( Long idAlumno, Long idCurso ) {

		try {
			
			AlumnoCursoEntity alumnoCursoEntity = alumnoCursoRepository.findByIdAlumnoAndIdCurso(idAlumno, idCurso);

			if( alumnoCursoEntity == null ) {
				log.error( Constantes.NO_RECORDS_FOUND );
				return Util.getResponse(true, Constantes.NO_RECORD_FOUND, null);
			}

			AlumnoEntity alumnoEntity = alumnoRepository.findById( idAlumno ).orElse(null);

			if ( alumnoEntity == null ) {
				log.error( Constantes.NO_RECORD_FOUND + " | Alumno ID: " + idAlumno );
				return Util.getResponse(true, Constantes.NO_RECORD_FOUND + " | Alumno ID: " + idAlumno , null);
			}

			ResponseDto cursoResponse = cursoClient.getCursoTrueById( idCurso );
			CursoDto cursoDto = mapper.convertValue( cursoResponse.getData(), CursoDto.class );
			
			if ( cursoResponse.getData() == null ) {
				log.error( Constantes.NO_RECORD_FOUND + " | Curso ID: " + idCurso );
				return Util.getResponse(true, Constantes.NO_RECORD_FOUND + " | Curso ID: " + idCurso , null);
			}

			AlumnoCursoDto alumnoCursoDto = AlumnoCursoDto.builder()
                .id( alumnoCursoEntity.getId()  )
				.idAlumno( alumnoEntity.getId() )
				.nombreAlumno( alumnoEntity.getNombres() + " " + alumnoEntity.getApellidos() )
				.sexoAlumno( alumnoEntity.getSexo() )
				.estadoAlumno( alumnoEntity.isEstado() )
				.idCurso( cursoDto.getId() )
				.nombreCurso( cursoDto.getDescripcion() )
				.build();
			
			return Util.getResponse(true, Constantes.OPERATION_SUCCESS, alumnoCursoDto);

		} catch ( RetryableException e ) {
			log.error( "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, e );
			return Util.getResponse(false, "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, null);
		
		} catch (Exception e) {
			log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}

	}



	@Override
	public ResponseDto getAlumnoCursosByAlumno( Long idAlumno ) {
		
		try {
			
			List<AlumnoCursoEntity> listAlumnoCursoEntity = alumnoCursoRepository.findAllByIdAlumno( idAlumno );

			if ( listAlumnoCursoEntity == null ) {
				return Util.getResponse( true, Constantes.NO_RECORDS_FOUND, null );
			}

			AlumnoEntity alumnoEntity = alumnoRepository.findById( idAlumno ).orElse(null);

			if ( alumnoEntity == null ) {
				log.error( Constantes.NO_RECORD_FOUND + " | Alumno ID: " + idAlumno );
				return Util.getResponse( true, Constantes.NO_RECORD_FOUND + " | Alumno ID: " + idAlumno, null );
			}


			List<CursoMinDto> listaCursosDtos = new ArrayList<CursoMinDto>();
			
			for ( AlumnoCursoEntity record : listAlumnoCursoEntity ) {
	
				ResponseDto responseDto = cursoClient.getCursoTrueById( record.getIdCurso() );
				CursoDto cursoDto = mapper.convertValue( responseDto.getData(), CursoDto.class );

				if ( cursoDto == null ) {
					log.error( Constantes.NO_RECORD_FOUND + " | Curso ID: " + record.getIdCurso() );
					continue;
				}

				listaCursosDtos.add(
					CursoMinDto.builder()
						.id( cursoDto.getId() )
						.descripcion( cursoDto.getDescripcion() )
						.build()
				);

			}

			if ( listaCursosDtos.size() == 0 ) {
				return Util.getResponse( true, Constantes.NO_RECORDS_FOUND + 
					": [ No hay cursos por mostrar para este alumno ]", null );
			}

			AlumnoCursosDto alumnoCursosDto = AlumnoCursosDto.builder()
				.idAlumno( alumnoEntity.getId() )
				.nombreAlumno( alumnoEntity.getNombres() + " " + alumnoEntity.getApellidos() )
				.sexoAlumno( alumnoEntity.getSexo() )
				.estadoAlumno( alumnoEntity.isEstado() )
				.cursos( listaCursosDtos )
				.build();

			return Util.getResponse( true, Constantes.OPERATION_SUCCESS, alumnoCursosDto );

		} catch ( RetryableException e ) {
			log.error( "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, e );
			return Util.getResponse(false, "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, null);
		
		} catch (Exception e) {
			log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}

	}



	@Override
	public ResponseDto createAlumnoCurso( AlumnoCursoSendDto alumnoCursoSendDto ) {

		try {

			Long idAlumno = alumnoCursoSendDto.getIdAlumno();
			Long idCurso = alumnoCursoSendDto.getIdCurso();

			if ( idAlumno == null | idCurso == null ) {
				log.error( Constantes.ATTRIBUTE_MISSING + " [idAlumno/idCurso]" );
				return Util.getResponse( false, Constantes.ATTRIBUTE_MISSING + " [idAlumno/idCurso]", null );
			}

			AlumnoCursoEntity alumnoCursoEntityFound = alumnoCursoRepository.findByIdAlumnoAndIdCurso( idAlumno, idCurso );

			if ( alumnoCursoEntityFound != null ) {
				log.error( Constantes.ALREADY_EXISTS );
				return Util.getResponse( false, Constantes.ALREADY_EXISTS, alumnoCursoEntityFound );
			}

			AlumnoEntity alumnoEntity = alumnoRepository.findById( idAlumno ).orElse(null);

			if ( alumnoEntity == null ) {
				log.error( Constantes.NO_RECORD_FOUND + " | Alumno ID: " + idAlumno );
				return Util.getResponse( false, Constantes.NO_RECORD_FOUND + " | Alumno ID: " + idAlumno , null );
			}

			ResponseDto cursoResponse = cursoClient.getCursoTrueById( idCurso );

			if ( cursoResponse.getData() == null ) {
				log.error( Constantes.NO_RECORD_FOUND + " | Curso ID: " + idCurso );
				return Util.getResponse( false, Constantes.NO_RECORD_FOUND + " | Curso ID: " + idCurso , null );
			}

			
			AlumnoCursoEntity alumnoCursoEntity = AlumnoCursoEntity.builder()
				.idAlumno( idAlumno )
				.idCurso( idCurso )
				.build();

			alumnoCursoRepository.save( alumnoCursoEntity );

			return Util.getResponse(true, Constantes.RECORD_CREATED, alumnoCursoEntity );

		} catch ( RetryableException e ) {
			log.error( "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, e );
			return Util.getResponse(false, "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, null);
		
		} catch (Exception e) {
			log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}

	}



	@Override
	public ResponseDto updateAlumnoCurso( AlumnoCursoSendDto alumnoCursoSendDto ) {
		
		try {

			Long id = alumnoCursoSendDto.getId();
			Long idAlumno = alumnoCursoSendDto.getIdAlumno();
			Long idCurso = alumnoCursoSendDto.getIdCurso();

			if ( idAlumno == null | idCurso == null ) {
				log.error( Constantes.ATTRIBUTE_MISSING + " [id/idAlumno/idCurso]" );
				return Util.getResponse( false, Constantes.ATTRIBUTE_MISSING + " [id/idAlumno/idCurso]", null );
			}

			AlumnoCursoEntity alumnoCursoEntityFound = alumnoCursoRepository.findById( id ).orElse(null);

			if ( alumnoCursoEntityFound == null ) {
				log.error( Constantes.NO_RECORD_FOUND );
				return Util.getResponse( false, Constantes.NO_RECORD_FOUND, null );
			}

			AlumnoCursoEntity alumnoCursoEntityDuplicated = alumnoCursoRepository.findByIdAlumnoAndIdCurso(idAlumno, idCurso);

			if( alumnoCursoEntityDuplicated != null ) {
				log.error( Constantes.ALREADY_EXISTS );
				return Util.getResponse(true, Constantes.ALREADY_EXISTS, alumnoCursoEntityDuplicated);
			}

			AlumnoEntity alumnoEntity = alumnoRepository.findById( idAlumno ).orElse(null);

			if ( alumnoEntity == null ) {
				log.error( Constantes.NO_RECORD_FOUND + " | Alumno ID: " + idAlumno );
				return Util.getResponse( false, Constantes.NO_RECORD_FOUND + " | Alumno ID: " + idAlumno , null );
			}

			ResponseDto cursoResponse = cursoClient.getCursoTrueById( idCurso );

			if ( cursoResponse.getData() == null ) {
				log.error( Constantes.NO_RECORD_FOUND + " | Curso ID: " + idCurso );
				return Util.getResponse( false, Constantes.NO_RECORD_FOUND + " | Curso ID: " + idCurso , null );
			}

			alumnoCursoEntityFound.setIdAlumno( idAlumno );
			alumnoCursoEntityFound.setIdCurso( idCurso );

			alumnoCursoRepository.save( alumnoCursoEntityFound );

			return Util.getResponse(true, Constantes.RECORD_UPDATED, alumnoCursoEntityFound );
			
		} catch ( RetryableException e ) {
			log.error( "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, e );
			return Util.getResponse(false, "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, null);
		
		} catch (Exception e) {
			log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}
	}



	@Override
	public ResponseDto deleteAlumnoCursoById( Long id ) {

		try {

			if ( !alumnoCursoRepository.existsById( id ) ) {
				log.error( Constantes.NO_RECORD_FOUND );
				return Util.getResponse( false, Constantes.NO_RECORD_FOUND, null );
			}

			alumnoCursoRepository.deleteById( id );
			return Util.getResponse(true, Constantes.RECORD_DELETED, null );
			
		} catch (Exception e) {
			log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}

	};



	@Override
	public ResponseDto deleteAlumnoCursoByAlumnoAndCurso( Long idAlumno, Long idCurso ) {

		try {

			AlumnoCursoEntity alumnoCursoEntityFound = alumnoCursoRepository.findByIdAlumnoAndIdCurso(idAlumno, idCurso);

			if( alumnoCursoEntityFound == null ) {
				log.error( Constantes.NO_RECORD_FOUND );
				return Util.getResponse( false, Constantes.NO_RECORD_FOUND, null );
			}

			alumnoCursoRepository.deleteById( alumnoCursoEntityFound.getId() );
			return Util.getResponse(true, Constantes.RECORD_DELETED, null );
			
		} catch (Exception e) {
			log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}

	};

}
