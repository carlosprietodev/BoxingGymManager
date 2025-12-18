package es.boxinggymmanager.DAO;

import es.boxinggymmanager.beans.Cliente;
import es.boxinggymmanager.beans.Entrenamiento;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteDAO extends GenericoDAO<Cliente> implements IClienteDAO{
    @Override
    public Cliente getClienteDni(String dni) {
        Cliente cliente = null;

        try{
            startTransaction();
            cliente = sesion.createQuery("FROM Cliente c WHERE c.dni = :dni", Cliente.class)
                    .setParameter("dni", dni)
                    .uniqueResult();

        }catch (Exception e) {
            e.printStackTrace();
        } finally {

            endTransaction();

        }

        return cliente;
    }

    @Override
    public Cliente getClienteEmail(String email) {

        Cliente cliente = null;

        try{
            startTransaction();
            cliente = sesion.createQuery("FROM Cliente c WHERE c.email = :email", Cliente.class)
                    .setParameter("email", email)
                    .uniqueResult();

        }catch (Exception e) {
            e.printStackTrace();
        } finally {

            endTransaction();

        }

        return cliente;

    }

    @Override
    public Map<String, Integer> getNumClientesEntrenamiento() {
        Map<String, Integer> clientesPorEntrenamiento = new HashMap<>();

        try {
            startTransaction();

            List<Object[]> resultados = sesion.createQuery(
                    "SELECT c.entrenamiento.tipo, COUNT(c) " +
                            "FROM Cliente c " +
                            "GROUP BY c.entrenamiento.tipo",
                    Object[].class
            ).getResultList();

            // por cada fila obtenemos el tipo de entrenamiento y la cantidad de clientes para ese entrenamiento
            for (Object[] fila : resultados) {
                Entrenamiento.Tipo tipoEntrenamientoEnum = (Entrenamiento.Tipo) fila[0];
                Long cantidad = (Long) fila[1]; // casteamos a Long que es lo que devuelve hibernate cuando hacemos Count
                clientesPorEntrenamiento.put(tipoEntrenamientoEnum.name(), cantidad.intValue()); //Enum.name para pasarlo a string
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            endTransaction();
        }

        return clientesPorEntrenamiento;

    }

    @Override
    public Map<String, Integer> getNumClientesLocalidad() {
        Map<String, Integer> clientesPorLocalidad = new HashMap<>();

        try {
            startTransaction();

            List<Object[]> resultados = sesion.createQuery(
                    "SELECT c.localidad, COUNT(c) " +
                            "FROM Cliente c " +
                            "GROUP BY c.localidad",
                    Object[].class
            ).getResultList();

            //por cada array fila obtenemos la localidad y la cantidad
            for (Object[] fila : resultados) {
                String localidad = (String) fila[0];
                Long cantidad = (Long) fila[1]; // casteamos a Long que es lo que devuelve hibernate cuando usamos Count
                clientesPorLocalidad.put(localidad, cantidad.intValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            endTransaction();
        }

        return clientesPorLocalidad;

    }


}
