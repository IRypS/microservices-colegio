package com.edu.certus.msprofesor.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.certus.msprofesor.client.CursoClient;
import com.edu.certus.msprofesor.dto.CursoDto;
import com.edu.certus.msprofesor.dto.CursoMinDto;
import com.edu.certus.msprofesor.dto.ProfesorCursoDto;
import com.edu.certus.msprofesor.dto.ProfesorCursoSendDto;
import com.edu.certus.msprofesor.dto.ProfesorCursosDto;
import com.edu.certus.msprofesor.dto.ResponseDto;
import com.edu.certus.msprofesor.entity.ProfesorCursoEntity;
import com.edu.certus.msprofesor.entity.ProfesorEntity;
import com.edu.certus.msprofesor.repository.ProfesorCursoRepository;
import com.edu.certus.msprofesor.repository.ProfesorRepository;
import com.edu.certus.msprofesor.service.ProfesorCursoService;
import com.edu.certus.msprofesor.util.Constantes;
import com.edu.certus.msprofesor.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProfesorCursoServiceImpl implements ProfesorCursoService {
    


    @Autowired
    private ProfesorCursoRepository profesorCursoRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private CursoClient cursoClient;

    @Autowired
    private ObjectMapper mapper;



    @Override
    public ResponseDto getAllProfesorCurso() {

        try {

			List<ProfesorCursoEntity> listProfesorCursoEntity = profesorCursoRepository.findAll();

			if ( listProfesorCursoEntity == null ) {
				return Util.getResponse( true, Constantes.NO_RECORDS_FOUND, null );
			}

			List<ProfesorCursoDto> listProfesorCurso = new ArrayList<ProfesorCursoDto>();
			
			for ( ProfesorCursoEntity record : listProfesorCursoEntity ) {

				ProfesorEntity profesorEntity = profesorRepository.findById( record.getIdProfesor() ).orElse(null);

				if ( profesorEntity == null ) {
					log.error( Constantes.NO_RECORD_FOUND + " | Profesor ID: " + record.getIdProfesor() );
					continue;
				}
	
				ResponseDto responseDto = cursoClient.getCursoTrueById( record.getIdCurso() );
				CursoDto cursoDto = mapper.convertValue( responseDto.getData(), CursoDto.class );

				if ( cursoDto == null ) {
					log.error( Constantes.NO_RECORD_FOUND + " | Curso ID: " + record.getIdCurso() );
					continue;
				}

				listProfesorCurso.add(
					ProfesorCursoDto.builder()
                        .id( record.getId() )
						.idProfesor( profesorEntity.getId() )
						.nombreProfesor( profesorEntity.getNombres() + " " + profesorEntity.getApellidos() )
						.sexoProfesor( profesorEntity.getSexo() )
						.estadoProfesor( profesorEntity.isEstado() )
						.idCurso( cursoDto.getId() )
						.nombreCurso( cursoDto.getDescripcion() )
						.build()
				);

			}

			return Util.getResponse( true, Constantes.OPERATION_SUCCESS, listProfesorCurso );

		} catch ( RetryableException e ) {
			log.error( "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, e );
			return Util.getResponse(false, "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, null);
		
		} catch ( Exception e ) {
			log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}

    };



    @Override
    public ResponseDto getAllProfesorCursos() {

        try {
			
			List<ProfesorCursoEntity> listProfesorCursoEntity = profesorCursoRepository.findAll();

			if ( listProfesorCursoEntity == null ) {
				return Util.getResponse( true, Constantes.NO_RECORDS_FOUND, null );
			}


			Map<Long, List<ProfesorCursoEntity>> cursosPorProfesor = listProfesorCursoEntity.stream()
				.collect( Collectors.groupingBy( record -> record.getIdProfesor() ) );


			List<ProfesorCursosDto> listProfesorCursos = new ArrayList<ProfesorCursosDto>();

			for ( Map.Entry<Long, List<ProfesorCursoEntity>> entry : cursosPorProfesor.entrySet() ) {

				Long idProfesor = entry.getKey();
				List<ProfesorCursoEntity> listaCursos = entry.getValue();

				ProfesorEntity profesorEntity = profesorRepository.findById( idProfesor ).orElse(null);

				if ( profesorEntity == null ) {
					log.error( Constantes.NO_RECORD_FOUND + " | Profesor ID: " + idProfesor );
					continue;
				}

				List<CursoMinDto> listaCursosDtos = new ArrayList<>();
				
				for ( ProfesorCursoEntity record : listaCursos ) {

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
						": [ No hay cursos por mostrar para el profesor con ID " + idProfesor + "]" );
					continue;
				};

				listProfesorCursos.add(
					ProfesorCursosDto.builder()
						.idProfesor( profesorEntity.getId() )
						.nombreProfesor( profesorEntity.getNombres() + " " + profesorEntity.getApellidos() )
						.sexoProfesor( profesorEntity.getSexo() )
						.estadoProfesor( profesorEntity.isEstado() )
						.cursos( listaCursosDtos )
						.build()
				);

			}

			return Util.getResponse( true, Constantes.OPERATION_SUCCESS, listProfesorCursos );

		} catch ( RetryableException e ) {
			log.error( "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, e );
			return Util.getResponse(false, "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, null);
		
		} catch ( Exception e ) {
			log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}

    }



    @Override
    public ResponseDto getProfesorCursoById( Long id ) {

        try {

			ProfesorCursoEntity profesorCursoEntity = profesorCursoRepository.findById(id).orElse(null);

			if( profesorCursoEntity == null ) {
				log.error( Constantes.NO_RECORDS_FOUND );
				return Util.getResponse(true, Constantes.NO_RECORD_FOUND, null);
			}
			
			ProfesorEntity profesorEntity = profesorRepository.findById( profesorCursoEntity.getIdProfesor() ).orElse(null);

			if ( profesorEntity == null ) {
				log.error( Constantes.NO_RECORD_FOUND + " | Profesor ID: " + profesorCursoEntity.getIdProfesor() );
				return Util.getResponse(true, Constantes.NO_RECORD_FOUND + " | Profesor ID: " + profesorCursoEntity.getIdProfesor() , null);
			}

			ResponseDto cursoResponse = cursoClient.getCursoTrueById( profesorCursoEntity.getIdCurso() );
			CursoDto cursoDto = mapper.convertValue( cursoResponse.getData(), CursoDto.class );
			
			if ( cursoResponse.getData() == null ) {
				log.error( Constantes.NO_RECORD_FOUND + " | Curso ID: " + profesorCursoEntity.getIdCurso() );
				return Util.getResponse(true, Constantes.NO_RECORD_FOUND + " | Curso ID: " + profesorCursoEntity.getIdCurso() , null);
			}

			ProfesorCursoDto profesorCursoDto = ProfesorCursoDto.builder()
                .id( id )
				.idProfesor( profesorEntity.getId() )
				.nombreProfesor( profesorEntity.getNombres() + " " + profesorEntity.getApellidos() )
				.sexoProfesor( profesorEntity.getSexo() )
				.estadoProfesor( profesorEntity.isEstado() )
				.idCurso( cursoDto.getId() )
				.nombreCurso( cursoDto.getDescripcion() )
				.build();
			
			return Util.getResponse(true, Constantes.OPERATION_SUCCESS, profesorCursoDto);

		} catch ( RetryableException e ) {
			log.error( "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, e );
			return Util.getResponse(false, "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, null);
		
		} catch ( Exception e ) {
			log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}

    };



	@Override
	public ResponseDto getProfesorCursoByProfesorAndCurso( Long idProfesor, Long idCurso ) {

		try {
			
			ProfesorCursoEntity profesorCursoEntity = profesorCursoRepository.findByIdProfesorAndIdCurso(idProfesor, idCurso);

			if( profesorCursoEntity == null ) {
				log.error( Constantes.NO_RECORDS_FOUND );
				return Util.getResponse(true, Constantes.NO_RECORD_FOUND, null);
			}

			ProfesorEntity profesorEntity = profesorRepository.findById( idProfesor ).orElse(null);

			if ( profesorEntity == null ) {
				log.error( Constantes.NO_RECORD_FOUND + " | Profesor ID: " + idProfesor );
				return Util.getResponse(true, Constantes.NO_RECORD_FOUND + " | Profesor ID: " + idProfesor , null);
			}

			ResponseDto cursoResponse = cursoClient.getCursoTrueById( idCurso );
			CursoDto cursoDto = mapper.convertValue( cursoResponse.getData(), CursoDto.class );
			
			if ( cursoResponse.getData() == null ) {
				log.error( Constantes.NO_RECORD_FOUND + " | Curso ID: " + idCurso );
				return Util.getResponse(true, Constantes.NO_RECORD_FOUND + " | Curso ID: " + idCurso , null);
			}

			ProfesorCursoDto profesorCursoDto = ProfesorCursoDto.builder()
                .id( profesorCursoEntity.getId()  )
				.idProfesor( profesorEntity.getId() )
				.nombreProfesor( profesorEntity.getNombres() + " " + profesorEntity.getApellidos() )
				.sexoProfesor( profesorEntity.getSexo() )
				.estadoProfesor( profesorEntity.isEstado() )
				.idCurso( cursoDto.getId() )
				.nombreCurso( cursoDto.getDescripcion() )
				.build();
			
			return Util.getResponse(true, Constantes.OPERATION_SUCCESS, profesorCursoDto);

		} catch ( RetryableException e ) {
			log.error( "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, e );
			return Util.getResponse(false, "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, null);
		
		} catch (Exception e) {
			log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}

	};



	@Override
	public ResponseDto getProfesorCursosByProfesor( Long idProfesor ) {

		try {
			
			List<ProfesorCursoEntity> listProfesorCursoEntity = profesorCursoRepository.findAllByIdProfesor( idProfesor );

			if ( listProfesorCursoEntity == null ) {
				return Util.getResponse( true, Constantes.NO_RECORDS_FOUND, null );
			}

			ProfesorEntity profesorEntity = profesorRepository.findById( idProfesor ).orElse(null);

			if ( profesorEntity == null ) {
				log.error( Constantes.NO_RECORD_FOUND + " | Profesor ID: " + profesorEntity );
				return Util.getResponse( true, Constantes.NO_RECORD_FOUND + " | Profesor ID: " + profesorEntity, null );
			}


			List<CursoMinDto> listaCursosDtos = new ArrayList<CursoMinDto>();
			
			for ( ProfesorCursoEntity record : listProfesorCursoEntity ) {
	
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
					": [ No hay cursos por mostrar para este profesor ]", null );
			}

			ProfesorCursosDto profesorCursosDto = ProfesorCursosDto.builder()
				.idProfesor( profesorEntity.getId() )
				.nombreProfesor( profesorEntity.getNombres() + " " + profesorEntity.getApellidos() )
				.sexoProfesor( profesorEntity.getSexo() )
				.estadoProfesor( profesorEntity.isEstado() )
				.cursos( listaCursosDtos )
				.build();

			return Util.getResponse( true, Constantes.OPERATION_SUCCESS, profesorCursosDto );

		} catch ( RetryableException e ) {
			log.error( "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, e );
			return Util.getResponse(false, "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, null);
		
		} catch (Exception e) {
			log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}

	};



	@Override
	public ResponseDto createProfesorCurso( ProfesorCursoSendDto profesorCursoSendDto ) {
		
		try {

			Long idProfesor = profesorCursoSendDto.getIdProfesor();
			Long idCurso = profesorCursoSendDto.getIdCurso();

			if ( idProfesor == null | idCurso == null ) {
				log.error( Constantes.ATTRIBUTE_MISSING + " [idProfesor/idCurso]" );
				return Util.getResponse( false, Constantes.ATTRIBUTE_MISSING + " [idProfesor/idCurso]", null );
			}

			ProfesorCursoEntity profesorCursoEntityFound = profesorCursoRepository.findByIdProfesorAndIdCurso( idProfesor, idCurso );

			if ( profesorCursoEntityFound != null ) {
				log.error( Constantes.ALREADY_EXISTS );
				return Util.getResponse( false, Constantes.ALREADY_EXISTS, profesorCursoEntityFound );
			}

			ProfesorEntity profesorEntity = profesorRepository.findById( idProfesor ).orElse(null);

			if ( profesorEntity == null ) {
				log.error( Constantes.NO_RECORD_FOUND + " | Profesor ID: " + idProfesor );
				return Util.getResponse( false, Constantes.NO_RECORD_FOUND + " | Profesor ID: " + idProfesor , null );
			}

			ResponseDto cursoResponse = cursoClient.getCursoTrueById( idCurso );

			if ( cursoResponse.getData() == null ) {
				log.error( Constantes.NO_RECORD_FOUND + " | Curso ID: " + idCurso );
				return Util.getResponse( false, Constantes.NO_RECORD_FOUND + " | Curso ID: " + idCurso , null );
			}

			
			ProfesorCursoEntity profesorCursoEntity = ProfesorCursoEntity.builder()
				.idProfesor( idProfesor )
				.idCurso( idCurso )
				.build();

			profesorCursoRepository.save( profesorCursoEntity );

			return Util.getResponse(true, Constantes.RECORD_CREATED, profesorCursoEntity );

		} catch ( RetryableException e ) {
			log.error( "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, e );
			return Util.getResponse(false, "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, null);
		
		} catch (Exception e) {
			log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}

	};



	@Override
	public ResponseDto updateProfesorCurso( ProfesorCursoSendDto profesorCursoSendDto ) {

		try {

			Long id = profesorCursoSendDto.getId();
			Long idProfesor = profesorCursoSendDto.getIdProfesor();
			Long idCurso = profesorCursoSendDto.getIdCurso();

			if ( id == null | idProfesor == null | idCurso == null ) {
				log.error( Constantes.ATTRIBUTE_MISSING + " [id/idProfesor/idCurso]" );
				return Util.getResponse( false, Constantes.ATTRIBUTE_MISSING + " [id/idProfesor/idCurso]", null );
			}

			ProfesorCursoEntity profesorCursoEntityFound = profesorCursoRepository.findById( id ).orElse(null);

			if ( profesorCursoEntityFound == null ) {
				log.error( Constantes.NO_RECORD_FOUND );
				return Util.getResponse( false, Constantes.NO_RECORD_FOUND, null );
			}

			ProfesorCursoEntity profesorCursoEntityDuplicated = profesorCursoRepository.findByIdProfesorAndIdCurso(idProfesor, idCurso);

			if( profesorCursoEntityDuplicated != null ) {
				log.error( Constantes.ALREADY_EXISTS );
				return Util.getResponse(true, Constantes.ALREADY_EXISTS, profesorCursoEntityDuplicated);
			}

			ProfesorEntity profesorEntity = profesorRepository.findById( idProfesor ).orElse(null);

			if ( profesorEntity == null ) {
				log.error( Constantes.NO_RECORD_FOUND + " | Profesor ID: " + idProfesor );
				return Util.getResponse( false, Constantes.NO_RECORD_FOUND + " | Profesor ID: " + idProfesor , null );
			}

			ResponseDto cursoResponse = cursoClient.getCursoTrueById( idCurso );

			if ( cursoResponse.getData() == null ) {
				log.error( Constantes.NO_RECORD_FOUND + " | Curso ID: " + idCurso );
				return Util.getResponse( false, Constantes.NO_RECORD_FOUND + " | Curso ID: " + idCurso , null );
			}

			profesorCursoEntityFound.setIdProfesor( idProfesor );
			profesorCursoEntityFound.setIdCurso( idCurso );

			profesorCursoRepository.save( profesorCursoEntityFound );

			return Util.getResponse(true, Constantes.RECORD_UPDATED, profesorCursoEntityFound );
			
		} catch ( RetryableException e ) {
			log.error( "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, e );
			return Util.getResponse(false, "[ms-curso] " + Constantes.NO_SERVICE_AVAILABLE, null);
		
		} catch (Exception e) {
			log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}

	};



	@Override
	public ResponseDto deleteProfesorCursoById( Long id ) {

		try {

			if ( !profesorCursoRepository.existsById( id ) ) {
				log.error( Constantes.NO_RECORD_FOUND );
				return Util.getResponse( false, Constantes.NO_RECORD_FOUND, null );
			}

			profesorCursoRepository.deleteById( id );
			return Util.getResponse(true, Constantes.RECORD_DELETED, null );
			
		} catch (Exception e) {
			log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}

	};



	@Override
	public ResponseDto deleteProfesorCursoByProfesorAndCurso( Long idProfesor, Long idCurso ) {

		try {

			ProfesorCursoEntity profesorCursoEntityFound = profesorCursoRepository.findByIdProfesorAndIdCurso(idProfesor, idCurso);

			if( profesorCursoEntityFound == null ) {
				log.error( Constantes.NO_RECORD_FOUND );
				return Util.getResponse( false, Constantes.NO_RECORD_FOUND, null );
			}

			profesorCursoRepository.deleteById( profesorCursoEntityFound.getId() );
			return Util.getResponse(true, Constantes.RECORD_DELETED, null );
			
		} catch (Exception e) {
			log.error( Constantes.OPERATION_FAILED, e );
			return Util.getResponse(false, Constantes.OPERATION_FAILED, null);
		}

	};

}
