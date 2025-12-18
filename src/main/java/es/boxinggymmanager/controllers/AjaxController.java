package es.boxinggymmanager.controllers;

import es.boxinggymmanager.DAO.ClienteDAO;
import es.boxinggymmanager.DAO.IClienteDAO;
import es.boxinggymmanager.DAO.IEmpleadoDAO;
import es.boxinggymmanager.DAO.IGenericoDAO;
import es.boxinggymmanager.DAOFactory.DAOFactory;
import es.boxinggymmanager.beans.Cliente;
import es.boxinggymmanager.beans.Empleado;
import es.boxinggymmanager.models.Utilities;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "AjaxController", urlPatterns = {"/AjaxController"})
public class AjaxController extends HttpServlet {

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

        String accion = request.getParameter("accion");
        Cliente cliente = null;
        DAOFactory daoF = DAOFactory.getDAOFactory();
        IClienteDAO clienteDAO = daoF.getClienteDAO();


        switch (accion){
            case "verEmail":

                String email = request.getParameter("email");

                if(!email.isEmpty()){
                    cliente = clienteDAO.getClienteEmail(email);
                    // Creamos el objeto JSON para la respuesta
                    JSONObject jsonRespuesta = new JSONObject();
                    // si obtenemos un usuario con ese email, no esta disponible
                    if (cliente != null) {
                        jsonRespuesta.put("disponible", "no");
                    } else {
                        jsonRespuesta.put("disponible", "si");
                    }

                    // Enviamos la respuesta JSON al cliente
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(jsonRespuesta.toString());

                }
                break;

            case "verDni":
                String dni = request.getParameter("dni");

                if(!dni.isEmpty()){
                    cliente = clienteDAO.getClienteDni(dni);
                    // Creamos el objeto JSON para la respuesta
                    JSONObject jsonRespuesta = new JSONObject();
                    // si obtenemos un cliente con ese dni, no esta disponible
                    if (cliente != null) {
                        jsonRespuesta.put("disponible", "no");
                    } else {
                        jsonRespuesta.put("disponible", "si");
                    }

                    // Enviamos la respuesta JSON al cliente
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(jsonRespuesta.toString());

                }

                break;

        }


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
