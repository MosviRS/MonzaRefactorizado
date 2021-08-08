package Entidades;

public class clientes extends notas {

    /**
     * @return the Ap
     */
    public String getAp() {
        return Ap;
    }

    /**
     * @param Ap the Ap to set
     */
    public void setAp(String Ap) {
        this.Ap = Ap;
    }

    /**
     * @return the Am
     */
    public String getAm() {
        return Am;
    }

    /**
     * @param Am the Am to set
     */
    public void setAm(String Am) {
        this.Am = Am;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
        
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the idcliente
     */
    public String getIdcliente() {
        return idcliente;
    }

    /**
     * @param idcliente the idcliente to set
     */
    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    protected String nombre;
     protected String Ap;
      protected String Am;
    protected String direccion;

    protected String idcliente;

    protected String telefono;
}
