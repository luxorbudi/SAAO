package pe.edu.sistemas.unayoe.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.unayoe.core.dao.DAOImpl;
import pe.edu.sistemas.unayoe.dao.dominio.Curso; 


@Repository("cursoDao")
@Transactional
public class CursoDaoImpl extends DAOImpl<Curso,String> implements CursoIDao{

	public List<Curso> listarCursos() throws Exception {
		//devuelve toda la informacion de los cursos
		return super.listarTodos(Curso.class);
	}
	
	public Curso obtenerCurso(String codigo) throws Exception{
		return super.obtenerEntidadPorId(Curso.class,codigo);
	}

}
