package com.edu.certus.msalumno.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "alumno_curso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoCursoEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_alumno_curso" )
    private Long id;
    
    @Column( name = "cod_alumno" )
    private Long idAlumno;
    
    @Column( name = "cod_curso" )
    private Long idCurso;

}
