package metodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Uriel
 */
import java.sql.*;

public class Conexion
{

    Connection con;

    /**
     * Conecta con una base de datos de oracle o mysql
     *
     * @param host -Nombre del servidor o la dirección ip
     * @param instancia -Es el servicio de escucha de oracle (SID)
     * @param usuario -Usuario de la base de datos
     * @param pass -Password del usuario en la base de datos
     * @param manejador -para oracle (1) para mysql(2)
     */
    
//    public Connection GetConexion(){
//        String base="mydb";
//        String user="root";
//        String password="";
//        
//    }
    
    public Connection Conecta(String host, String instancia, String usuario, String pass, int manejador) throws SQLException
            //Funcion para configurar la conexion a la BD
    {
        String driver = "";
        String error = "";
        String url = "";
        //oracle.jdbc.driver.OracleConnection@19134f4
        //com.mysql.jdbc.JDBC4Connection@94948a

        //ORACLE
        
        
        
        if (manejador == 1 || manejador == 2)//Comprobacion del Manejador si es para oracle o para MySql(BD)
        {
            if (manejador == 1)//Manejador para Oracle
            {
                driver = "oracle.jdbc.driver.OracleDriver";//driver proporcionado por Oracle
                error = "No se pudo cargar el driver Oracle";//Mensaje de error al no cargar el Driver 
                url = "jdbc:oracle:thin:@" + host + ":3306:" + instancia;//Direccion del Servidor o Host de la BD
            }
            else if (manejador == 2)//manejador para SQl
            {
                driver = "com.mysql.cj.jdbc.Driver";//Driver de MySql
                //Direccion del servidor o host donde se ubica la BD(MySql)
                url = "jdbc:mysql://" + host + "/" + instancia + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                //Mensaje de Error
                error = "No se pudo cargar el driver Mysql";
            }
            try
            {
                Class.forName(driver).newInstance();//Carga la Base de datos del Servidor
            } catch (Exception e)
            {
                // Mensaje de Error al Cargar la BD
                System.out.println(error);

            }
            try
            {
                // De esta forma se obtiene la conexi¢n 
                con = DriverManager.getConnection(url, usuario, pass);//Cenexion a BD usando direccion , usuario y contraseña
                System.out.println("Conexi¢n establecida.");//Mensaje de Confirmacion
            } catch (SQLException sqle)
            {
                // Ocurrió un error con la conexi¢n 
                System.out.println("Error con la conexi¢n a la base de datos:");

            }
        } else
        {
            System.out.println("ERROR DEBES MANDAR UN 1 PARA ORACLE O UN 2 PARA MYSQL");//Mensaje de Error del Manejador 
        }
        return con;
    }

    /**
     * modifica un registro
     *
     * @param instancia -Es la base de datos a la que se quiere conectar
     * @param usuario -Usuario de la base de datos
     * @param pass -Password del usuario en la base de datos
     */
    //Funcion Mejorada
    public Connection Conecta(/*String host,*/String bd, String usuario, String pass)//Funcion para modificar o registrar Registros(o Datos de la BD)
    {
        String url = "jdbc:odbc:" + bd;//"+host+"/"+bd;       
        try
        {
            Driver d = (Driver) Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();//Carga del Driver para uso en Mysql
        } catch (Exception e)
        {
            System.out.println("No se ha podido cargar el Driver JDBC-ODBC");
        }
        try
        {            // De esta forma se obtiene la conexi¢n 
            con = DriverManager.getConnection(url, usuario, pass);//Cenexion a BD usando direccion , usuario y contraseña
            System.out.println("Conexi¢n establecida.");//Mensaje de Confirmacion de Conexion
        } catch (SQLException sqle)
        {
            // Ocurrió un error con la conexi¢n 
            System.out.println("Error con la conexi¢n a la base de datos:");

        }
        return con;
    }

    /**
     * cierra la conexión de la base de datos
     *
     * @param conect recibe la conexión que se desea cerrar
     */
    public void desconectar(Connection conect)
    {
        conect = null;
    }
}
