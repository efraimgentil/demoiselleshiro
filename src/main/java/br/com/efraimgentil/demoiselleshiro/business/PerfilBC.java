package br.com.efraimgentil.demoiselleshiro.business;

import java.util.List;

import br.com.efraimgentil.demoiselleshiro.domain.Perfil;
import br.com.efraimgentil.demoiselleshiro.domain.Permissao;
import br.com.efraimgentil.demoiselleshiro.persistence.PerfilDAO;
import br.com.efraimgentil.demoiselleshiro.persistence.PermissaoDAO;
import br.gov.frameworkdemoiselle.annotation.Priority;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;

/**
 * 
 * @author Efraim Gentil
 * @email efraim.gentil@gmail.com
 * @date Dec 7, 2013
 */
public class PerfilBC extends DelegateCrud<Perfil, Integer, PerfilDAO> {
	
	private static final long serialVersionUID = -5302305715858321374L;

	@Startup
	@Priority(2)
	@Transactional
	public void init(){
		if( findAll().isEmpty() ){
			PermissaoDAO permissaoDAO = Beans.getReference(PermissaoDAO.class);
			List<Permissao>  permissoes = permissaoDAO .findAll();
			insert( new Perfil("Perfil1" , "PERFIL1" , permissoes ) );
//			insert( new Usuario( "usuario1" , "usuario@email.com", "usuario", "") );
		}
	}
	
}
