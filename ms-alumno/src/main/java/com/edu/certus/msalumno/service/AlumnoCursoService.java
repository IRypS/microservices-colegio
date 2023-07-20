package com.edu.certus.msalumno.service;

import com.edu.certus.msalumno.dto.AlumnoCursoSendDto;
import com.edu.certus.msalumno.dto.ResponseDto;

public interface AlumnoCursoService {
	

	/**
	 * Obtiene un listado de objetos <b>AlumnoCursoDto</b> (alumno - curso)
	 * <b>(Tanto cursos como alumnos deben estar en un estado visible)</b>
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y listado de objetos <b>AlumnoCursoDto</b> si se encuentran datos. <br><br>
     *         - Código exitoso si no se encuentran registros. <br><br>
     *         - Código fallido en caso de error.
	 */
	public ResponseDto getAllAlumnoCurso();



	/**
	 * Obtiene un listado de objetos <b>AlumnoCursosDto</b> (alumno - listado de cursos)
	 * <b>(Tanto cursos como alumnos deben estar en un estado visible)</b>
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y listado de objetos <b>AlumnoCursosDto</b> si se encuentran datos. <br><br>
     *         - Código exitoso si no se encuentran registros. <br><br>
     *         - Código fallido en caso de error.
	 */
	public ResponseDto getAllAlumnoCursos();



	/**
	 * Obtiene un registro <b>AlumnoCursoDto</b> (alumno - curso) a partir de un ID
	 * @param id ID del registro a buscar
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y registro encontrado a partir del ID a buscar <br><br>
     *         - Código exitoso si no se encuentra el registro, los datos del alumno o los datos del curso. <br><br>
     *         - Código fallido en caso de error.
	 */
	public ResponseDto getAlumnoCurso( Long id );



	/**
	 * Obtiene un registro <b>AlumnoCursoDto</b> (alumno - curso) a partir de un ID de alumno y un ID de curso
	 * @param idAlumno ID del alumno a buscar en un registro
	 * @param idCurso ID del curso a buscar junto con el ID del alumno
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y registro encontrado a partir del ID alumno e ID curso. <br><br>
     *         - Código exitoso si no se encuentra el registro, los datos del alumno o los datos del curso. <br><br>
     *         - Código fallido en caso de error.
	 */
	public ResponseDto getAlumnoCursoByAlumnoAndCurso( Long idAlumno, Long idCurso );



	/**
	 * Obtiene un objeto <b>AlumnoCursosDto</b> (alumno - listado de cursos) a partir de un ID de alumno
	 * @param idAlumno ID del alumno para buscar los registros pertenecientes
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y registro encontrado si se encuentran cursos para el alumno <br><br>
     *         - Código exitoso si no se encuentra el registro, los datos del alumno o los datos de ningún curso. <br><br>
     *         - Código fallido en caso de error.
	 */
	public ResponseDto getAlumnoCursosByAlumno( Long idAlumno );



	/**
	 * Crea un nuevo objeto <b>AlumnoCursoSendDto</b> (alumno - curso) 
	 * @param alumnoCursoSendDto Objeto <b>AlumnoCursoSendDto</b> con el ID del alumno y el ID del curso a crear
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y registro creado. <br><br>
     *         - Código fallido si no se colocan ambos IDs. <br><br>
     *         - Código fallido si se encuentra un registro ya creado con ambos IDs. <br><br>
     *         - Código fallido si no se colocan IDs de un alumno y curso válido. <br><br>
     *         - Código fallido en caso de error. 
	 */
	public ResponseDto createAlumnoCurso( AlumnoCursoSendDto alumnoCursoSendDto );



	/**
	 * Actualiza un objeto <b>AlumnoCursoSendDto</b> (alumno - curso)  
	 * @param alumnoCursoSendDto Objeto <b>AlumnoCursoSendDto</b> con el ID del registro, ID del alumno y el ID del curso a actualizar
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y registro actualizado. <br><br>
     *         - Código fallido si no se encuentran un registro con el ID ingresado. <br><br>
	 * 		   - Código fallido si no se colocan ambos IDs. <br><br>
     *         - Código fallido en caso de error.
	 */
	public ResponseDto updateAlumnoCurso( AlumnoCursoSendDto alumnoCursoSendDto );



	/**
	 * Borra un objeto <b>AlumnoCursoDto</b> (alumno - curso) a partir de un ID
	 * @param id ID del registro a borrar
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y curso eliminado del alumno. <br><br>
     *         - Código si no encuentra el registro que se prentede borrar. <br><br>
     *         - Código fallido en caso de error.
	 */
	public ResponseDto deleteAlumnoCursoById( Long id );



	/**
	 * Borra un objeto <b>AlumnoCursoDto</b> (alumno - curso) a partir de IDs alumno y curso
	 * @param idAlumno ID del alumno a borrar en un registro
	 * @param idCurso ID del curso a borrar junto con el ID del alumno
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y curso eliminado del alumno. <br><br>
     *         - Código si no encuentra el registro que se prentede borrar. <br><br>
     *         - Código fallido en caso de error.
	 */
	public ResponseDto deleteAlumnoCursoByAlumnoAndCurso( Long idAlumno, Long idCurso );


}
