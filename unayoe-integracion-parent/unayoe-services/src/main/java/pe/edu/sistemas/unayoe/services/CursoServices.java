package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;

public interface CursoServices {
	
	

	public List<CursoBO> listarCursos() throws Exception;
	public List<CursoBO> listarCursosPorTutor(String codTutor) throws Exception;
	
	public void  guardarCursos(List<CursoBO> lista) throws Exception;

}
