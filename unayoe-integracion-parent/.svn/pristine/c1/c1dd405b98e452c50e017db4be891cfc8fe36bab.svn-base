package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import pe.edu.sistemas.unayoe.dao.AlumnoIDao;
import pe.edu.sistemas.unayoe.dao.UsuarioIDao;
import pe.edu.sistemas.unayoe.dao.dominio.Alumno;
import pe.edu.sistemas.unayoe.dao.dominio.Usuario;
import pe.edu.sistemas.unayoe.dao.jdbc.AlumnoDAO;
import pe.edu.sistemas.unayoe.services.AlumnoServices; 
import pe.edu.sistemas.unayoe.services.transformer.AlumnoTransformerToBO;
import pe.edu.sistemas.unayoe.services.transformer.AlumnoTransformerToENTIDAD; 
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO; 
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaCAlumnoBO; 
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTAlumBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;

@Service("alumnoServices") 	
public class AlumnoServicesImpl implements AlumnoServices{
	
	@Autowired
	private AlumnoDAO alumnoDAO;
	
	@Autowired
	private  AlumnoTransformerToENTIDAD  alumnoTransformerToENTIDAD;
	
	@Autowired
	private AlumnoTransformerToBO alumnoTransformerToBO;
	
	@Autowired
	private AlumnoIDao alumnoIDao; 
	
	public AlumnoBO obtenerAlumno(String codigo) throws Exception {
		System.out.println("codigo  : "+ codigo);
		Alumno alumnoEntidad = alumnoIDao.obtenerAlumno(codigo);
		return alumnoTransformerToBO.transformer(alumnoEntidad);
	}
	
	public void  guardarAlumnos(List<AlumnoBO> lista)throws Exception {
		alumnoDAO.insertarLista(alumnoTransformerToENTIDAD.transformer(lista));
	}
  

	public void actualizarListaAsistenciaClases(
			List<AsistenciaCAlumnoBO> lista, String curso, String fecha)
			throws Exception {
		//alumnoDAO.actualizarListaAsistencia(lista, curso, fecha);
		
	} 

	public List<AsistenciaCAlumnoBO> buscarAsistenciaDeAlumnosClases(
			String fecha, String codigoCurso) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
 
	
	
	

}
