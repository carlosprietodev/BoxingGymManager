package es.boxinggymmanager.DAO;

import es.boxinggymmanager.beans.Empleado;

public interface IEmpleadoDAO {

    /**
    * metodo para buscar un empleado por email
     * @param email cadena
     * @return un objeto empleado
    * */
    public Empleado getEmpleadoEmail(String email);
}
