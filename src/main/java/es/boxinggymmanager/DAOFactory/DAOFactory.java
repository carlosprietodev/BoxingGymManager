package es.boxinggymmanager.DAOFactory;

import es.boxinggymmanager.DAO.*;

public abstract class DAOFactory {


    public abstract IGenericoDAO getGenericoDAO();

    public abstract IEmpleadoDAO getEmpleadoDAO();

    public abstract IEntrenamientoDAO getEntrenamientoDAO();

    public abstract IClienteDAO getClienteDAO();

    public abstract IPeticionDAO getPeticionDAO();


    /**
     * Fábrica abstracta
     * @return Objeto de la fábrica abstracta
     */
    public static DAOFactory getDAOFactory() {
        
        DAOFactory daof = null;

        daof = new MySQLDAOFactory();

        return daof;
    }

}
