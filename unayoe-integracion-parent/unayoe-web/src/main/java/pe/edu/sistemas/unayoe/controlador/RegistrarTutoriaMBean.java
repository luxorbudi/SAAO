package pe.edu.sistemas.unayoe.controlador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import controladorReporte.ControladorReporte;
import controladorReporte.HorarioTutoria;
import pe.edu.sistemas.unayoe.model.AsistenciaTutoriaModel;
import pe.edu.sistemas.unayoe.model.DiaSemanaModel;
import pe.edu.sistemas.unayoe.services.AlumnoServices;
import pe.edu.sistemas.unayoe.services.MatriculaServices;
import pe.edu.sistemas.unayoe.services.TutoriaServices;
import pe.edu.sistemas.unayoe.services.CursoServices;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;

@Controller("registrarTutoriaMBean")
@ViewScoped
public class RegistrarTutoriaMBean {

    @Autowired
    private AsistenciaTutoriaModel asistenciaTutoriaModel;
    @Autowired
    private CursoServices cursoServices;
    
    @Autowired
    private TutoriaServices tutorServices;
    
    @Autowired
    private AlumnoServices alumnoServices;
    
    @Autowired
    private MatriculaServices matriculaServices;
    
    @Autowired
    private List<DiaSemanaModel> diasSemanaModel;
    
    @Autowired
    private  DiaSemanaModel diaSemanaModel;

    // atributos para la seleccion del combo
    private boolean select;
    private AsistenciaTutoriaModel asistenciaTutoriaModelSelect;
    private List<AsistenciaTutoriaModel> listAsistenciaTutoria;
    private Date date;
    private String dia;
    Calendar hoy;

    //constructor
    public RegistrarTutoriaMBean() {
    	hoy = new GregorianCalendar();
        System.out.println("::::: LOADING ::::::::");
        asistenciaTutoriaModelSelect = new AsistenciaTutoriaModel();
        listAsistenciaTutoria= new ArrayList<AsistenciaTutoriaModel>();
    }
    
    private String obtenerPeriodo(){
    	String periodo="0";
    	Date fecha_actual=new Date();
    	Integer mes=fecha_actual.getMonth();
    	if(12>=mes&&mes>=8)
    		periodo="2";
    	else
    		if(mes>=4)
    			periodo="1";
    	
    	return periodo;
    }
    //metodo de instancia
    public String registrarDatosTutoria() {
    	diasSemanaModel=new ArrayList<DiaSemanaModel>();
        System.out.println("ingresarDatosTutoriai");
        listarCursos();
        listarProfesores();
        this.diasSemanaModel.add(new DiaSemanaModel("0","Lunes") );
        this.diasSemanaModel.add(new DiaSemanaModel("1","Martes"));
        this.diasSemanaModel.add(new DiaSemanaModel("2","Miercoles"));
        this.diasSemanaModel.add(new DiaSemanaModel("3","Jueves"));
        this.diasSemanaModel.add(new DiaSemanaModel("4","Viernes"));
        this.diasSemanaModel.add(new DiaSemanaModel("5","Sábado"));
        
        /*****************************************************************************************/
        asistenciaTutoriaModelSelect.settPeriodo(obtenerPeriodo());
        
        return "/paginas/admin/registrar/registrarDatosDeTutoria.xhtml";
    } 
    
    //metodo de instancia
    public String registrarDatosTutoriaDirAca() {
    	diasSemanaModel=new ArrayList<DiaSemanaModel>();
        System.out.println("ingresarDatosTutoriai");
        listarCursos();
        listarProfesores();
        this.diasSemanaModel.add(new DiaSemanaModel("0","Lunes") );
        this.diasSemanaModel.add(new DiaSemanaModel("1","Martes"));
        this.diasSemanaModel.add(new DiaSemanaModel("2","Miercoles"));
        this.diasSemanaModel.add(new DiaSemanaModel("3","Jueves"));
        this.diasSemanaModel.add(new DiaSemanaModel("4","Viernes"));
        this.diasSemanaModel.add(new DiaSemanaModel("5","Sábado"));
        
        /*****************************************************************************************/
        
        
        return "/paginas/diraca/registrar/registrarDatosDeTutoria.xhtml";
    }

    // metodos que domina de la pantallas
    public void onRowSelect(SelectEvent event) {
        System.out.println("entra a la seleccion");
         
        select = false;
        listarCursos();
       
    }

    public void listarCursos() {
        System.out.println("Listando los cursos:");

        List<CursoBO> listarCursos = null;
        try {
            listarCursos = cursoServices.listarCursos();
            asistenciaTutoriaModelSelect.setListarCursos(listarCursos);
            asistenciaTutoriaModel.setListarCursos(listarCursos);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    } 
    
    public void listarProfesores() {
        System.out.println("Listando los profesores:");

        List<ProfesorBO> listarProfesores = null;
        try {
        	listarProfesores = tutorServices.listarProfesores();
        	
        	System.out.println("lista " + listarProfesores);
        	System.out.println("tamaño de la lista " + listarProfesores.size());
        	ProfesorBO profe = listarProfesores.get(0);
        	
        	System.out.println("Profesor de nombre " + profe.getpNombre());
        	System.out.println("Profesor de codig " + profe.getpCodigo()); 
        	
            asistenciaTutoriaModel.setListarProfesores(listarProfesores);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    } 
    
    

    public void buscarRegistrosTutoria() {//se esta  usando?
    	
    	AlumnoBO alu= new  AlumnoBO();
    	List<MatriculaBO> listaMatricula= new ArrayList<MatriculaBO> ();
    	//asistenciaTutoriaModel.a_codigo
    	String codigo_alumno= asistenciaTutoriaModelSelect.getA_codigo();
    	String periodo= asistenciaTutoriaModelSelect.gettPeriodo();
    	
    	//Empieza la busqueda:
    	try {
			 alu=alumnoServices.obtenerAlumno(codigo_alumno);
			 if(alu!=null){
				 	asistenciaTutoriaModelSelect.setA_nombre(alu.getaNombre());
		    		/********Busqueda de cursos*********/
		    		listaMatricula= matriculaServices.obtenerMatriculaAlumnoPorPeriodo(codigo_alumno, hoy.get(Calendar.YEAR), Integer.parseInt(periodo));
		    		
		    		asistenciaTutoriaModel.setListarCursos(obtenerCursos(listaMatricula));
			 }else{
		    		asistenciaTutoriaModel.setA_nombre("No registrado");
		    	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    		
    		
        System.out.println("::::::: buscarAsistenciaAlumnosTutoria:::::::::");
        System.out.println(":::::: Fecha " + getDate());
        System.out.println("::::::: Codigo " + asistenciaTutoriaModelSelect.getC_codigo());
        System.out.println("::::::: Codigo " + asistenciaTutoriaModelSelect.gettPeriodo());
        /**********************************************************************/
        
    }
    
    public void buscarMatriculaAlumno(){
     	AlumnoBO alu= new  AlumnoBO();
     	List<MatriculaBO> listaMatricula= new ArrayList<MatriculaBO> ();
     	String codigo_alumno= asistenciaTutoriaModelSelect.getA_codigo();
     	String periodo= asistenciaTutoriaModelSelect.gettPeriodo();
     	try {
 			 alu=alumnoServices.obtenerAlumno(codigo_alumno);
 			 if(alu!=null){
 				 	asistenciaTutoriaModelSelect.setA_nombre(alu.getaNombre()+" "+alu.getaApellido());
 		    		/********Busqueda de cursos*********/
 		    		listaMatricula= matriculaServices.obtenerMatriculaAlumnoPorPeriodo(codigo_alumno, hoy.get(Calendar.YEAR), Integer.parseInt(periodo));
 		    		asistenciaTutoriaModel.setListarCursos(obtenerCursos(listaMatricula));
 			 }else{
 		    		asistenciaTutoriaModel.setA_nombre("No registrado");
 		    	}
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    }
     
    public void buscarHorariosTutoria() {
    	List<TutoriaBO> listaHorarios =  new ArrayList<TutoriaBO>();
    	
    	Integer tperiodo= Integer.parseInt(asistenciaTutoriaModelSelect.gettPeriodo());
    	String a_codigo=asistenciaTutoriaModelSelect.getA_codigo();
    	Integer anio=hoy.get(Calendar.YEAR);
    	listAsistenciaTutoria.clear();
    	try {
    		AlumnoBO abo=alumnoServices.obtenerAlumno(a_codigo);
    		if(abo!=null){
    			asistenciaTutoriaModelSelect.setA_nombre(abo.getaNombre()+" "+abo.getaApellido());	
    			asistenciaTutoriaModelSelect.setA_apellido(abo.getaApellido());
			listaHorarios= tutorServices.listarHorariosDeTutoria(anio, tperiodo, a_codigo);
			if(listaHorarios.size()>0){
				for(TutoriaBO tbo :listaHorarios){
					listAsistenciaTutoria.add(convertirAModel(tbo));
				}
			}
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public void buscarHorariosTutoriaProfesor() {
    	listarProfesores();
    	listarCursos();
    	List<TutoriaBO> listaHorarios =  new ArrayList<TutoriaBO>();
    	
    	Integer tperiodo= Integer.parseInt(asistenciaTutoriaModelSelect.gettPeriodo());
    	String p_codigo=asistenciaTutoriaModelSelect.getP_codigo();
    	Integer anio=hoy.get(Calendar.YEAR);
    	asistenciaTutoriaModelSelect.setP_nombre("No se Encontro Profesor");
    	try {
    		//AlumnoBO abo=alumnoServices.obtenerAlumno(p_codigo);
    		for(ProfesorBO pbo:  asistenciaTutoriaModel.getListarProfesores()){
    			if(p_codigo.equalsIgnoreCase(pbo.getpCodigo()))
    				asistenciaTutoriaModelSelect.setP_nombre(pbo.getpNombre()+" "+pbo.getpApellidos());
    		}
    		
    		
    			
			listaHorarios= tutorServices.listarHorariosDeTutoriaProfesor(anio, tperiodo, p_codigo);
			if(listaHorarios.size()>0){
				listAsistenciaTutoria.clear();
				for(TutoriaBO tbo :listaHorarios){
					listAsistenciaTutoria.add(convertirAModel(tbo));
				}
			}
    		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    public void buscarHorariosTutoriaSemanal() {
    	listarProfesores();
    	List<TutoriaBO> listaHorarios =  new ArrayList<TutoriaBO>();
    	String dia= asistenciaTutoriaModelSelect.getDia();
    	Integer tperiodo= Integer.parseInt(asistenciaTutoriaModelSelect.gettPeriodo());
    	String c_codigo=asistenciaTutoriaModelSelect.getC_codigo();
    	Integer anio=hoy.get(Calendar.YEAR);
    	asistenciaTutoriaModelSelect.setP_nombre("No se Encontro Profesor");
    	try {
    		//AlumnoBO abo=alumnoServices.obtenerAlumno(p_codigo);
    		for(ProfesorBO pbo:  asistenciaTutoriaModel.getListarProfesores()){
    			if(c_codigo.equalsIgnoreCase(pbo.getpCodigo()))
    				asistenciaTutoriaModelSelect.setP_nombre(pbo.getpNombre()+" "+pbo.getpApellidos());
    		}

			listaHorarios= tutorServices.listarHorariosDeTutoriaxSemana(anio, tperiodo, c_codigo,dia);
			if(listaHorarios.size()>0){
				listAsistenciaTutoria.clear();
				for(TutoriaBO tbo :listaHorarios){
					listAsistenciaTutoria.add(convertirAModel(tbo));
				}
			}
    		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void guardar() {
        System.out.println("metodo guardar :  Hora ini : "+asistenciaTutoriaModel.getHoraIni() +"\n"+
        "Hora fin: "+ asistenciaTutoriaModel.getHoraFin());
        System.out.println("Dia: " + dia+ "Profe:"+ asistenciaTutoriaModelSelect.gettCodigo()+ "-"+"Nombre: "+asistenciaTutoriaModelSelect.getA_apellido());
        TutoriaBO tutoriabo=  new TutoriaBO();
        tutoriabo.setaCodigo(asistenciaTutoriaModelSelect.getA_codigo());//codgo alumno
        tutoriabo.settAnioo(String.valueOf(hoy.get(Calendar.YEAR)));
        tutoriabo.settPeriodo(asistenciaTutoriaModelSelect.gettPeriodo());
        tutoriabo.setpCodigo(asistenciaTutoriaModelSelect.getP_codigo());//tutor
        tutoriabo.setDia(asistenciaTutoriaModelSelect.getDia());
        tutoriabo.setcCodigo(asistenciaTutoriaModelSelect.getC_codigo());
        tutoriabo.setHoraIni(asistenciaTutoriaModelSelect.getHoraIni());
        tutoriabo.setHoraFin(asistenciaTutoriaModelSelect.getHoraFin());
 
        try {
			tutorServices.guardarDatosTutoria(tutoriabo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void imprimirReporteHorarioAlumno(){
    	System.out.println("Impresion de reporte de horario:");
    	
    	ControladorReporte reporte =  new ControladorReporte();
    	reporte.setNombreReporte("horarioTutoriaAlumno");
    	reporte.generarReporteHorarioDocente(obtenerParametros()  ,obtenerCampos() );
    	for(AsistenciaTutoriaModel horario  :listAsistenciaTutoria){
    		System.out.println(horario);
    	}
    }
    
    public void imprimirReporteHorarioDocente(){
    	System.out.println("Impresion de reporte de horario:");
    	
    	ControladorReporte reporte =  new ControladorReporte();
    	reporte.setNombreReporte("horarioTutoriaProfesor");
    	reporte.generarReporteHorarioDocente(obtenerParametros()  ,obtenerCampos() );
    	for(AsistenciaTutoriaModel horario  :listAsistenciaTutoria){
    		System.out.println(horario);
    	}
    }

    private Map obtenerParametros(){
    	Map pars = new HashMap();
    	
    	pars.put("nomPro", asistenciaTutoriaModelSelect.getP_nombre());
    	pars.put("escudounmsm", "imagenes/unmsm.gif");
    	return pars;
    }
    
    private Collection obtenerCampos(){
    	Collection list = new ArrayList();
    	for(AsistenciaTutoriaModel model : listAsistenciaTutoria){
    		HorarioTutoria horarioDocente =new HorarioTutoria();
    		horarioDocente.setCodCurso(model.getC_codigo());
    		horarioDocente.setDia(model.getDia());
    		horarioDocente.setHoraFin(model.getHoraFin());
    		horarioDocente.setHoraIni(model.getHoraIni());
    		horarioDocente.setNomCurso(model.getC_nombre());
    		horarioDocente.setNomProfesor(model.getP_nombre()+" "+model.getP_apellidos());
    		horarioDocente.setRepitencias(model.getRepitencia());
    		horarioDocente.setCodAlu(model.getA_codigo());
    		horarioDocente.setNomAlu(model.getA_nombre()+" "+model.getA_apellido());
    		
    		list.add(horarioDocente);
    	}
    	return list;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
 

    public AsistenciaTutoriaModel getAsistenciaTutoriaModelSelect() {
        return asistenciaTutoriaModelSelect;
    }

    public AsistenciaTutoriaModel getAsistenciaTutoriaModel() {
        return asistenciaTutoriaModel;
    }

    public void setAsistenciaTutoriaModelSelect(
            AsistenciaTutoriaModel asistenciaTutoriaModelSelect) {
        this.asistenciaTutoriaModelSelect = asistenciaTutoriaModelSelect;
    }

    public List<AsistenciaTutoriaModel> getListAsistenciaTutoria() {
        return listAsistenciaTutoria;
    }

    public void setListAsistenciaTutoria(List<AsistenciaTutoriaModel> listAsistenciaTutoria) {
        this.listAsistenciaTutoria = listAsistenciaTutoria;
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setAsistenciaTutoriaModel(
			AsistenciaTutoriaModel asistenciaTutoriaModel) {
		this.asistenciaTutoriaModel = asistenciaTutoriaModel;
	}
    
    public void buscarAlumno(){
    	String codigo= asistenciaTutoriaModel.getA_codigo();
    	System.out.println("Alumno: "+codigo);
    	
    	
    }
    
    public List<CursoBO> obtenerCursos(List<MatriculaBO> listaMatricula){
    	List<CursoBO> listarCursos = new ArrayList<CursoBO> ();
    	for(MatriculaBO mbo  : listaMatricula){
    		CursoBO cbo= new CursoBO();
    		cbo.setcCodigo(mbo.getcCodigo());
    		cbo.setNombre(mbo.getcNombre());
    		listarCursos.add(cbo);
    	}
    	
    	return listarCursos;
    }

	public List<DiaSemanaModel> getDiasSemanaModel() {
		return diasSemanaModel;
	}

	public void setDiasSemanaModel(List<DiaSemanaModel> diasSemanaModel) {
		this.diasSemanaModel = diasSemanaModel;
	}

	public DiaSemanaModel getDiaSemanaModel() {
		return diaSemanaModel;
	}

	public void setDiaSemanaModel(DiaSemanaModel diaSemanaModel) {
		this.diaSemanaModel = diaSemanaModel;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public AsistenciaTutoriaModel convertirAModel(TutoriaBO  tutoriabo){
		AsistenciaTutoriaModel horario = new AsistenciaTutoriaModel();
		try {
			AlumnoBO abo =   alumnoServices.obtenerAlumno(tutoriabo.getaCodigo());
		horario.setA_codigo(tutoriabo.getaCodigo());
		horario.setA_nombre(abo.getaNombre()+" "+abo.getaApellido());
		horario.setC_codigo(tutoriabo.getcCodigo());
		horario.setC_nombre(buscarCursoenLista(tutoriabo.getcCodigo()).getNombre());
		horario.setRepitencia("");
		horario.setP_codigo(tutoriabo.getpCodigo());
		ProfesorBO pbp=buscarProfesorenLista(tutoriabo.getpCodigo());
		horario.setP_nombre(pbp.getpNombre());
		horario.setP_apellidos(pbp.getpApellidos());
		horario.setDia(tutoriabo.getDia());
		horario.setHoraIni(tutoriabo.getHoraIni());
		horario.setHoraFin(tutoriabo.getHoraFin());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return horario;
	}
	
	public String visualizarHorarioDeTutoriaSemanal() {
		asistenciaTutoriaModelSelect = new AsistenciaTutoriaModel();
		listarProfesores();
		listarCursos();
		System.out.println("visualizarHorarioDeTutoriaSemanal");
		asistenciaTutoriaModelSelect.settPeriodo(obtenerPeriodo());
		return "/paginas/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";

	}
	
	public String visualizarHorarioDeTutoriaSemanalUser() {
		asistenciaTutoriaModelSelect = new AsistenciaTutoriaModel();
		listarProfesores();
		listarCursos();
		System.out.println("visualizarHorarioDeTutoriaSemanal");
		asistenciaTutoriaModelSelect.settPeriodo(obtenerPeriodo());
		return "/paginas/user/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";

	}
	
	public String visualizarHorarioDeTutoriaSemanalDirAca() {
		asistenciaTutoriaModelSelect = new AsistenciaTutoriaModel();
		listarProfesores();
		listarCursos();
		System.out.println("visualizarHorarioDeTutoriaSemanal");
		asistenciaTutoriaModelSelect.settPeriodo(obtenerPeriodo());
		return "/paginas/diraca/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";

	}
	

	public String visualizarHorarioDeTutoriaSemanalUnayoe() {
		asistenciaTutoriaModelSelect = new AsistenciaTutoriaModel();
		listarProfesores();
		listarCursos();
		System.out.println("visualizarHorarioDeTutoriaSemanal");
		asistenciaTutoriaModelSelect.settPeriodo(obtenerPeriodo());
		return "/paginas/unayoe/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";

	}
	
	private CursoBO buscarCursoenLista(String c_codigo){
		CursoBO curso = new CursoBO();
		List<CursoBO> listarCursos = asistenciaTutoriaModel.getListarCursos();
		for(CursoBO c : listarCursos){
			if(c.getcCodigo().equalsIgnoreCase(c_codigo))
				return c;
		}
		return curso;
	}
	
	private ProfesorBO  buscarProfesorenLista(String p_codigo){
		ProfesorBO profesor = new ProfesorBO();
		List<ProfesorBO> listarProfesor = asistenciaTutoriaModel.getListarProfesores();
		for(ProfesorBO p : listarProfesor){
			if(p.getpCodigo().equalsIgnoreCase(p_codigo))
				return p;
		}
		return profesor;
	}
	
	// metodos que domina de la pantallas
	public String visualizarHorarioDeTutoriaAlumno() {
		asistenciaTutoriaModelSelect = new AsistenciaTutoriaModel();
		listarCursos();
		listarProfesores();
		System.out.println("visualizarHorarioDeTutoriaAlumno");
		asistenciaTutoriaModelSelect.settPeriodo(obtenerPeriodo());
		return "/paginas/visualizar/visualizarHorariosDeTutoriaAlumno.xhtml";
	}
	
	// metodos que domina de la pantallas
		public String visualizarHorarioDeTutoriaAlumnoUnayoe() {
			asistenciaTutoriaModelSelect = new AsistenciaTutoriaModel();
			listarCursos();
			listarProfesores();
			System.out.println("visualizarHorarioDeTutoriaAlumno");
			asistenciaTutoriaModelSelect.settPeriodo(obtenerPeriodo());
			return "/paginas/unayoe/visualizar/visualizarHorariosDeTutoriaAlumno.xhtml";
		}
	
	public String visualizarHorarioDeTutoriaAlumnoUser() {
		asistenciaTutoriaModelSelect = new AsistenciaTutoriaModel();
		listarCursos();
		listarProfesores();
		System.out.println("visualizarHorarioDeTutoriaAlumno");
		asistenciaTutoriaModelSelect.settPeriodo(obtenerPeriodo());
		return "/paginas/user/visualizar/visualizarHorariosDeTutoriaAlumno.xhtml";
	}
	
	public String visualizarHorarioDeTutoriaAlumnoDirAca() {
		asistenciaTutoriaModelSelect = new AsistenciaTutoriaModel();
		listarCursos();
		listarProfesores();
		System.out.println("visualizarHorarioDeTutoriaAlumno");
		asistenciaTutoriaModelSelect.settPeriodo(obtenerPeriodo());
		return "/paginas/diraca/visualizar/visualizarHorariosDeTutoriaAlumno.xhtml";
	}
	public String visualizarHorarioDeTutoriaTutor() {
		asistenciaTutoriaModelSelect = new AsistenciaTutoriaModel();
		System.out.println("visualizarHorarioDeTutoria");
		listarCursos();
		asistenciaTutoriaModelSelect.settPeriodo(obtenerPeriodo());
		return "/paginas/visualizar/visualizarHorariosDeTutoriaDocente.xhtml";

	}

	public String visualizarHorarioDeTutoriaTutorUser() {
		asistenciaTutoriaModelSelect = new AsistenciaTutoriaModel();
		System.out.println("visualizarHorarioDeTutoria");
		listarCursos();
		asistenciaTutoriaModelSelect.settPeriodo(obtenerPeriodo());
		return "/paginas/user/visualizar/visualizarHorariosDeTutoriaDocente.xhtml";

	}

	public String visualizarHorarioDeTutoriaTutorDirAca() {
		asistenciaTutoriaModelSelect = new AsistenciaTutoriaModel();
		System.out.println("visualizarHorarioDeTutoria");
		listarCursos();
		asistenciaTutoriaModelSelect.settPeriodo(obtenerPeriodo());
		return "/paginas/diraca/visualizar/visualizarHorariosDeTutoriaDocente.xhtml";

	}
	public String visualizarHorarioDeTutoriaTutorUnayoe() {
		asistenciaTutoriaModelSelect = new AsistenciaTutoriaModel();
		System.out.println("visualizarHorarioDeTutoria");
		listarCursos();
		asistenciaTutoriaModelSelect.settPeriodo(obtenerPeriodo());
		return "/paginas/unayoe/visualizar/visualizarHorariosDeTutoriaDocente.xhtml";

	}
	
}
    
    


