package es.boxinggymmanager.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "clientes",uniqueConstraints = {@UniqueConstraint(columnNames = "Dni",name = "UK_cliente_dni"),
        @UniqueConstraint(columnNames = "Email",name = "UK_cliente_email")})
public class Cliente  implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "IdCliente")
    private Integer idCliente;

    @Column(name = "Nombre", length = 20, nullable = false)
    private String nombre;

    @Column(name = "Apellidos", length = 30, nullable = false)
    private String apellidos;

    @Column(name = "Dni", length = 9, nullable = false)
    private String dni;

    @Column(name = "Telefono", length = 9, nullable = false)
    private String telefono;

    @Column(name = "Email", length = 50, nullable = false)
    private String email;

    @Column(name = "Localidad", length = 40, nullable = false)
    private String localidad;

    @Temporal(TemporalType.DATE)
    @Column(name = "FechaRegistro")
    private Date fechaRegistro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdEntrenamiento", foreignKey = @ForeignKey(name = "FK_clientes_entrenamientos"))
    private Entrenamiento entrenamiento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdEmpleado", foreignKey = @ForeignKey(name = "FK_clientes_empleados"))
    private Empleado empleado;

    public Cliente() {

    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Entrenamiento getEntrenamiento() {
        return entrenamiento;
    }

    public void setEntrenamiento(Entrenamiento entrenamiento) {
        this.entrenamiento = entrenamiento;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
