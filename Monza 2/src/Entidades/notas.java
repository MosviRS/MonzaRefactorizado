package Entidades;

public class notas extends info_productos {

    /**
     * @return the nombre
     */
 

    /**
     * @return the ex
     */
    public int getEx() {
        return ex;
    }

    /**
     * @param ex the ex to set
     */
    public void setEx(int ex) {
        this.ex = ex;
    }

    /**
     * @return the faltante
     */
    public double getFaltante() {
        return faltante;
    }

    /**
     * @param faltante the faltante to set
     */
    public void setFaltante(double faltante) {
        this.faltante = faltante;
    }

    /**
     * @return the monto_recibido
     */
    public double getMonto_recibido() {
        return monto_recibido;
    }

    /**
     * @param monto_recibido the monto_recibido to set
     */
    public void setMonto_recibido(double monto_recibido) {
        this.monto_recibido = monto_recibido;
    }

    /**
     * @return the fecha_nota
     */
    public String getFecha_nota() {
        return fecha_nota;
    }

    /**
     * @param fecha_nota the fecha_nota to set
     */
    public void setFecha_nota(String fecha_nota) {
        this.fecha_nota = fecha_nota;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the abono
     */
    public double getAbono() {
        return abono;
    }

    /**
     * @param abono the abono to set
     */
    public void setAbono(double abono) {
        this.abono = abono;
    }

    /**
     * @return the entrega
     */
    public int getEntrega() {
        return entrega;
    }

    /**
     * @param entrega the entrega to set
     */
    public void setEntrega(int entrega) {
        this.entrega = entrega;
    }

    /**
     * @return the no_nota
     */
    public String getNo_nota() {
        return no_nota;
    }

    /**
     * @param no_nota the no_nota to set
     */
    public void setNo_nota(String no_nota) {
        this.no_nota = no_nota;
    }

    /**
     * @return the poductos
     */
    public String getPoductos() {
        return poductos;
    }

    /**
     * @param poductos the poductos to set
     */
    public void setPoductos(String poductos) {
        this.poductos = poductos;
    }

    protected double total;

    protected double abono;
    protected double monto_recibido;
    protected double faltante;

    protected int ex;
    protected int entrega;

    protected String no_nota;

    protected String poductos;
    
    protected String fecha_nota;
    
}
