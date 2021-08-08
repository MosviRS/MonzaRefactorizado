/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import LogIn.LogIn;
import PanelConrtrol.PC;
import static PanelConrtrol.PC.bit;
import static PanelConrtrol.PC.p;
import clases.editTable;
import clases.editTabletrue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mosvi
 */
public class SqLclientes {
      String nocliente;
      double cantidad,saldo,totalcompra;
       SqlBitacora sqlbita= new SqlBitacora();
      SqLNotas sqlnotas= new SqLNotas();
       LogIn L=new LogIn();
      public void vizualizar_tabla(JTable tabla,Connection con){
         Querys q = new Querys();
        DefaultTableModel atm=new editTabletrue();
        String []titulo=new String[] {"No.Nota","Nombre","Direccion","Fecha de Compra","Total a Pagar","Abonado","Saldo"};
        
          String qu="SELECT notas.nota, clientes.nombre, clientes.direccion, notas.fecha_nota, notas.total, abono.abonado, abono.faltante "+
          "FROM (clientes INNER JOIN notas ON clientes.idclientes = notas.no_cliente) INNER JOIN abono ON notas.nota = abono.nota_abono";
         atm= q.Seleccioncond(con,qu, titulo);
         
         
         tabla.setRowHeight(16);
         tabla.setModel(atm);
      
                     
    }
     public void elimnar(JTable tabla,Connection con){
      Querys q = new Querys();
       
      try{
           
            String s1=q.Delete(con,"notas","nota", String.valueOf(tabla.getValueAt(tabla.getSelectedRow(),0)));
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
                                  
                                      valores =("'"+tabla.getValueAt(tabla.getSelectedRow(), 3).toString() + "'");
                                                       
                                                    
                                                        
                                       columna="fecha_nota";
                                 
                                        s1=p.Modificar(con,"notas", columna, valores,"nota='"+tabla.getValueAt(tabla.getSelectedRow(), 0).toString()+"'"); 
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
     public void operacion(JTable tabla,Connection con,Double op,JFrame pc){
                        String valores,columna,s1;
                         Querys p= new Querys();
                         DefaultTableModel atm = (editTabletrue) tabla.getModel();
                          double totalabono,faltante;
                          cantiadad(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
                       
                          totalabono=cantidad+op;
                          faltante=saldo-op;
                          valores =(String.valueOf(totalabono)+",");
                          valores +=(String.valueOf(faltante));
                          String to=tabla.getValueAt(tabla.getSelectedRow(), 4).toString();
                         //actualoza los datos en faltantes y abonado
                          s1=p.Modificar(con,"abono","abonado, faltante", valores,"nota_abono='"+tabla.getValueAt(tabla.getSelectedRow(), 0).toString()+"'"); 
                                        if (s1 != null)
                                        {    
                                            System.out.println("valio queso");
                                              JOptionPane.showMessageDialog(null,"Upss! ha ocurrido un error al modificar el campo"+s1,"Error", JOptionPane.ERROR_MESSAGE);
                                        } else
                                        {          bit.setDid("Abono");
                                                   bit.setCantiadad(String.valueOf(op));
                                                   bit.setNo_nota(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
                                                   bit.setCuentaUsuario(L.us);
                                                   sqlnotas.insertbitacora(bit,pc); 
                                                   
                                               
                                                 
                                             //agregarProductos(cli.getModeloPro(),cli.getNombrePro(),cli.getPrecio(),cli.getExistencia(),cli.getCategoria(),cli.getProvedMarca(),jf.getSelectedFile().getAbsolutePath().replace("\\","/"));
                                        } 
                                        
                       //comprueb si ya ha abonado todo y lo elimina automaticamente                 
                                        
                        System.out.println(Double.parseDouble(to)+" "+totalabono);
                       if(totalabono>=Double.parseDouble(to)){
                                 System.out.println("ssss");
                                     Querys q = new Querys();

                                    try{
                                           System.out.println(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()+"cdefd ");
                                           s1=q.Delete(con,"notas","nota",tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
                                          
                                              


                                             if (s1 != null)
                                             {    
                                                 System.out.println("valio queso");
                                             } else
                                             {    JOptionPane.showMessageDialog(null,"La nota se ha pagado completamente","Notificacion",JOptionPane.INFORMATION_MESSAGE);
                                                  System.out.println("Exito");
                                                  System.out.println(tabla.getSelectedRow());
                                                  //atm.removeRow(tabla.getSelectedRow());
                                                 // tabla.setModel(atm);
                                                  //agregarProductos(cli.getModeloPro(),cli.getNombrePro(),cli.getPrecio(),cli.getExistencia(),cli.getCategoria(),cli.getProvedMarca(),jf.getSelectedFile().getAbsolutePath().replace("\\","/"));
                                             }

                                    }catch(Exception e){
                                           System.out.println("jsbjbskxs");
                                    }
                           
                       }
                   
                       //tabla.setModel(atm);
                       vizualizar_tabla(tabla,con);
                          
     }
       public boolean cantiadad(String nota){
        PreparedStatement ps = null;//Objeto que permite usar declaraciones preparadas por la libreria para un facil acceso a MySql
        ResultSet rs = null;//Muestra Datos almacenados en la BD
        Connection con;//objeto que permite la conexion a la BD(MySql)
        con= ManipulaDBC.conectaDB();//objeto que permite la conexion a la BD(MySql)
        //Codigo MySql para Seleccionar(o bien Buscar) Registros, guardado en una Variable de tipo String
        //para seleccionar elementos en la Tabla Usuarios de la BD, previamente diseñada
        String sql = "SELECT abonado,faltante FROM abono WHERE nota_abono =?";
        try{
            ps = con.prepareStatement(sql);//Precarga declaraciondes por default de MySql
            ps.setString(1,nota);//Busqueda de usuario
            rs = ps.executeQuery();//Muestra el Resultado del usuario  encontrado
            if(rs.next()){
                //toma datos del usuario encontrado y los envia a la Clase Entidad Usuarios
                cantidad=rs.getDouble(1);
                saldo=rs.getDouble(2);
                System.out.println(saldo);
             
                return true;//Retorna una comprobacion                 
            }
             System.out.println("cliente:"+nocliente);
            return false;
            
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
           
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
