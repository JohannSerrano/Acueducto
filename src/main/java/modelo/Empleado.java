package modelo;

public class Empleado {
    private int id;
    private String nombre;
    private String apellido;
    private String cargo; // Instalador, Mantenimiento, Fontanero

    // Constructor
    public Empleado() {
        // No necesitamos inicializar instalacionesEncargadas ni salario
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}