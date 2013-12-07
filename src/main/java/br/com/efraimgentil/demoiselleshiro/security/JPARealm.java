package br.com.efraimgentil.demoiselleshiro.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import br.com.efraimgentil.demoiselleshiro.domain.Perfil;
import br.com.efraimgentil.demoiselleshiro.domain.Permissao;
import br.com.efraimgentil.demoiselleshiro.domain.Usuario;
import br.com.efraimgentil.demoiselleshiro.persistence.UsuarioDAO;
import br.gov.frameworkdemoiselle.util.Beans;

public class JPARealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
		UsuarioDAO usuarioDAO = Beans.getReference(UsuarioDAO.class);
		String username =  (String) token.fromRealm( getName() ).iterator().next();
		Usuario usuario = usuarioDAO.porUsuario(  username );
		if(usuario != null){
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			Perfil perfil = usuario.getPerfil();
			info.addRole( perfil.getCodigo() );
			for(Permissao permissao : perfil.getPermissoes() ){
				info.addStringPermission( permissao.getCodigo() );
			}
			return info;
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsuarioDAO usuarioDAO = Beans.getReference(UsuarioDAO.class);
		UsernamePasswordToken token = ((UsernamePasswordToken) authenticationToken);
		Usuario usuario = usuarioDAO.porUsuario(  token.getUsername() );
		if (usuario != null) {
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(  token.getUsername() , usuario.getSenha().toCharArray() , getName());
			return simpleAuthenticationInfo;
		}
		return null;
	}

}
