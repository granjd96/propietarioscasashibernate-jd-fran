/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespuerto.pro.propietarioscasasmaven.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author granj
 */
public class ConectarDB {

    Connection conexion = null;
    String jdbcUrl;
    String usuario = "root";
    String clave = "1q2w3e4r";

    private int idMaximo=0;
    public ConectarDB() {
        //jdbcUrl = "jdbc:mysql://172.19.99.235:3306/mysql?serverTimezone=UTC";
        //jdbcUrl = "jdbc:mysql://192.168.1.112:3306/propietarioscasas?serverTimezone=UTC";
        //jdbcUrl = "jdbc:mysql://172.19.99.79:3306/propietarioscasas?serverTimezone=UTC";
        jdbcUrl = "jdbc:mysql://192.168.56.101:3306/propietarioscasas?serverTimezone=UTC";
        usuario = "root";
        clave = "1q2w3e4r";

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConectarDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (Connection con = DriverManager.getConnection(jdbcUrl, usuario, clave);) {
            Statement st = con.createStatement();
            String mostrarTablas = "SHOW TABLES";
////            String crearPropietarios = "CREATE TABLE PROPS \n"+
////                    "(  DNI VARCHAR2(9 BYTE) NOT NULL \n"+
////                    ", NOMBRE VARCHAR2(50 BYTE) NOT NULL \n"+
////                    ", APELLIDOS VARCHAR2(150 BYTE) NOT NULL \n"+
////                    ", CONSTRAINT PROPIETARIOS_PK PRIMARY KEY \n"+
////                    "  ( DNI) )";
//            boolean resultado = st.execute(mostrarTablas);
//            ResultSet rs = st.executeQuery(mostrarTablas);
            //System.out.println(rs.toString());
//            ResultSet propietarios = st.executeQuery("SELECT * FROM propietarios");
//            ResultSet casas = st.executeQuery("SELECT * FROM CASAS");
//            
            st.close();
//            
//            
//            
//            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Casa> agregarCasasDB() {
        ArrayList<Casa> arrayCasas = new ArrayList<Casa>();
        try (Connection con = DriverManager.getConnection(jdbcUrl, usuario, clave);) {
            Statement st = con.createStatement();

            ResultSet casas = st.executeQuery("SELECT * FROM CASAS");
            casas.beforeFirst();
//            casas.next();

            boolean r = casas.next();
            while (r) {
                int id = casas.getInt("ID_CASA");
                String direccion = casas.getString("DIRECCION");
                int metros = casas.getInt("METROS");
                boolean ascensor = casas.getBoolean("ASCENSOR");
                boolean garaje = casas.getBoolean("GARAJE");
                int precio = casas.getInt("PRECIO");
                System.out.println(id
                        + " - " + direccion + " "
                        + " - " + metros + " "
                        + " - " + ascensor + " "
                        + " - " + garaje + " "
                        + " - " + precio);
                Casa c = new Casa();
                c.setDireccion(direccion);
                c.setId(id);
                c.setGaraje(garaje);
                c.setAscensor(ascensor);
                c.setPrecio(precio);
                c.setMetros(metros);
                arrayCasas.add(c);
                if(idMaximo < id){
                    idMaximo = id;
                }
                r = casas.next();
            }
//            for (int i = 1; i <= 7; i++) {
//                if (i != 3) {
//                    System.out.println(casas.getObject(i).toString());
//                }
//
//            }
//
//            int id = Integer.parseInt(casas.getObject(1).toString());
//            System.out.println("id: " + id);
//            System.out.println(casas.getObject(1).toString());
//            System.out.println(casas.getObject(2).toString());
//            //System.out.println(casas.getObject(3).toString());
//            System.out.println(casas.getObject(4).toString());
//            System.out.println(casas.getObject(5).toString());
//            System.out.println(casas.getObject(6).toString());
//            System.out.println(casas.getObject(7).toString());
            //arrayCasas.add((Casa) casas.getObject(1));
            //System.out.println(arrayCasas.get(0).toString());
            //System.out.println(props);

            casas.close();
//            
            st.close();
//            
//            
//            
//            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arrayCasas;
    }

    public ArrayList<Propietario> agregarPropietariosDB() {
        ArrayList<Propietario> arrayPropietarios = new ArrayList<Propietario>();
        try (Connection con = DriverManager.getConnection(jdbcUrl, usuario, clave);) {
            Statement st = con.createStatement();

            ResultSet propietarios = st.executeQuery("SELECT * FROM PROPIETARIOS");
            propietarios.beforeFirst();
            boolean r = propietarios.next();
            while (r) {
                String id = propietarios.getString("ID_DNI_PROPIETARIOS");
                String nombre = propietarios.getString("NOMBRE");
                String apellidos = propietarios.getString("APELLIDOS");
                System.out.println(id
                        + " - " + nombre + " "
                        + apellidos);
                Propietario p = new Propietario();
                p.setDni(id);
                p.setNombre(nombre);
                p.setApellidos(apellidos);
                arrayPropietarios.add(p);
                r = propietarios.next();
            }

//            
//            System.out.println(propietarios.getObject(1).toString());
//            System.out.println(propietarios.getObject(2).toString());
//            System.out.println(propietarios.getObject(3).toString());
            //arrayCasas.add((Casa) casas.getObject(1));
            //System.out.println(arrayCasas.get(0).toString());
            //System.out.println(props);
            propietarios.close();
//            
            st.close();
//            
//            
//            
//            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arrayPropietarios;
    }

    public void agregarUsuario(String dni, String nombre, String apellido){
        try (Connection con = DriverManager.getConnection(jdbcUrl, usuario, clave);) {
            Statement st = con.createStatement();
            String comando = "INSERT PROPIETARIOS(ID_DNI_PROPIETARIOS, NOMBRE, APELLIDOS) values("
                    + "'"+dni+"',"
                    + "'"+nombre+"',"
                    + "'"+apellido+"')";
                    
            st.execute(comando);
            st.close();
        }   catch (SQLException ex) {
            Logger.getLogger(ConectarDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    };
    
    public void agregarCasa(String direccion, int metros, boolean ascensor, boolean garaje, int precio){
        idMaximo++;
        int id = idMaximo;
        int ascensorNum = 0;
        int garajeNum = 0;
        if(ascensor){
            ascensorNum=1;
        }
        if(garaje){
            garajeNum=1;
        }
        try (Connection con = DriverManager.getConnection(jdbcUrl, usuario, clave);) {
            Statement st = con.createStatement();
            String comando = "INSERT CASAS(ID_CASA,DIRECCION,METROS,ASCENSOR,GARAJE,PRECIO) values("
                    + id+","
                    + "'"+direccion+"',"
                    + metros+","
                    + ascensorNum+","
                    + garajeNum+","
                    + precio+")";
            st.execute(comando);
            st.close();
        }   catch (SQLException ex) {
            Logger.getLogger(ConectarDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
;
        
    public void operar(String operacion) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, usuario, clave);) {
            Statement st = con.createStatement();
            String operacionDB = operacion;

            boolean resultado = st.execute(operacionDB);

            if (resultado) {
                System.out.println("operacion exitosa");
            } else {
                System.out.println("operacion no valida");
            }
//            
            st.close();
//            
//            
//            
//            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    
    //EntityManagerFactory

    public int getIdMaximo() {
        return idMaximo;
    }
    
    
}
