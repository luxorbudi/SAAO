package pe.edu.sistemas.unayoe.controlador;

import javax.faces.bean.ViewScoped;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pe.edu.sistemas.unayoe.model.DatosAlumnoExcelModel;
import pe.edu.sistemas.unayoe.model.ArchivoModel;
import pe.edu.sistemas.unayoe.services.AlumnoServices; 
import pe.edu.sistemas.unayoe.services.CursoServices;
import pe.edu.sistemas.unayoe.services.GrupoServices;
import pe.edu.sistemas.unayoe.services.MatriculaServices;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO; 
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.GrupoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaBO;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile; 

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext; 

@Controller("datosAlumnosMBean")
@ViewScoped
public class DatosAlumnosMBean {
	
	@Autowired
	private AlumnoServices alumnoServices;
	@Autowired
	private CursoServices cursoServices;
	@Autowired
	private GrupoServices grupoServices;
	@Autowired
	private MatriculaServices matriculaServices;
	@Autowired
	private List<DatosAlumnoExcelModel>  datosAlumnoExcelModels;
	@Autowired
	private DatosAlumnoExcelModel  datosAlumnoExcelModel;
	@Autowired
	private ArchivoModel archivoModel;
	private  String nombreArchivo;
	private UploadedFile file;
	private List <AlumnoBO> listaAlumnosBO;
	private List <CursoBO> listaCursosBO;
	private List <GrupoBO> listaGruposBO;
	private List <MatriculaBO> listaMatriculasBO;
	
	private static int COD_ALUMNO=0;
	private static int APE_PATERNO=1;
	private static int APE_MATERNO=2;
	private static int NOM_ALUMNO=3;
	private static int ANIO_INGRESO=4;
	private static int COD_SEMESTRE=5; //EJEMPLO: 20141
	private static int COD_PLAN=6; // EJEMPLO: 2009
	private static int COD_ESPECIAL=7;
	private static int COD_ASIGNATURA=8;
	private static int COD_SECCION=9;
	private static int COD_GRUPO=10; //BLANCO , GE : ES ELECTIVO
	private static int COD_AULA=11; //BLANCO
	private static int DES_ASIGNATURA=12; //BLANCO
	private static int NUM_CRED=13; //BLANCO
	private static int MOD_USER=14; // COD_USUARIO QUE REGISTRO LA MATRICULA
	private static int MOD_FECHA=15;
	private static int REP_PLAN=16;//NUMERO DE REPITENCIAS
	private static int REP_X_EQUIV=17; //POR CURSO DE EQUVALENCIA
	private static int COUNT=18; // CICLO
	
	
	private Map<String,AlumnoBO> mapaAlumnosBO = new HashMap<String,AlumnoBO>();
	
	
	public List<DatosAlumnoExcelModel> getDatosAlumnoExcelModels() {
		return datosAlumnoExcelModels;
	}

	public void setDatosAlumnoExcelModels(List<DatosAlumnoExcelModel> DatosAlumnoExcelModels) {
		this.datosAlumnoExcelModels = DatosAlumnoExcelModels;
	}

	public DatosAlumnoExcelModel getDatosAlumnoExcelModel() {
		return datosAlumnoExcelModel;
	}

	public void setDatosAlumnoExcelModel(DatosAlumnoExcelModel DatosAlumnoExcelModel) {
		this.datosAlumnoExcelModel = DatosAlumnoExcelModel;
	}
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public ArchivoModel getArchivoModel() {
		return archivoModel;
	}

	public void setArchivoModel(ArchivoModel archivoModel) {
		this.archivoModel = archivoModel;
	}


	public void handleFileUpload(FileUploadEvent event) {
		datosAlumnoExcelModels.clear();
		UploadedFile archivoCargado = event.getFile();
        if(archivoCargado != null) {
        	file=event.getFile();
        	archivoModel.setNombre(archivoCargado.getFileName());
            FacesMessage message = new FacesMessage("Succesful", archivoCargado.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }else{
        	System.out.println("No hay  ningun archivo");
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Archivo es nulo", null));
        }
        InputStream file;

        try {
        	 
        file = event.getFile().getInputstream();
       
        XSSFWorkbook wb = new XSSFWorkbook(file);
        
        XSSFSheet ws = wb.getSheetAt(0);//Sheet ws = wb.getSheet("Input");
        int rowNum = ws.getLastRowNum() + 1;
        int colNum = ws.getRow(0).getLastCellNum();
        String [][] data = new String [rowNum] [colNum];
        datosAlumnoExcelModels.removeAll(datosAlumnoExcelModels);
        for(int i = 1; i <rowNum; i++){
        	XSSFRow row = ws.getRow(i);
                for (int j = 0; j < colNum; j++){
                	XSSFCell cell = row.getCell(j);
                    String value =  cell!=null?cell.toString():"";
                    data[i][j] = value;
                    System.out.println ("the value is " + value);
                }
                DatosAlumnoExcelModel dataModel=convertirAModelAlumno(row);
                if(Integer.parseInt(dataModel.getRepitencias())>=3)
                	datosAlumnoExcelModels.add(dataModel);
        }
        wb.close();
        } catch (IOException e) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error reading file" + e, null));
        	e.printStackTrace();
        }
        
        //actualizarDatatable();
        
        //return "/paginas/ingresarAsistenciaTutoria.xhtml";
    }
	
	public   DatosAlumnoExcelModel  convertirAModelAlumno(XSSFRow fila_alumno){
		DatosAlumnoExcelModel datos = new DatosAlumnoExcelModel();
		
		datos.setCod_alumno(fila_alumno.getCell(COD_ALUMNO)!=null?fila_alumno.getCell(COD_ALUMNO).toString():"");
		datos.setAp_paterno(fila_alumno.getCell(APE_PATERNO)!=null?fila_alumno.getCell(APE_PATERNO).toString():"");
		datos.setAp_materno(fila_alumno.getCell(APE_MATERNO)!=null?fila_alumno.getCell(APE_MATERNO).toString():"");
		datos.setNombres(fila_alumno.getCell(NOM_ALUMNO)!=null?fila_alumno.getCell(NOM_ALUMNO).toString():"");
		datos.setAnio_ingreso(fila_alumno.getCell(ANIO_INGRESO)!=null?validarDatoEntero(fila_alumno.getCell(ANIO_INGRESO)).toString():"0");
		datos.setCod_sem(fila_alumno.getCell(COD_SEMESTRE)!=null?validarDatoEntero(fila_alumno.getCell(COD_SEMESTRE)).toString():"0");
		datos.setCod_plan(fila_alumno.getCell(COD_PLAN)!=null?validarDatoEntero(fila_alumno.getCell(COD_PLAN)).toString():"0");
		datos.setCod_curso(fila_alumno.getCell(COD_ASIGNATURA)!=null?validarDatoEntero(fila_alumno.getCell(COD_ASIGNATURA)).toString():"0");
		datos.setCod_seccion(fila_alumno.getCell(COD_SECCION)!=null? String.valueOf(validarDatoEntero(fila_alumno.getCell(COD_SECCION))):"0");
		datos.setDes_curso( fila_alumno.getCell(DES_ASIGNATURA)!=null?fila_alumno.getCell(DES_ASIGNATURA).toString():""); 
		datos.setCreditos(fila_alumno.getCell(NUM_CRED)!=null?validarDatoEntero(fila_alumno.getCell(NUM_CRED)).toString():"0");
		datos.setRepitencias(fila_alumno.getCell(REP_PLAN)!=null?validarDatoEntero(fila_alumno.getCell(REP_PLAN)).toString():"");
		datos.setCiclo(fila_alumno.getCell(COUNT)!=null?fila_alumno.getCell(COUNT).toString():"");
		return datos;
	}
	
	public AlumnoBO convertirAAlumnoBO(DatosAlumnoExcelModel alumnoData){
		AlumnoBO abo=new AlumnoBO();
		abo.setaCodigo(alumnoData.getCod_alumno());
		abo.setaApellido(alumnoData.getAp_paterno()+" "+alumnoData.getAp_materno());
		abo.setaNombre(alumnoData.getNombres());
		return abo;
	}
	
	public CursoBO convertirACursoBO(DatosAlumnoExcelModel cursoData){
		CursoBO cbo=new CursoBO();
		cbo.setcCodigo(cursoData.getCod_curso());
		cbo.setCreditos(new BigInteger(String.valueOf(cursoData.getCreditos())));
		cbo.setNombre(cursoData.getDes_curso());
		return cbo;
	}
	
	public GrupoBO convertirAGrupoBO(DatosAlumnoExcelModel grupoData){
		GrupoBO gbo=new GrupoBO();
		BigDecimal periodo=new BigDecimal(grupoData.getCod_sem().substring(4));
		Integer periodoEntero = periodo.intValue();
		gbo.setcCodigo(grupoData.getCod_curso());
		gbo.setAnio(grupoData.getCod_sem().substring(0, 4));
		gbo.setPeriodo(periodoEntero.toString());
		gbo.setGrupo(grupoData.getCod_seccion().toString());
		return gbo;
	}
	
	public MatriculaBO convertirAMatriculaBO(DatosAlumnoExcelModel matriculaData){
		MatriculaBO mbo=new MatriculaBO();
		BigDecimal repitencia=new BigDecimal(matriculaData.getRepitencias());
		Integer repitenciaEntero = repitencia.intValue();
		BigDecimal periodo=new BigDecimal(matriculaData.getCod_sem().substring(4));
		Integer periodoEntero = periodo.intValue();
		mbo.setaCodigo(matriculaData.getCod_alumno());
		mbo.setAnio(matriculaData.getCod_sem().substring(0, 4));
		mbo.setRepitencias(repitenciaEntero.toString());
		mbo.setPeriodo(periodoEntero.toString());
		mbo.setGrupo(matriculaData.getCod_seccion().toString());
		mbo.setcCodigo(matriculaData.getCod_curso());
		return mbo;
	}
	
	
	public void guardar(){
		System.out.println("Guardar");
		Boolean repetido = false;
		int resultado=0;
		if(datosAlumnoExcelModels.size()>0){
			System.out.println("Hay alumnos");
			try {
				convertirListaAObjectosNegocios(datosAlumnoExcelModels);
				alumnoServices.guardarAlumnos(listaAlumnosBO);
				cursoServices.guardarCursos(listaCursosBO);
				grupoServices.guardarGrupos(listaGruposBO);
				resultado=matriculaServices.guardarMatriculas(listaMatriculasBO);

				if(resultado==1)
					mostrarMensajeCorrecto();
				if(resultado==0)
					mostrarMensajeRepetidos();
				
				datosAlumnoExcelModels.clear();
				archivoModel.setNombre("");
			} catch (Exception e) {
				mostrarMensajeError();
				e.printStackTrace();
			}
		}else{
			FacesMessage message = new FacesMessage("Faltan datos", "No hay alumnos cargados");
            FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	
	public List <AlumnoBO> convertirListaAObjectosNegocios(List <DatosAlumnoExcelModel>  listaDatosAlumnoExcelModel ){
		List <AlumnoBO> lista = new ArrayList<AlumnoBO>();
		
		listaAlumnosBO= new ArrayList<AlumnoBO>();
		listaCursosBO= new ArrayList<CursoBO>();
		listaGruposBO= new ArrayList<GrupoBO>();
		listaMatriculasBO= new ArrayList<MatriculaBO>();
		
		List<AlumnoBO> listaAlumnos ;
		
		for(DatosAlumnoExcelModel datamodel : listaDatosAlumnoExcelModel){
			AlumnoBO abo= convertirAAlumnoBO(datamodel);
			CursoBO  cbo= convertirACursoBO(datamodel);
			GrupoBO  gbo= convertirAGrupoBO(datamodel);
			MatriculaBO  mbo= convertirAMatriculaBO(datamodel);
			
			if(!listaAlumnosBO.contains(abo))
				listaAlumnosBO.add(abo);
			
			if(!listaCursosBO.contains(cbo))
				listaCursosBO.add(cbo);
			
			if(!listaGruposBO.contains(gbo))
				listaGruposBO.add(gbo);
			
			if(!listaMatriculasBO.contains(mbo))
				listaMatriculasBO.add(mbo);
			
			
			if(!mapaAlumnosBO.containsKey(abo.getaCodigo()))
				mapaAlumnosBO.put(abo.getaCodigo(),abo);
			
			 
			//lista.add(new ArrayList(mapaAlumnosBO.values()));
		}
		listaAlumnos = new ArrayList<AlumnoBO>(mapaAlumnosBO.values());
		return listaAlumnos;
	}
	
	
	 
	 public UploadedFile getFile() {
	        return file;
	    }
	 
	    public void setFile(UploadedFile file) {
	        this.file = file;
	    }
	     
	    public void mostrar() {
	        if(file != null) {
	            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
	            FacesContext.getCurrentInstance().addMessage(null, message);
	        }else{
	        	System.out.println("No hay  ningun archivo");
	        	
	        }
	        
	        System.out.println("nombre del acchivo excel es : "+this.nombreArchivo);
	        nombreArchivo="un nombre";
	    }
   
	 public void actualizarDatatable() {
	    RequestContext context = RequestContext.getCurrentInstance();
	    context.reset("pnlGridLoginAcceso:tablaAlumnos");
	 }
	 
	 public Integer validarDatoEntero(XSSFCell  valorCelda){
		 String valorCadena="";
		 Double valorNumerico=0D;
		 switch(valorCelda.getCellType()) {
		 	case  XSSFCell.CELL_TYPE_NUMERIC :valorNumerico=valorCelda.getNumericCellValue(); return valorNumerico.intValue() ;
		 	case  XSSFCell.CELL_TYPE_STRING :  valorCadena=valorCelda.getStringCellValue(); return Integer.parseInt(valorCadena);
		 	case  XSSFCell.CELL_TYPE_BLANK :   return 0;
		 	default : return 0;
		 }
	 }
	 
	 private void mostrarMensajeCorrecto(){
		 System.out.println("Entra mensaje");
		 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Los alumnos se registraron correctamente.", "Correcto");
         FacesContext.getCurrentInstance().addMessage(null, message);
	 }
	 
	 private void mostrarMensajeRepetidos(){
		 System.out.println("Entra mensaje");
		 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,"El excel tiene registros ya existentes.", "Repetidos");
         FacesContext.getCurrentInstance().addMessage(null, message);
	 }
	 
	 private void mostrarMensajeError(){
		 System.out.println("Entra mensaje");
		 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ocurrio un error al guardar.", "Error");
         FacesContext.getCurrentInstance().addMessage(null, message);
	 }


}
