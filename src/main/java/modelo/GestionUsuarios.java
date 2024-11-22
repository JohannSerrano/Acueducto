package modelo;

import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class GestionUsuarios {

    // Leer usuarios desde el archivo de texto (usuarios.txt)
    public LinkedList<Usuario> leerUsuariosDesdeArchivo(String rutaCompleta) throws IOException {
        LinkedList<Usuario> usuarios = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCompleta))) {
            String linea;
            Usuario usuario = null;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Código:")) {
                    if (usuario != null) {
                        usuarios.add(usuario);
                    }
                    usuario = new Usuario();
                    usuario.setCodigo(Integer.parseInt(linea.split(": ")[1]));
                } else if (linea.startsWith("Nombre:")) {
                    usuario.setNombre(linea.split(": ")[1]);
                } else if (linea.startsWith("Apellidos:")) {
                    usuario.setApellidos(linea.split(": ")[1]);
                } else if (linea.startsWith("Cédula:")) {
                    usuario.setCedula(linea.split(": ")[1]);
                } else if (linea.startsWith("Dirección:")) {
                    usuario.setDireccion(linea.split(": ")[1]);
                } else if (linea.startsWith("Estrato:")) {
                    usuario.setEstrato(linea.split(": ")[1]);
                } else if (linea.startsWith("Ciudad:")) {
                    usuario.setCiudad(linea.split(": ")[1]);
                } else if (linea.startsWith("Código Postal:")) {
                    usuario.setCodigoPostal(linea.split(": ")[1]);
                }
            }
            if (usuario != null) {
                usuarios.add(usuario);
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se encontró. Iniciaremos con una lista de usuarios vacía.");
        }
        return usuarios;
    }

    // Escribir usuarios en el archivo de texto (usuarios.txt)
    public void escribirUsuariosEnArchivo(LinkedList<Usuario> usuarios, String rutaCompleta) throws IOException {
        File archivoUsuarios = new File(rutaCompleta);
        try (PrintWriter pluma = new PrintWriter(archivoUsuarios)) {
            pluma.println("Lista de Usuarios");
            pluma.println("============================");

            for (Usuario usuario : usuarios) {
                pluma.println("Código: " + usuario.getCodigo());
                pluma.println("Nombre: " + usuario.getNombre());
                pluma.println("Apellidos: " + usuario.getApellidos());
                pluma.println("Cédula: " + usuario.getCedula());
                pluma.println("Dirección: " + usuario.getDireccion());
                pluma.println("Estrato: " + usuario.getEstrato());
                pluma.println("Ciudad: " + usuario.getCiudad());
                pluma.println("Código Postal: " + usuario.getCodigoPostal());
                pluma.println("------------------------------------");
            }
        }
    }

    // Serializar los usuarios en el archivo .data
    public void serializarUsuarios(LinkedList<Usuario> usuarios, String rutaCompleta) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(new File(rutaCompleta));
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(usuarios);
        }
    }

    // Buscar un usuario por su código
    public Usuario buscarUsuarioPorCodigo(LinkedList<Usuario> usuarios, int codigoUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCodigo() == codigoUsuario) {
                return usuario;
            }
        }
        return null;
    }

    // Editar un usuario existente
    public boolean editarUsuario(LinkedList<Usuario> usuarios, int codigoUsuario, Usuario usuarioActualizado) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCodigo() == codigoUsuario) {
                usuario.setNombre(usuarioActualizado.getNombre());
                usuario.setApellidos(usuarioActualizado.getApellidos());
                usuario.setCedula(usuarioActualizado.getCedula());
                usuario.setDireccion(usuarioActualizado.getDireccion());
                usuario.setEstrato(usuarioActualizado.getEstrato());
                usuario.setCiudad(usuarioActualizado.getCiudad());
                usuario.setCodigoPostal(usuarioActualizado.getCodigoPostal());
                return true; // Edición exitosa
            }
        }
        return false; // Usuario no encontrado
    }

    // Realizar recorrido doble de los usuarios y guardarlo en un archivo
    public void realizarRecorridoDoble(LinkedList<Usuario> usuarios, String rutaCompleta) throws IOException {
        try (PrintWriter pluma = new PrintWriter(new File(rutaCompleta))) {
            ListIterator<Usuario> it = usuarios.listIterator();

            pluma.println("Recorrido hacia adelante:");
            while (it.hasNext()) {
                Usuario usuario = it.next();
                pluma.println(usuario.getNombre());
            }

            pluma.println("Recorrido hacia atrás:");
            while (it.hasPrevious()) {
                Usuario usuario = it.previous();
                pluma.println(usuario.getNombre());
            }
        }
    }
}
