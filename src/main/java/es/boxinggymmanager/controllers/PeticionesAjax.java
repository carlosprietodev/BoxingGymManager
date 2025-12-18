package es.boxinggymmanager.controllers;

import es.boxinggymmanager.DAO.IGenericoDAO;
import es.boxinggymmanager.DAO.IPeticionDAO;
import es.boxinggymmanager.DAOFactory.DAOFactory;
import es.boxinggymmanager.beans.Cliente;
import es.boxinggymmanager.beans.Peticion;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "PeticionesAjax", urlPatterns = {"/PeticionesAjax"})
public class PeticionesAjax extends HttpServlet {
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

        IPeticionDAO peticionDAO = daoF.getPeticionDAO();

        if (session.getAttribute("empleado") == null) {
            response.getWriter().write("{\"error\":\"No hay empleado en sesión\"}");
            return;
        }

        try (PrintWriter writer = response.getWriter()) {
            // obtenemos la lista de peticiones pendientes de la bbdd
            List<Peticion> listadoPeticionesPendientes = peticionDAO.getPeticionesPendientes(Peticion.Estado.PENDIENTE);

            JSONArray jsonArray = new JSONArray();
            // por caca peticion cramos un objeto json con los dato del cliente y lo añadimos a la lista de jsonArray
            for (Peticion peticion : listadoPeticionesPendientes) {

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("idPeticion", peticion.getIdPeticion());

                // guardamas la fecha de la peticion en una variable y le damos el formato dd--mm--yy
                Date fechaPeticion = peticion.getFechaPeticion();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String fechaPeticionFormato = sdf.format(fechaPeticion);

                jsonObject.put("fechaPeticion", fechaPeticionFormato);
                jsonObject.put("nombre", peticion.getNombre());

                jsonObject.put("email", peticion.getEmail());
                jsonObject.put("telefono", peticion.getTelefono());
                jsonObject.put("tipoEntrenamiento", peticion.getTipoEntrenamiento());
                jsonObject.put("estado", peticion.getEstado());


                jsonArray.put(jsonObject);

            }

            // creamos un nuevo objeto json con el total y el array de json y enviamos los datos al cliente
            JSONObject responseJson = new JSONObject();
            responseJson.put("total", listadoPeticionesPendientes.size());
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
