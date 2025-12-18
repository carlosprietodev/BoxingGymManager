package es.boxinggymmanager.DAO;

import es.boxinggymmanager.beans.Entrenamiento;

import java.util.List;

public interface IEntrenamientoDAO {

    /**
    * metodo para obtener el listado de entrenamientos con sus clientes
     * @return una lista de entrenamientos con los clientes
    * */

    public List<Entrenamiento> getEntrenamientosClientes();
}
