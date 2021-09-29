/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller_0;

import java.io.File;
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
        
        int cantPeliculas = 0;//lecturaPeliculas()
        
                     //datos entradas
        String [] rutsEntradas = new String [350];
        int [] cantEntradas = new int [350];
        String [] peliculaClientes = new String [350];
        String [] horarioEntradas = new String [350];
        int [] salaComprada = new int [350];
        String [][] matrizEntradaCliente = new String [350][10];
        
        int cantCompraClientes = 0; 
        
        int [][] sala1M = new int [10][30]; optimizarSalas( sala1M);
        int [][] sala2M = new int [10][30]; optimizarSalas( sala2M);
        int [][] sala3M = new int [10][30]; optimizarSalas( sala3M);
        
        int [][] sala1T = new int [10][30]; optimizarSalas( sala1T);
        int [][] sala2T = new int [10][30]; optimizarSalas( sala2T);
        int [][] sala3T = new int [10][30]; optimizarSalas( sala3T);
        
    }
    
    public static void optimizarSalas(int [][] sala){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                sala[i][j] = -1;
            }
        }
        
        for (int i = 0; i < 4; i++) {
            for (int j = 26; j < 31; j++) {
                sala[i][j] = -1;
            }
        }
        
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 31; j++) {
                if(j>5 && j<26){
                    if(j % 2 != 0){
                        sala[i][j] = -1;
                    }
                }
            }
        }
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 31; j++) {
                if(j>5 && j<26){
                    if(j % 2 != 0){
                        sala[i][j] = 1;
                    }
                }
            }
        } 
                   
        for (int i = 4; i < 10; i++) {
            for (int j = 0; j < 31; j++) {
                
                if((j+1) % 2 == 0){
                    sala[i][j] = 1;
                }
                
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
    public static void devolucionEntrada(String rut, String [] rutsEntradas, int [] cantEntradas, String [] peliculaClientes,String [] horarioEntradas, int [] salaComprada, String [][] matrizEntradaCliente, int cantCompraClientes, String[]ruts,int cantClientes,int[]saldos){
        int pos=buscarPosCliente(rut,rutsEntradas,cantCompraClientes);
        int pos2=buscarPosCliente(rut,ruts,cantClientes);
        //obtenerEntradasUsuario();
        //opDevolucion();
    }
    
    public static void opcionMain(int cantPeliculas, String[]nombrePeliculas,int [] recaudacionMañana,int [] recaudacionTarde,int [] recaudacionTotal, String [] ruts, int cantClientes, String[] nombres, String[] apellidos, int[] saldos, int []cantEntradas, String[] peliculaClientes, String[] horarioEntradas,String[] rutsEntradas,String[][]matrizEntradaCliente, int cantCompraClientes){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Has ingresado al menu de administrador");
        System.out.println("Ingrese una opcion: (Taquilla(1)-Informacion por cliente(2)");
        int opcion=sc.nextInt();
        
        while(opcion!=1 && opcion!=2){
            System.out.println("Ingrese una opcion correcta: Taquilla(1)-Informacion por cliente(2)");
            opcion=sc.nextInt();
        }
        if (opcion==1){
            obtenerRecaudacion(cantPeliculas,nombrePeliculas,recaudacionMañana,recaudacionTarde,recaudacionTotal);
            }
                               
        if (opcion==2){
           obtenerInformacionUsuario( ruts,  cantClientes,nombres,  apellidos,saldos, cantEntradas,peliculaClientes,  horarioEntradas,  rutsEntradas,matrizEntradaCliente,  cantCompraClientes);
        }
        
    }

    public static void obtenerRecaudacion(int cantPeliculas, String[]nombrePeliculas,int [] recaudacionMañana,int [] recaudacionTarde,int [] recaudacionTotal) {
        for(int i=0;i<cantPeliculas;i++){
            int total=0;
            total=recaudacionMañana[i]+recaudacionTarde[i];
            System.out.println(nombrePeliculas[i]);
            System.out.println("La recaudacion total por la pelicula: ´"+nombrePeliculas[i]+"´ es de: "+recaudacionTotal[i]+" pesos");
            System.out.println("El monto recaudado a lo largo del dia es de: "+total+" pesos");
            System.out.println("El monto recaudado en el horario de mañana es de: "+recaudacionMañana[i]+" pesos");
            System.out.println("El monto recaudado en el horario de tarde es de: "+recaudacionTarde[i]+" pesos");
        }
    }

    public static void obtenerInformacionUsuario(String [] ruts, int cantClientes, String[] nombres, String[] apellidos, int[] saldos, 
            int []cantEntradas, String[] peliculaClientes, String[] horarioEntradas, String[] rutsEntradas,
            String[][]matrizEntradaCliente, int cantCompraClientes) {
        
        int rut=0;
        Scanner sc=new Scanner(System.in);
        System.out.println("Ingrese el rut a buscar; ");
        String rutBuscado=sc.nextLine();
        int pos=buscarPosCliente(rutBuscado,ruts,cantClientes);
        while(pos==-1){
            System.out.println("Ingrese un rut correcto ");
            rutBuscado=sc.nextLine();
            pos=buscarPosCliente(rutBuscado,ruts,cantClientes);
        }
        
        int pos2=buscarPosCliente(rutBuscado,rutsEntradas,cantCompraClientes);
        for (int i = 0; i < cantCompraClientes; i++) {
            if(rutsEntradas[i].equalsIgnoreCase(rutBuscado)){
                System.out.println(nombres[pos]+" "+apellidos[pos]+" con un saldo de: "+saldos[pos]+" pesos");
                System.out.println("Las entradas que ha comprado este clientes es de: "+cantEntradas[pos2]);
                System.out.println("La pelicula comprada fue: "+peliculaClientes[pos2]+" en el horario de: "+horarioEntradas[pos2]);
                for (int j = 0; j < cantCompraClientes; j++) {
                    String linea=matrizEntradaCliente[pos2][j];
                    String[]partes=linea.split(",");
                    System.out.println("Asiento: "+partes[0]+partes[1]);            
                }         
            }
        }
        
         
    }

    public static int buscarPosCliente(String rutBuscado, String [] ruts, int cantClientes) {
        int pos=-1;
        while(pos<=cantClientes-1 && !rutBuscado.equals(ruts[pos])){
                    pos++;
                }
        if(pos == cantClientes){
            return -1;
         }
        else{
            return pos;
        }
        
        
    }

    
    
    
    
    
    
    
    
    
    
    public static void obtenerEntradasUsuario(String rutBuscado,String[]peliculaClientes,int cantCompraClientes,int pos,
            String[]peliculaCliente,String[]horarioEntradas, int[]salaComprada, String [][] matrizEntradaCliente, int [] cantEntradas,String[] rutsEntradas, int [] saldos) {
        Scanner sc = new Scanner(System.in);
        int posClienteCompras =0;
        for (int j = 0; j < cantCompraClientes; j++) {
            if(rutsEntradas[j].equalsIgnoreCase(rutBuscado)){
                posClienteCompras = j;
                System.out.println("La/s peliculas del cliente son: ");
                System.out.println(peliculaClientes[j]+" con "+ cantEntradas[j]+" entradas.");
                System.out.println(peliculaCliente[j]+" en el horario de: "+ horarioEntradas[j]+"en la sala n° "+salaComprada[j]);
                System.out.println("Posicion de las butacas para la pelicula: ");
                for (int i = 0; i <cantEntradas[pos]; i++) {
                    String linea = matrizEntradaCliente[j][i];
                    String [] partes = linea.split(",");
                    System.out.println("\t/ "+partes[0]+partes[1]+" /");
                }
                
            }
        }
        
        System.out.println("Ingrese la pelicula a reembolsar");
        String peliculaReembolso =sc.nextLine();
        
        for (int i = 0; i < 10; i++) {
            if(!rutsEntradas[i].equalsIgnoreCase(rutBuscado) && !peliculaCliente[i].equalsIgnoreCase(peliculaReembolso)){
                
            }
        }
        
        int posPelicula=-1;
        while(posPelicula<=cantCompraClientes-1 && !rutBuscado.equals(rutsEntradas[posPelicula])){
                    posPelicula++;
        }
        if(pos == cantCompraClientes){
            posPelicula=  -1;
        }
        
        opDevolucion(cantEntradas, posPelicula, posClienteCompras, saldos, matrizEntradaCliente);
        
    }

    public static void opDevolucion(int []cantEntradas,int posPelicula,int posClienteCompras, int[]saldos, String [][] matrizEntradaCliente,  int [][] sala1M,int [][] sala2M,int [][] sala3M,int [][] sala1T,
            int [][] sala2T,int [][] sala3T, String []  horarioEntradas, int [] salasCompradas, String [] letras) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Cantidad de entradas a reembolsar: ");
        int cant=sc.nextInt();
        
        System.out.println("Cantidad de entradas a reembolsar: ");
        while(cant > cantEntradas[posClienteCompras] && cant<1){
            System.out.println("Ingrese nuevamente la cantidad de entradas.");
            System.out.print("Cantidad de entradas a reembolsar: ");
            cant=sc.nextInt();
        }
        
        for (int i = 0; i <cant; i++) {
            System.out.print("Ingrese butaca para devolución: ");
            String entradasPos = sc.next();
            for(int j = 0; j<cantEntradas[posClienteCompras];j++){
                String linea =  matrizEntradaCliente[posClienteCompras][j];
                String [] partes = linea.split(",");
                String entrada = partes[0]+partes[1];
                String fil = partes[0];
                int col = Integer.parseInt(partes[1]);
                
                if( matrizEntradaCliente[posClienteCompras][j].equalsIgnoreCase(entrada)){
                    String horario = horarioEntradas[posClienteCompras];
                    int sala = salasCompradas[posClienteCompras];
                    
                        if(horario.equalsIgnoreCase("M"))
                        {
                            
                            
                            String opHorario = "Mañana";
                            switch (sala) 
                            {
                                case 1:  
                                    devolucionButaca( sala1M,  fil,  col, letras);
                                    break;
                                case 2:  
                                    devolucionButaca( sala2M,  fil,  col, letras);
                                    break;
                                case 3:  
                                    devolucionButaca( sala3M,  fil,  col, letras);
                                    break;

                                default: 
                                    System.out.println("Sala invalida");
                                    break;

                            }

                        }
                        else if (horario.equalsIgnoreCase("T"))
                        {                           

                            String opHorario ="Tarde";
                            switch (sala) 
                            {
                                case 1:  
                                   devolucionButaca( sala1T,  fil,  col, letras);
                                    break;
                                case 2:  
                                    devolucionButaca( sala2T,  fil,  col, letras);
                                    break;
                                case 3:  
                                    devolucionButaca( sala3T,  fil,  col, letras);
                                    break;

                                default: 
                                    System.out.println("Sala invalida");
                                    break;

                            }  

                        }
                }
            }
        }
        
    }
    
    
    public static void devolucionButaca(int [][] sala, String fil, int col,String [] letras){
        int posFil = 0;
        for (int i = 0; i < 10; i++) {
            if(letras[i].equalsIgnoreCase(fil)){
                posFil = i;
            }
        }
        
        sala[posFil][col-1] = 0;
        
    }
        
}
