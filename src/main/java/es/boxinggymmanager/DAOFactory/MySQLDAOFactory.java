package es.boxinggymmanager.DAOFactory;

import es.boxinggymmanager.DAO.*;


/**
 * Fábrica concreta para la fuente de datos MySQL
 * 
 */
public class MySQLDAOFactory extends DAOFactory{

    @Override
    public IGenericoDAO getGenericoDAO() {
        return new GenericoDAO();
    }

    @Override
    public IEmpleadoDAO getEmpleadoDAO() {
        return new EmpleadoDAO();
    }
    @Override
    public IEntrenamientoDAO getEntrenamientoDAO() {
        return new EntrenamientoDAO();
    }

    @Override
    public IClienteDAO getClienteDAO() {return new ClienteDAO();
    }

    public IPeticionDAO getPeticionDAO(){
        return new PeticionDAO();
    };
}
