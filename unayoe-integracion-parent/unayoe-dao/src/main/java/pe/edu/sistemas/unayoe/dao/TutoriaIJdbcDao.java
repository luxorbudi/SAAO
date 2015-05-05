package pe.edu.sistemas.unayoe.dao;


import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTAlumBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTProfBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;
 

public interface TutoriaIJdbcDao {
	
	
	public List<AsistenciaTAlumBO>  buscarAsistenciaDeAlumnosTutoria(String periodo, String anio, String dia, String curso, String fecha) throws Exception;
	public List<AsistenciaTAlumBO>  buscarAsistenciaDeAlumnosTutoriaUser(String periodo, String anio, String dia, String curso, String codigoUser, String horaIni, String horaFin) throws Exception;
	public List<AsistenciaTProfBO>  buscarAsistenciaDocenteTutoria(String fecha, String codigoCurso) throws Exception;
	
	public void insertarTutoria(TutoriaBO tutoriabo) throws Exception;
	
	public List<TutoriaBO> listarHorariosDeTutoria(Integer anio , Integer periodo ,String a_codigo) throws Exception;
	
	public List<TutoriaBO> listarHorariosDeTutoriaProfesor(Integer anio , Integer periodo ,String p_codigo)throws Exception;
	
	public List<TutoriaBO> listarHorariosDeTutoriaxSemana(Integer anio , Integer periodo ,String p_codigo , String dia)throws Exception;
}
