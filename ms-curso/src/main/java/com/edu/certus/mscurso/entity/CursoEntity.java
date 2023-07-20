package com.edu.certus.mscurso.entity;

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
@Table( name = "curso" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoEntity {

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id_curso" )
    private Long id;

    @Column( name = "descripcion" )
    private String descripcion;

    @Column( name = "estado" )
    private boolean estado;
    
}
