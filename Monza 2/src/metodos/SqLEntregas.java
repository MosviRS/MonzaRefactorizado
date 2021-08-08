/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import clases.editTable;
import clases.editTabletrue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LAPROMA1-18
 */
public class SqLEntregas {
   String nocliente;
      public void vizualizar_tabla(JTable tabla,Connection con){
         Querys q = new Querys();
        DefaultTableModel atm=new editTabletrue();
        String []titulo=new String[] {"No.Nota","Nombre","Direccion","Reeferencia","fecha de entrega","Telefono"};
        
          String qu="SELECT entregas.nota_entregas, clientes.nombre, clientes.direccion, entregas.referencia, entregas.fecha_entrega, clientes.telefono"
                      +" FROM (clientes INNER JOIN notas ON clientes.idclientes = notas.no_cliente) INNER JOIN entregas ON notas.nota = entregas.nota_entregas";
         atm= q.Seleccioncond(con,qu, titulo);
         
         
         tabla.setRowHeight(16);
         tabla.setModel(atm);
      
                     
    }
     public void elimnar(JTable tabla,Connection con){
      Querys q = new Querys();
       
      try{
           
            String s1=q.Delete(con,"entregas","nota_entregas", String.valueOf(tabla.getValueAt(tabla.getSelectedRow(),0)));
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
     public void modificar(JTable tabla,Connection con){
      
               String valores,columna,s1;
                         Querys p= new Querys();
                         DefaultTableModel atm = (editTabletrue) tabla.getModel(); 
                               
                       
                        int opc=tabla.getSelectedColumn();
                         switch(opc){
                             case 0:
                                 
                             break;
                             
                             case 1:
                                case 2:
                                   case 5:
                                     valores =("'"+tabla.getValueAt(tabla.getSelectedRow(), 1).toString() + "',");
                                                        valores +=("'"+tabla.getValueAt(tabla.getSelectedRow(), 2).toString()+ "',");
                                                        valores +=("'"+tabla.getValueAt(tabla.getSelectedRow(), 5).toString()+ "'");
                                                        
                                      columna="nombre,direccion,telefono";
                                      buscar(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
                                       s1=p.Modificar(con,"clientes", columna, valores,"idclientes='"+nocliente+"'"); 
                                        if (s1 != null)
                                        {    
                                            System.out.println("valio queso");
                                              JOptionPane.showMessageDialog(null,"Upss! ha ocurrido un error al modificar el campo"+s1,"Error", JOptionPane.ERROR_MESSAGE);
                                        } else
                                        {

                                                 tabla.setModel(atm);
                                             //agregarProductos(cli.getModeloPro(),cli.getNombrePro(),cli.getPrecio(),cli.getExistencia(),cli.getCategoria(),cli.getProvedMarca(),jf.getSelectedFile().getAbsolutePath().replace("\\","/"));
                                        }                
                                 
                             break;
                                   case 3:
                                   case 4:
                                      valores =("'"+tabla.getValueAt(tabla.getSelectedRow(), 3).toString() + "',");
                                                        valores +=("'"+tabla.getValueAt(tabla.getSelectedRow(), 4).toString()+ "'");
                                                    
                                                        
                                      columna="fecha_entrega,referencia";
                                 
                                        s1=p.Modificar(con,"entregas", columna, valores,"nota_entregas='"+tabla.getValueAt(tabla.getSelectedRow(), 0).toString()+"'"); 
                                        if (s1 != null)
                                        {    
                                            System.out.println("valio queso");
                                              JOptionPane.showMessageDialog(null,"Upss! ha ocurrido un error al modificar el campo"+s1,"Error", JOptionPane.ERROR_MESSAGE);
                                        } else
                                        {

                                                 tabla.setModel(atm);
                                             //agregarProductos(cli.getModeloPro(),cli.getNombrePro(),cli.getPrecio(),cli.getExistencia(),cli.getCategoria(),cli.getProvedMarca(),jf.getSelectedFile().getAbsolutePath().replace("\\","/"));
                                        }  
                                       
                                   break;
                             
                         }                              
                       
                             
                       

                       
                            
  }
     public boolean buscar(String nota){
           PreparedStatement ps = null;//Objeto que permite usar declaraciones preparadas por la libreria para un facil acceso a MySql
        ResultSet rs = null;//Muestra Datos almacenados en la BD
        Connection con;//objeto que permite la conexion a la BD(MySql)
        con= ManipulaDBC.conectaDB();//objeto que permite la conexion a la BD(MySql)
        //Codigo MySql para Seleccionar(o bien Buscar) Registros, guardado en una Variable de tipo String
        //para seleccionar elementos en la Tabla Usuarios de la BD, previamente diseñada
        String sql = "SELECT no_cliente FROM notas WHERE nota =?";
        try{
            ps = con.prepareStatement(sql);//Precarga declaraciondes por default de MySql
            ps.setString(1,nota);//Busqueda de usuario
            rs = ps.executeQuery();//Muestra el Resultado del usuario  encontrado
            if(rs.next()){
                //toma datos del usuario encontrado y los envia a la Clase Entidad Usuarios
                nocliente=rs.getString(1);
             
                return true;//Retorna una comprobacion                 
            }
             System.out.println("cliente:"+nocliente);
            return false;
            
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println("valio acac");
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        
     }
 
   
}
