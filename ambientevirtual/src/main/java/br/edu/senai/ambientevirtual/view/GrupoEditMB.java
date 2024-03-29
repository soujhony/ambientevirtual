/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Bean de grupos
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.view;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.GrupoBC;
import br.edu.senai.ambientevirtual.business.TurmaBC;
import br.edu.senai.ambientevirtual.business.TutorBC;
import br.edu.senai.ambientevirtual.domain.Grupo;
import br.edu.senai.ambientevirtual.domain.Turma;
import br.edu.senai.ambientevirtual.domain.Tutor;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./grupo_list.jsf")
@RequiredRole("tut")
public class GrupoEditMB extends AbstractEditPageBean<Grupo, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoBC grupoBC;

	@Inject
	private TutorBC tutorBC;
	
	@Inject
	private TurmaBC turmaBC;
	
	@Inject
	private SecurityContext securityContext;
	
	public List<Turma> getTurmas() {
		return turmaBC.filtrarQuery("", new HashMap<String, String>());
	}
	
	@Override
	@Transactional
	public String delete() {
		this.grupoBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		Long id = Long.valueOf(securityContext.getUser().getId());
		Tutor tutor = tutorBC.loadTutor(id);
		getBean().setTutor(tutor);
		this.grupoBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		Long id = Long.valueOf(securityContext.getUser().getId());
		Tutor tutor = tutorBC.loadTutor(id);
		getBean().setTutor(tutor);
		this.grupoBC.update(getBean());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.grupoBC.load(getId()));
	}

}