package pe.edu.sistemas.unayoe.unayoe.bo;

 
import java.io.Serializable;
 
public class UsuarioBO implements Serializable {
  
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idRol;
	private String role;
	private String idUsuario;
	private String contrasenia;
	
	
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getIdRol() {
		return idRol;
	}
	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	} 
	
}

