/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Model de turmas.
* 
* Criado por - William Chenta
*/ 
package br.edu.senai.ambientevirtual.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "TB_TURMAS")
@TableGenerator(name = "TurmaGen", table = "TB_SEQUENCIAS", pkColumnName = "NM_CHAVE", pkColumnValue = "TURMASEQ", valueColumnName = "DS_VALOR")
public class Turma {

	@Id
	@Column(name="ID_TURMA")
	@GeneratedValue(generator="TurmaGen", strategy=GenerationType.TABLE)
	private Long id;
	@Column(name="DS_CODIGO", length=50, nullable=false)
	private String codigo;
	@Column(name="NM_CURSO", length=50, nullable=false)
	private String curso;
	@Column(name="NR_SEMESTRE", nullable=false)
	private int semestre;
	@ManyToMany
	@JoinTable(name="TB_ALUNOS_TB_TURMAS", 
		joinColumns={@JoinColumn(referencedColumnName="ID_TURMA")}, 
		inverseJoinColumns={@JoinColumn(referencedColumnName="ID_ALUNO")})
	private List<Aluno> alunos;
	@ManyToMany
	@JoinTable(name="TB_TUTORES_TB_TURMAS", 
		joinColumns={@JoinColumn(referencedColumnName="ID_TURMA")}, 
		inverseJoinColumns={@JoinColumn(referencedColumnName="ID_TUTOR")})
	private List<Tutor> tutores;
	@OneToMany(mappedBy="turma")
	private List<Grupo> grupos;
	
	public Turma() {
		tutores = new ArrayList<Tutor>();
		alunos = new ArrayList<Aluno>();
		grupos = new ArrayList<Grupo>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}
	
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public List<Tutor> getTutores() {
		return tutores;
	}
	
	public void setTutores(List<Tutor> tutores) {
		this.tutores = tutores;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
}
