package Entidades;

public class orders extends clientes {

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the telefono
     */
    public String getTelefonoPedidos() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefonoPedidos(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the fech
     */
    public String getFech() {
        return fech;
    }

    /**
     * @param fech the fech to set
     */
    public void setFech(String fech) {
        this.fech = fech;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the cant
     */
    public int getCant() {
        return cant;
    }

    /**
     * @param cant the cant to set
     */
    public void setCant(int cant) {
        this.cant = cant;
    }

    /**
     * @return the nota
     */
    public String getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(String nota) {
        this.nota = nota;
    }

    protected String referencia;

    protected String telefono;

    protected String fech;

    protected String hora;

    protected int cant;

    protected String nota;
}
