package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class TutoriaBO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String tAnioo;
	private String tPeriodo;
	private String tCodigo;
	private String aCodigo;
	private String pCodigo;
	private String cCodigo;
	private String cNombre;
	private String dia;
	private String horaIni;
	private String horaFin;
	//BORRAR DESPUES
	private String nombre;
	private String repitencias;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRepitencias() {
		return repitencias;
	}
	public void setRepitencias(String repitencias) {
		this.repitencias = repitencias;
	}
	public String gettAnioo() {
		return tAnioo;
	}
	public void settAnioo(String tAnioo) {
		this.tAnioo = tAnioo;
	}
	public String gettPeriodo() {
		return tPeriodo;
	}
	public void settPeriodo(String tPeriodo) {
		this.tPeriodo = tPeriodo;
	}
	public String gettCodigo() {
		return tCodigo;
	}
	public void settCodigo(String tCodigo) {
		this.tCodigo = tCodigo;
	}
	public String getaCodigo() {
		return aCodigo;
	}
	public void setaCodigo(String aCodigo) {
		this.aCodigo = aCodigo;
	}
	public String getpCodigo() {
		return pCodigo;
	}
	public void setpCodigo(String pCodigo) {
		this.pCodigo = pCodigo;
	}
	public String getcCodigo() {
		return cCodigo;
	}
	public void setcCodigo(String cCodigo) {
		this.cCodigo = cCodigo;
	}
	public String getcNombre() {
		return cNombre;
	}
	public void setcNombre(String cNombre) {
		this.cNombre = cNombre;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getHoraIni() {
		return horaIni;
	}
	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	} 

}
