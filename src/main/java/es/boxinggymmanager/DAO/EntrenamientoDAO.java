package es.boxinggymmanager.DAO;

import es.boxinggymmanager.beans.Empleado;
import es.boxinggymmanager.beans.Entrenamiento;

import java.util.List;

public class EntrenamientoDAO extends GenericoDAO<Entrenamiento> implements IEntrenamientoDAO{

    @Override
    public List<Entrenamiento> getEntrenamientosClientes() {

        List<Entrenamiento> listaEntrenamientos = null;

        try {
            startTransaction();

            listaEntrenamientos = sesion.createQuery(
                    "SELECT DISTINCT e FROM Entrenamiento e LEFT JOIN FETCH e.clientes", Entrenamiento.class
            ).list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            endTransaction();
        }

        return listaEntrenamientos;

    }
}
