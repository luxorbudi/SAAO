package pe.edu.sistemas.unayoe.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.dao.UsuarioIDao;
import pe.edu.sistemas.unayoe.dao.dominio.Usuario;
import pe.edu.sistemas.unayoe.services.UsuarioServices;
import pe.edu.sistemas.unayoe.services.transformer.UsuarioTransformerToBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;
 

@Service("usuarioServices")
public class UsuarioServicesImpl implements UsuarioServices {

	@Autowired
	private UsuarioIDao usuarioDao;
	
	@Autowired
	private UsuarioTransformerToBO usuarioTransformerToBO;
	
	public UsuarioBO obtenerUsuario(String usuario) throws Exception {
		System.out.println("usuario "+ usuario);
		Usuario usuarioEntidad = usuarioDao.obtenerUsuario(usuario);
		return usuarioTransformerToBO.transformer(usuarioEntidad);
	}

}
