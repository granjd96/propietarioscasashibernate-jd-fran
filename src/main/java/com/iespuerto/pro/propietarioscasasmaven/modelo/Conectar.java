/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespuerto.pro.propietarioscasasmaven.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author José David González Díaz
 */
public class Conectar {

    public static EntityManagerFactory emf;
    private static int idMaximo=0;
    
    public static void crearFactory() {
        emf = Persistence.createEntityManagerFactory("unidadPersistencia");
    }

    public static EntityManager nuevaEntityManager() {
        return emf.createEntityManager();
    }

    public static void cerrarFactory() {
        emf.close();
    }

    public static ArrayList<Propietarios> obtenerPropietarios() {
        ArrayList<Propietarios> propietarios = new ArrayList<Propietarios>();
        EntityManager em = Conectar.nuevaEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        List<Propietarios> ps = em.createNamedQuery("Propietarios.findAll", Propietarios.class)
                .getResultList();
        System.out.println("Lista");
        for (int i = 0; i < ps.size(); i++) {
            propietarios.add(ps.get(i));

        }

        System.out.println("Array");
        for (int i = 0; i < propietarios.size(); i++) {
            System.out.println(propietarios.get(i).getNombre() + " " + propietarios.get(i).getApellidos() + " - " + propietarios.get(i).getIdDniPropietarios());

        }
        et.commit();
        em.close();
        
        return propietarios;
    }

    public static ArrayList<Casas> obtenerCasas() {
        ArrayList<Casas> casas = new ArrayList<>();
        EntityManager em = Conectar.nuevaEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        List<Casas> ps = em.createNamedQuery("Casas.findAll", Casas.class)
                .getResultList();
        System.out.println("Lista");
        for (int i = 0; i < ps.size(); i++) {
            casas.add(ps.get(i));
            if(ps.get(i).getIdCasa() > idMaximo){
                idMaximo = ps.get(i).getIdCasa();
            }
        }

        System.out.println("Array");
        for (int i = 0; i < casas.size(); i++) {
            System.out.println(casas.get(i).getIdCasa()+" - "+casas.get(i).getDireccion());
        }
        et.commit();
        em.close();
        return casas;
    }

    public static Propietarios obtenerPropietarioPorDNI(String id) {
        //ConectarDB.crearFactory(); //Esto solo se realiza una vez por aplicacion. Hacer un close de emf cuando se cierre

        EntityManager em = Conectar.nuevaEntityManager();

        Propietarios p = em.createNamedQuery("Propietarios.findByIdDniPropietarios", Propietarios.class)
                .setParameter("idDniPropietarios", id)
                .getSingleResult();

        System.out.println(p.getNombre() + " - " + p.getIdDniPropietarios());

        em.close();
        return p;
    }

    public static Casas obtenerCasasPorID(int id) {
        //ConectarDB.crearFactory(); //Esto solo se realiza una vez por aplicacion. Hacer un close de emf cuando se cierre

        EntityManager em = Conectar.nuevaEntityManager();

        Casas c = em.createNamedQuery("Casas.findByIdCasa", Casas.class)
                .setParameter("idCasa", id)
                .getSingleResult();

        System.out.println(c.getIdCasa()+" - "+c.getDireccion());
        em.close();
        return c;
    }
    
    public static void agregarPropietarios(String dni, String nombre, String apellidos){
        EntityManager em = Conectar.nuevaEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        String comando = "INSERT PROPIETARIOS(ID_DNI_PROPIETARIOS, NOMBRE, APELLIDOS) values("
                    + "'"+dni+"',"
                    + "'"+nombre+"',"
                    + "'"+apellidos+"')";
        em.createNativeQuery(comando).executeUpdate();
        et.commit();
        
        for (int i = 0; i < Conectar.obtenerPropietarios().size(); i++) {
            System.out.println(Conectar.obtenerPropietarios().get(i).getNombre());
            
        }
        em.close();
        
    }
    
    public static void agregarCasas(String direccion, int metros, boolean ascensor, boolean garaje, int precio){
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
        EntityManager em = Conectar.nuevaEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        String comando = "INSERT CASAS(ID_CASA,DIRECCION,METROS,ASCENSOR,GARAJE,PRECIO) values("
                    + id+","
                    + "'"+direccion+"',"
                    + metros+","
                    + ascensorNum+","
                    + garajeNum+","
                    + precio+")";
        em.createNativeQuery(comando).executeUpdate();
        et.commit();
        em.close();
    }
    
    public static void modificarPropietario(String dni, String nombre, String apellidos, String dniAntiguo){
        EntityManager em = Conectar.nuevaEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        String comando = "UPDATE PROPIETARIOS SET "
                + "ID_DNI_PROPIETARIOS = '"+dni+"', "
                + "NOMBRE = '"+nombre+"', "
                + "APELLIDOS = '"+apellidos+"' "
                + "WHERE ID_DNI_PROPIETARIOS = '"+dniAntiguo+"'";
        em.createNativeQuery(comando).executeUpdate();
        et.commit();
        em.close();
    }
    
    public static void modificarCasa(int id, String direccion, int metros, boolean ascensor, boolean garaje, int precio){
        int ascensorNum = 0;
        int garajeNum = 0;
        if(ascensor){
            ascensorNum=1;
        }
        if(garaje){
            garajeNum=1;
        }
        String comando = "UPDATE CASAS SET "
                + "DIRECCION = '"+direccion+"', "
                + "METROS = "+metros+", "
                + "ASCENSOR = "+ascensorNum+", "
                + "GARAJE = "+garajeNum+", "
                + "PRECIO = "+precio+" "
                + "WHERE ID_CASA = "+id;
        
        EntityManager em = Conectar.nuevaEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.createNativeQuery(comando).executeUpdate();
        et.commit();
        em.close();
    }
    
    public static void borrarPropietario(String dni){
        EntityManager em = Conectar.nuevaEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        String comando = "DELETE FROM PROPIETARIOS WHERE ID_DNI_PROPIETARIOS = '"+dni+"'";
        System.out.println("Hola");
        em.createNativeQuery(comando, Propietarios.class).executeUpdate();
        System.out.println("Adios");
        et.commit();
        System.out.println("Adosito");
        em.close();
     }
    
    public static void borrarCasa(int id){
        EntityManager em = Conectar.nuevaEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        String comando = "DELETE FROM CASAS WHERE ID_CASA = "+id;
        em.createNativeQuery(comando, Casas.class).executeUpdate();
        et.commit();
        em.close();
    }
}
