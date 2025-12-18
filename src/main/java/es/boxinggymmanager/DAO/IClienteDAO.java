package es.boxinggymmanager.DAO;

import es.boxinggymmanager.beans.Cliente;

import java.util.HashMap;
import java.util.Map;

public interface IClienteDAO {

    /**
    * metodo para obtener un cliente mediante un dni
     * @return un objeto cliente
    * */
    public Cliente getClienteDni(String dni);

    /**
    * metodo para obtener un cliente mediante el email
     * @return un objeto cliente
    * */
    public Cliente getClienteEmail(String email);

    /**
    * metodo para obtener el numero de clientes por cada tipo de entrenamiento
    * devuelve un mapa con el tipo como clave y el num de clientes como valor
    * */

    public Map<String, Integer> getNumClientesEntrenamiento();

    /**
    * metodo para obtener el numero de clientes por localidad
    * devuelve un mapa con la localidad como clave y el num de clientes como valor
    * */

    public Map<String, Integer> getNumClientesLocalidad();
}
