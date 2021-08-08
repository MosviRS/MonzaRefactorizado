/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import Entidades.bitacoraA;
import cjb.ci.Mensaje;
import clases.editTabletrue;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mosvi
 */
public class SqlBitacora {
    
    public void insertarbitacora(bitacoraA bit,JFrame PC,Connection con,JTable agenda5){
        Querys q = new Querys();
         String valores1 =("'"+bit.getCuentaUsuario() + "',");
                                    valores1 +=("'"+bit.getDid()+ "',");
                                    valores1 += ("'"+bit.getCantiadad()+ "',");
                                    valores1 += ("'"+bit.getNotaB() + "',");
                                    valores1 += ("'"+bit.getFecha()+"'");
        String s1=q.Insertar(con,"bitacora","cuneta_usuario, movimiento, cantidad, nota_bitacora, fecha_dia", valores1);
               
       
        if (s1 != null)
        {    Mensaje.error(PC, s1);
            System.out.println("valio queso");
        } else
        {   // visualizar_tabla(agenda5, con);
             System.out.println("Exito");
        }
        
    }
      public void elimnar(JTable tabla,Connection con){
      Querys q = new Querys();
       
      try{
           
            String s1=q.Delete(con,"bitacora","nota_bitacora", String.valueOf(tabla.getValueAt(tabla.getSelectedRow(),3)));
            DefaultTableModel atm = (editTabletrue) tabla.getModel(); 
                               
           
                    if (s1 != null)
               {    
                   System.out.println("valio queso");
               } else
               {
                    System.out.println("Exito");
                    System.out.println(tabla.getSelectedRow());
                    atm.removeRow(tabla.getSelectedRow());
                    tabla.setModel(atm);
                    //agregarProductos(cli.getModeloPro(),cli.getNombrePro(),cli.getPrecio(),cli.getExistencia(),cli.getCategoria(),cli.getProvedMarca(),jf.getSelectedFile().getAbsolutePath().replace("\\","/"));
               }
                    
      }catch(Exception e){
          
      }
      
  }
     public void vizualizar_tabla(JTable tabla,Connection con){
         Querys q = new Querys();
        DefaultTableModel atm=new editTabletrue();
        String []titulo=new String[] {"Clave Administrador","Accion","Cantidad Recibida","No. Nota"};
        
//          String qu="SELECT bitacora.cuneta_usuario, bitacora.movimiento, bitacora.cantidad, bitacora.nota_bitacora, clientes.nombre "+
//          "FROM usuario INNER JOIN ((clientes INNER JOIN notas ON clientes.idclientes = notas.no_cliente) INNER JOIN bitacora ON notas.nota = bitacora.nota_bitacora) ON usuario.idcuenta = bitacora.cuneta_usuario";
         
         atm= q.SeleccionTable(con,"cuneta_usuario, movimiento, cantidad, nota_bitacora","bitacora","nota_bitacora = ?", titulo);
         tabla.setRowHeight(16);
         tabla.setModel(atm);
      
                     
    }
    
    
}
