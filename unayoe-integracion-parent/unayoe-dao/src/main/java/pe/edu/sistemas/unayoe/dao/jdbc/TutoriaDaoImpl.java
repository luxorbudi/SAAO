package pe.edu.sistemas.unayoe.dao.jdbc;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.unayoe.core.dao.DAOImpl; 
import pe.edu.sistemas.unayoe.dao.TutoriaIDao; 
import pe.edu.sistemas.unayoe.dao.dominio.Profesor; 
import pe.edu.sistemas.unayoe.dao.dominio.Tutoria;


@Repository("asistenciaTutoriaDao")
@Transactional
public class TutoriaDaoImpl extends DAOImpl<Profesor,String>  implements TutoriaIDao{

	public List<Profesor> listarProfesores() throws Exception {
		 
		return super.listarTodos(Profesor.class);
		 
	}

}
