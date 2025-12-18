package es.boxinggymmanager.controllers;

import es.boxinggymmanager.DAO.IEmpleadoDAO;
import es.boxinggymmanager.DAO.IGenericoDAO;
import es.boxinggymmanager.DAO.IPeticionDAO;
import es.boxinggymmanager.DAOFactory.DAOFactory;
import es.boxinggymmanager.beans.Empleado;
import es.boxinggymmanager.beans.Entrenamiento;
import es.boxinggymmanager.beans.Peticion;
import es.boxinggymmanager.models.Utilities;
import jakarta.mail.MessagingException;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



@WebServlet(name = "PeticionController", urlPatterns = {"/PeticionController"})
public class PeticionController extends HttpServlet {

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

        IGenericoDAO genericoDAO = daoF.getGenericoDAO();
        IPeticionDAO peticionDAO = daoF.getPeticionDAO();
        Peticion peticion= null;
        Empleado empleado = null;

        switch (opcion) {


            // opcion para el boton de enviar del formulario de contacto
            case "Enviar":
                empleado = (Empleado) genericoDAO.getById(1,Empleado.class);
                if(empleado != null) {
                // obtenemos los parametros del formulario para enviar el correo
                    String nombre = request.getParameter("nombre");
                    String email = request.getParameter("email");
                    String telefono = request.getParameter("telefono");
                    String tipoEntrenamiento = request.getParameter("entrenamiento");

                    try{
                        // llamamos al metodo de enviar correo pasando el request y los datos del formulario
                        Utilities.enviarCorreo(request,email,nombre,telefono,tipoEntrenamiento);
                        System.out.println("Correo enviado");

                    }catch (Exception e){
                        // si no se envia mostramos un mensaje por consola
                        e.printStackTrace();
                        System.out.println("CORREO NO ENVIADO");

                    }
                    // creamos el objeto peticion
                    peticion = new Peticion();
                    peticion.setEmpleado(empleado);

                    // obtenemos la fecha actual
                    Date fechaPeticion = new Date();
                    peticion.setFechaPeticion(fechaPeticion);
                    peticion.setEstado(Peticion.Estado.PENDIENTE);


 // obtener el tipo de entrenamiento del formulario y parsearlo a Enum con el metodo del Utilities
                    Peticion.TipoEntrenamiento tipoEntrenamientoEnum = (Peticion.TipoEntrenamiento) Utilities.convertirStringEnum(tipoEntrenamiento);
                    peticion.setTipoEntrenamiento(tipoEntrenamientoEnum);

                    try {
                        // Cargamos los datos de la peticion con BeanUtils
                        BeanUtils.populate(peticion,request.getParameterMap());

                        // guardamos el objeto peticion en la bbdd
                        genericoDAO.insertOrUpdate(peticion);

                    }catch (IllegalAccessException | InvocationTargetException e) {
                        Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, e);
                    }

                }

                break;

            case "AceptarPeticion":
            // obtenemos el id de la peticion que vamos a aceptar y se la pasamos al metodo del dao que actualiza la peticion
                int idPeticion = Integer.parseInt(request.getParameter("idPeticion"));
                if(idPeticion>0){
                    peticionDAO.aceptarPeticion(idPeticion);
                }


                break;

            case "RechazarPeticion":
                // obtenemos el id de la peticion que vamos a rechazar y se la pasamos al metodo del dao que actualiza la peticion
                int idPeticionRechazar = Integer.parseInt(request.getParameter("idPeticionRechazar"));
                if(idPeticionRechazar>0){
                    peticionDAO.rechazarPeticion(idPeticionRechazar);

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

