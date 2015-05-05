package pe.edu.sistemas.unayoe.dao;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.sistemas.unayoe.dao.dominio.Alumno;

public class AlumnoDaoTest   {
	@Autowired
	private AlumnoIDao alumnoDao;

	@Test
	public void insertarAlumnoTest(){
		Alumno a = new Alumno();
		cargarDatosAlu(a);
		
		try {
			alumnoDao.insertarAlumno(a);
			System.out.println(a);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	public void cargarDatosAlu(Alumno a ){
		a.setACodigo("10200022");
		a.setADni(new BigInteger("46565605"));
		a.setAApellidos("Ochoa Aly");
		a.setAEmail("carlos@gmail.com");
		a.setANombre("Carlos");
		a.setADireccion("Mz h  lote  15");

	}
	

}
