package pe.edu.sistemas.unayoe.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.unayoe.core.dao.DAOImpl;
import pe.edu.sistemas.unayoe.dao.dominio.Usuario;

 

 

@Repository("usuarioDao")
@Transactional
public class UsuarioDaoImpl extends DAOImpl<Usuario,String> implements UsuarioIDao {

	public Usuario obtenerUsuario(String usuario) throws Exception {
		return super.obtenerEntidadPorId(Usuario.class,usuario);
	}
	
	//tambien se puede ir a jdbc si es que no se puede mapear completamente la tabla
	//CUS REQUERIDOS

}
