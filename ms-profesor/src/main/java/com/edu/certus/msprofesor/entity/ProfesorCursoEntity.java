package com.edu.certus.msprofesor.entity;

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
@Table(name = "curso_profesor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorCursoEntity {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_profesor_curso" )
    private Long id;
    
    @Column( name = "codProfesor" )
    private Long idProfesor;
    
    @Column( name = "cod_curso" )
    private Long idCurso;

}

