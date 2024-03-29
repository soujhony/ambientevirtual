/*
* Ambiente Virtual
* Copyright (C) 2014 SENAI
* ----------------------------------------------------------------------------
* Este arquivo é parte do Sistema Ambiente Virtual.
* Finalidade - Bean de mensagem
* 
* Criado por - William Chenta
*/
package br.edu.senai.ambientevirtual.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.edu.senai.ambientevirtual.business.MensagemBC;
import br.edu.senai.ambientevirtual.domain.Mensagem;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;

@ViewController
@RequiredRole({"tut","alu"})
public class MensagemListMB extends AbstractListPageBean<Mensagem, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private MensagemBC mensagemBC;
	private String prmIdMensagem;
	private Mensagem mensagem;
	private Map<String, String> params = new HashMap<String, String>();
	
	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public String getPrmIdMensagem() {
		return prmIdMensagem;
	}

	public void setPrmIdMensagem(String prmIdMensagem) {
		this.prmIdMensagem = prmIdMensagem;
	}

	@Override
	protected List<Mensagem> handleResultList() {
		return mensagemBC.filtrarQuery("", params);
	}
	
	public void loadDetalhesMensagem() {
		mensagem = mensagemBC.load(Long.valueOf(prmIdMensagem));
	}

}
