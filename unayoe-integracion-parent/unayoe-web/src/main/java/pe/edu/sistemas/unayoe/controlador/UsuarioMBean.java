package pe.edu.sistemas.unayoe.controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pe.edu.sistemas.unayoe.model.UsuarioModel;
import pe.edu.sistemas.unayoe.services.UsuarioServices;

@Controller("usuarioMBean")
@ViewScoped
public class UsuarioMBean {

	@Autowired
	private UsuarioModel usuarioModel;
	@Autowired
	private UsuarioServices usuarioServices;
	
	public UsuarioMBean(){
		
		usuarioModel = new UsuarioModel();
		
	}
	
	public String nuevo() {
		
		
		return "/paginas/admin/mantenimiento/usuario/nuevoUsuario.xhtml";

	}
	
	public String guardar() {
		
		mostrarMensajeCorrecto();
		return "/paginas/admin/mantenimiento/usuario/nuevoUsuario.xhtml";

	}
	

	 private void mostrarMensajeCorrecto(){
		 System.out.println("Entra mensaje");
		 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"El usuario se guardo correctamente.", "Correcto");
        FacesContext.getCurrentInstance().addMessage(null, message);
	 }

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	} 
	
	
	
	
}
