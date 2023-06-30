package com.edu.certus.msalumno.service;

import com.edu.certus.msalumno.dto.AlumnoDto;
import com.edu.certus.msalumno.dto.ResponseDto;

public interface AlumnoService {


    /**
     * Obtiene un listado de todos los alumnos existentes
     * @param onlyTrue Valor <b>true</b> si se buscarán sólo los registros visibles
     * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y listado de alumnos si se encuentran datos. <br><br>
     *         - Código exitoso si no se encuentran alumnos. <br><br>
     *         - Código fallido en caso de error.
     */
    public ResponseDto getAllAlumno( boolean onlyTrue );



    /**
     * Obtiene un alumno a partir de un ID proporcionado
     * @param idAlumno ID del alumno a buscar
     * @param onlyTrue Valor <b>true</b> si se buscará el id sólo en los registros visibles
     * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso e información del alumno si se encuentra el ID solicitado. <br><br>
     *         - Código exitoso si no se encuentran el alumno solicitado. <br><br>
     *         - Código fallido en caso de error.
     */
    public ResponseDto getAlumnoById( Long idAlumno, boolean onlyTrue );



    /**
     * Crea un alumno a partir de un Objeto <b>AlumnoDto</b>
     * @param alumnoDto Objeto <b>AlumnoDto</b> con los datos a crear 
     * <b>(El objeto a crear no debe estar visible y registrado previamente)</b>
     * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y alumno creado si se registra exitósamente. <br><br>
     *         - Código fallido y alumno repetido si se encuentra un alumno ya registrado. <br><br>
     *         - Código fallido en caso de error.
     */
    public ResponseDto createAlumno( AlumnoDto alumnoDto );



    /**
     * Actualiza un alumno a partir de un Objeto <b>AlumnoDto</b>
     * @param alumnoDto Objeto <b>AlumnoDto</b> con el ID y los datos a actualizar. 
     * <b>(No actualiza el estado)</b>
     * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso y alumno si se actualiza exitósamente. <br><br>
     *         - Código fallido si no se encuentra un alumno para actualizar. <br><br>
     *         - Código fallido en caso de error.
     */
    public ResponseDto updateAlumno( AlumnoDto alumnoDto );



    /**
     * Borra lógicamente un alumno visible
     * @param idAlumno ID de un alumno existente a borrar
     * @return Objeto <b>ResponseDto</b> con los siguientes resultados: <br><br>
     *         - Código exitoso si el alumno se borra exitósamente. <br><br>
     *         - Código fallido si no se encuentra un alumno para borrar. <br><br>
     *         - Código fallido en caso de error.
     */
    public ResponseDto deleteAlumnoById( Long idAlumno );

}
