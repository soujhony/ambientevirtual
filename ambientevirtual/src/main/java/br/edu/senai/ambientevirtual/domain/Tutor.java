/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Model de tutores.
* 
* Criado por - William Chenta
*/ 
package br.edu.senai.ambientevirtual.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "TB_TUTORES")
@TableGenerator(name = "TutorGen", table = "TB_SEQUENCIAS", pkColumnName = "NM_CHAVE", pkColumnValue = "TUTORSEQ", valueColumnName = "DS_VALOR")
public class Tutor {

	@Id
	@Column(name="ID_TUTOR")
	@GeneratedValue(generator="TutorGen", strategy=GenerationType.TABLE)
	private Long id;
	@Column(name="NM_NUCLEO", length=100, nullable=false)
	private String nucleo;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_USUARIO", nullable=false)
	private Usuario usuario;
	@ManyToMany(mappedBy="tutores")
	private List<Turma> turmas;

	public Tutor() {
		this.usuario = new Usuario();
		this.usuario.setTipoUsu("tut");
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNucleo() {
		return nucleo;
	}

	public void setNucleo(String nucleo) {
		this.nucleo = nucleo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}	

}
