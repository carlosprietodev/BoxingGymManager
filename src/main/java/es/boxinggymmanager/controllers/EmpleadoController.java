package es.boxinggymmanager.controllers;

import es.boxinggymmanager.DAO.IEntrenamientoDAO;
import es.boxinggymmanager.DAO.IGenericoDAO;
import es.boxinggymmanager.DAOFactory.DAOFactory;
import es.boxinggymmanager.beans.Cliente;
import es.boxinggymmanager.beans.Empleado;
import es.boxinggymmanager.beans.Entrenamiento;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "EmpleadoController", urlPatterns = {"/EmpleadoController"})
public class EmpleadoController extends HttpServlet {
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
        String url = "/JSP/Empleado/empleado.jsp";
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
        String url = "/JSP/Empleado/empleado.jsp";
        HttpSession session = request.getSession();
        DAOFactory daoF = DAOFactory.getDAOFactory();
        IEntrenamientoDAO entrenamientoDAO =  daoF.getEntrenamientoDAO();
        IGenericoDAO genericoDAO = daoF.getGenericoDAO();

        List <Entrenamiento> listaEntrenamientos = null;
        List <Cliente> listaClientes = null;
        Cliente cliente = null;
        Empleado empleado = null;
        Entrenamiento entrenamiento = null;
        switch (opcion) {
            // opcion para ir la vista de nuevo cliente
            case"Nuevo":
                listaEntrenamientos = genericoDAO.selectAll(Entrenamiento.class);
                if(listaEntrenamientos!=null && !listaEntrenamientos.isEmpty()){
                    request.setAttribute("listaEntrenamientos", listaEntrenamientos);
                    url="/JSP/Empleado/crearCliente.jsp";

                }
                break;

            case"Crear":
                empleado = (Empleado) session.getAttribute("empleado");
                if(empleado != null) {
                    cliente = new Cliente();
                    cliente.setEmpleado((empleado));

                    // obtenemos la fecha actual
                    Date fechaRegistro = new Date();
                    cliente.setFechaRegistro(fechaRegistro);

 // obtener el id del entrenamiento seleccionado buscar en la bbdd ese entrenamiento y crear un objeto entrenamiento que es el de cliente

                    Integer idEntrenamiento = Integer.parseInt(request.getParameter("entrenamiento"));
                    entrenamiento = (Entrenamiento) genericoDAO.getById(idEntrenamiento, Entrenamiento.class);
                    // asignamos el entrenamiento al cliente
                    cliente.setEntrenamiento(entrenamiento);

                    try {
                        // Evitar que BeanUtils intente poblar 'entrenamiento'
                        Map<String, String[]> params = new HashMap<>(request.getParameterMap());
                        params.remove("entrenamiento");
                        // Cargamos los datos del cliente con BeanUtils sin el entrenamiento
                        BeanUtils.populate(cliente, params);

                        // guardamos el objeto en la bbdd
                        genericoDAO.insertOrUpdate(cliente);
                        url = "/JSP/Empleado/verClientes.jsp";

                    }catch (IllegalAccessException | InvocationTargetException e) {
                        Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, e);
                    }

                }
                break;

            case "Ver":
                url = "/JSP/Empleado/verClientes.jsp";

                break;

            // opcion para ir a la vista de editar cliente
            case "Editar":
                listaEntrenamientos = genericoDAO.selectAll(Entrenamiento.class);
                // obtenemos el id del cliente que queremos modificar
                Integer idCliente = Integer.parseInt(request.getParameter("idCliente"));
                // buscamos en la bbdd por id y lo guardamos en un objeto cliente
                cliente = (Cliente) genericoDAO.getById(idCliente, Cliente.class);
                // si ese cliente existe vamos a la vista de modificar y pasamos el cliente por sesion
                if(cliente != null){
                    session.setAttribute("cliente", cliente);
                    request.setAttribute("listaEntrenamientos", listaEntrenamientos);
                    url = "/JSP/Empleado/modificarCliente.jsp";
                }
                break;

            case "Modificar":
                // obtebemos el cliente de la sesion
                cliente = (Cliente) session.getAttribute("cliente");
                if(cliente != null){

// obtener el id del entrenamiento seleccionado buscar en la bbdd ese entrenamiento y crear un objeto entrenamiento que es el de cliente

                    Integer idEntrenamientoNuevo = Integer.parseInt(request.getParameter("entrenamiento"));
                    entrenamiento = (Entrenamiento) genericoDAO.getById(idEntrenamientoNuevo, Entrenamiento.class);
                    cliente.setEntrenamiento(entrenamiento);

                    try {
                        // Evitar que BeanUtils intente poblar 'entrenamiento'
                        Map<String, String[]> params = new HashMap<>(request.getParameterMap());
                        params.remove("entrenamiento");
                        // Cargamos los datos del cliente con BeanUtils sin el entrenamiento
                        BeanUtils.populate(cliente, params);

                        // guardamos el objeto actualizado en la bbdd
                        genericoDAO.insertOrUpdate(cliente);
                        url = "/JSP/Empleado/verClientes.jsp";

                    }catch (IllegalAccessException | InvocationTargetException e) {
                        Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, e);
                    }

                }

                break;

            case "eliminarCliente":
                // obtenemos el id del cliente a eliminar

                Integer idClienteEliminar = Integer.parseInt(request.getParameter("idCliente"));

                // buscamos el cliente a eliminar por id en la bbdd
                cliente = (Cliente) genericoDAO.getById(idClienteEliminar, Cliente.class);
                if(cliente != null){
                    genericoDAO.delete(cliente);
                    url = "/JSP/Empleado/verClientes.jsp";

                }else{
                    url = "/JSP/Empleado/empleado.jsp";
                }

                break;


            case"VerPeticiones":
                url="JSP/Empleado/empleado.jsp";

                break;

            case"VerEstadisticas":
                url = "JSP/Empleado/verEstadisticas.jsp";
                break;

            case "VerEntrenamientos":
                // cargamos la lista de entrenamientos con sus clientes con el metodo del dao
                listaEntrenamientos = entrenamientoDAO.getEntrenamientosClientes();
                // si es distinta de null pasamos por peticion la lista y vamos a la vista de ver entrenamientos
                if(listaEntrenamientos != null){
                    request.setAttribute("listaEntrenamientos", listaEntrenamientos);
                    url = "/JSP/Empleado/verEntrenamientos.jsp";
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
