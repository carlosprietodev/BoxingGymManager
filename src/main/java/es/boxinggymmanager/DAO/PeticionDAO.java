package es.boxinggymmanager.DAO;

import es.boxinggymmanager.beans.Cliente;
import es.boxinggymmanager.beans.Entrenamiento;
import es.boxinggymmanager.beans.Peticion;

import java.util.ArrayList;
import java.util.List;

public class PeticionDAO extends GenericoDAO<Peticion> implements IPeticionDAO{


    @Override
    public List<Peticion> getPeticionesPendientes(Enum estado) {

        List<Peticion> peticionesPendientes = new ArrayList<>();

        try{
            startTransaction();
            peticionesPendientes = sesion.createQuery("FROM Peticion p WHERE p.estado = :estado", Peticion.class)
                    .setParameter("estado", estado)
                    .getResultList();

        }catch (Exception e) {
            e.printStackTrace();
        } finally {

            endTransaction();

        }

        return peticionesPendientes;

    }

    @Override
    public boolean aceptarPeticion(int idPeticion) {
        boolean aceptada = false;

        try{
            startTransaction();

            int filasActualizadas = sesion.createQuery(
                            "UPDATE Peticion p SET p.estado = :estado WHERE p.idPeticion = :idPeticion")
                    .setParameter("estado", Peticion.Estado.ACEPTADA)
                    .setParameter("idPeticion", idPeticion)
                    .executeUpdate();

            if(filasActualizadas > 0) {
                aceptada = true;
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {

            endTransaction();

        }

        return aceptada;

    }

    @Override
    public boolean rechazarPeticion(int idPeticion) {

        boolean rechazada = false;

        try{
            startTransaction();

            int filasActualizadas = sesion.createQuery(
                            "UPDATE Peticion p SET p.estado = :estado WHERE p.idPeticion = :idPeticion")
                    .setParameter("estado", Peticion.Estado.RECHAZADA)
                    .setParameter("idPeticion", idPeticion)
                    .executeUpdate();

            if(filasActualizadas > 0) {
                rechazada = true;
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {

            endTransaction();

        }

        return rechazada;

    }
}
