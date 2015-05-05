package pe.edu.sistemas.unayoe.controlador;

import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.edu.sistemas.unayoe.model.LoginModel;
import pe.edu.sistemas.unayoe.services.UsuarioServices;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;

@Controller("loginMBean")
@ViewScoped
public class LoginMBean {

    @Autowired
    private LoginModel loginModel;
    @Autowired
    private UsuarioServices usuarioServices;
    private String rol="0"; 
    
    public LoginMBean() {
        System.out.println(":::::::INICIO :::::::::");
        rol="0";
    } 

    public String obtieneRol() {
        System.out.println("::::::::::::::: ENTRO ACA1 ::::::::::::::::");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("::::::::::::::: ENTRO ACA ::::::::::::::::");
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            User user = (User) auth.getPrincipal();
            String name = user.getUsername();
            System.out.println("USER INGRESADO" + name);
             Object[] rol = user.getAuthorities().toArray();
            
            
            System.out.println("USUARIO " + name + "  CON ROL " + rol[0].toString());
            return rol[0].toString();
        }

        return "0";
    }


    public String entrar() {
        try {

            //httpServletRequest = null;
            UsuarioBO usuarioBO = new UsuarioBO();
            usuarioBO = usuarioServices.obtenerUsuario(this.loginModel
                    .getUsuario());
            System.out.println("usuarioBO " + usuarioBO);
            if (usuarioBO != null) {
                //httpServletRequest.setAttribute("sessionUsuario", usuarioBO.getUsuario());
                //FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Correcto", null);
                //faceContext.addMessage(null, facesMessage);
                return "/paginas/principal.xhtml";
            } else {
                //facesMessage=new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrecto", null);
                //faceContext.addMessage(null, facesMessage);
                return "/login.xhtml";
            }

        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
        return "/login.xhtml";

    }

    public LoginModel getLoginModel() {
        return loginModel;
    } 

	/**
     * @return the rol
     */
    public String getRol() {
        rol=obtieneRol();
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
}
