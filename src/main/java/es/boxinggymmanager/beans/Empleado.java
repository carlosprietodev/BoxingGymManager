package es.boxinggymmanager.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "empleados")
public class Empleado  implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "IdEmpleado")
    private Integer idEmpleado;

    @Column(name = "Nombre", length = 20, nullable = false)
    private String nombre;
    @Column(name = "Apellidos", length = 30, nullable = false)
    private String apellidos;
    @Column(name = "Email", length = 50, nullable = false)
    private String email;
    @Column(name = "Password", length = 100, nullable = false)
    private String password;


    // Esto indica que la relación está mapeada en la clase Cliente, y el atributo que define la relación allí se llama empleado
    // El mappedBy le dice a JPA que no cree una nueva tabla intermedia, porque la relación ya está definida en Cliente mediante una foreign key.
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<Cliente> clientes;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<Peticion> peticiones;



    public Empleado() {
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Peticion> getPeticiones() {
        return peticiones;
    }

    public void setPeticiones(List<Peticion> peticiones) {
        this.peticiones = peticiones;
    }
}
