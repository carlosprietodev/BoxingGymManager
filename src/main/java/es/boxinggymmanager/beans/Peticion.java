package es.boxinggymmanager.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "peticiones")
public class Peticion implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "IdPeticion")
    private Integer idPeticion;

    @Temporal(TemporalType.DATE)
    @Column(name = "FechaPeticion")
    private Date fechaPeticion;

    @Column(name = "Nombre", length = 20, nullable = false)
    private String nombre;

    @Column(name = "Email", length = 50, nullable = false)
    private String email;

    @Column(name = "Telefono", length = 9, nullable = false)
    private String telefono;


    public enum TipoEntrenamiento {
        BASICO,CONTACTO_DIRIGIDO,CARDIOBOX,TECNICO_ESPECIALIZADO;

    }

    @Enumerated(EnumType.STRING)
    @Column(name = "TipoEntrenamiento", nullable = false)
    private TipoEntrenamiento tipoEntrenamiento;

    public enum Estado {
        PENDIENTE,ACEPTADA,RECHAZADA;

    }

    @Enumerated(EnumType.STRING)
    @Column(name = "Estado", nullable = false, length = 15)
    private Estado estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdEmpleado", foreignKey = @ForeignKey(name = "FK_peticiones_empleados"))
    private Empleado empleado;



    public Peticion() {
    }

    public Integer getIdPeticion() {
        return idPeticion;
    }

    public void setIdPeticion(Integer idPeticion) {
        this.idPeticion = idPeticion;
    }

    public Date getFechaPeticion() {
        return fechaPeticion;
    }

    public void setFechaPeticion(Date fechaPeticion) {
        this.fechaPeticion = fechaPeticion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoEntrenamiento getTipoEntrenamiento() {
        return tipoEntrenamiento;
    }

    public void setTipoEntrenamiento(TipoEntrenamiento tipoEntrenamiento) {
        this.tipoEntrenamiento = tipoEntrenamiento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
