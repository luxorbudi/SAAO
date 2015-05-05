package pe.edu.sistemas.unayoe.model;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioRolBO;

@Component("usuarioModel")
@RequestScoped
public class UsuarioModel {

	private String idUsuario;
	private String clave;
	private String nombres;
	private String paterno;
	private String materno;
	private String correo;
	private String direccion;
	private String telefono;
	private String rol;
	private UsuarioBO usuario;
	private UsuarioRolBO usuarioRol;
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getPaterno() {
		return paterno;
	}
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}
	public String getMaterno() {
		return materno;
	}
	public void setMaterno(String materno) {
		this.materno = materno;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public UsuarioBO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioBO usuario) {
		this.usuario = usuario;
	}
	public UsuarioRolBO getUsuarioRol() {
		return usuarioRol;
	}
	public void setUsuarioRol(UsuarioRolBO usuarioRol) {
		this.usuarioRol = usuarioRol;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	} 
	
}
