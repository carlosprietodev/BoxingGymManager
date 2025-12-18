package es.boxinggymmanager.controllers;


import es.boxinggymmanager.DAO.IClienteDAO;
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
import java.util.Map;

@WebServlet(name = "EstadisticasAjax", urlPatterns = {"/EstadisticasAjax"})
public class EstadisticasAjax extends HttpServlet {

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
        DAOFactory daoF = DAOFactory.getDAOFactory();
        IClienteDAO clienteDAO = daoF.getClienteDAO();
        Map<String,Integer> clientesPorEntrenamiento = null;
        Map<String,Integer> clientesPorLocalidad = null;

        switch (accion){

            case"clientesEntrenamiento":
                clientesPorEntrenamiento= clienteDAO.getNumClientesEntrenamiento();
                if(clientesPorEntrenamiento!=null){
                    JSONObject respuestaJSON = new JSONObject();

                    // creamos los json array
                    JSONArray labels = new JSONArray();
                    JSONArray valores = new JSONArray();

                    // recorremos el mapa
                    for (Map.Entry<String, Integer> entry : clientesPorEntrenamiento.entrySet()) {
          // añadimos la clave al label que es el el entranamiento y el valor de esa clave al array json valores que son el num de clientes
                        labels.put(entry.getKey());
                        valores.put(entry.getValue());
                    }

                    // asignamos labels y valores a la respuestaJSON
                    respuestaJSON.put("labels", labels);
                    respuestaJSON.put("valores", valores);
                    // Enviamos la respuesta JSON al cliente
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(respuestaJSON.toString());

                }

                break;

            case"clientesLocalidad":

                clientesPorLocalidad= clienteDAO.getNumClientesLocalidad();
                if(clientesPorLocalidad!=null){
                    JSONObject respuestaJSON = new JSONObject();

                    JSONArray labels = new JSONArray();
                    JSONArray valores = new JSONArray();

                    // recorremos el mapa
                    for (Map.Entry<String, Integer> entry : clientesPorLocalidad.entrySet()) {
                        // añadimos la clave al label que es la localidad y el valor de esa clave al array json valores que son el num de clientes
                        labels.put(entry.getKey());
                        valores.put(entry.getValue());
                    }
 // añadimos labels y valores a la respuestaJSON
                    respuestaJSON.put("labels", labels);
                    respuestaJSON.put("valores", valores);

                    // Enviamos la respuesta JSON al cliente
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(respuestaJSON.toString());
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
