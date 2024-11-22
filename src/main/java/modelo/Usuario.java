package modelo;

import java.io.Serializable;

public class Usuario implements Serializable {

    // Asegúrate de definir un UID (identificador único) para la serialización
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String apellidos;
    private String cedula;
    private String direccion;
    private String estrato;
    private String ciudad;
    private String codigoPostal;
    private int codigo; // Mantén esta variable como 'codigo'

    // Constructor
    public Usuario(String nombre, String apellidos, String cedula, String direccion, String estrato, String ciudad, String codigoPostal, int codigo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.direccion = direccion;
        this.estrato = estrato;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.codigo = codigo; // Usa 'codigo' aquí
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEstrato() {
        return estrato;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public int getCodigo() {
        return codigo; // Usa 'codigo' aquí
    }

    // Setters (si los necesitas)
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo; // Usa 'codigo' aquí
    }

    // Constructor vacío
    public Usuario() {
    }
}
