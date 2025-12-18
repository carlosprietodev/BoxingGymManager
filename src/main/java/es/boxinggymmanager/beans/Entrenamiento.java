package es.boxinggymmanager.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "entrenamientos")
public class Entrenamiento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "IdEntrenamiento")
    private Integer idEntrenamiento;


    public enum Tipo {
        BASICO,CONTACTO_DIRIGIDO,CARDIOBOX,TECNICO_ESPECIALIZADO;

    }

    @Enumerated(EnumType.STRING)
    @Column(name = "Tipo", nullable = false)
    private Tipo tipo;

    @Column(name = "Descripcion",columnDefinition = "MEDIUMTEXT")
    private String descripcion;

    @OneToMany(mappedBy = "entrenamiento", cascade = CascadeType.ALL)
    private List<Cliente> clientes;

    public Entrenamiento() {
    }

    public Integer getIdEntrenamiento() {
        return idEntrenamiento;
    }

    public void setIdEntrenamiento(Integer idEntrenamiento) {
        this.idEntrenamiento = idEntrenamiento;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
