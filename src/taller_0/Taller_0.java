/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller_0;

import java.io.File;
import ucn.*;
import java.util.Scanner;
import java.io.IOException;

public class Taller_0 
{

        
    public static void main(String[] args) throws IOException 
    {
                     // lectura Clientes.txt
        String [] nombres = new String [350];
        String [] apellidos = new String [350];
        String [] ruts = new String [350];
        String [] contraseñas = new String [350];
        int [] saldos = new int [350];
        int cantClientes = lecturaClientes(nombres, apellidos,ruts,contraseñas,saldos);
        
                     //lectura Status.txt
        String [] status = new String [350];
        
                     // lectura peliculas.txt
        String [] tipoPelicula = new String[45];
        String [] nombrePeliculas = new String[45];
        
        int [] recaudacionMañana = new int [45];
        int [] recaudacionTarde = new int [45];
        int [] recaudacionTotal = new int [45];        
                
        int [] salas1 = {1,2,3};
        
        boolean [][] funcionesMañana = new boolean [45][3];
        boolean [][] funcionesTarde = new boolean [45][3];
        
        int cantPeliculas = 0;
        
                     //datos entradas
        String [] rutsEntradas = new String [350];
        int [] cantEntradas = new int [350];
        String [] peliculaClientes = new String [350];
        String [] horarioEntradas = new String [350];
        int [] salaComprada = new int [350];
        String [][] matrizEntradaCliente = new String [350][10];
        
        int cantCompraClientes = 0; 
        
        int [][] sala1M = new int [10][30];
    }
    
    public static void optimizarSalas(int [][]sala)
    {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                sala[i][j] = -1;
            }
        }
        
        for (int i = 0; i < 5; i++) {
            for (int j = 26; j < 31; j++) {
                sala[i][j] = -1;
            }
        }
        
        
    }
    public static int lecturaClientes(String [] nombres, String [] apellidos, String[]ruts, String [] contraseñas, int [] saldos) throws IOException 
    {
        Scanner sc = new Scanner(new File("clientes.txt"));
        
        int cantClientes = 0;
        
        while(sc.hasNextLine()) {
            String linea = sc.next();
            String [] partes = linea.split(",");
            String nombre = partes[0];
            nombres[cantClientes] = nombre;
            
            String apellido = partes[1];
            apellidos [cantClientes] = apellido;
            
            String rut = partes[2];
            ruts[cantClientes] = rut;
            
            String contraseña = partes[3];
            contraseñas[cantClientes] = contraseña;
            
            int saldo = Integer.parseInt(partes[4]);
            saldos[cantClientes] = saldo;
            
            cantClientes++;      
        }
        sc.close();
        return cantClientes;
    }
    
    public static void lecturaStados (String [] ruts, String [] status, int cantClientes) throws IOException
    {
        Scanner sc = new Scanner(new File("Status.txt"));        
        while(sc.hasNextLine())
        {
            String linea = sc.next();
            String [] partes = linea.split(",");
            String rutBuscar = partes[0];
            
            int posUsuario = buscarPosCliente( ruts,  rutBuscar, cantClientes);
            
            if(posUsuario != -1)
            {
                String statu = partes[1];
                status[posUsuario] = statu;
                
            }else
            {
                System.out.println("Usuario No Encontrado");   
            }
        }
        
    }
    
    public static int buscarPosCliente(String [] ruts, String rutBuscar, int cantClientes)
    {
        int ret = -1;
        for (int i = 0; i < cantClientes; i++) {
            if(ruts[i].equalsIgnoreCase(rutBuscar)){
                return i;
            }
            else{
                return -1;
            }
        }
        return ret;
    }
    
    public static int lecturaPeliculas (String [] tipoPeliculas,String [] nombrePeliculas,int [] recaudacionTotal,int [] salas, boolean [][] funcionesMañana, boolean [][] funcionesTarde) throws IOException 
    {
        Scanner sc = new Scanner(new File("Peliculas.txt"));
        int cantPeliculas = 0;
        while(sc.hasNextLine()){
            String linea = sc.next();
            String [] partes = linea.split(",");
            
            String nombrePelicula = partes[0];
            nombrePeliculas[cantPeliculas] = nombrePelicula;
            
            String tipoPelicula = partes[1];
            tipoPeliculas[cantPeliculas] = tipoPelicula;
            
            int recaudacionPelicula = Integer.parseInt(partes[2]);
            recaudacionTotal [cantPeliculas] = recaudacionPelicula;
            
            for (int i = 3; i < 9; i++) {
                
            }
            
            
            int salaF1 = Integer.parseInt(partes[3]);
            String horarioF1 = partes[4];
            
            ingresarFuncion ( horarioF1, salaF1, salas,  funcionesMañana,  funcionesTarde,  cantPeliculas);
            
            int salaF2 = Integer.parseInt(partes[5]);
            String horarioF2 = partes[5];
            
            ingresarFuncion ( horarioF2,  salaF2,  salas, funcionesMañana, funcionesTarde, cantPeliculas);
            
            int salaF3 = Integer.parseInt(partes[7]);
            String horarioF3 = partes[8];
            
            ingresarFuncion (horarioF3,  salaF3, salas,  funcionesMañana, funcionesTarde,  cantPeliculas);
            
            
            
            
        }
        return cantPeliculas;
    }
    
    public static void ingresarFuncion (String horario, int sala,int [] salas, boolean [][] funcionesMañana, boolean [][] funcionesTarde, int cantPeliculas)
    {
        int salaN = 0;
        for(int i = 0; i<3;i++){
            if (sala == i){
                salaN = i;
            }
        }
        
        if(horario.equalsIgnoreCase("M")){
            funcionesMañana[cantPeliculas][salaN]= true;
        }else if(horario.equalsIgnoreCase("T")) {
            funcionesTarde [cantPeliculas][salaN] = true;
        }
        
        
    }
    
    
    
    
}
