package Entidades;

public class Provedores {

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
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
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

    /**
     * @return the tipoMer
     */
    public String getTipoMer() {
        return tipoMer;
    }

    /**
     * @param tipoMer the tipoMer to set
     */
    public void setTipoMer(String tipoMer) {
        this.tipoMer = tipoMer;
    }
    public  Provedores(){
        
    }
    private String nombre;

    private String direccion;

    private String correo;

    private String telefono;

    private String tipoMer;
}
