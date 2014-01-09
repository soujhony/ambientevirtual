package br.edu.senai.ambientevirtual.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.AlunoBC;
import br.edu.senai.ambientevirtual.business.UsuarioBC;
import br.edu.senai.ambientevirtual.domain.Aluno;
import br.edu.senai.ambientevirtual.domain.Sexo;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./aluno_list.jsf")
public class AlunoEditMB extends AbstractEditPageBean<Aluno, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoBC alunoBC;

	@Inject
	private UsuarioBC usuarioBC;
	
	@Inject
	private FacesContext facesContext;
	
	private List<SelectItem> sexos;
	
	public AlunoEditMB() {
		sexos = new ArrayList<SelectItem>();
		sexos.add(new SelectItem(Sexo.FEMININO, "Feminino"));
		sexos.add(new SelectItem(Sexo.MASCULINO, "Masculino"));
	}
	
	@Override
	@Transactional
	public String delete() {
		this.alunoBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		getBean().getUsuario().setTipoUsu("alu");
		this.alunoBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		getBean().getUsuario().setTipoUsu("alu");
		this.alunoBC.update(getBean());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.alunoBC.load(getId()));
	}

	@Transactional
	public void checaLogin() {
		if (usuarioBC.existeLogin(this.getBean().getUsuario().getLogin())) {
			facesContext.addMessage("login", new FacesMessage(
					FacesMessage.SEVERITY_WARN, "Login já exitente. Tente um login diferente.", null));
		} else {
			facesContext.addMessage("login", new FacesMessage("OK"));
		}
	}
	
	public List<SelectItem> getSexos() {
		return sexos;
	}

	public void setSexos(List<SelectItem> sexos) {
		this.sexos = sexos;
	}

	
}