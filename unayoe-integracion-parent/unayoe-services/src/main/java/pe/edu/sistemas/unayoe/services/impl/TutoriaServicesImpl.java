package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.dao.TutoriaIDao;
import pe.edu.sistemas.unayoe.dao.TutoriaIJdbcDao;
import pe.edu.sistemas.unayoe.services.TutoriaServices;
import pe.edu.sistemas.unayoe.services.transformer.ProfesorTransformerToBo;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaCAlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTAlumBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTProfBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;



@Service("asistenciaTutoriaServices") 	
public class TutoriaServicesImpl implements TutoriaServices {
	

	@Autowired
	private TutoriaIDao asistenciaTutoriaIDao;
	@Autowired
	private TutoriaIJdbcDao asistenciaTutoriaIJdbcDao;
	
	@Autowired
	private ProfesorTransformerToBo profesorTransformerToBo;
	
	
	
	public List<AsistenciaTAlumBO> buscarAsistenciaDeAlumnosTutoria(
			String periodo, String anio, String dia, String curso, String fecha) throws Exception {
		
		
		return asistenciaTutoriaIJdbcDao.buscarAsistenciaDeAlumnosTutoria(periodo, anio, dia, curso, fecha);
	}
	
	public List<AsistenciaTAlumBO>  buscarAsistenciaDeAlumnosTutoriaUser(String periodo, String anio, String dia, String curso, String codUser, String horaIni, String horaFin)
	throws Exception {
		
		
		return asistenciaTutoriaIJdbcDao.buscarAsistenciaDeAlumnosTutoriaUser(periodo, anio, dia, curso, codUser, horaIni, horaFin);
	}

	public List<AsistenciaTProfBO> buscarAsistenciaDeProfesorTutoria(
			String fecha, String codigoCurso) throws Exception {

		return asistenciaTutoriaIJdbcDao.buscarAsistenciaDocenteTutoria(fecha, codigoCurso);
	}

	public List<ProfesorBO> listarProfesores() throws Exception {
		return profesorTransformerToBo.transformer(asistenciaTutoriaIDao.listarProfesores());
	}
	
	public void  guardarDatosTutoria(TutoriaBO  tutoriabo) throws Exception{
		asistenciaTutoriaIJdbcDao.insertarTutoria(tutoriabo);
	}
	
	public List<TutoriaBO> listarHorariosDeTutoria(Integer anio , Integer periodo ,String a_codigo) throws Exception{
		return asistenciaTutoriaIJdbcDao.listarHorariosDeTutoria(anio, periodo, a_codigo);
	}
	
	public List<TutoriaBO> listarHorariosDeTutoriaProfesor(Integer anio , Integer periodo ,String p_codigo) throws Exception{
		return asistenciaTutoriaIJdbcDao.listarHorariosDeTutoriaProfesor(anio, periodo, p_codigo);
	}

	
	public List<TutoriaBO> listarHorariosDeTutoriaxSemana(Integer anio , Integer periodo ,String c_codigo , String dia) throws Exception{
		return asistenciaTutoriaIJdbcDao.listarHorariosDeTutoriaxSemana(anio, periodo, c_codigo ,  dia);
	}

	public void actualizarListaAsistenciaTutoriaAlumno(
			List<AsistenciaTAlumBO> lista, String curso, String fecha)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void actualizarListaAsistenciaTutoriaTutor(
			List<AsistenciaTProfBO> lista, String curso, String fecha)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
