package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.dao.CursoIDao;
import pe.edu.sistemas.unayoe.dao.dominio.Alumno;
import pe.edu.sistemas.unayoe.dao.dominio.Curso;
import pe.edu.sistemas.unayoe.dao.jdbc.AlumnoDAO;
import pe.edu.sistemas.unayoe.dao.jdbc.CursoDAO;
import pe.edu.sistemas.unayoe.services.CursoServices;
import pe.edu.sistemas.unayoe.services.transformer.CursoTransformerToBO;
import pe.edu.sistemas.unayoe.services.transformer.CursoTransformerToENTIDAD;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;

@Service("cursoServices") 	
public class CursoServicesImpl implements CursoServices{
	
	@Autowired
	private CursoIDao cursoIDao;
	
	@Autowired
	private CursoDAO cursoDAO;
	
	@Autowired
	private CursoTransformerToBO cursoTransformerToBO;

	@Autowired
	private CursoTransformerToENTIDAD  cursoTransformerToENTIDAD;
	 
	
	public CursoBO obtenerAlumno(String codigo) throws Exception {
		System.out.println("codigo  : "+ codigo);
		Curso cursoEntidad = cursoIDao.obtenerCurso(codigo);
		return cursoTransformerToBO.transformer(cursoEntidad);
	}
	
	public List<CursoBO> listarCursos() throws Exception {
		 //para poder visualizar en las pantallas se debe realizar esta transformacion
		return cursoTransformerToBO.transformer(cursoIDao.listarCursos());
	}
	
	public void  guardarCursos(List<CursoBO> lista)throws Exception {
		cursoDAO.insertarLista(cursoTransformerToENTIDAD.transformer(lista));
	}

	public List<CursoBO> listarCursosPorTutor(String codTutor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
