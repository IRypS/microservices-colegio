package com.edu.certus.msprofesor.service;

import com.edu.certus.msprofesor.dto.ProfesorCursoSendDto;
import com.edu.certus.msprofesor.dto.ResponseDto;

public interface ProfesorCursoService {
	
	/**
	 * Obtiene un listado de objetos <b>ProfesorCursoDto</b> (profesor - curso)
	 * <b>(Tanto cursos como profesores deben estar en un estado visible)</b>
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y listado de objetos <b>ProfesorCursoDto</b> si se encuentran datos. <br><br>
     *         - Código exitoso si no se encuentran registros. <br><br>
     *         - Código fallido en caso de error.
	 */
	public ResponseDto getAllProfesorCurso();



	/**
	 * Obtiene un listado de objetos <b>ProfesorCursosDto</b> (profesor - listado de cursos)
	 * <b>(Tanto cursos como profesores deben estar en un estado visible)</b>
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y listado de objetos <b>ProfesorCursosDto</b> si se encuentran datos. <br><br>
     *         - Código exitoso si no se encuentran registros. <br><br>
     *         - Código fallido en caso de error.
	 */
	public ResponseDto getAllProfesorCursos();



	/**
	 * Obtiene un registro <b>ProfesorCursoDto</b> (profesor - curso) a partir de un ID
	 * @param id ID del registro a buscar
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y registro encontrado a partir del ID a buscar <br><br>
     *         - Código exitoso si no se encuentra el registro, los datos del profesor o los datos del curso. <br><br>
     *         - Código fallido en caso de error.
	 */
	public ResponseDto getProfesorCursoById( Long id );



	/**
	 * Obtiene un registro <b>ProfesorCursoDto</b> (profesor - curso) a partir de un ID de profesor y un ID de curso
	 * @param idProfesor ID del profesor a buscar en un registro
	 * @param idCurso ID del curso a buscar junto con el ID del profesor
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y registro encontrado a partir del ID profesor e ID curso. <br><br>
     *         - Código exitoso si no se encuentra el registro, los datos del profesor o los datos del curso. <br><br>
     *         - Código fallido en caso de error.
	 */
	public ResponseDto getProfesorCursoByProfesorAndCurso( Long idProfesor, Long idCurso );



	/**
	 * Obtiene un objeto <b>ProfesorCursosDto</b> (profesor - listado de cursos) a partir de un ID de profesor
	 * @param idProfesor ID del profesor para buscar los registros pertenecientes
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y registro encontrado si se encuentran cursos para el profesor <br><br>
     *         - Código exitoso si no se encuentra el registro, los datos del profesor o los datos de ningún curso. <br><br>
     *         - Código fallido en caso de error.
	 */
	public ResponseDto getProfesorCursosByProfesor( Long idProfesor );



	/**
	 * Crea un nuevo objeto <b>ProfesorCursoSendDto</b> (profesor - curso) 
	 * @param profesorCursoSendDto Objeto <b>ProfesorCursoSendDto</b> con el ID del profesor y el ID del curso a crear
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y registro creado. <br><br>
     *         - Código fallido si no se colocan ambos IDs. <br><br>
     *         - Código fallido si se encuentra un registro ya creado con ambos IDs. <br><br>
     *         - Código fallido si no se colocan IDs de un profesor y curso válido. <br><br>
     *         - Código fallido en caso de error. 
	 */
	public ResponseDto createProfesorCurso( ProfesorCursoSendDto profesorCursoSendDto );



	/**
	 * Actualiza un objeto <b>ProfesorCursoSendDto</b> (profesor - curso)  
	 * @param profesorCursoSendDto Objeto <b>ProfesorCursoSendDto</b> con el ID del registro, ID del profesor y el ID del curso a actualizar
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y registro actualizado. <br><br>
     *         - Código fallido si no se encuentran un registro con el ID de registro ingresado. <br><br>
	 * 		   - Código fallido si no se colocan ambos IDs. <br><br>
     *         - Código fallido en caso de error.
	 */
	public ResponseDto updateProfesorCurso( ProfesorCursoSendDto profesorCursoSendDto );



	/**
	 * Borra un objeto <b>ProfesorCursoDto</b> (profesor - curso) a partir de un ID
	 * @param id ID del registro a borrar
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y curso eliminado del profesor. <br><br>
     *         - Código si no encuentra el registro que se prentede borrar. <br><br>
     *         - Código fallido en caso de error.
	 */
	public ResponseDto deleteProfesorCursoById( Long id );



	/**
	 * Borra un objeto <b>ProfesorCursoDto</b> (profesor - curso) a partir de IDs profesor y curso
	 * @param idProfesor ID del profesor a borrar en un registro
	 * @param idCurso ID del curso a borrar junto con el ID del profesor
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y curso eliminado del profesor. <br><br>
     *         - Código si no encuentra el registro que se prentede borrar. <br><br>
     *         - Código fallido en caso de error.
	 */
	public ResponseDto deleteProfesorCursoByProfesorAndCurso( Long idProfesor, Long idCurso );

}
