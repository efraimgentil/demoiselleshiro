package br.com.efraimgentil.demoiselleshiro.business;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.util.StringUtils;

import br.com.efraimgentil.demoiselleshiro.domain.Perfil;
import br.com.efraimgentil.demoiselleshiro.domain.Usuario;
import br.com.efraimgentil.demoiselleshiro.persistence.PerfilDAO;
import br.com.efraimgentil.demoiselleshiro.persistence.UsuarioDAO;
import br.com.efraimgentil.demoiselleshiro.util.StringUtil;
import br.gov.frameworkdemoiselle.annotation.Priority;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;

@BusinessController
public class UsuarioBC extends DelegateCrud<Usuario, Long, UsuarioDAO> {

	private static final long serialVersionUID = -6969780059193052188L;
	
	@Startup
	@Priority(3)
	@Transactional
	public void init(){
		if( findAll().isEmpty() ){
			PerfilDAO perfilDAO = Beans.getReference( PerfilDAO.class );
			Perfil perfil = perfilDAO .load(1);
			Usuario usuario = new Usuario( "usuario1" , "usuario@email.com", "usuario", StringUtil.md5("123456"));
			usuario.setPerfil(perfil);;
			insert( usuario );
		}
	}

}
