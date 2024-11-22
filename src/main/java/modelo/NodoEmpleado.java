    package modelo;

    public class NodoEmpleado {
        private Empleado empleado;
        private NodoEmpleado siguiente;

        public NodoEmpleado(Empleado empleado) {
            this.empleado = empleado;
            this.siguiente = null;
        }

        public Empleado getEmpleado() {
            return empleado;
        }

        public void setEmpleado(Empleado empleado) {
            this.empleado = empleado;
        }

        public NodoEmpleado getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(NodoEmpleado siguiente) {
            this.siguiente = siguiente;
        }
    }
