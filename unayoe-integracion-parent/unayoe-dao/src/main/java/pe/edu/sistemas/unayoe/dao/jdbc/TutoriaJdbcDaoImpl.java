package pe.edu.sistemas.unayoe.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.edu.sistemas.unayoe.core.dao.jdbc.BaseDAO;
import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.core.util.DAOExcepcion;
import pe.edu.sistemas.unayoe.dao.TutoriaIJdbcDao;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTAlumBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTProfBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.DiasClaseBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;


@Repository("asistenciaTutoriaJdbcDao")
public class TutoriaJdbcDaoImpl  extends BaseDAO implements TutoriaIJdbcDao{
	
	
	//obtiene la informacion del role_admin
	public List<AsistenciaTAlumBO> buscarAsistenciaDeAlumnosTutoria(
			String periodo, String anio, String dia, String curso, String fecha) throws Exception {
		 
		
		String query = "select alu.a_codigo, alu.a_nombre, alu.a_apellidos, tut.hora_ini,tut.hora_fin, cur.NOMBRE, asis.fecha_T, "
				+ " asis.ASISTENCIA_T, asis.OBSERVACION_T from tutoria tut "
				+ "inner join profesor pro on pro.p_codigo = tut.p_codigo "
				+ "inner join ASISTENCIA_T_ALUM asis on asis.T_ANIO = tut.t_anio and "
				+ "asis.T_PERIODO = tut.T_PERIODO and asis.t_codigo = tut.T_CODIGO inner join alumno alu "
				+ "on alu.a_codigo = tut.a_codigo inner join curso cur on cur.c_codigo = tut.C_CODIGO "
				+ "where tut.t_anio = ? and "
				+ "tut.t_periodo = ?  and " 
				+ "tut.c_codigo = ?  and "
				+ "trim(tut.dia) = ? and "
				+ "to_char(asis.fecha_T,'dd/mm/yyyy')  = ?  "; 
		
		List<AsistenciaTAlumBO> listaAsistenciaAlumnoTutoria = new ArrayList<AsistenciaTAlumBO>();
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = Conexion.obtenerConexion();
			stmt = con.prepareStatement(query);
			stmt.setString(1, anio);
			stmt.setString(2,periodo);
			stmt.setString(3,curso);
			stmt.setString(4,dia); 
			stmt.setString(5,fecha); 
			rs = stmt.executeQuery();
			while (rs.next()) {
				AsistenciaTAlumBO asistencia = new AsistenciaTAlumBO();
				
				AlumnoBO alumno = new AlumnoBO();				
				alumno.setaCodigo(rs.getString("a_codigo"));
				alumno.setaNombre(rs.getString("a_nombre"));
				alumno.setaApellido(rs.getString("a_apellidos"));
				asistencia.setAlumno(alumno);
				
				asistencia.setAsistenciaT(rs.getString("ASISTENCIA_T"));
				asistencia.setObservacionT(rs.getString("OBSERVACION_T"));
				 
				DiasClaseBO diasClase = new DiasClaseBO();
				diasClase.setHoraIni(rs.getString("hora_ini"));
				diasClase.setHoraFin(rs.getString("hora_fin")); 
				asistencia.setDiasClases(diasClase);
				listaAsistenciaAlumnoTutoria.add(asistencia);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		System.out.println(listaAsistenciaAlumnoTutoria.size());
		return listaAsistenciaAlumnoTutoria;
	}
	
	
	//obtiene la informacion del user role_user
		public List<AsistenciaTAlumBO> buscarAsistenciaDeAlumnosTutoriaUser(
				String periodo, String anio, String dia, String curso, String codigoUser, String horaIni, String horaFin) throws Exception {
			 
			
			String query = "select alu.a_codigo, alu.a_nombre, alu.a_apellidos, tut.hora_ini,tut.hora_fin, cur.NOMBRE,cur.C_CODIGO, asis.fecha_T, "
					+ " asis.ASISTENCIA_T, asis.OBSERVACION_T from tutoria tut "
					+ "inner join profesor pro on pro.p_codigo = tut.p_codigo "
					+ "inner join ASISTENCIA_T_ALUM asis on asis.T_ANIO = tut.t_anio and "
					+ "asis.T_PERIODO = tut.T_PERIODO and asis.t_codigo = tut.T_CODIGO inner join alumno alu "
					+ "on alu.a_codigo = tut.a_codigo inner join curso cur on cur.c_codigo = tut.C_CODIGO "
					+ "where tut.p_codigo = ? and "
					+ "tut.t_anio = ? and "
					+ "tut.t_periodo = ? and "
					+ "tut.hora_ini <= ? and "
					+ "tut.hora_fin >= ?  ";
					//+ "tut.dia = ? ";
			
			List<AsistenciaTAlumBO> listaAsistenciaAlumnoTutoria = new ArrayList<AsistenciaTAlumBO>();
			
			Connection con = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				con = Conexion.obtenerConexion();
				stmt = con.prepareStatement(query);
				stmt.setString(1, codigoUser);
				stmt.setString(2,anio);
				stmt.setString(3,periodo);  
				stmt.setString(4,horaIni); 
				stmt.setString(5,horaFin); 
				//stmt.setString(6,dia); 
				rs = stmt.executeQuery();
				while (rs.next()) {
					AsistenciaTAlumBO asistencia = new AsistenciaTAlumBO();
					
					AlumnoBO alumno = new AlumnoBO();				
					alumno.setaCodigo(rs.getString("a_codigo"));
					alumno.setaNombre(rs.getString("a_nombre"));
					alumno.setaApellido(rs.getString("a_apellidos"));
					asistencia.setAlumno(alumno);
					
					CursoBO cursoBO = new CursoBO();
					cursoBO.setcCodigo(rs.getString("C_CODIGO"));
					cursoBO.setNombre(rs.getString("NOMBRE"));
					asistencia.setCurso(cursoBO);
					asistencia.setAsistenciaT(rs.getString("ASISTENCIA_T"));

					asistencia.setObservacionT(rs.getString("OBSERVACION_T"));
					DiasClaseBO diasClase = new DiasClaseBO();
					diasClase.setHoraIni(rs.getString("hora_ini"));
					diasClase.setHoraFin(rs.getString("hora_fin")); 
					
					listaAsistenciaAlumnoTutoria.add(asistencia);
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				throw new DAOExcepcion(e.getMessage());
			} finally {
				this.cerrarResultSet(rs);
				this.cerrarStatement(stmt);
				this.cerrarConexion(con);
			}
			System.out.println(listaAsistenciaAlumnoTutoria.size());
			return listaAsistenciaAlumnoTutoria;
		}

	public List<AsistenciaTProfBO> buscarAsistenciaDocenteTutoria(
			String fecha, String codigoCurso) throws Exception {
		String query = "SELECT C.NOMBRE, P.P_CODIGO, P.P_NOMBRE, P.P_APELLIDOS,ACP.ASISTENCIA "
				+ "FROM  ASISTENCIA_C_PROF ACP inner join PROFESOR P on ACP.P_CODIGO = P.P_CODIGO "
				+ "inner join CURSO C on ACP.C_CODIGO = C.C_CODIGO "
				+ "WHERE C.C_CODIGO = ? "
				+ "AND to_char(ACP.fecha,'dd/mm/yyyy') = ?";
		List<AsistenciaTProfBO> listaAsistenciaAlumnoTutoria = new ArrayList<AsistenciaTProfBO>();
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = Conexion.obtenerConexion();
			stmt = con.prepareStatement(query);
			stmt.setString(1, codigoCurso);
			stmt.setString(2, fecha);
			rs = stmt.executeQuery();
			while (rs.next()) {
				AsistenciaTProfBO asistencia = new AsistenciaTProfBO();
				CursoBO curso = new CursoBO();
				curso.setcCodigo(rs.getString("NOMBRE"));
				curso.setNombre(rs.getString("NOMBRE"));
				asistencia.setCurso(curso);
				
				ProfesorBO profesor = new ProfesorBO();
				profesor.setpCodigo(rs.getString("P_CODIGO"));
				profesor.setpNombre(rs.getString("P_NOMBRE"));
				profesor.setpCodigo(rs.getString("P_CODIGO"));
				profesor.setpApellidos(rs.getString("P_APELLIDOS"));				 
				asistencia.setProfesor(profesor);
				
				DiasClaseBO diasClase = new DiasClaseBO();
				asistencia.setDiasClase(diasClase);
				asistencia.getDiasClase().setHoraIni(rs.getString("P_NOMBRE"));
				asistencia.getDiasClase().setHoraFin(rs.getString("P_NOMBRE"));
				asistencia.setAsistenciaT(rs.getString("P_APELLIDOS"));
				asistencia.setObservacionT(rs.getString("ASISTENCIA"));
				 
				
				listaAsistenciaAlumnoTutoria.add(asistencia);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		System.out.println(listaAsistenciaAlumnoTutoria.size());
		return listaAsistenciaAlumnoTutoria;
	}

	public void insertarTutoria(TutoriaBO tutoriabo) throws Exception{
		boolean esNuevo=false;// para actualizar
		
		String queryObtenerSiguienteID ="select 'T'||TRIM(TO_CHAR(codigo,'0000000')) from (select TO_NUMBER( SUBSTR(t.t_codigo, 2))+1  as codigo from tutoria t "
									+ "where t.t_anio= ? and t.t_periodo=? order by SUBSTR(t.t_codigo,2) desc)where rownum <=1 ";
		
		
		
		String querySaveOrUpdate = "MERGE INTO tutoria t USING "+
		"(SELECT ? T_ANIO,"+
		"? T_PERIODO,"+
		"? T_CODIGO ,"+
		"? A_CODIGO ,"+
		"? P_CODIGO,"+
		"? DIA,"+
		"? HORA_INI,"+
		"? HORA_FIN ,"+
		"? C_CODIGO "+
		"FROM dual "+
		") tutoria ON (t.T_ANIO= tutoria.T_ANIO AND t.T_PERIODO= tutoria.T_PERIODO AND TRIM(t.T_CODIGO) = tutoria.T_CODIGO  ) "+
		"WHEN MATCHED THEN "+
		"UPDATE "+
		"SET "+
		"t.A_CODIGO = tutoria.A_CODIGO, "+
		"t.DIA      = tutoria.DIA, "+
		"t.HORA_INI = tutoria.HORA_INI, "+
		"t.HORA_FIN = tutoria.HORA_FIN, "+
		"t.C_CODIGO = tutoria.C_CODIGO "+
		"WHEN NOT MATCHED THEN "+
		"INSERT "+
		"( "+
		"t.T_ANIO, "+
		"t.T_PERIODO, "+
		"t.T_CODIGO, "+
		"t.A_CODIGO, "+
		"t.P_CODIGO, "+
		"t.DIA, "+
		"t.HORA_INI, "+
		"t.HORA_FIN, "+
		"t.C_CODIGO "+
		") "+
        "VALUES "+
      "("+
      "tutoria.T_ANIO, "+
      "tutoria.T_PERIODO, "+
      "tutoria.T_CODIGO,  "+
      "tutoria.A_CODIGO,  "+
      "tutoria.P_CODIGO,  "+
      "tutoria.DIA,   "+
      "tutoria.HORA_INI, "+
      "tutoria.HORA_FIN, "+
      "tutoria.C_CODIGO "+
      ")";
		
		String t_codigo="codigo";
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PreparedStatement stmtSgte = null;
        ResultSet rsSgte = null;
        try {
                con = Conexion.obtenerConexion();
                
                
                if(tutoriabo.gettCodigo()==null ||tutoriabo.gettCodigo().isEmpty()){// si no tiene codigo
                	esNuevo=true;
                	stmtSgte=con.prepareStatement(queryObtenerSiguienteID);
                	stmtSgte.setInt(1,Integer.parseInt(tutoriabo.gettAnioo()));
                	stmtSgte.setInt(2,Integer.parseInt(tutoriabo.gettPeriodo()));  
                	rsSgte = stmtSgte.executeQuery();
                	if(rsSgte.next())
                		t_codigo=rsSgte.getString(1);
                	else
                		t_codigo="T0000001";
                }
                
                stmt = con.prepareStatement(querySaveOrUpdate);
                stmt.setInt(1,Integer.parseInt(tutoriabo.gettAnioo()));
                stmt.setInt(2,Integer.parseInt(tutoriabo.gettPeriodo()));
                if(esNuevo)
                	stmt.setString(3,t_codigo);
                stmt.setString(4,tutoriabo.getaCodigo());
                stmt.setString(5,tutoriabo.getpCodigo());
                stmt.setString(6,tutoriabo.getDia());
                stmt.setString(7,tutoriabo.getHoraIni()+":00:00");
                stmt.setString(8,tutoriabo.getHoraFin()+":00:00");
                stmt.setString(9,tutoriabo.getcCodigo());  
                
                int i = stmt.executeUpdate();
                
                if (i != 1) {
                       //throw new SQLException("No se pudo insertar el grupo");
               	  System.err.println("No se pudo insertar la matricula , el registro ya existe.");
                }

        } catch (SQLException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
                throw new DAOExcepcion(e.getMessage());
        } finally {
                this.cerrarResultSet(rs);
                this.cerrarStatement(stmt);
                this.cerrarConexion(con);
        }
	}
	
	public List<TutoriaBO> listarHorariosDeTutoria(Integer anio , Integer periodo ,String a_codigo)throws Exception{
		String query = "select  t.T_ANIO , t.T_PERIODO , t.T_CODIGO , t.A_CODIGO , t.P_CODIGO , t.DIA ,t.HORA_INI ,"
				+ " t.HORA_FIN ,t.C_CODIGO  from tutoria t "
				+ " where t.T_ANIO=? and t.T_PERIODO=? and t.A_CODIGO =?";


		
		List<TutoriaBO> listaHorarioTutoria = new ArrayList<TutoriaBO>();
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = Conexion.obtenerConexion();
			stmt = con.prepareStatement(query);
			stmt.setInt(1, anio);
			stmt.setInt(2,periodo);
			stmt.setString(3, a_codigo);
			rs = stmt.executeQuery();
			while (rs.next()) {
				TutoriaBO horario = new TutoriaBO();
				
				horario.settAnioo(rs.getString("T_ANIO"));
				horario.settPeriodo(rs.getString("T_PERIODO").trim());
				horario.settCodigo(rs.getString("T_CODIGO").trim());
				horario.setaCodigo(rs.getString("A_CODIGO").trim());
				horario.setpCodigo(rs.getString("P_CODIGO").trim());
				horario.setDia(rs.getString("DIA"));
				horario.setHoraIni(rs.getString("HORA_INI"));
				horario.setHoraFin(rs.getString("HORA_FIN"));
				horario.setcCodigo(rs.getString("C_CODIGO").trim());
				listaHorarioTutoria.add(horario);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		System.out.println(listaHorarioTutoria.size());
		return listaHorarioTutoria;
	}
	
	
	public List<TutoriaBO> listarHorariosDeTutoriaProfesor(Integer anio , Integer periodo ,String p_codigo)throws Exception{
		String query = "select  t.T_ANIO , t.T_PERIODO , t.T_CODIGO , t.A_CODIGO , t.P_CODIGO , t.DIA ,t.HORA_INI ,"
				+ " t.HORA_FIN ,t.C_CODIGO  from tutoria t "
				+ " where t.T_ANIO=? and t.T_PERIODO=? and t.P_CODIGO =?";


		
		List<TutoriaBO> listaHorarioTutoria = new ArrayList<TutoriaBO>();
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = Conexion.obtenerConexion();
			stmt = con.prepareStatement(query);
			stmt.setInt(1, anio);
			stmt.setInt(2,periodo);
			stmt.setString(3, p_codigo);
			rs = stmt.executeQuery();
			while (rs.next()) {
				TutoriaBO horario = new TutoriaBO();
				
				horario.settAnioo(rs.getString("T_ANIO"));
				horario.settPeriodo(rs.getString("T_PERIODO"));
				horario.settCodigo(rs.getString("T_CODIGO"));
				horario.setaCodigo(rs.getString("A_CODIGO"));
				horario.setpCodigo(rs.getString("P_CODIGO"));
				horario.setDia(rs.getString("DIA"));
				horario.setHoraIni(rs.getString("HORA_INI"));
				horario.setHoraFin(rs.getString("HORA_FIN"));
				horario.setcCodigo(rs.getString("C_CODIGO"));
				listaHorarioTutoria.add(horario);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		System.out.println(listaHorarioTutoria.size());
		return listaHorarioTutoria;
	}
	
	public List<TutoriaBO> listarHorariosDeTutoriaxSemana(Integer anio , Integer periodo ,String c_codigo , String dia)throws Exception{
		String query = "select  t.T_ANIO , t.T_PERIODO , t.T_CODIGO , t.A_CODIGO , t.P_CODIGO , t.DIA ,t.HORA_INI ,"
				+ " t.HORA_FIN ,t.C_CODIGO  from tutoria t where  t.t_anio=? and  t.t_periodo=? and "+
                         " trim(t.dia) like ?  and trim(t.c_codigo) like ? ";


		
		List<TutoriaBO> listaHorarioTutoria = new ArrayList<TutoriaBO>();
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = Conexion.obtenerConexion();
			stmt = con.prepareStatement(query);
			stmt.setInt(1, anio);
			stmt.setInt(2,periodo);
			stmt.setString(3, "%"+dia+"%");
			stmt.setString(4, "%"+c_codigo+"%");
			rs = stmt.executeQuery();
			while (rs.next()) {
				TutoriaBO horario = new TutoriaBO();
				
				horario.settAnioo(rs.getString("T_ANIO"));
				horario.settPeriodo(rs.getString("T_PERIODO"));
				horario.settCodigo(rs.getString("T_CODIGO"));
				horario.setaCodigo(rs.getString("A_CODIGO"));
				horario.setpCodigo(rs.getString("P_CODIGO"));
				horario.setDia(rs.getString("DIA"));
				horario.setHoraIni(rs.getString("HORA_INI"));
				horario.setHoraFin(rs.getString("HORA_FIN"));
				horario.setcCodigo(rs.getString("C_CODIGO"));
				listaHorarioTutoria.add(horario);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		System.out.println(listaHorarioTutoria.size());
		return listaHorarioTutoria;
	}
	
	
	
	
	/*
	public Collection<Categoria> buscarPorNombre(String nombre)
			throws DAOExcepcion {
		String query = "select id_categoria, nombre, descripcion from categoria where nombre like ?";
		Collection<Categoria> lista = new ArrayList<Categoria>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = ConexionBD.obtenerConexion();
			stmt = con.prepareStatement(query);
			stmt.setString(1, "%" + nombre + "%");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Categoria vo = new Categoria();
				vo.setIdCategoria(rs.getInt("id_categoria"));
				vo.setNombre(rs.getString("nombre"));
				vo.setDescripcion(rs.getString("descripcion"));
				lista.add(vo);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		System.out.println(lista.size());
		return lista;
	}

	public Categoria insertar(Categoria vo) throws DAOExcepcion {
		String query = "insert into categoria(nombre,descripcion) values (?,?)";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = ConexionBD.obtenerConexion();
			stmt = con.prepareStatement(query);
			stmt.setString(1, vo.getNombre());
			stmt.setString(2, vo.getDescripcion());
			int i = stmt.executeUpdate();
			if (i != 1) {
				throw new SQLException("No se pudo insertar");
			}
			// Obtener el ultimo id
			int id = 0;
			query = "select last_insert_id()";
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			vo.setIdCategoria(id);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		return vo;
	}

	public Categoria obtener(int idCategoria) throws DAOExcepcion {
		Categoria vo = new Categoria();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String query = "select id_categoria, nombre, descripcion from categoria where id_categoria=?";
			con = ConexionBD.obtenerConexion();
			stmt = con.prepareStatement(query);
			stmt.setInt(1, idCategoria);
			rs = stmt.executeQuery();
			if (rs.next()) {
				vo.setIdCategoria(rs.getInt(1));
				vo.setNombre(rs.getString(2));
				vo.setDescripcion(rs.getString(3));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		return vo;
	}

	public void eliminar(int idCategoria) throws DAOExcepcion {
		String query = "delete from categoria WHERE id_categoria=?";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConexionBD.obtenerConexion();
			stmt = con.prepareStatement(query);
			stmt.setInt(1, idCategoria);
			int i = stmt.executeUpdate();
			if (i != 1) {
				throw new SQLException("No se pudo eliminar");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
	}

	public Categoria actualizar(Categoria vo) throws DAOExcepcion {
		String query = "update categoria set nombre=?,descripcion=? where id_categoria=?";
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConexionBD.obtenerConexion();
			stmt = con.prepareStatement(query);
			stmt.setString(1, vo.getNombre());
			stmt.setString(2, vo.getDescripcion());
			stmt.setInt(3, vo.getIdCategoria());
			int i = stmt.executeUpdate();
			if (i != 1) {
				throw new SQLException("No se pudo actualizar");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		return vo;
	}

	public Collection<Categoria> listar() throws DAOExcepcion {
		Collection<Categoria> c = new ArrayList<Categoria>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = ConexionBD.obtenerConexion();
			String query = "select id_categoria,nombre,descripcion from categoria order by nombre";
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Categoria vo = new Categoria();
				vo.setIdCategoria(rs.getInt("id_categoria"));
				vo.setNombre(rs.getString("nombre"));
				vo.setDescripcion(rs.getString("descripcion"));
				c.add(vo);
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		return c;
	}*/

}
