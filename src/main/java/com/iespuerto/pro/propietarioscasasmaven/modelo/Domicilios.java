/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespuerto.pro.propietarioscasasmaven.modelo;

/**
 *Interfaz que relaciona la clase Propietarios con la clase Casas
 * @author Tote
 * @param <T> 
 * @param <V>
 */
public interface Domicilios<T,V> {

    /**
     *Método ideado para guardar objetos en sus respectivas estucturas.
     * @param obj recibe el objeto a guardar
     */
    abstract void add(T obj);

    /**
     *Método ideado para escribir en fichero.
     * @param obj recibe el objeto del cual extraer el texto
     * @return retorna true si consigue escribir y false en el caso contrario.
     */
    abstract boolean escribir(V obj);
    
    
}
