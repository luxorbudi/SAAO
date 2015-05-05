package pe.edu.sistemas.unayoe.dao;

import java.util.List;

import pe.edu.sistemas.unayoe.dao.dominio.Curso;

public interface CursoIDao {

	
	public List<Curso> listarCursos()  throws Exception;
	public Curso obtenerCurso(String codigo) throws Exception;
	
}
