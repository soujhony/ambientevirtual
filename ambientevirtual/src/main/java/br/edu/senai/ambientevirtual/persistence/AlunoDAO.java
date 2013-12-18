package br.edu.senai.ambientevirtual.persistence;

import java.util.List;

import javax.persistence.TypedQuery;

import br.edu.senai.ambientevirtual.domain.Aluno;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class AlunoDAO extends JPACrud<Aluno, Long> {

	private static final long serialVersionUID = 1L;
	
	public List<Aluno> filtrar(String filtro, String valor) {
		System.out.println("AlunoaDAO tipo" + filtro + ", valor" + valor);
		TypedQuery<Aluno> busca;
		if (filtro.equals("matricula")) {
			busca = getEntityManager().createQuery(
					"select a from Aluno a where :filtro like :valor", getBeanClass());
			busca.setParameter("filtro", "a."+filtro);
		} else if (!filtro.isEmpty()) {
			busca = getEntityManager().createQuery(
					"select a from Aluno a where :filtro like :valor", getBeanClass());
			busca.setParameter("filtro", "a.usuario."+filtro);
		} else {
			busca = getEntityManager().createQuery(
					"select a from Aluno a where a.matricula like :valor" + 
					" or a.usuario.nome like :valor or a.usuario.email like :valor", 
					getBeanClass());
		}
		busca.setParameter("valor", valor);
		System.out.println(busca.getResultList().size());
		return busca.getResultList();
	}
}
