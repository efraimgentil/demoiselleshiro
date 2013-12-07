package br.com.efraimgentil.demoiselleshiro.view;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;

@ManagedBean
@RequestScoped
public class LoginMB  {
	
	private String username;
	
	private String password;
	
	public String login() throws IOException, ServletException {
		try{
			SecurityUtils.getSecurityManager().login( SecurityUtils.getSubject()  ,  new UsernamePasswordToken(username, password) );
			return "/index.jsf?faces-redirect=true";
		}catch(AuthenticationException ex){
			String msg = "Usuário e/ou senha invalidos.";
			FacesContext.getCurrentInstance().addMessage( null ,
					new FacesMessage( FacesMessage.SEVERITY_ERROR , msg , msg)
			);
			System.out.println( ex.getMessage() );
		}
		return null;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
