package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaCAlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTAlumBO;


public interface AlumnoServices {
	
	public AlumnoBO obtenerAlumno(String usuario) throws Exception;
	public void  guardarAlumnos(List<AlumnoBO> lista) throws Exception;
	public List<AsistenciaCAlumnoBO>  buscarAsistenciaDeAlumnosClases(String fecha, String codigoCurso) throws Exception;
	public void actualizarListaAsistenciaClases(List<AsistenciaCAlumnoBO> lista, String curso, String fecha) throws Exception;

}
