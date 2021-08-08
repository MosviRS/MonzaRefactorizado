/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Rasteck7
 */
public class ManipulaDBC//Metodo que permite la manipulacion y el acceso a la  BD(SQL)
{

    public static Connection conectaDB()//Funcion de tipo conection 
    {
        Conexion x = new Conexion();//objeto de tipo conection, realizara un enlace a la BD correspondiente
        try
        {
            //Configuracion para realizar conexion al servidor y a la BD
            return x.Conecta("localhost:3306", "mydb", "root", "", 2);//Host,Nombre de la BD,Usuario,Contraseña, Manejador para MySql 
        } catch (SQLException ex)
        {
            return null;//Error de Conexion 
        }
    }

    public static void desconectaDB(Connection con)//Funcion para liberar la conexion a la BD
    {
        Conexion x = new Conexion();
        x.desconectar(con);//Libera Conexion a BD
    }

}
