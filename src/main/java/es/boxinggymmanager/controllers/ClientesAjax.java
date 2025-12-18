package es.boxinggymmanager.controllers;


import es.boxinggymmanager.DAO.IClienteDAO;
import es.boxinggymmanager.DAO.IGenericoDAO;
import es.boxinggymmanager.DAOFactory.DAOFactory;
import es.boxinggymmanager.beans.Cliente;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "ClientesAjax", urlPatterns = {"/ClientesAjax"})
public class ClientesAjax extends HttpServlet {


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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        DAOFactory daoF = DAOFactory.getDAOFactory();
        IGenericoDAO genericoDAO = daoF.getGenericoDAO();

        if (session.getAttribute("empleado") == null) {
            response.getWriter().write("{\"error\":\"No hay empleado en sesión\"}");
            return;
        }

        try (PrintWriter writer = response.getWriter()) {
            // obtenemos la lista de clientes
            List<Cliente> listadoClientes = genericoDAO.selectAll(Cliente.class);

            JSONArray jsonArray = new JSONArray();
            // por caca cliente cramos un objeto json con los dato del cliente y lo añadimos a la lista de jsonArray
            for (Cliente cliente : listadoClientes) {

                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("idCliente", cliente.getIdCliente());
                    jsonObject.put("nombre", cliente.getNombre());
                    jsonObject.put("apellidos", cliente.getApellidos());
                    jsonObject.put("dni", cliente.getDni());
                    jsonObject.put("email", cliente.getEmail());
                    jsonObject.put("telefono", cliente.getTelefono());
                    jsonObject.put("localidad", cliente.getLocalidad());
                    jsonObject.put("entrenamiento", cliente.getEntrenamiento().getTipo().name());

                    jsonArray.put(jsonObject);

            }

            // creamos un nuevo objeto json con el total y el array de json y enviamos los datos al cliente
            JSONObject responseJson = new JSONObject();
            responseJson.put("total", listadoClientes.size());
            responseJson.put("rows", jsonArray);

            writer.write(responseJson.toString());
        } catch (IOException e) {
            response.getWriter().write("{\"error\":\"Error de entrada/salida\"}");
        } catch (Exception e) {
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
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
