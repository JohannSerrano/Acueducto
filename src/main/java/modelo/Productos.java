package modelo;

import java.io.Serializable;

public class Productos implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String nombreServicios;
    private int cantidad;
    private String ubicacion;
    private String codigoUsuario;
    private double precio;



    // Constructor
    public Productos(String codigo, String nombreServicios, int cantidad, String ubicacion, String codigoUsuario) {
        this.codigo = codigo;
        this.nombreServicios = nombreServicios;
        this.cantidad = cantidad;
        this.ubicacion = ubicacion;
        this.codigoUsuario = codigoUsuario; // Asignar el codigoUsuario
        this.precio = asignarPrecioPorServicio(nombreServicios);
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreServicios() {  // Cambié el nombre del método
        return nombreServicios;
    }

    public void setNombreServicios(String nombreServicios) {  // Cambié el nombre del método
        this.nombreServicios = nombreServicios;
        this.precio = asignarPrecioPorServicio(nombreServicios);
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public double getPrecioTotal() {
        return this.precio * this.cantidad;
    }

    private double asignarPrecioPorServicio(String nombreServicios) {
        switch (nombreServicios) {
            case "Hogar Cristalino":
                return 200000;
            case "Industria Verde":
                return 700000;
            case "Campos Verdes":
                return 400000;
            default:
                return 0;
        }
    }
}
