package Entidades;

public class bitacoraA extends notas {

    /**
     * @return the cuentaUsuario
     */
    public String getCuentaUsuario() {
        return cuentaUsuario;
    }

    /**
     * @param cuentaUsuario the cuentaUsuario to set
     */
    public void setCuentaUsuario(String cuentaUsuario) {
        this.cuentaUsuario = cuentaUsuario;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the did
     */
    public String getDid() {
        return did;
    }

    /**
     * @param did the did to set
     */
    public void setDid(String did) {
        this.did = did;
    }

    /**
     * @return the cantiadad
     */
    public String getCantiadad() {
        return cantiadad;
    }

    /**
     * @param cantiadad the cantiadad to set
     */
    public void setCantiadad(String cantiadad) {
        this.cantiadad = cantiadad;
    }

    /**
     * @return the notaB
     */
    public String getNotaB() {
        return notaB;
    }

    /**
     * @param notaB the notaB to set
     */
    public void setNotaB(String notaB) {
        this.notaB = notaB;
    }
    public bitacoraA(){
        
    }
    private String cuentaUsuario;
    private String fecha;
    private String did;
    private String cantiadad;
    private String notaB;
}
