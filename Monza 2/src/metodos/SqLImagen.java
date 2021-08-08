/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import static PanelConrtrol.PC.atm;
import static PanelConrtrol.PC.*;
import clases.IMGtabla;
import clases.editTable;
import clases.editTabletrue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LAPROMA1-21
 */
public class SqLImagen {
     public String guardarProductos(Connection con,String modelo, String nombrePro,double precio,int exietn,String cat,String prov, String ruta){
        String insert = "insert into productos(idmodelo, nombre, precio, existencia, clasificacion, provedor, imagen) values(?,?,?,?,?,?,?)";
        
        FileInputStream fi = null;
        PreparedStatement ps = null;
        try{
            File file = new File(ruta);
            fi = new FileInputStream(file);
            
            ps = con.prepareStatement(insert);
            ps.setString(1, modelo);
            ps.setString(2, nombrePro);
            ps.setDouble(3, precio);
            ps.setInt(4, exietn);
            ps.setString(5, cat);
            ps.setString(6, prov);
            ps.setBinaryStream(7, fi);
            
            ps.executeUpdate();
            return null;
        }catch(Exception ex){
            return "Error al agregar usuario "+ex.getMessage();
        }
    }
     
      public ResultSet visualizar(Connection con){
   
        ResultSet rs = null;
        try{
            PreparedStatement ps = con.prepareStatement("select * from productos");
            rs = ps.executeQuery();
        }catch(Exception ex){
            System.out.println("Error de consulta");
        }
        return rs;
    }
      
    public void visualizar_tabla(JTable tabla,Connection con){
   
    ResultSet rs = visualizar(con);
    //Image img = null;   
 
    DefaultTableModel atm=new editTabletrue();
   
         String []titulo=new String[] {"Modelo","Nombre","Precio","Existencia","Categoria","Marca/Provedor","Imagen"};
         tabla.setDefaultRenderer(Object.class,new IMGtabla());
         atm.setColumnIdentifiers(titulo);
         
    
    try{
        
        while(rs.next()){
                Object[] fila = new Object[7];
                fila[0] = rs.getObject(1);
                fila[1] = rs.getObject(2);
                fila[2] = rs.getObject(3);
                fila[3] = rs.getObject(4);
                fila[4] = rs.getObject(5);
                fila[5] = rs.getObject(6);
            
                
                Blob blob = rs.getBlob(7);

                if(blob != null){
                   try{
                        byte[] data = blob.getBytes(1, (int)blob.length());
                        BufferedImage img = null;
                        try{
                        img = ImageIO.read(new ByteArrayInputStream(data));
                        }catch(Exception ex){
                        System.out.println(ex.getMessage());
                        }
                        ImageIcon icono = new ImageIcon(img);
                        fila[6] = new JLabel(reducirtamamo(icono));
                        }catch(Exception ex){
                            fila[6] = "No Imagen";
                        }
                }
                else{
                    fila[6] = "No Imagen";
                }
                
                atm.addRow(fila);  
            }
        
         tabla.setRowHeight(70);
         tabla.setModel(atm);
    }catch(Exception ex){
        System.out.println(ex.getMessage());
        ex.printStackTrace();   
    }  
}
      public ImageIcon reducirtamamo(ImageIcon im){
    
      Image conver=im.getImage();
      Image tam=conver.getScaledInstance(100,120,Image.SCALE_SMOOTH);
      ImageIcon imgfinal=new ImageIcon(tam);
      
      return imgfinal;
   }
       public void elimnar(JTable tabla,Connection con){
      Querys q = new Querys();
      try{
            String s1=q.Deletetext(con,"productos","idmodelo", String.valueOf(tabla.getValueAt(tabla.getSelectedRow(),0)));
            DefaultTableModel atm = (editTabletrue) tabla.getModel(); 
           
                    if (s1 != null)
               {    
                   System.out.println("valio queso");
               } else
               {
                    System.out.println("Exito");
                    atm.removeRow(tabla.getSelectedRow());
                    tabla.setModel(atm);
                    //agregarProductos(cli.getModeloPro(),cli.getNombrePro(),cli.getPrecio(),cli.getExistencia(),cli.getCategoria(),cli.getProvedMarca(),jf.getSelectedFile().getAbsolutePath().replace("\\","/"));
               }
                    
      }catch(Exception e){
          
      }
  }
        public String modificar(Connection con,String modelo, String nombrePro,double precio,int exietn,String cat,String prov,JTable tabla){
           
                               
        String insert = "update productos set idmodelo = ?, nombre = ?, precio = ?, existencia = ?, clasificacion = ?, provedor = ? where idmodelo='"+modelo+"'";
        
        FileInputStream fi = null;
        PreparedStatement ps = null;
        try{
          
            
            ps = con.prepareStatement(insert);
            ps.setString(1, modelo);
            ps.setString(2, nombrePro);
            ps.setDouble(3, precio);
            ps.setInt(4, exietn);
            ps.setString(5, cat);
            ps.setString(6, prov);
          
            
            ps.executeUpdate();
          
            return null;
        }catch(Exception ex){
            return "Error al modificar usuario "+ex.getMessage();
        }
    }
       public String modificarimagen(Connection con,String modelo, String ruta){
           
                               
        String insert = "update productos set imagen = ? where idmodelo='"+modelo+"'";
        
        FileInputStream fi = null;
        PreparedStatement ps = null;
        try{
            File file = new File(ruta);
            fi = new FileInputStream(file);
            
            ps = con.prepareStatement(insert);
            
            ps.setBinaryStream(1, fi);
           
            
            ps.executeUpdate();
          
            return null;
        }catch(Exception ex){
            return "Error al modificar usuario "+ex.getMessage();
        }
    }
      
   public void Modificarcargado(JTable tabla,Connection con){
         DefaultTableModel atm = (editTabletrue) tabla.getModel(); 
        String mod= String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 0));
        String nom=String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 1));
        Double prec=Double.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
        int cant=Integer.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 3).toString());
        String cate=String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 4));
        String marca=String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 5));
    
       String s1= modificar(con,mod,nom,prec,cant,cate,marca,tabla);
        if(s1!=null){
            System.out.println("valio queso");
        }else{
            
            System.out.println("exito");
        }
     }
     
     public void Modificarxcragadoimagen(JTable tabla,Connection con,String ruta){
        
        String mod= String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 0));
        
    
       String s1= modificarimagen(con,mod,ruta);
        if(s1!=null){
            System.out.println("valio queso");
        }else{
            
            tabla.setValueAt(new JLabel(reducirtamamo(new ImageIcon(ruta))), tabla.getSelectedRow(), tabla.getSelectedColumn());
            System.out.println("exito");
        }
     }
//     public void cargar(JTable tabla,Connection con){
//         
//        name1.setText(String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 0)));
//        ap1.setText(String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 1)));
//        
//        am1.setText(String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 2)));
//        email1.setText(String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 3)));
//        nmm1.setText(String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 4)));
//        nmo1.setText(String.valueOf(tabla.getValueAt(tabla.getSelectedRow(), 5)));
//        
//     }
}
