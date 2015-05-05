package pe.edu.sistemas.unayoe.dao.jdbc;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List; 
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional; 
import pe.edu.sistemas.unayoe.core.dao.jdbc.BaseDAO;
import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.core.util.DAOExcepcion;
import pe.edu.sistemas.unayoe.dao.dominio.Alumno;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaCAlumnoBO; 
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTAlumBO;

@Repository("alumnoDAO")
@Transactional
public class AlumnoDAO extends BaseDAO {
	
	 public Alumno insertar(Alumno alumno) throws DAOExcepcion {
         String query = "insert into alumno(a_codigo,a_nombre,a_apellidos,a_fnacimiento,a_direccion,a_email,a_telefono,a_dni) values (?,?,?,?,?,?,?,?)";
         Connection con = null;
         PreparedStatement stmt = null;
         ResultSet rs = null;
         alumno.setADni(BigInteger.ZERO);
         try {
        	 		
                 con = Conexion.obtenerConexion();
                 stmt = con.prepareStatement(query);
                 stmt.setString(1, alumno.getACodigo());
                 stmt.setString(2, alumno.getANombre());
                 stmt.setString(3, alumno.getAApellidos());
                 stmt.setString(4, alumno.getAFnacimiento());
                 stmt.setString(5, alumno.getADireccion());
                 stmt.setString(6, alumno.getAEmail());
                 if(alumno.getATelefono()!=null)
                	 stmt.setLong(7,alumno.getATelefono().longValue());
                 else
                	 stmt.setNull(7, Types.INTEGER);
                 if(alumno.getADni()!=null)
                	 stmt.setLong(8,alumno.getADni().longValue());
                 else
                	 stmt.setNull(8, Types.INTEGER);

                 int i = stmt.executeUpdate();
                 if (i != 1) {
                        throw new SQLException("No se pudo insertar el alumno");
                 }
                 // Obtener el ultimo id
                 /*int id = 0;
                 query = "select last_insert_id()";
                 stmt = con.prepareStatement(query);
                 rs = stmt.executeQuery();
                 if (rs.next()) {
                        id = rs.getInt(1);
                 }
                 System.out.println("El id:"+id);*/

         } catch (SQLException e) {
                 System.err.println(e.getMessage());
                 e.printStackTrace();
                 throw new DAOExcepcion(e.getMessage());
         } finally {
                 this.cerrarResultSet(rs);
                 this.cerrarStatement(stmt);
                 this.cerrarConexion(con);
         }
         return alumno;
	 }
	 
	 public void insertarLista(List<Alumno>  lista){
		 for(Alumno alumno : lista){
			 try {
				//alumno=insertar(alumno);
				 alumno=insertarActualizar(alumno); 
			} catch (DAOExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }
	 
	 public Alumno insertarActualizar(Alumno alumno) throws DAOExcepcion {
         String query = "insert into alumno(a_codigo,a_nombre,a_apellidos,a_fnacimiento,a_direccion,a_email,a_telefono,a_dni) values (?,?,?,?,?,?,?,?)";
         
         String queryMerge="MERGE INTO alumno a " +
                 "USING (SELECT ? a_codigo, ? a_nombre, ? a_apellidos ,? a_fnacimiento , ? a_direccion, ? a_email,? a_telefono,? a_dni  FROM dual) alumno " +
                 "ON (TRIM(a.a_codigo) = alumno.a_codigo ) " +
                 "WHEN MATCHED THEN " +
                 "UPDATE SET a.a_nombre = alumno.a_nombre, a.a_apellidos = alumno.a_apellidos, a.a_fnacimiento = alumno.a_fnacimiento, "
                 + "		a.a_direccion = alumno.a_direccion, a.a_email = alumno.a_email, a.a_telefono = alumno.a_telefono, a.a_dni = alumno.a_dni  " +
                 "WHEN NOT MATCHED THEN " +
                 "INSERT (a.a_codigo,a.a_nombre,a.a_apellidos,a.a_fnacimiento,a.a_direccion,a.a_email,a.a_telefono,a.a_dni) " +
                 "VALUES (alumno.a_codigo,alumno.a_nombre,alumno.a_apellidos,alumno.a_fnacimiento,alumno.a_direccion,alumno.a_email,alumno.a_telefono,alumno.a_dni)";
         Connection con = null;
         PreparedStatement stmt = null;
         ResultSet rs = null;
         try {
        	 		System.out.println("cod alu:"+alumno.getACodigo());
                 con = Conexion.obtenerConexion();
                 stmt = con.prepareStatement(queryMerge);
                 stmt.setString(1, alumno.getACodigo());
                 stmt.setString(2, alumno.getANombre());
                 stmt.setString(3, alumno.getAApellidos());
                 stmt.setString(4, alumno.getAFnacimiento());
                 stmt.setString(5, alumno.getADireccion());
                 stmt.setString(6, alumno.getAEmail());
                 if(alumno.getATelefono()!=null)
                	 stmt.setLong(7,alumno.getATelefono().longValue());
                 else
                	 stmt.setNull(7, Types.INTEGER);
                 if(alumno.getADni()!=null)
                	 stmt.setLong(8,alumno.getADni().longValue());
                 else
                	 stmt.setNull(8, Types.INTEGER);
                 
                 int i = stmt.executeUpdate();
                 
                 if (i != 1) {
                        throw new SQLException("No se pudo insertar");
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
         return alumno;
	 }
	 
	 
	 public void actualizarListaAsistenciaClases(List<AsistenciaCAlumnoBO> lista, String curso, String fecha){
		 System.out.println("tAMAÑO DE LA LISTA ENVIADA "+ lista.size());
		 for(AsistenciaCAlumnoBO alumno : lista){
			 try {
				 actualizarAsistenciaClases(alumno,  curso,  fecha); 
			} catch (DAOExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }
	 
	 
	 public void actualizarAsistenciaClases(AsistenciaCAlumnoBO asistenciaCAlumnoBO, String curso, String fecha) throws DAOExcepcion {
         String query = "update ASISTENCIA_C_ALUM ACA set ACA.ASISTENCIA =? WHERE  ACA.A_CODIGO =? AND ACA.C_CODIGO =? and TO_CHAR(ACA.FECHA,'dd/mm/yyyy') =?";
         
         Connection con = null;
         PreparedStatement stmt = null;
         try {
        	 
        	 	 con = Conexion.obtenerConexion();
                 stmt = con.prepareStatement(query);
                 stmt.setString(1,asistenciaCAlumnoBO.getAsistencia()); 
                 stmt.setString(2,asistenciaCAlumnoBO.getaCodigo()); 
                 stmt.setString(3,curso); 
                 stmt.setString(4,fecha); 
                  
                int i = stmt.executeUpdate();
     			if (i != 1) {
     				throw new SQLException("No se pudo actualizar");
     			}
         } catch (SQLException e) {
                 System.err.println(e.getMessage());
                 e.printStackTrace();
                 throw new DAOExcepcion(e.getMessage());
         } finally {
                 this.cerrarStatement(stmt);
                 this.cerrarConexion(con);
         } 
	 }
	 
	 public void actualizarListaAsistenciaTutoria(List<AsistenciaTAlumBO> lista, String curso, String fecha){
		 System.out.println("tAMAÑO DE LA LISTA ENVIADA "+ lista.size());
		 for(AsistenciaTAlumBO alumno : lista){
			 try {
				 actualizarAsistenciaTutoria(alumno,  curso,  fecha); 
			} catch (DAOExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }
	 
	 
	 public void actualizarAsistenciaTutoria(AsistenciaTAlumBO asistenciaDocenteTutoriaBO, String curso, String fecha) throws DAOExcepcion {
         String query = "update ASISTENCIA_C_PROF ACP set ACP.ASISTENCIA =? WHERE ACP.P_CODIGO =? AND ACP.C_CODIGO =? AND TO_CHAR(ACP.FECHA,'dd/mm/yyyy') =?";
         
         Connection con = null;
         PreparedStatement stmt = null;
         try {
        	  
                con = Conexion.obtenerConexion();
                 stmt = con.prepareStatement(query);
                 stmt.setString(1,asistenciaDocenteTutoriaBO.getAsistenciaT()); 
                 stmt.setString(2,asistenciaDocenteTutoriaBO.gettCodigo()); 
                 stmt.setString(3,curso); 
                 stmt.setString(4,fecha); 
                  
                int i = stmt.executeUpdate();
     			if (i != 1) {
     				throw new SQLException("No se pudo actualizar");
     			}
         } catch (SQLException e) {
                 System.err.println(e.getMessage());
                 e.printStackTrace();
                 throw new DAOExcepcion(e.getMessage());
         } finally {
                 this.cerrarStatement(stmt);
                 this.cerrarConexion(con);
         } 
	 }
	 
}
