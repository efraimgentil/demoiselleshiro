package br.com.efraimgentil.demoiselleshiro.persistence;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.efraimgentil.demoiselleshiro.domain.Usuario;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.template.JPACrud;

/**
 * 
 * @author Efraim Gentil
 * @email efraim.gentil@gmail.com
 * @date Dec 7, 2013
 */
public class UsuarioDAO extends JPACrud<Usuario, Long> {

	private static final long serialVersionUID = 933826108061338132L;
	
	
	public Usuario porUsuario(String usuario){
		try{
			String sql = "SELECT u FROM Usuario u WHERE u.usuario = :usuario";
			Query query = getEntityManager().createQuery(sql);
			query.setParameter("usuario", usuario);
			return (Usuario) query.getSingleResult();
		}catch(PersistenceException e){
			return null;
		}
	}
	
	public Usuario porUsuarioESenha(String usuario , String senha){
		try{
			String sql = "SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.senha = :senha";
			Query query = getEntityManager().createQuery(sql);
			query.setParameter("usuario", usuario);
			query.setParameter("senha", senha);
			return (Usuario) query.getSingleResult();
		}catch(PersistenceException e){
			return null;
		}
	}
	
}
