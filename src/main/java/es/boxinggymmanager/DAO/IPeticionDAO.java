package es.boxinggymmanager.DAO;

import es.boxinggymmanager.beans.Peticion;

import java.util.List;

public interface IPeticionDAO {
    /**
    * metodo para obtener las peticiones pendientes de la bbdd
     * @return una lista de peticiones pendientes
    * */

    public List<Peticion> getPeticionesPendientes(Enum estado);

    /**

   metodo para actualizar una peticion a aceptada pasando el id peticion
     */
    public boolean aceptarPeticion(int idPeticion);

    /**
    metodo para actualizar una peticion a rechazada pasando el id peticion
     */
    public boolean rechazarPeticion(int idPeticion);
}
