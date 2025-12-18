
package es.boxinggymmanager.DAO;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface IGenericoDAO<T> {
    
    public void insertOrUpdate(T objeto);

    public <T> List<T> selectAll(Class<T> claseEntidad);

    public <T> T getById(Serializable pk, Class<T> claseEntidad);

    public void delete(T objeto);
    
    
    
}
