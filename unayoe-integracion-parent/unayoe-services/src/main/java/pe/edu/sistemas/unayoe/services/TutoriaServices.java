package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaCAlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTAlumBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTProfBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;

public interface TutoriaServices {

	
	public List<ProfesorBO> listarProfesores() throws Exception;
	public List<AsistenciaTAlumBO>  buscarAsistenciaDeAlumnosTutoria(String periodo, String anio, String dia, String curso, String fecha) throws Exception;
	public List<AsistenciaTAlumBO>  buscarAsistenciaDeAlumnosTutoriaUser(String periodo, String anio, String dia, String curso, String codUser, String horaIni, String horaFin) throws Exception;
	public List<AsistenciaTProfBO>  buscarAsistenciaDeProfesorTutoria(String fecha, String codigoCurso) throws Exception;
	public void actualizarListaAsistenciaTutoriaAlumno(List<AsistenciaTAlumBO> lista, String curso, String fecha) throws Exception;
	public void actualizarListaAsistenciaTutoriaTutor(List<AsistenciaTProfBO> lista, String curso, String fecha) throws Exception;
	public void  guardarDatosTutoria(TutoriaBO  tutoriabo) throws Exception;
	public List<TutoriaBO> listarHorariosDeTutoria(Integer anio , Integer periodo ,String a_codigo)throws Exception;
	public List<TutoriaBO> listarHorariosDeTutoriaProfesor(Integer anio , Integer periodo ,String p_codigo) throws Exception;
	public List<TutoriaBO> listarHorariosDeTutoriaxSemana(Integer anio , Integer periodo ,String c_codigo , String dia)throws Exception;
	
}
