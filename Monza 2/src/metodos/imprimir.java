/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metodos;

import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class imprimir {
    private void Dibuja_Linea(PrintWriter ps) {
           try {
               ps.println("----------------------------------------");
           } catch (Exception e) {
               System.out.print(e);
           }
       }
   //para cortar el papel de mi ticketera
    private void cortar(PrintWriter ps) {

           try {
               char[] ESC_CUT_PAPER = new char[]{0x1B, 'm'};
               ps.write(ESC_CUT_PAPER);

           } catch (Exception e) {
               System.out.print(e);
           }
       }

       private void correr(int fin, PrintWriter pw) {
           try {
               int i = 0;
               for (i = 1; i <= fin; i++) {
                   pw.println("");
               }
           } catch (Exception e) {
               System.out.print(e);
           }
       }

       private void setFormato(double formato, PrintWriter pw) {
           try {
               char[] ESC_CUT_PAPER = new char[]{0x1B, '!', (char) formato};
               pw.write(ESC_CUT_PAPER);

           } catch (Exception e) {
               System.out.print(e);
           }
       }

   // para el color de mi ticketera
   private void setRojo(PrintWriter pw) {
           try {
               char[] ESC_CUT_PAPER = new char[]{0x1B, 'r', 1};
               pw.write(ESC_CUT_PAPER);
           } catch (Exception e) {
               System.out.print(e);
           }
       }

   private void setNegro(PrintWriter pw) {
           try {
               char[] ESC_CUT_PAPER = new char[]{0x1B, 'r', 0};
               pw.write(ESC_CUT_PAPER);

           } catch (Exception e) {
               System.out.print(e);
           }
       }
public void comenzarImprmir(){
    try{
                FileWriter file = new FileWriter("COM4:");
                BufferedWriter buffer = new BufferedWriter(file);
                PrintWriter ps = new PrintWriter(buffer);

                setFormato(1, ps);
                ps.println("mi razon rozial");
                ps.println("");
                ps.println("");
                ps.println("RUC :" + "");
                Dibuja_Linea(ps);
                ps.println("Ticket    :" + "" + " - " + "");
                ps.println("S/N       :" + "");
                ps.println("Fecha     :" + "" + "  Hora : " + "");
                ps.println("Caj   : " + "" + " Ven : " + "" + " Int : " + "");
                Dibuja_Linea(ps);
                ps.println("Sr(a)     :" + "");
                Dibuja_Linea(ps);
                ps.println("Cant     " + "Descripcion" + "             " + "PVP");
                Dibuja_Linea(ps);
                int lineas = 7;
                       
                // aqui recorro mis productos y los imprimo

                Dibuja_Linea(ps);
                ps.println("TOTAL         : S./ " + "");
                ps.println();
                String ultimo = "              " + "";
                ultimo += "                   " + "";
                ps.println(ultimo);
                ps.println("  NO SE ACEPTAN CAMBIOS NI DEVOLUCIONES");
                ps.println("        GRACIAS POR SU COMPRA          ");
                correr(10, ps);
                cortar(ps);
                ps.close();
            }catch(IOException e){
                System.out.println(e);
        }
}
public void comenzar2(){
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        java.io.File fichero = new java.io.File("C:\\Users\\mosvi\\OneDrive\\Documentos\\frfrf.docx");
        if (desktop.isSupported(Desktop.Action.PRINT)){
        try {
        desktop.print(fichero);
        } catch (Exception e){
        System.out.print("El sistema no permite imprimir usando la clase Desktop");
        e.printStackTrace();
        }
        }else{
        System.out.print("El sistema no permite imprimir usando la clase Desktop");
        }
//ava.awt.Desktop desktop = java.awt.Desktop.getDesktop();
//java.io.File fichero = new java.io.File("C:/doc.doc");
//if (desktop.isSupported(Desktop.Action.PRINT)){
//try {
//try{
//String nombreImpresora = "HP Color LaserJet CP1215"; //dato a modificar segun la impresora
//Process pr = Runtime.getRuntime().exec("Rundll32 printui.dll,PrintUIEntry /y /n \""+nombreImpresora+"\"");
//}catch(Exception ex){
//System.out.println("Ha ocurrido un error al ejecutar el comando. Error: "+ex);
//}
//desktop.print(fichero);
//} catch (Exception e){
//System.out.print("El sistema no permite imprimir usando la clase Desktop");
//e.printStackTrace();
//}
//}else{
//System.out.print("El sistema no permite imprimir usando la clase Desktop");
//}



//    String texto = "Esto es lo que va a la impresora";
// 
//		PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
//                  
// 
//		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
//		DocPrintJob docPrintJob = printService.createPrintJob();
//		Doc doc = new SimpleDoc(texto.getBytes(), flavor, null);
//		try {
//			docPrintJob.print(doc, null);
//		} catch (PrintException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

public void comenzar3(){
   DocFlavor flavor = DocFlavor.INPUT_STREAM.POSTSCRIPT;
   PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
   aset.add(MediaSizeName.ISO_A4);
   PrintService[] pservices =
                 PrintServiceLookup.lookupPrintServices(flavor, aset);
   if (pservices.length > 0) {
       DocPrintJob pj = pservices[0].createPrintJob();
       try {
          // FileInputStream fis = new FileInputStream("test.ps");
           Doc doc = new SimpleDoc("hol comowq", flavor, null);
           pj.print(doc, aset);
        }catch (PrintException e) { //Ésta otra
            System.out.println(e);
        }
   }
}
public void imprimirComprobante(JasperPrint reporte){
 
    try{
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
	System.out.println("Lista de impresoras disponibles");

	for (PrintService printService: printServices) {
		System.out.println("\t" + printService.getName());
	}
//	    Connection conn = null;
//	    conn = conexion.obtenerConexion();
//	    String dir= "DIRECCION REPORTE";
//	    JasperReport reporteJasper = JasperCompileManager.compileReport(dir);
//	    Map parametro = new HashMap();
//	    parametro.put("@nro", nro);
//	    JasperPrint mostrarReporte = JasperFillManager.fillReport(reporteJasper,parametro, conn);
 
 
 
	// ESTABLECE DATOS DE IMPRESORAS
 
	    PrinterJob job = PrinterJob.getPrinterJob();
             DocFlavor flavor = DocFlavor.INPUT_STREAM.POSTSCRIPT;
             PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
             aset.add(MediaSizeName.ISO_A4);
	    PrintService[] services = PrintServiceLookup.lookupPrintServices(null,null);
	    int selectedService = 0;
	    for(int i = 0; i < services.length;i++){
                System.out.println(services[i].getName());
	    if(services[i].getName().toUpperCase().contains("CANON MP250 SERIES")){
	        selectedService = i;
                System.out.println("si hay impresoar");
	    }
 
	    }
 
	      job.setPrintService(services[selectedService]);
	      PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
	      MediaSizeName mediaSizeName = MediaSize.findMedia(4,4,MediaPrintableArea.INCH);
	      printRequestAttributeSet.add(mediaSizeName);
	      printRequestAttributeSet.add(new Copies(1));
	      JRPrintServiceExporter exporter;
	      exporter = new JRPrintServiceExporter();
	      exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, services[selectedService]);
	    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, services[selectedService].getAttributes());
	    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
	    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
	    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
	    exporter.setParameter(JRExporterParameter.JASPER_PRINT,reporte);
	    exporter.exportReport();
    }catch(JRException ex){
        JOptionPane.showMessageDialog(null, "Error de JREEXEPCION: " + ex);
 
    } catch (PrinterException ex) {
        JOptionPane.showMessageDialog(null,"ERROR PRINTEREXCEPCION " + ex);
    }
 
}
}
