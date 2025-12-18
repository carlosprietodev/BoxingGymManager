
package es.boxinggymmanager.controllers;

import es.boxinggymmanager.DAO.EmpleadoDAO;
import es.boxinggymmanager.DAO.IEmpleadoDAO;
import es.boxinggymmanager.DAO.IGenericoDAO;
import es.boxinggymmanager.DAOFactory.DAOFactory;
import es.boxinggymmanager.beans.Cliente;
import es.boxinggymmanager.beans.Empleado;
import es.boxinggymmanager.beans.Entrenamiento;
import es.boxinggymmanager.models.Utilities;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ".";
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String opcion = request.getParameter("accion");
        String url = ".";
        HttpSession session = request.getSession();
        DAOFactory daoF = DAOFactory.getDAOFactory();
        IEmpleadoDAO empleadoDAO =  daoF.getEmpleadoDAO();
        IGenericoDAO genericoDAO = daoF.getGenericoDAO();
        Empleado empleado = null;

        switch (opcion) {
            case"Iniciar":
                url="/JSP/InicioSesion/inicioSesion.jsp";
                break;

            case"Privacidad":
                url="/JSP/PrivacidadLegalidad/privacidad.jsp";
                break;

                // opcion para iniciar sesion
            case "Entrar":
// obtenemos el email y la contraseña del formulario
                String emailEmpleado = request.getParameter("email_login");
                String passwordEmpleado = request.getParameter("password_login");

                // convertimos a md5 la password introducida en el formulario de inicio sesion
                String passwordMd5 = Utilities.passwordMd5(passwordEmpleado);

                // comprobamos si existe un empleado con ese email
                empleado = empleadoDAO.getEmpleadoEmail(emailEmpleado);

                if(empleado!=null) {
                    if(empleado.getPassword().equals(passwordMd5)) {
                        session.setAttribute("empleado", empleado);
                        url="/JSP/Empleado/empleado.jsp";
                    }else{
                        request.setAttribute("aviso", "La contraseña introducida no es correcta.");
                        url="/JSP/InicioSesion/inicioSesion.jsp";
                    }

                }else{
                    request.setAttribute("aviso","El email introducido no es correcto..");
                    url= "/JSP/InicioSesion/inicioSesion.jsp";
                }
                break;

                // opcion para cerrar sesion
            case "Cerrar":
// obtenemos el empleado de la sesion
                empleado = (Empleado) session.getAttribute("empleado");
                if(empleado!=null) {
                    session.removeAttribute("empleado");
                    url="/JSP/InicioSesion/inicioSesion.jsp";
                }

                break;

        }
        request.getRequestDispatcher(url).forward(request, response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
