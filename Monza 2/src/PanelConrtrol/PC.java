/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PanelConrtrol;

import Entidades.Provedores;
import Entidades.Usuario;
import Entidades.bitacoraA;
import Entidades.clientes;
import Entidades.orders;
import LogIn.*;
import cjb.ci.Mensaje;
import clases.IMGtabla;
import clases.editTable;
import java.awt.FileDialog;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import metodos.Querys;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import metodos.ManipulaDBC;
import metodos.SqLEntregas;
import metodos.SqLImagen;
import metodos.SqLNotas;
import metodos.SqLProvedores;
import metodos.SqLclientes;
import metodos.SqlBitacora; 
import metodos.Conexion;
import metodos.Sqlproductos_notas;
import metodos.imprimir;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Rasteck7
 */
public class PC extends javax.swing.JFrame {

    public static Usuario mod;
    Connection con;
    clientes cli;
    Provedores prov;
    Querys q = new Querys();
    public static DefaultTableModel atm = new editTable();
    JFileChooser jf;
    SqLImagen sqli = new SqLImagen();
    SqLProvedores sqlprov = new SqLProvedores();
    SqLEntregas sqlentr = new SqLEntregas();
    SqLclientes sqlcli = new SqLclientes();
    SqlBitacora sqlbita = new SqlBitacora();
    int noTabPane;
    //Variables para Notas
    public static clientes p = new clientes();
    public static orders op = new orders();
   
    SqLNotas MN = new SqLNotas();
    public DefaultTableModel mdl;
    public static bitacoraA bit = new bitacoraA();
    TableRowSorter tbfil;
    LogIn L = new LogIn();
    public static ArrayList<String> auxrr = new ArrayList();
    TableRowSorter<TableModel> tr, tr1, tr2, tr3;
    Date dt = new Date();
    Mensaje mj = new Mensaje();
    public Sqlproductos_notas pord= new Sqlproductos_notas();

    /**
     * Creates new form Registro
     */
    public PC() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Panel de Control");
        //Nav.setEnabledAt(4, false);
        ImageIcon icon = new ImageIcon("src/IMGM/LogoMonza.png");
        this.setIconImage(icon.getImage());
        
    }

    public PC(Usuario mod) {
        initComponents();
        this.mod = mod;
        this.setLocationRelativeTo(null);
        this.setTitle("Panel de Control");
        //Nav.setEnabledAt(4, false);
        ImageIcon icon = new ImageIcon("src/IMGM/LogoMonza.png");
        this.setIconImage(icon.getImage());
        US.setText("User:" + L.us);
        System.out.println(L.us);
        
        System.out.println(mod.getTipo());
        if (mod.getTipo() == 1) {

            Nav.setEnabledAt(0, true);
            Nav.setEnabledAt(1, true);
            Nav.setEnabledAt(2, true);
            Nav.setEnabledAt(3, true);
            Nav.setEnabledAt(4, true);
            Nav.setEnabledAt(5, true);

        } else if (mod.getTipo() == 2) {
            Nav.setEnabledAt(0, true);
            Nav.setEnabledAt(1, true);
            Nav.setEnabledAt(2, true);
            Nav.setEnabledAt(3, true);
            Nav.setEnabledAt(4, true);
            Nav.setEnabledAt(5, false);
           
        }

        String[] titulo1 = new String[]{"Modelo", "Nombre", "Precio", "Marca/Provedor", "Cantidad", "Categoria", "Imagen"};
        agenda1.setDefaultRenderer(Object.class, new IMGtabla());
        atm.setColumnIdentifiers(titulo1);
        agenda1.setRowHeight(70);
        agenda1.setModel(atm);
        //         Tabla Productos
//        DefaultTableModel mdlPR = (DefaultTableModel) agenda1.getModel();
//        agenda1.getModel();
//        tr = new TableRowSorter<>(mdlPR);
//        agenda1.setRowSorter(tr);
////         Tabla Clientes
//        DefaultTableModel mdlCl = (DefaultTableModel) agenda.getModel();
//        agenda.getModel();
//        tr1 = new TableRowSorter<>(mdlCl);
//        agenda.setRowSorter(tr1);
////         Tabla Entregas
//        DefaultTableModel mdlE = (DefaultTableModel) agenda3.getModel();
//        agenda3.getModel();
//        tr2 = new TableRowSorter<>(mdlE);
//        agenda3.setRowSorter(tr2);

        String[] titulo = new String[]{"Nombre", "Descripcion", "PrecioG", "precioN", "Imagen"};

        //TablaNotas
        mdl = (DefaultTableModel) TNote.getModel();
        mdl = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "(ID)Modelo", "Nombre del Producto", "cantidad", "Monto por unidad", "Monto subtotal"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        TNote.setModel(mdl);
        jTFuser3.setEditable(false);

//         Tabla Provedores
        DefaultTableModel mdlP = (DefaultTableModel) agenda4.getModel();
        agenda4.getModel();
        tr = new TableRowSorter<>(mdlP);
        agenda4.setRowSorter(tr3);
        dateofpurchase.setDate(dt);
        dateB.setDate(dt);
        dateB.setEnabled(false);
//        Date1.setText("Fecha : " + dateofpurchase.getDate().getDay()+"/"+dateofpurchase.getDate().getMonth()+"/"+dateofpurchase.getDate().getYear());

    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLocaleChooser1 = new com.toedter.components.JLocaleChooser();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuEliminar = new javax.swing.JMenuItem();
        jMenuCargar = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        Eliminar1 = new javax.swing.JMenuItem();
        jArticulos = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        Nav = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        Nota = new javax.swing.JPanel();
        DeliveryType = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        Amount = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        credit = new javax.swing.JRadioButton();
        counted = new javax.swing.JRadioButton();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        nameProduct = new javax.swing.JTextField();
        status = new javax.swing.JLabel();
        NNote = new javax.swing.JLabel();
        Date = new javax.swing.JLabel();
        priceOne = new javax.swing.JLabel();
        model = new javax.swing.JTextField();
        addProduct = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        TNote = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        Reference = new javax.swing.JTextArea();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        Save = new javax.swing.JButton();
        dateofpurchase = new com.toedter.calendar.JDateChooser();
        Deadline = new com.toedter.calendar.JDateChooser();
        jSeparator20 = new javax.swing.JSeparator();
        jSeparator21 = new javax.swing.JSeparator();
        NumNote = new javax.swing.JTextField();
        Quantity = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        Total = new javax.swing.JLabel();
        storage = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        Productos = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        ap1 = new javax.swing.JTextField();
        nmo1 = new javax.swing.JTextField();
        am1 = new javax.swing.JTextField();
        name1 = new javax.swing.JTextField();
        email1 = new javax.swing.JTextField();
        nmm1 = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        buscar1 = new javax.swing.JTextField();
        jBGuardar1 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        agenda1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txtFile = new javax.swing.JTextField();
        Clientes = new javax.swing.JPanel();
        Clean = new javax.swing.JButton();
        buscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTFuser3 = new javax.swing.JTextField();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        agenda = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        Entregas = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        buscar2 = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        jScrollPane11 = new javax.swing.JScrollPane();
        agenda3 = new javax.swing.JTable();
        Minimize1 = new javax.swing.JButton();
        Proveedores = new javax.swing.JPanel();
        Nomprov = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        buscar3 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        dirccprove = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        emailprov = new javax.swing.JTextField();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        telprov = new javax.swing.JTextField();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        mercprov = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane12 = new javax.swing.JScrollPane();
        agenda4 = new javax.swing.JTable();
        Add1 = new javax.swing.JButton();
        Bitacora = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        agenda5 = new javax.swing.JTable();
        Date1 = new javax.swing.JLabel();
        Minimize2 = new javax.swing.JButton();
        dateB = new com.toedter.calendar.JDateChooser();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        Shutdown = new javax.swing.JButton();
        Minimize = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        US = new javax.swing.JLabel();

        jMenuEliminar.setText("Eliminar");
        jMenuEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuEliminar);

        jMenuCargar.setText("Cargar");
        jMenuCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCargarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuCargar);

        Eliminar1.setText("Eliminar");
        Eliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Eliminar1ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(Eliminar1);

        jArticulos.setText("Articulos de la nota");
        jArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jArticulosActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jArticulos);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(243, 240, 235));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Nav.setBackground(new java.awt.Color(243, 240, 235));
        Nav.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Nav.setToolTipText("");
        Nav.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                NavStateChanged(evt);
            }
        });
        Nav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NavMousePressed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(243, 240, 235));

        Nota.setBackground(new java.awt.Color(243, 240, 235));
        Nota.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DeliveryType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inmediata", "Por entregar"}));
        Nota.add(DeliveryType, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 450, 100, -1));

        jLabel26.setBackground(new java.awt.Color(235, 235, 235));
        jLabel26.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLabel26.setText("Fecha de Entrega:");
        Nota.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 420, 120, 20));

        Amount.setBackground(new java.awt.Color(243, 240, 235));
        Amount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Amount.setForeground(new java.awt.Color(102, 102, 102));
        Amount.setText("00.00");
        Amount.setBorder(null);
        Amount.setCaretColor(new java.awt.Color(204, 51, 0));
        Amount.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Amount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AmountMouseClicked(evt);
            }
        });
        Nota.add(Amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 410, 140, 20));

        jSeparator6.setBackground(new java.awt.Color(235, 235, 235));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        Nota.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, 160, 10));

        jLabel25.setBackground(new java.awt.Color(235, 235, 235));
        jLabel25.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLabel25.setText("Monto Recibido:");
        Nota.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 120, 20));

        credit.setBackground(new java.awt.Color(243, 240, 235));
        credit.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        credit.setText("Credito");
        credit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditActionPerformed(evt);
            }
        });
        Nota.add(credit, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 90, -1));

        counted.setBackground(new java.awt.Color(243, 240, 235));
        counted.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        counted.setText("Contado");
        counted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countedActionPerformed(evt);
            }
        });
        Nota.add(counted, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 90, -1));

        jSeparator5.setBackground(new java.awt.Color(235, 235, 235));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        Nota.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 390, 160, 10));

        jLabel24.setBackground(new java.awt.Color(235, 235, 235));
        jLabel24.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLabel24.setText("Total:");
        Nota.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, 40, 20));

        jLabel7.setBackground(new java.awt.Color(235, 235, 235));
        jLabel7.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLabel7.setText("Cantidad:");
        Nota.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 70, 20));

        jSeparator4.setBackground(new java.awt.Color(235, 235, 235));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        Nota.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 190, 10));

        nameProduct.setBackground(new java.awt.Color(243, 240, 235));
        nameProduct.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nameProduct.setForeground(new java.awt.Color(102, 102, 102));
        nameProduct.setText("Nombre del Producto");
        nameProduct.setBorder(null);
        nameProduct.setCaretColor(new java.awt.Color(204, 51, 0));
        nameProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nameProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameProductMouseClicked(evt);
            }
        });
        Nota.add(nameProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 170, 20));

        status.setBackground(new java.awt.Color(235, 235, 235));
        status.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        Nota.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 230, 20));

        NNote.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        NNote.setText("Numero de Nota:");
        Nota.add(NNote, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 120, 40));

        Date.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        Date.setText("Fecha de Compra:");
        Nota.add(Date, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 140, 40));

        priceOne.setBackground(new java.awt.Color(235, 235, 235));
        priceOne.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        Nota.add(priceOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, 70, 20));

        model.setBackground(new java.awt.Color(243, 240, 235));
        model.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        model.setForeground(new java.awt.Color(102, 102, 102));
        model.setText("Ingrese Modelo");
        model.setBorder(null);
        model.setCaretColor(new java.awt.Color(204, 51, 0));
        model.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        model.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modelMouseClicked(evt);
            }
        });
        model.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                modelKeyReleased(evt);
            }
        });
        Nota.add(model, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 140, 20));

        addProduct.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        addProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/add2.png"))); // NOI18N
        addProduct.setBorder(null);
        addProduct.setBorderPainted(false);
        addProduct.setContentAreaFilled(false);
        addProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addProduct.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/add1.png"))); // NOI18N
        addProduct.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/add1.png"))); // NOI18N
        addProduct.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/add1.png"))); // NOI18N
        addProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addProductMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addProductMouseReleased(evt);
            }
        });
        addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductActionPerformed(evt);
            }
        });
        Nota.add(addProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 60, -1, 40));

        TNote.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(TNote);

        Nota.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 900, 240));

        Reference.setColumns(20);
        Reference.setRows(5);
        jScrollPane7.setViewportView(Reference);

        Nota.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 420, 340, 60));

        jLabel27.setBackground(new java.awt.Color(235, 235, 235));
        jLabel27.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLabel27.setText("Tipo de Entrega:");
        Nota.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 110, 20));

        jLabel28.setBackground(new java.awt.Color(235, 235, 235));
        jLabel28.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLabel28.setText("Referencia de entrega:");
        Nota.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 390, 160, 20));

        Save.setBackground(new java.awt.Color(243, 240, 235));
        Save.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        Save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/s1.png"))); // NOI18N
        Save.setBorder(null);
        Save.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/s2.png"))); // NOI18N
        Save.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/s2.png"))); // NOI18N
        Save.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/s2.png"))); // NOI18N
        Save.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/s2.png"))); // NOI18N
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });
        Nota.add(Save, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 390, 60, 60));

        dateofpurchase.setBackground(new java.awt.Color(243, 240, 235));
        Nota.add(dateofpurchase, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 150, -1));

        Deadline.setMaxSelectableDate(new java.util.Date(253370790084000L));
        Nota.add(Deadline, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 450, 150, -1));

        jSeparator20.setBackground(new java.awt.Color(235, 235, 235));
        jSeparator20.setForeground(new java.awt.Color(0, 0, 0));
        Nota.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 160, 10));

        jSeparator21.setBackground(new java.awt.Color(235, 235, 235));
        jSeparator21.setForeground(new java.awt.Color(0, 0, 0));
        Nota.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 40, 110, 10));

        NumNote.setBackground(new java.awt.Color(243, 240, 235));
        NumNote.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NumNote.setForeground(new java.awt.Color(102, 102, 102));
        NumNote.setText("XXXX");
        NumNote.setBorder(null);
        NumNote.setCaretColor(new java.awt.Color(204, 51, 0));
        NumNote.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        NumNote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NumNoteMouseClicked(evt);
            }
        });
        NumNote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NumNoteKeyReleased(evt);
            }
        });
        Nota.add(NumNote, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 20, 100, 20));

        Quantity.setBackground(new java.awt.Color(243, 240, 235));
        Quantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Quantity.setForeground(new java.awt.Color(102, 102, 102));
        Quantity.setText("0");
        Quantity.setBorder(null);
        Quantity.setCaretColor(new java.awt.Color(204, 51, 0));
        Quantity.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Quantity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QuantityMouseClicked(evt);
            }
        });
        Nota.add(Quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 140, 20));

        jSeparator7.setBackground(new java.awt.Color(235, 235, 235));
        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        Nota.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 160, 10));

        Total.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Total.setText("xxxx");
        Nota.add(Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 370, 160, -1));

        storage.setBackground(new java.awt.Color(235, 235, 235));
        storage.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        Nota.add(storage, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, 70, 20));

        jLabel20.setBackground(new java.awt.Color(235, 235, 235));
        jLabel20.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLabel20.setText("Modelo:");
        Nota.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 60, 20));

        jLabel19.setBackground(new java.awt.Color(235, 235, 235));
        jLabel19.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLabel19.setText("En Almacen:");
        Nota.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 90, 20));

        jLabel21.setBackground(new java.awt.Color(235, 235, 235));
        jLabel21.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLabel21.setText("Precio Unitario:");
        Nota.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, 110, 20));

        jLabel22.setBackground(new java.awt.Color(235, 235, 235));
        jLabel22.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLabel22.setText("Nombre del Producto:");
        Nota.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 160, 20));

        jScrollPane1.setViewportView(Nota);

        Nav.addTab("Nota", jScrollPane1);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Productos.setBackground(new java.awt.Color(243, 240, 235));
        Productos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jLabel9.setText("Categoria:");
        Productos.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 110, -1));

        jLabel10.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Modelo:");
        Productos.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 20));

        jLabel12.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jLabel12.setText("Precio:");
        Productos.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 100, 20));

        jLabel13.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jLabel13.setText("Nombre:");
        Productos.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 90, 20));

        jLabel14.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jLabel14.setText("Marca/Proveedor;");
        Productos.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 150, 20));

        jLabel15.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        jLabel15.setText("Cantidad:");
        Productos.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, 20));

        ap1.setBackground(new java.awt.Color(243, 240, 235));
        ap1.setBorder(null);
        ap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ap1ActionPerformed(evt);
            }
        });
        Productos.add(ap1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 250, 20));

        nmo1.setBackground(new java.awt.Color(243, 240, 235));
        nmo1.setBorder(null);
        Productos.add(nmo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 250, 20));

        am1.setBackground(new java.awt.Color(243, 240, 235));
        am1.setBorder(null);
        am1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                am1ActionPerformed(evt);
            }
        });
        am1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                am1KeyTyped(evt);
            }
        });
        Productos.add(am1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 250, 20));

        name1.setBackground(new java.awt.Color(243, 240, 235));
        name1.setBorder(null);
        name1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name1ActionPerformed(evt);
            }
        });
        Productos.add(name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 250, 20));

        email1.setBackground(new java.awt.Color(243, 240, 235));
        email1.setBorder(null);
        email1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                email1ActionPerformed(evt);
            }
        });
        Productos.add(email1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 250, 20));

        nmm1.setBackground(new java.awt.Color(243, 240, 235));
        nmm1.setBorder(null);
        nmm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nmm1ActionPerformed(evt);
            }
        });
        Productos.add(nmm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 250, 20));

        jSeparator10.setBackground(new java.awt.Color(235, 235, 235));
        jSeparator10.setForeground(new java.awt.Color(0, 0, 0));
        Productos.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 250, 10));

        jSeparator11.setBackground(new java.awt.Color(235, 235, 235));
        jSeparator11.setForeground(new java.awt.Color(0, 0, 0));
        Productos.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 250, 10));

        jSeparator12.setBackground(new java.awt.Color(235, 235, 235));
        jSeparator12.setForeground(new java.awt.Color(0, 0, 0));
        Productos.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 250, 10));

        jSeparator13.setBackground(new java.awt.Color(235, 235, 235));
        jSeparator13.setForeground(new java.awt.Color(0, 0, 0));
        Productos.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 250, 10));

        jSeparator14.setBackground(new java.awt.Color(235, 235, 235));
        jSeparator14.setForeground(new java.awt.Color(0, 0, 0));
        Productos.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 250, 20));

        jSeparator15.setBackground(new java.awt.Color(235, 235, 235));
        jSeparator15.setForeground(new java.awt.Color(0, 0, 0));
        Productos.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 250, 10));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/icons8-search-48_1.png"))); // NOI18N
        Productos.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 50, 40));

        buscar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscar1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscar1KeyTyped(evt);
            }
        });
        Productos.add(buscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 170, -1));

        jBGuardar1.setBackground(new java.awt.Color(243, 240, 235));
        jBGuardar1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jBGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/s1.png"))); // NOI18N
        jBGuardar1.setText("Guardar");
        jBGuardar1.setBorder(null);
        jBGuardar1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/s2.png"))); // NOI18N
        jBGuardar1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/s2.png"))); // NOI18N
        jBGuardar1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/s2.png"))); // NOI18N
        jBGuardar1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/s2.png"))); // NOI18N
        jBGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardar1ActionPerformed(evt);
            }
        });
        Productos.add(jBGuardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 150, 60));

        agenda1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Modelo", "Nombre", "Precio", "Marca/Proveedor", "Catidad", "Categoria", "Imagen"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        agenda1.setComponentPopupMenu(jPopupMenu1);
        agenda1.setRowHeight(70);
        agenda1.setSelectionBackground(new java.awt.Color(206, 166, 8));
        agenda1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agenda1MouseClicked(evt);
            }
        });
        agenda1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                agenda1KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(agenda1);

        jScrollPane8.setViewportView(jScrollPane3);

        Productos.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 720, 350));

        jButton1.setText("Selecciona la imagen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Productos.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, -1));
        Productos.add(txtFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 130, -1));

        jPanel4.add(Productos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 520));

        Nav.addTab("Productos", jPanel4);

        Clientes.setBackground(new java.awt.Color(243, 240, 235));
        Clientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Clean.setText("Abonado");
        Clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CleanActionPerformed(evt);
            }
        });
        Clientes.add(Clean, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, 100, 30));

        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscarKeyTyped(evt);
            }
        });
        Clientes.add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 170, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/icons8-search-48_1.png"))); // NOI18N
        Clientes.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 50, 40));

        jTFuser3.setBackground(new java.awt.Color(243, 240, 235));
        jTFuser3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTFuser3.setForeground(new java.awt.Color(102, 102, 102));
        jTFuser3.setText("Inserte Cantidad");
        jTFuser3.setBorder(null);
        jTFuser3.setCaretColor(new java.awt.Color(204, 51, 0));
        jTFuser3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTFuser3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTFuser3MouseClicked(evt);
            }
        });
        Clientes.add(jTFuser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 420, 110, 20));

        jSeparator16.setBackground(new java.awt.Color(235, 235, 235));
        jSeparator16.setForeground(new java.awt.Color(0, 0, 0));
        Clientes.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 440, 130, 10));

        jLabel11.setBackground(new java.awt.Color(235, 235, 235));
        jLabel11.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLabel11.setText("Cantidad:");
        Clientes.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 420, 70, 20));

        agenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No. Nota", "Nombre", "Dirección", "Fecha de Compra", "Total a Pagar", "Abonado", "Saldo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        agenda.setColumnSelectionAllowed(true);
        agenda.setComponentPopupMenu(jPopupMenu2);
        agenda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        agenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agendaMouseClicked(evt);
            }
        });
        agenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                agendaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(agenda);
        agenda.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jScrollPane9.setViewportView(jScrollPane2);

        Clientes.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1050, 370));

        jButton2.setText("Imprimir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Clientes.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 420, 110, -1));

        Nav.addTab("Clientes", Clientes);

        Entregas.setBackground(new java.awt.Color(243, 240, 235));
        Entregas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/icons8-search-48_1.png"))); // NOI18N
        Entregas.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 50, 40));

        buscar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscar2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscar2KeyTyped(evt);
            }
        });
        Entregas.add(buscar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 170, -1));

        agenda3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No. de Nota", "Nombre", "Dirección", "Referencia", "Fecha de Entrega", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        agenda3.setColumnSelectionAllowed(true);
        agenda3.setComponentPopupMenu(jPopupMenu2);
        agenda3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agenda3MouseClicked(evt);
            }
        });
        agenda3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                agenda3KeyReleased(evt);
            }
        });
        jScrollPane11.setViewportView(agenda3);
        agenda3.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jScrollPane10.setViewportView(jScrollPane11);

        Entregas.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1040, 370));

        Minimize1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        Minimize1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/pdf-40.png"))); // NOI18N
        Minimize1.setText("Exportar PDF");
        Minimize1.setBorder(null);
        Minimize1.setBorderPainted(false);
        Minimize1.setContentAreaFilled(false);
        Minimize1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Minimize1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/export-pdf-40.png"))); // NOI18N
        Minimize1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/export-pdf-40.png"))); // NOI18N
        Minimize1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/export-pdf-40.png"))); // NOI18N
        Minimize1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Minimize1ActionPerformed(evt);
            }
        });
        Entregas.add(Minimize1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, 150, 40));

        Nav.addTab("Entregas", Entregas);

        Proveedores.setBackground(new java.awt.Color(243, 240, 235));
        Proveedores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Nomprov.setBackground(new java.awt.Color(243, 240, 235));
        Nomprov.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Nomprov.setForeground(new java.awt.Color(102, 102, 102));
        Nomprov.setText("Inserta Nombre");
        Nomprov.setBorder(null);
        Nomprov.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NomprovMouseClicked(evt);
            }
        });
        Nomprov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NomprovActionPerformed(evt);
            }
        });
        Nomprov.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NomprovKeyReleased(evt);
            }
        });
        Proveedores.add(Nomprov, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 44, 170, 20));

        jSeparator8.setBackground(new java.awt.Color(235, 235, 235));
        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        Proveedores.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 180, 10));

        jLabel2.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLabel2.setText("Nombre del Provedor:");
        Proveedores.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 160, 30));

        buscar3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscar3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscar3KeyTyped(evt);
            }
        });
        Proveedores.add(buscar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 170, -1));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/icons8-search-48_1.png"))); // NOI18N
        Proveedores.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 50, 40));

        dirccprove.setBackground(new java.awt.Color(243, 240, 235));
        dirccprove.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dirccprove.setForeground(new java.awt.Color(102, 102, 102));
        dirccprove.setText("Inserta Dirección");
        dirccprove.setBorder(null);
        dirccprove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dirccproveMouseClicked(evt);
            }
        });
        dirccprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dirccproveActionPerformed(evt);
            }
        });
        dirccprove.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dirccproveKeyReleased(evt);
            }
        });
        Proveedores.add(dirccprove, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 240, 20));

        jLabel3.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLabel3.setText("Direccón:");
        Proveedores.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 70, 30));

        jSeparator9.setBackground(new java.awt.Color(235, 235, 235));
        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        Proveedores.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 250, 10));

        emailprov.setBackground(new java.awt.Color(243, 240, 235));
        emailprov.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailprov.setForeground(new java.awt.Color(102, 102, 102));
        emailprov.setText("Inserta Correo Electronico");
        emailprov.setBorder(null);
        emailprov.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emailprovMouseClicked(evt);
            }
        });
        emailprov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailprovActionPerformed(evt);
            }
        });
        emailprov.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailprovKeyReleased(evt);
            }
        });
        Proveedores.add(emailprov, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 190, 20));

        jSeparator17.setBackground(new java.awt.Color(235, 235, 235));
        jSeparator17.setForeground(new java.awt.Color(0, 0, 0));
        Proveedores.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 200, 10));

        jLabel4.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLabel4.setText("Correo Electronico:");
        Proveedores.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 130, 30));

        telprov.setBackground(new java.awt.Color(243, 240, 235));
        telprov.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        telprov.setForeground(new java.awt.Color(102, 102, 102));
        telprov.setText("Inserta Telefono");
        telprov.setBorder(null);
        telprov.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                telprovMouseClicked(evt);
            }
        });
        telprov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telprovActionPerformed(evt);
            }
        });
        telprov.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                telprovKeyReleased(evt);
            }
        });
        Proveedores.add(telprov, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 290, 20));

        jSeparator18.setBackground(new java.awt.Color(235, 235, 235));
        jSeparator18.setForeground(new java.awt.Color(0, 0, 0));
        Proveedores.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 300, 10));

        jLabel17.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLabel17.setText("Telefono:");
        Proveedores.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 120, 30));

        jLabel18.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        jLabel18.setText("Mercancia:");
        Proveedores.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 80, 30));

        jSeparator19.setBackground(new java.awt.Color(235, 235, 235));
        jSeparator19.setForeground(new java.awt.Color(0, 0, 0));
        Proveedores.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 250, 10));

        mercprov.setBackground(new java.awt.Color(243, 240, 235));
        mercprov.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mercprov.setForeground(new java.awt.Color(102, 102, 102));
        mercprov.setText("Inserta Mercancia");
        mercprov.setBorder(null);
        mercprov.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mercprovMouseClicked(evt);
            }
        });
        mercprov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mercprovActionPerformed(evt);
            }
        });
        mercprov.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mercprovKeyReleased(evt);
            }
        });
        Proveedores.add(mercprov, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 240, 20));

        agenda4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Dirección", "Apellido", "No. Telefonico", "Correo", "Tipo de Mercancia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        agenda4.setColumnSelectionAllowed(true);
        agenda4.setComponentPopupMenu(jPopupMenu2);
        agenda4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agenda4MouseClicked(evt);
            }
        });
        agenda4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                agenda4KeyReleased(evt);
            }
        });
        jScrollPane12.setViewportView(agenda4);
        agenda4.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (agenda4.getColumnModel().getColumnCount() > 0) {
            agenda4.getColumnModel().getColumn(5).setHeaderValue("Tipo de Mercancia");
        }

        jScrollPane4.setViewportView(jScrollPane12);

        Proveedores.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 990, 290));

        Add1.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        Add1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/add2.png"))); // NOI18N
        Add1.setBorder(null);
        Add1.setBorderPainted(false);
        Add1.setContentAreaFilled(false);
        Add1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Add1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/add1.png"))); // NOI18N
        Add1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/add1.png"))); // NOI18N
        Add1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/add1.png"))); // NOI18N
        Add1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Add1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Add1MouseReleased(evt);
            }
        });
        Add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add1ActionPerformed(evt);
            }
        });
        Proveedores.add(Add1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, -1, 40));

        Nav.addTab("Proveedores", Proveedores);

        Bitacora.setBackground(new java.awt.Color(243, 240, 235));
        Bitacora.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        agenda5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Clave Administrador", "Acción", "Cantidad Recibida", "No. Nota", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        agenda5.setComponentPopupMenu(jPopupMenu2);
        agenda5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agenda5MouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(agenda5);
        agenda5.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        Bitacora.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 960, 430));

        Date1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        Date1.setText("Fecha: ");
        Bitacora.add(Date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 50, 30));

        Minimize2.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        Minimize2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/pdf-40.png"))); // NOI18N
        Minimize2.setText("Exportar PDF");
        Minimize2.setBorder(null);
        Minimize2.setBorderPainted(false);
        Minimize2.setContentAreaFilled(false);
        Minimize2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Minimize2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/export-pdf-40.png"))); // NOI18N
        Minimize2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/export-pdf-40.png"))); // NOI18N
        Minimize2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/export-pdf-40.png"))); // NOI18N
        Minimize2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Minimize2ActionPerformed(evt);
            }
        });
        Bitacora.add(Minimize2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, 40));

        dateB.setBackground(new java.awt.Color(243, 240, 235));
        Bitacora.add(dateB, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 150, -1));

        Nav.addTab("Bitacora", Bitacora);

        jPanel1.add(Nav, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 550));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1050, 550));

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Microsoft Tai Le", 1, 28)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/engranajes.png"))); // NOI18N
        jLabel8.setText("Centro de trabajo");
        jPanel10.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 370, 70));

        Shutdown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/Shutdown1.png"))); // NOI18N
        Shutdown.setBorder(null);
        Shutdown.setBorderPainted(false);
        Shutdown.setContentAreaFilled(false);
        Shutdown.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Shutdown.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/Shutdown2.png"))); // NOI18N
        Shutdown.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/Shutdown2.png"))); // NOI18N
        Shutdown.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/Shutdown2.png"))); // NOI18N
        Shutdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShutdownActionPerformed(evt);
            }
        });
        jPanel10.add(Shutdown, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, -1, 40));

        Minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/Minimize.png"))); // NOI18N
        Minimize.setBorder(null);
        Minimize.setBorderPainted(false);
        Minimize.setContentAreaFilled(false);
        Minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Minimize.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/Minimize2.png"))); // NOI18N
        Minimize.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/Minimize2.png"))); // NOI18N
        Minimize.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/Minimize2.png"))); // NOI18N
        Minimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MinimizeActionPerformed(evt);
            }
        });
        jPanel10.add(Minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 10, -1, 40));

        Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/close.png"))); // NOI18N
        Close.setBorder(null);
        Close.setBorderPainted(false);
        Close.setContentAreaFilled(false);
        Close.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Close.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/close2.png"))); // NOI18N
        Close.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/close2.png"))); // NOI18N
        Close.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/close2.png"))); // NOI18N
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });
        jPanel10.add(Close, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 10, -1, 40));

        US.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        US.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMGM/UserPC.png"))); // NOI18N
        US.setText("User:");
        jPanel10.add(US, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 280, -1));

        getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 630));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MinimizeActionPerformed
        this.setState(PC.ICONIFIED);
    }//GEN-LAST:event_MinimizeActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "Desea Salir?", "Exit", dialog);
        if (result == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_CloseActionPerformed

    private void ShutdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShutdownActionPerformed
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "Cerrando Sesión\nEstas seguro de salir ?", "Exit", dialog);
        if (result == 0) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new LogIn().setVisible(true);
                }
            });
        }
       
        this.dispose();
    }//GEN-LAST:event_ShutdownActionPerformed

    private void NavMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NavMousePressed
        //        // TODO add your handling code here:
        //        buscar.setText("");
        //        buscar1.setText("");
        //        String filtro=buscar.getText();
        //        if(!filtro.equals("")){
        //            tr.setRowFilter(RowFilter.regexFilter(filtro));
        //        }else{
        //            tr.setRowFilter(null);
        //        }
        //        String filtro2=buscar1.getText();
        //        if(!filtro2.equals("")){
        //            tr2.setRowFilter(RowFilter.regexFilter(filtro));
        //        }else{
        //            tr2.setRowFilter(null);
        //        }
    }//GEN-LAST:event_NavMousePressed

    private void agenda1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agenda1MouseClicked
        //        // TODO add your handling code here:
        //        fslt=agenda1.getSelectedRow();
        //        name1.setText(agenda1.getValueAt(fslt, 0).toString());
        //        ap1.setText(agenda1.getValueAt(fslt, 1).toString());
        //        am1.setText(agenda1.getValueAt(fslt, 2).toString());
        //        nmm1.setText(agenda1.getValueAt(fslt, 3).toString());
        //        nmo1.setText(agenda1.getValueAt(fslt, 4).toString());
        //        email1.setText(agenda1.getValueAt(fslt, 5).toString());
        //        data3[0]=agenda1.getValueAt(fslt, 0).toString();
        //        data3[1]=agenda1.getValueAt(fslt, 1).toString();
        //        data3[2]=agenda1.getValueAt(fslt, 2).toString();
        //        data3[3]=agenda1.getValueAt(fslt, 3).toString();
        //        data3[4]=agenda1.getValueAt(fslt, 4).toString();
        //        data3[5]=agenda1.getValueAt(fslt, 5).toString();
        //        filas=fslt;
        if (agenda1.getSelectedColumn() == 6) {
            jf = new JFileChooser();
            FileNameExtensionFilter fil = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
            jf.setFileFilter(fil);
            jf.setCurrentDirectory(new File("\\Downloads"));
            int el = jf.showOpenDialog(this);
            if (el == JFileChooser.APPROVE_OPTION) {
                //txtRuta.setText(jf.getSelectedFile().getAbsolutePath());

                sqli.Modificarxcragadoimagen(agenda1, con, jf.getSelectedFile().getAbsolutePath());

                //lblFoto.setIcon(new ImageIcon(txtRuta.getText()));
            }
        }
    }//GEN-LAST:event_agenda1MouseClicked

    private void buscar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar1KeyReleased
        // TODO add your handling code here:
//        String filtro = buscar1.getText();
//        if (!filtro.equals("")) {
//            tr.setRowFilter(RowFilter.regexFilter(filtro));
//        } else {
//            tr.setRowFilter(null);
//        }
    }//GEN-LAST:event_buscar1KeyReleased

    private void name1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name1ActionPerformed
        // TODO add your handling code here:
        ap1.requestFocus();

    }//GEN-LAST:event_name1ActionPerformed

    private void ap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ap1ActionPerformed
        // TODO add your handling code here:
        am1.requestFocus();
    }//GEN-LAST:event_ap1ActionPerformed

    private void Minimize1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Minimize1ActionPerformed
        try{
        Conexion con= new Conexion();
        imprimir imp= new imprimir();
        Connection conn;
        conn= ManipulaDBC.conectaDB();;
        
            JasperReport reporte = null;
            String path ="C:\\Monza 2\\src\\reportes\\reportEntregasHz.jasper";
         
           // Map parametro = new HashMap();
            //parametro.put("id_estado", 36);
            
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, conn); //Sustituir null por parametro
            //imp.imprimirComprobante(jprint);
            JasperViewer view = new JasperViewer(jprint, false);
            
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            
            view.setVisible(true);
            
        } catch (JRException ex) {
            Logger.getLogger(PC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_Minimize1ActionPerformed

    private void agenda3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agenda3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_agenda3MouseClicked

    private void buscar2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar2KeyReleased
        // TODO add your handling code here:
//        String filtro = buscar2.getText();
//        if (!filtro.equals("")) {
//            tr2.setRowFilter(RowFilter.regexFilter(filtro));
//        } else {
//            tr2.setRowFilter(null);
//        }
    }//GEN-LAST:event_buscar2KeyReleased

    private void agendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agendaMouseClicked
        // TODO add your handling code here:
        try {
            //Guardamos en un entero la fila seleccionada.
            int filaseleccionada = agenda.getSelectedRow();
            if (filaseleccionada == -1) {
                JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");

            } else {
                jTFuser3.setEditable(true);

//                textfield_bastidor.setText(bastidor);
//                textfield_color.setText(color);
//                textfield_marca.setText(email);
//                textfield_modelo.setText(matricula);
//                textfield_matricula.setText(marca);
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_agendaMouseClicked

    private void jTFuser3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTFuser3MouseClicked
        // TODO add your handling code here:
        if (agenda.getSelectedRow() > -1) {
            jTFuser3.setText("");
        }


    }//GEN-LAST:event_jTFuser3MouseClicked

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        //        // TODO add your handling code here:
//        String filtro = buscar.getText();
//        if (!filtro.equals("")) {
//            tr1.setRowFilter(RowFilter.regexFilter(filtro));
//        } else {
//            tr1.setRowFilter(null);
//        }
    }//GEN-LAST:event_buscarKeyReleased

    private void CleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CleanActionPerformed
        SqLNotas sqlnotas = new SqLNotas();
        // TODO add your handling code here:
        //        String cad;
        //        fslt=agenda.getSelectedRow();
        //        data[0]=agenda1.getValueAt(fslt, 0).toString();
        //        data[1]=agenda1.getValueAt(fslt, 1).toString();
        //        data[2]=agenda1.getValueAt(fslt, 2).toString();
        //        data[3]=agenda1.getValueAt(fslt, 3).toString();
        //        data[4]=agenda1.getValueAt(fslt, 4).toString();
        //        data[5]=agenda1.getValueAt(fslt, 5).toString();
        //        cad=data[0]+"--"+data[1]+"--"+data[2]+"--"+data[3]+"--"+data[4]+"--"+data[5];
        //        if (agenda.getSelectedRow()!=-1) {
        //            removeLineFromFile(cad);
        //            modelo.removeRow(agenda.getSelectedRow());
        //        }else{
        //            JOptionPane.showMessageDialog(null, "Debes seleccionar para poder Eliminar!");
        //        }
//        Date dtN=dateofpurchase.getDate();
//        bit.setFecha(dtN.toString().replace(" ", "/"));

        try {
            //Guardamos en un entero la fila seleccionada.
            int filaseleccionada = agenda.getSelectedRow();

            if (filaseleccionada == -1) {
                JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");

            } else {
                sqlcli.operacion(agenda, con, Double.parseDouble(jTFuser3.getText()), this);

                jTFuser3.setEditable(false);
                jTFuser3.setText("Inserte Cantidad");
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_CleanActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        //        java.awt.EventQueue.invokeLater(new Runnable() {
        //            public void run() {
        //                new exa().setVisible(true);
        //            }
        //        });
        //        this.dispose();

        Date dtN = dateofpurchase.getDate();
        Date dtE = Deadline.getDate();
        bit = new bitacoraA();
        auxrr = new ArrayList();
        for (int i = 0; i < TNote.getRowCount(); i++) {
            auxrr.add((String) TNote.getValueAt(i, 0));
            auxrr.add((String) TNote.getValueAt(i, 1));
            auxrr.add((String) TNote.getValueAt(i, 2));
            auxrr.add((String) TNote.getValueAt(i, 3));
            auxrr.add((String) TNote.getValueAt(i, 4));
        }
        System.out.println(auxrr);
        p.setMonto_recibido(Double.parseDouble(Amount.getText()));
        p.setFaltante(p.getTotal() - p.getMonto_recibido());
        p.setFecha_nota(dtN.toString().replace(" ", "/"));
//        bit.setFecha_nota(dateofpurchase.toString());
        bit.setFecha(dtN.toString().replace(" ", "/"));
        bit.setCantiadad(Double.toString(p.getMonto_recibido()));
        bit.setNo_nota(p.getNo_nota());
        bit.setCuentaUsuario(L.us);

        //sea contado
        if (PC.credit.isSelected()) {
            bit.setDid("Abono");
        } else {
            bit.setDid("Contado");
        }
        if (PC.DeliveryType.getSelectedItem().equals("Por entregar")) {
            op.setReferencia(Reference.getText());
            op.setFech(dtE.toString().replace(" ", "/"));
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InfoClientes().setVisible(true);
            }
        });
        this.dispose();

//    InfoClientes ir= new InfoClientes();
//    ir.setVisible(true);
//    ir.setLocationRelativeTo(null);

    }//GEN-LAST:event_SaveActionPerformed

    private void addProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductActionPerformed
        Date dtN = dateofpurchase.getDate();
        if (!model.getText().equals("")
                && !nameProduct.getText().equals("")
                && !Quantity.getText().equals("")
                && !NumNote.getText().equals("")
                && !dtN.toString().equals("")
                && !NumNote.getText().equals("XXXX")) {

            p.setModeloPro(model.getText());
            p.setNombrePro(nameProduct.getText());
            p.setExistencia(Integer.parseInt(storage.getText()));
            p.setNo_nota(NumNote.getText());
            p.setPrecio(Double.parseDouble(priceOne.getText()));
            op.setCant(Integer.parseInt(Quantity.getText()));
            //Cantidad
            if (MN.Verificar(p, op)) {
                NumNote.setEnabled(false);
                
                p.setEx(p.getExistencia() - op.getCant());
                p.setTotal(p.getTotal() + (op.getCant() * p.getPrecio()));
                insertTable(p.getModeloPro(), p.getNombrePro(), op.getCant(), p.getPrecio());
                Total.setText(p.getTotal() + "");
                MN.actualizar(p, this);
                status.setText("Agregado con exito..");
                model.setText("");
                nameProduct.setText("");
                storage.setText("");
                priceOne.setText("");
                Quantity.setText("");

//                int dialog = JOptionPane.DEFAULT_OPTION;
//                JOptionPane.showConfirmDialog(null, "Articulo agregado exitosamente! :3","Saved successfully!",dialog);
            } else {
                int dialog = JOptionPane.DEFAULT_OPTION;
                JOptionPane.showConfirmDialog(null, "Lo siento algunos datos son incorrectos :c", "save error", dialog);
                if (NumNote.getText().equals("")
                        || p.getNo_nota().equals("XXXX")) {
                    JOptionPane.showConfirmDialog(null, "Numero de Nota vacia"
                            + "\nIngresar Nota antes de agregar articulos", "save error", dialog);
                }
//                if (MN.verificarNote(p)||
//                        NumNote.getText().equals("")||
//                        p.getNo_nota().equals("XXXX")) {
//                    JOptionPane.showConfirmDialog(null, "Numero de Nota Existente O vacia","save error",dialog);
//                }
            }
        } else {
            int dialog = JOptionPane.DEFAULT_OPTION;
            JOptionPane.showConfirmDialog(null, "Lo siento algunos datos son incorrectos :c", "save error", dialog);
            if (NumNote.getText().equals("")
                    || p.getNo_nota().equals("XXXX")) {
                JOptionPane.showConfirmDialog(null, "Numero de Nota vacia"
                        + "\nIngresar Nota antes de agregar articulos", "save error", dialog);
            }
        }
    }//GEN-LAST:event_addProductActionPerformed
    private void insertTable(String IDM, String Nombre, int Cantidad, Double precioU) {
        String SubT;
        SubT = (Double.toString(Cantidad * precioU));
        String cadena = IDM + "--" + Nombre + "--" + Cantidad + "--" + precioU + "--" + SubT + "--";
        StringTokenizer linea = new StringTokenizer(cadena, "--");///para convertir la cadena 
        Vector v = new Vector();///vector donde se guardan los elementos
        while (linea.hasMoreTokens()) {///si hay mas datos en la linea
            v.addElement(linea.nextToken());
        }
        System.out.println(v);
        mdl.addRow(v);

    }
    private void addProductMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addProductMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_addProductMouseReleased

    private void addProductMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addProductMousePressed

    }//GEN-LAST:event_addProductMousePressed

    private void modelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modelMouseClicked
        // TODO add your handling code here:
        if (model.getText().equals("Ingrese Modelo")) {
            model.setText("");
        }
    }//GEN-LAST:event_modelMouseClicked

    private void nameProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameProductMouseClicked
        // TODO add your handling code here:
        if (nameProduct.getText().equals("Nombre del Producto")) {
            nameProduct.setText("");
        }
    }//GEN-LAST:event_nameProductMouseClicked

    private void jTFuser2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFuser2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFuser2ActionPerformed

    private void AmountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AmountMouseClicked
        // TODO add your handling code here:
        if (Amount.getText().equals("00.00")) {
            Amount.setText("");
        }
    }//GEN-LAST:event_AmountMouseClicked

    private void Add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add1ActionPerformed
        // TODO add your handling code here:

        llenarclaseprovedores();
        sqlprov.insertarprovedores(prov, this, agenda4, con);

    }//GEN-LAST:event_Add1ActionPerformed

    private void Add1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Add1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Add1MouseReleased

    private void Add1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Add1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Add1MousePressed

    private void agenda4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agenda4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_agenda4MouseClicked

    private void mercprovKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mercprovKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_mercprovKeyReleased

    private void mercprovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mercprovActionPerformed
        // TODO add your handling code here:
        ((JTextField) evt.getSource()).transferFocus();
    }//GEN-LAST:event_mercprovActionPerformed

    private void mercprovMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mercprovMouseClicked
        // TODO add your handling code here:
         if (mercprov.getText().equals("Inserta Mercancia")) {
            mercprov.setText("");
        }
    }//GEN-LAST:event_mercprovMouseClicked

    private void telprovKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telprovKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_telprovKeyReleased

    private void telprovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telprovActionPerformed
        // TODO add your handling code here:
        ((JTextField) evt.getSource()).transferFocus();
    }//GEN-LAST:event_telprovActionPerformed

    private void telprovMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_telprovMouseClicked
        // TODO add your handling code here:
         if (telprov.getText().equals("Inserta Telefono")) {
            telprov.setText("");
        }
    }//GEN-LAST:event_telprovMouseClicked

    private void emailprovKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailprovKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_emailprovKeyReleased

    private void emailprovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailprovActionPerformed
        // TODO add your handling code here:
        ((JTextField) evt.getSource()).transferFocus();
    }//GEN-LAST:event_emailprovActionPerformed

    private void emailprovMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailprovMouseClicked
        // TODO add your handling code here:
         if (emailprov.getText().equals("Inserta Correo Electronico")) {
            emailprov.setText("");
        }
        
    }//GEN-LAST:event_emailprovMouseClicked

    private void dirccproveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dirccproveKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_dirccproveKeyReleased

    private void dirccproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dirccproveActionPerformed
        // TODO add your handling code here:
        ((JTextField) evt.getSource()).transferFocus();
    }//GEN-LAST:event_dirccproveActionPerformed

    private void dirccproveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dirccproveMouseClicked
        // TODO add your handling code here:
         if (dirccprove.getText().equals("Inserta Dirección")) {
            dirccprove.setText("");
        }
    }//GEN-LAST:event_dirccproveMouseClicked

    private void buscar3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar3KeyReleased
        // TODO add your handling code here:
//        String filtro = buscar3.getText();
//        if (!filtro.equals("")) {
//            tr3.setRowFilter(RowFilter.regexFilter(filtro));
//        } else {
//            tr3.setRowFilter(null);
//        }
    }//GEN-LAST:event_buscar3KeyReleased

    private void NomprovKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NomprovKeyReleased
        if (Nomprov.getText().equals("Inserta un correo electronico")) {
            Nomprov.setText("");
        }
    }//GEN-LAST:event_NomprovKeyReleased

    private void NomprovMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NomprovMouseClicked
        // TODO add your handling code here:
        if (Nomprov.getText().equals("Inserta Nombre")) {
            Nomprov.setText("");
        }
    }//GEN-LAST:event_NomprovMouseClicked

    private void agenda5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agenda5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_agenda5MouseClicked

    private void Minimize2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Minimize2ActionPerformed
        try{
        Conexion con= new Conexion();
        Connection conn;
        imprimir imp= new imprimir();
        conn= ManipulaDBC.conectaDB();;
        
                    JasperReport reporte = null;
            String path = "C:\\Monza 2\\src\\reportes\\reportbitacora.jasper";
            
            Map parametro = new HashMap();
            //parametro.put("id_estado", 36);
            
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, conn); //Sustituir null por parametro
            imp.imprimirComprobante(jprint);
            JasperViewer view = new JasperViewer(jprint, false);
            
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            
            view.setVisible(true);
            
        } catch (JRException ex) {
            Logger.getLogger(PC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Minimize2ActionPerformed

    private void jBGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardar1ActionPerformed
        // TODO add your handling code here:
        try {
            if (name1.getText().equals("") || ap1.getText().equals("") || am1.getText().equals("") || email1.getText().equals("") || nmm1.getText().equals("")
                    || nmo1.getText().equals("")) {
                mensaje("Upss! Faltan campos por llenar");
            } else {

                sqli = new SqLImagen();

                llenarclaseproductos();

                String s1 = sqli.guardarProductos(con, cli.getModeloPro(), cli.getNombrePro(), cli.getPrecio(), cli.getExistencia(), cli.getCategoria(), cli.getProvedMarca(), jf.getSelectedFile().getAbsolutePath());

                // String s1 = q.Insertar(con, "productos"," idmodelo, nombre, precio, existencia, clasificacion, provedores_id ", valores1);
                if (s1 != null) {
                    Mensaje.error(this, s1);
                    System.out.println("valio queso");
                } else {
                    System.out.println("Exito");
                    sqli.visualizar_tabla(agenda1, con);
                    //agregarProductos(cli.getModeloPro(),cli.getNombrePro(),cli.getPrecio(),cli.getExistencia(),cli.getCategoria(),cli.getProvedMarca(),jf.getSelectedFile().getAbsolutePath().replace("\\","/"));
                }
            }

        } catch (Exception e) {
            if (txtFile.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Falta Seleccionar una imagen ", "Selecciona Imagen", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }//GEN-LAST:event_jBGuardar1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        con = ManipulaDBC.conectaDB();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        ManipulaDBC.desconectaDB(con);
    }//GEN-LAST:event_formWindowClosed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jf = new JFileChooser();
        FileNameExtensionFilter fil = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        jf.setFileFilter(fil);
        jf.setCurrentDirectory(new File("\\Downloads"));
        int el = jf.showOpenDialog(this);
        if (el == JFileChooser.APPROVE_OPTION) {
            //txtRuta.setText(jf.getSelectedFile().getAbsolutePath());
            txtFile.setText(jf.getSelectedFile().getName());

            //lblFoto.setIcon(new ImageIcon(txtRuta.getText()));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void am1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_am1KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            getToolkit().beep();

            evt.consume();

        }
    }//GEN-LAST:event_am1KeyTyped

    private void NomprovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomprovActionPerformed
        // TODO add your handling code here:
        ((JTextField) evt.getSource()).transferFocus();
    }//GEN-LAST:event_NomprovActionPerformed

    private void am1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_am1ActionPerformed
        // TODO add your handling code here:
        email1.requestFocus();
    }//GEN-LAST:event_am1ActionPerformed

    private void email1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_email1ActionPerformed
        // TODO add your handling code here:
        nmm1.requestFocus();
    }//GEN-LAST:event_email1ActionPerformed

    private void nmm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nmm1ActionPerformed
        // TODO add your handling code here:
        nmo1.requestFocus();
    }//GEN-LAST:event_nmm1ActionPerformed

    private void NavStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_NavStateChanged
        // TODO add your handling code here:
        System.out.println();
        if (evt.getSource() instanceof JTabbedPane) {

            JTabbedPane pane = (JTabbedPane) evt.getSource();
            noTabPane = pane.getSelectedIndex();
            switch (pane.getSelectedIndex()) {
                case 0:

                    break;
                case 1:

                    sqli.visualizar_tabla(agenda1, con);
                    break;
                case 2:
                    sqlcli.vizualizar_tabla(agenda, con);
                    break;
                case 3:
                    sqlentr.vizualizar_tabla(agenda3, con);
                    break;
                case 4:
                    sqlprov.visualizar_tabla(agenda4, con);

                    break;
                case 5:
                    sqlbita.vizualizar_tabla(agenda5, con);

                    break;

            }
        }

    }//GEN-LAST:event_NavStateChanged

    private void Eliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Eliminar1ActionPerformed
        // TODO add your handling code here:
        System.out.println("hola?");

        switch (noTabPane) {
            case 0:

                break;

            case 2:
                sqlcli.elimnar(agenda, con);
                break;
            case 3:
                sqlentr.elimnar(agenda3, con);
                break;
            case 4:
                sqlprov.elimnar(agenda4, con);
                break;
            case 5:
                sqlbita.elimnar(agenda5, con);
                break;

        }

    }//GEN-LAST:event_Eliminar1ActionPerformed

    private void jMenuEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEliminarActionPerformed
        // TODO add your handling code here:
        sqli.elimnar(agenda1, con);
    }//GEN-LAST:event_jMenuEliminarActionPerformed

    private void jMenuCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCargarActionPerformed
        // TODO add your handling code here:
        // sqli.cargar(agenda1, con);
    }//GEN-LAST:event_jMenuCargarActionPerformed

    private void agenda1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_agenda1KeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sqli.Modificarcargado(agenda1, con);
        }
    }//GEN-LAST:event_agenda1KeyReleased

    private void agenda4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_agenda4KeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sqlprov.modificar(agenda4, con);
        }

    }//GEN-LAST:event_agenda4KeyReleased

    private void countedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countedActionPerformed
        // TODO add your handling code here:
        credit.setSelected(false);
    }//GEN-LAST:event_countedActionPerformed

    private void creditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditActionPerformed
        // TODO add your handling code here:
        counted.setSelected(false);
    }//GEN-LAST:event_creditActionPerformed

    private void QuantityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuantityMouseClicked
        // TODO add your handling code here:
        if (Quantity.getText().equals("0")) {
            Quantity.setText("");
        }
    }//GEN-LAST:event_QuantityMouseClicked

    private void NumNoteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NumNoteMouseClicked
        // TODO add your handling code here:
        if (NumNote.getText().equals("XXXX")) {
            NumNote.setText("");
        }
    }//GEN-LAST:event_NumNoteMouseClicked

    private void modelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modelKeyReleased
        // Busqueda Model:
        if (!model.equals("")) {
            p.setModeloPro(model.getText());
            try {
                p = MN.BusquedaM(p, this);
            } catch (Exception e) {
            }
            System.out.println(p.getNombrePro() + "," + p.getExistencia() + "," + p.getPrecio());
            nameProduct.setText(p.getNombrePro());
            storage.setText(Integer.toString(p.getExistencia()));
            priceOne.setText(Double.toString(p.getPrecio()));
            status.setText("");
        } else {
            System.out.println(p.getNombrePro() + "," + p.getExistencia() + "," + p.getPrecio());
            nameProduct.setText("");
            storage.setText("");
            priceOne.setText("");
            status.setText("");
        }

    }//GEN-LAST:event_modelKeyReleased

    private void buscar3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar3KeyTyped
//        // TODO add your handling code here:
        String filtro = buscar3.getText();
        buscar3.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                tbfil.setRowFilter(RowFilter.regexFilter("(?i)" + filtro));
            }

        });
        tbfil = new TableRowSorter(agenda4.getModel());
        agenda4.setRowSorter(tbfil);
    }//GEN-LAST:event_buscar3KeyTyped

    private void buscar1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar1KeyTyped
        // TODO add your handling code here:
        String filtro = buscar1.getText();
        buscar1.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                tbfil.setRowFilter(RowFilter.regexFilter("(?i)" + filtro));
            }

        });
        tbfil = new TableRowSorter(agenda1.getModel());
        agenda1.setRowSorter(tbfil);
    }//GEN-LAST:event_buscar1KeyTyped

    private void agendaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_agendaKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sqlcli.modificar(agenda, con);
        }

    }//GEN-LAST:event_agendaKeyReleased

    private void agenda3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_agenda3KeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sqlentr.modificar(agenda3, con);
        }
    }//GEN-LAST:event_agenda3KeyReleased

    private void buscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyTyped
        // TODO add your handling code here:
        String filtro = buscar.getText();

        buscar.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                tbfil.setRowFilter(RowFilter.regexFilter("(?i)" + filtro));
                //  tbfil.setRowFilter(RowFilter.regexFilter("(?i)"+buscar.getText(),0));
            }

        });
        tbfil = new TableRowSorter(agenda.getModel());
        agenda.setRowSorter(tbfil);
    }//GEN-LAST:event_buscarKeyTyped

    private void jArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jArticulosActionPerformed
        // TODO add your handling code here:

        switch (noTabPane) {
            case 0:

                break;

            case 2:
                validaproductos(agenda);
                break;
            case 3:
                validaproductos(agenda3);
                break;
            case 4:

                break;
            case 5:

                break;

        }

    }//GEN-LAST:event_jArticulosActionPerformed

    private void buscar2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar2KeyTyped
        // TODO add your handling code here:
        String filtro = buscar2.getText();
        buscar2.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                tbfil.setRowFilter(RowFilter.regexFilter("(?i)" + filtro));
            }

        });
        tbfil = new TableRowSorter(agenda3.getModel());
        agenda3.setRowSorter(tbfil);
    }//GEN-LAST:event_buscar2KeyTyped

    private void NumNoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumNoteKeyReleased
        // TODO add your handling code here:
        p.setNo_nota(NumNote.getText());
        if (MN.VerificacionNota(p, L)) {
            System.out.println("Nota existe");
            mj.error(this, "Error!\n No. de Nota existente intente con otro");
            NumNote.setText("");
        } else {
            System.out.println("Nota no Exite");
        }
    }//GEN-LAST:event_NumNoteKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        imprimir imp= new imprimir();
        imp.comenzar2();
        imp.comenzar3();
    }//GEN-LAST:event_jButton2ActionPerformed
    public void llenarclaseproductos() {
        cli = new clientes();
        cli.setModeloPro(name1.getText());
        cli.setNombrePro(ap1.getText());
        cli.setPrecio(Double.parseDouble(am1.getText()));
        cli.setProvedMarca(email1.getText());
        cli.setExistencia(Integer.parseInt(nmm1.getText()));
        cli.setCategoria(nmo1.getText());
    }

    public void llenarclaseprovedores() {
        prov = new Provedores();
        prov.setNombre(Nomprov.getText());
        prov.setCorreo(emailprov.getText());
        prov.setTipoMer(mercprov.getText());
        prov.setDireccion(dirccprove.getText());
        prov.setTelefono(telprov.getText());
    }

    public void mensaje(String texto) {
        JOptionPane.showMessageDialog(null, texto);
    }

    public void validaproductos(JTable tbl) {
        
        try {
            //Guardamos en un entero la fila seleccionada.
            int filaseleccionada = tbl.getSelectedRow();

            if (filaseleccionada == -1) {
                JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");

            } else {
                ConsultaArt art = new ConsultaArt(tbl.getValueAt(tbl.getSelectedRow(), 0).toString());
                art.setVisible(true);

            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
        }
    }
//     public void agregarProductos(String mod,String nom,Double preci, int exis,String cate,String provM,String im){
//      atm.addRow(new Object[]{
//          mod,nom,preci,exis,cate,provM,new JLabel(reducirtamamo(im))
//      });
//   }
//   public ImageIcon reducirtamamo(String im){
//      ImageIcon img= new ImageIcon(im);
//      Image conver=img.getImage();
//      Image tam=conver.getScaledInstance(100,120,Image.SCALE_SMOOTH);
//      ImageIcon imgfinal=new ImageIcon(tam);
//      
//      return imgfinal;
//   }
//   

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PC(mod).setVisible(true);
                
             
            }
        });
      
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add1;
    private javax.swing.JTextField Amount;
    private javax.swing.JPanel Bitacora;
    private javax.swing.JButton Clean;
    private javax.swing.JPanel Clientes;
    private javax.swing.JButton Close;
    private javax.swing.JLabel Date;
    private javax.swing.JLabel Date1;
    private com.toedter.calendar.JDateChooser Deadline;
    public static javax.swing.JComboBox<String> DeliveryType;
    private javax.swing.JMenuItem Eliminar1;
    private javax.swing.JPanel Entregas;
    private javax.swing.JButton Minimize;
    private javax.swing.JButton Minimize1;
    private javax.swing.JButton Minimize2;
    private javax.swing.JLabel NNote;
    public javax.swing.JTabbedPane Nav;
    private javax.swing.JTextField Nomprov;
    private javax.swing.JPanel Nota;
    private javax.swing.JTextField NumNote;
    private javax.swing.JPanel Productos;
    private javax.swing.JPanel Proveedores;
    private javax.swing.JTextField Quantity;
    private javax.swing.JTextArea Reference;
    private javax.swing.JButton Save;
    private javax.swing.JButton Shutdown;
    private javax.swing.JTable TNote;
    private javax.swing.JLabel Total;
    private javax.swing.JLabel US;
    private javax.swing.JButton addProduct;
    private javax.swing.JTable agenda;
    private javax.swing.JTable agenda1;
    private javax.swing.JTable agenda3;
    private javax.swing.JTable agenda4;
    private javax.swing.JTable agenda5;
    public static javax.swing.JTextField am1;
    public static javax.swing.JTextField ap1;
    private javax.swing.JTextField buscar;
    private javax.swing.JTextField buscar1;
    private javax.swing.JTextField buscar2;
    private javax.swing.JTextField buscar3;
    public static javax.swing.JRadioButton counted;
    public static javax.swing.JRadioButton credit;
    private com.toedter.calendar.JDateChooser dateB;
    private com.toedter.calendar.JDateChooser dateofpurchase;
    private javax.swing.JTextField dirccprove;
    public static javax.swing.JTextField email1;
    private javax.swing.JTextField emailprov;
    private javax.swing.JMenuItem jArticulos;
    private javax.swing.JButton jBGuardar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.components.JLocaleChooser jLocaleChooser1;
    private javax.swing.JMenuItem jMenuCargar;
    private javax.swing.JMenuItem jMenuEliminar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTFuser3;
    private javax.swing.JTextField mercprov;
    private javax.swing.JTextField model;
    public static javax.swing.JTextField name1;
    private javax.swing.JTextField nameProduct;
    public static javax.swing.JTextField nmm1;
    public static javax.swing.JTextField nmo1;
    private javax.swing.JLabel priceOne;
    private javax.swing.JLabel status;
    private javax.swing.JLabel storage;
    private javax.swing.JTextField telprov;
    private javax.swing.JTextField txtFile;
    // End of variables declaration//GEN-END:variables
}
