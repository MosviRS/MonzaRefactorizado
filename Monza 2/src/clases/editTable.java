/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LAPROMA1-17
 */
public class editTable extends DefaultTableModel {
        @Override
        public boolean isCellEditable(int row,int column) {
           if(column==(-1)){
               return true;
           }else{
               return false;
           }
        }
}
