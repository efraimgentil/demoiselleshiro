package br.com.efraimgentil.demoiselleshiro.business;

import br.com.efraimgentil.demoiselleshiro.domain.Permissao;
import br.com.efraimgentil.demoiselleshiro.domain.Usuario;
import br.com.efraimgentil.demoiselleshiro.persistence.PermissaoDAO;
import br.gov.frameworkdemoiselle.annotation.Priority;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

/**
 * 
 * @author Efraim Gentil
 * @email efraim.gentil@gmail.com
 * @date Dec 7, 2013
 */
public class PermissaoBC extends DelegateCrud<Permissao, Long, PermissaoDAO> {

	private static final long serialVersionUID = 1L;

	@Startup
	@Priority(1)
	@Transactional
	public void init(){
		if( findAll().isEmpty() ){
			insert( new Permissao("Listar bookmarks", "Listar bookmarks", "LISTAR_BOOKMARK") );
			insert( new Permissao("Alterar bookmarks", "Alterar bookmarks", "ALTERAR_BOOKMARK") );
			insert( new Permissao("Adicionar bookmarks", "Adicionar bookmarks", "ADICIONAR_BOOKMARK") );
			insert( new Permissao("Excluir bookmarks", "Excluir bookmarks", "EXCLUIR_BOOKMARK") );
//			insert( new Usuario( "usuario1" , "usuario@email.com", "usuario", "") );
		}
	}
	
}
