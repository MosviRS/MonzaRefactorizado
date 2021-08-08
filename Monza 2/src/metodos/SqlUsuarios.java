package metodos;

import Entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class SqlUsuarios extends Conexion {
    
    public boolean registrar(Usuario usr) {//Metodo de registro recibe un Usuario
        PreparedStatement ps = null; //Objeto que permite usar declaraciones preparadas por la libreria para un facil acceso a MySql
        Connection con;//objeto que permite la conexion a la BD(MySql)
        con= ManipulaDBC.conectaDB();//Objeto que permite manipular a la BD
        //Codigo MySql para insertar Registros, guardado en una Variable de tipo String
        //para registrar en la Tabla Usuarios de la BD, previamente diseñada
        String sql = "INSERT INTO usuario (idcuenta, nombre, tipo, Apaterno, Amaterno, password, tipo_pregunta, respuesta, correo) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);//Precarga declaraciondes por default de MySql
            //Inserta nuevos campos a la Tabla Usuarios
            //Numero de busqueda,Datos a Ingresar;
            ps.setString(1, usr.getUser());
            ps.setString(2, usr.getNombreUser());
            ps.setInt(3, usr.getTipo());
            ps.setString(4, usr.getApaterno());
            ps.setString(5, usr.getAmaterno());
            ps.setString(6, usr.getPassword());
            ps.setInt(7, usr.getPregunta());
            ps.setString(8, usr.getRespuesta());
            ps.setString(9, usr.getCorreo());
            ps.execute();
            return true; // Retorna una comprobacion si el registro fue exitoso
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());//Mensajes de Error 
            return false;
        } finally {
            try {
                ManipulaDBC.desconectaDB(con);//Libera conexion a la BD
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());//Mas Mensajes de Error
            }
        }
    }

    public boolean login(Usuario usr) {//Funcion para comprobacion de Usuario, recibe un Usuario en una variable de Tipo String
        PreparedStatement ps = null;//Objeto que permite usar declaraciones preparadas por la libreria para un facil acceso a MySql
        ResultSet rs = null;//Muestra Datos almacenados en la BD
        Connection con;//objeto que permite la conexion a la BD(MySql)
        con= ManipulaDBC.conectaDB();//Objeto que permite manipular a la BD
        //Codigo MySql para Seleccionar(o bien Buscar) Registros, guardado en una Variable de tipo String
        //para seleccionar elementos en la Tabla Usuarios de la BD, previamente diseñada
        String sql = "SELECT idcuenta,nombre, tipo, Apaterno, Amaterno, password, tipo_pregunta, respuesta, correo FROM usuario WHERE nombre =?";
        try {
            ps = con.prepareStatement(sql);//Precarga declaraciondes por default de MySql
            System.out.println(usr.getUser());
            ps.setString(1, usr.getUser());//Busqueda de usuario
            rs = ps.executeQuery();//Muestra el Resultado del usuario  encontrado
            
            if (rs.next()) {//muestra todos los datos del usuario encontrado
                
                if (usr.getPassword().equals(rs.getString(6))) {//Comprueba que el password sea correcto y coicida con el usuario                                        
                    //Envia datos del Usuario ubicado a la clase entidad Usuarios 
                    usr.setUser(rs.getString(1));
                    usr.setNombreUser(rs.getString(2));
                    usr.setTipo(rs.getInt(3));
                    usr.setApaterno(rs.getString(4));
                    usr.setAmaterno(rs.getString(5));
                    usr.setPregunta(rs.getInt(7));
                    usr.setRespuesta(rs.getString(8));
                    usr.setCorreo(rs.getString(9));
                    
                    return true;// Retorna una comprobacion de la existencia del usuario en la BD 
                } else {
                    return false;
                }
            }

            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());//Mensajes de Error
            return false;
        } finally {
            try {
                con.close();//libera conexion de la BD
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());//Mas mensajes de error 
            }
        }
        
    }
    public boolean Olvide(Usuario usr){
        PreparedStatement ps = null;//Objeto que permite usar declaraciones preparadas por la libreria para un facil acceso a MySql
        ResultSet rs = null;//Muestra Datos almacenados en la BD
        Connection con;//objeto que permite la conexion a la BD(MySql)
        con= ManipulaDBC.conectaDB();//objeto que permite la conexion a la BD(MySql)
        //Codigo MySql para Seleccionar(o bien Buscar) Registros, guardado en una Variable de tipo String
        //para seleccionar elementos en la Tabla Usuarios de la BD, previamente diseñada
        String sql = "SELECT idcuenta,nombre, tipo, Apaterno, Amaterno, password, tipo_pregunta, respuesta, correo FROM usuario WHERE nombre =?";
        try{
            ps = con.prepareStatement(sql);//Precarga declaraciondes por default de MySql
            ps.setString(1, usr.getUser());//Busqueda de usuario
            rs = ps.executeQuery();//Muestra el Resultado del usuario  encontrado
            if(rs.next()){
                //toma datos del usuario encontrado y los envia a la Clase Entidad Usuarios
                
                usr.setUser(rs.getString(1));
                usr.setNombreUser(rs.getString(2));
                usr.setTipo(rs.getInt(3));
                usr.setApaterno(rs.getString(4));
                usr.setAmaterno(rs.getString(5));
                usr.setPassword(rs.getString(6));
                usr.setPregunta(rs.getInt(7));
                usr.setRespuesta(rs.getString(8));
                usr.setCorreo(rs.getString(9));
                System.out.println(usr.getNombreUser());
                return true;//Retorna una comprobacion                 
            }
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
    public int existeUsuario(String usuario) {//Comprobacion de existencia del Usuario
        PreparedStatement ps = null;//Objeto que permite usar declaraciones preparadas por la libreria para un facil acceso a MySql
        ResultSet rs = null;//Muestra Datos almacenados en la BD
        Connection con;//objeto que permite la conexion a la BD(MySql)
        con= ManipulaDBC.conectaDB();//objeto que permite la conexion a la BD(MySql)
        //Seleccionar Contar idcuenta  de Tabla Usuario, donde Idcuenta es igual a...
        // Codigo de MySql para buscar Idcuenta en la tabla Usuario
        String sql = "SELECT count(idcuenta) FROM usuario WHERE idcuenta = ?";

        try {
            ps = con.prepareStatement(sql);//Precarga declaraciondes por default de MySql
            ps.setString(1, usuario);//Busqueda de usuario
            rs = ps.executeQuery();//Muestra resultado de la busqueda

            if (rs.next()) {
                return rs.getInt(1);//Retorna ID
            }

            return 1;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return 1;
        } finally {
            try {
                con.close();//Libera conexion
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
     public boolean validaSA(Usuario usr) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con;
        con= ManipulaDBC.conectaDB();

        String sql = "SELECT idcuenta,nombre, tipo, Apaterno, Amaterno, password, tipo_pregunta, respuesta, correo FROM usuario WHERE password =? and tipo=1";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getPassword());
            rs = ps.executeQuery();

            if (rs.next()) {                
                usr.setNombreUser(rs.getString(2));                
                System.out.println("Nombre: "+usr.getNombreUser());
                return true;
            }

            return false;

        } catch (SQLException e) {
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
    
     public void modificar(Usuario usr) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con;
        con= ManipulaDBC.conectaDB();
       
        String sql = "UPDATE usuario SET idcuenta=?,nombre=?, tipo=?, Apaterno=?, Amaterno=?, password=?, tipo_pregunta=?, respuesta=?, correo=?  WHERE idcuenta =?";
        
        try {
            ps = con.prepareStatement(sql);
            //ps.setString(1, usr.getPassword());
            //rs = ps.executeQuery();

            ps.setString(1, usr.getUser());
            ps.setString(2, usr.getNombreUser());
            ps.setInt(3, usr.getTipo());
            ps.setString(4, usr.getApaterno());
            ps.setString(5, usr.getAmaterno());
            ps.setString(6,usr.getPassword());
            ps.setInt(7, usr.getPregunta());
            ps.setString(8, usr.getRespuesta());
            ps.setString(9, usr.getCorreo());
            ps.setString(10, usr.getUser());
            int res = ps.executeUpdate();
            

            //

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            //
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
     
    public boolean esEmail(String correo) {
        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(correo);
        return mather.find();
    }

}
