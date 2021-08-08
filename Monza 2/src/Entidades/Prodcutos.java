package Entidades;

public abstract class Prodcutos {

    /**
     * @return the modeloPro
     */
    public String getModeloPro() {
        return modeloPro;
    }

    /**
     * @param modeloPro the modeloPro to set
     */
    public void setModeloPro(String modeloPro) {
        this.modeloPro = modeloPro;
    }

    /**
     * @return the nombrePro
     */
    public String getNombrePro() {
        return nombrePro;
    }

    /**
     * @param nombrePro the nombrePro to set
     */
    public void setNombrePro(String nombrePro) {
        this.nombrePro = nombrePro;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * @return the provedMarca
     */
    public String getProvedMarca() {
        return provedMarca;
    }

    /**
     * @param provedMarca the provedMarca to set
     */
    public void setProvedMarca(String provedMarca) {
        this.provedMarca = provedMarca;
    }
    public Prodcutos(){
        
    }
    private String modeloPro;

    private String nombrePro;

    private Double precio;

    private String provedMarca;
}
