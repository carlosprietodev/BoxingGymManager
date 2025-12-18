package es.boxinggymmanager.DAO;

import es.boxinggymmanager.beans.Empleado;

public class EmpleadoDAO extends GenericoDAO<Empleado> implements IEmpleadoDAO {

    @Override
    public Empleado getEmpleadoEmail(String email) {
        Empleado empleado = null;

        try{
            startTransaction();
            empleado = sesion.createQuery("FROM Empleado e WHERE e.email = :email", Empleado.class)
                    .setParameter("email", email)
                    .uniqueResult();

        }catch (Exception e) {
            e.printStackTrace();
        } finally {

            endTransaction();

        }

        return empleado;
    }
}
