package com.edu.certus.msalumno.service;

import com.edu.certus.msalumno.dto.ResponseDto;

public interface AlumnoCursoProfesorService {
    
    /**
	 * Obtiene un listado de objetos <b>AlumnoCursoProfesorDto</b> (alumno - curso - profesor)
	 * <b>(Alumnos, profesores y cursos deben estar en un estado visible)</b>
	 * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y listado de objetos <b>AlumnoCursoProfesorDto</b> si se encuentran datos. <br><br>
     *         - Código exitoso si no se encuentran registros. <br><br>
     *         - Código fallido en caso de error.
	 */
    public ResponseDto getAllAlumnoCursoProfesor();

    
}
