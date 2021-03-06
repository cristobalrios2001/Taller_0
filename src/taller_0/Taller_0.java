package taller_0;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;

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
        lecturaStados (ruts,  status,  cantClientes);
        
        
                     // lectura peliculas.txt
        String [] tipoPelicula = new String[45];
        String [] nombrePeliculas = new String[45];
        
        int [] recaudacionMañana = new int [45];
        int [] recaudacionTarde = new int [45];
        int [] recaudacionTotal = new int [45];  
        
        
        boolean [][] funcionesMañana = new boolean [45][3];
        boolean [][] funcionesTarde = new boolean [45][3];
        
        int [] salas = {1,2,3};
        String [] letras = {"A","B","C","D","E","F","G","H","I","J"};
        
        int cantPeliculas = lecturaPeliculas (tipoPelicula, nombrePeliculas, recaudacionTotal, salas,  funcionesMañana,  funcionesTarde);
        
                     //datos entradas
        String [] rutsEntradas = new String [350];
        int [] cantEntradas = new int [350];
        String [] peliculaClientes = new String [350];
        String [] horarioEntradas = new String [350];
        int [] salaComprada = new int [350];
        String [][] matrizEntradaCliente = new String [350][10];
        
        int[] cantCompraClientesArray=new int[1];
        int cantCompraClientes = cantCompraClientesArray[0]; 
        
        int [][] sala1M = new int [10][31]; optimizarSalas( sala1M);
        int [][] sala2M = new int [10][31]; optimizarSalas( sala2M);
        int [][] sala3M = new int [10][31]; optimizarSalas( sala3M);
        
        int [][] sala1T = new int [10][31]; optimizarSalas( sala1T);
        int [][] sala2T = new int [10][31]; optimizarSalas( sala2T);
        int [][] sala3T = new int [10][31]; optimizarSalas( sala3T);
         
        
        
        
        
        sistema ( saldos, cantPeliculas,  tipoPelicula, nombres,  apellidos,  contraseñas,  cantClientes,
             nombrePeliculas,  funcionesMañana,  funcionesTarde, salas,  sala1M, sala2M, sala3M, sala1T,
             sala2T, sala3T, letras,status,  ruts,   cantCompraClientes, rutsEntradas,
             cantEntradas, peliculaClientes,   horarioEntradas,  salaComprada,  matrizEntradaCliente, peliculaClientes,
             salaComprada,  rutsEntradas,  recaudacionMañana,  recaudacionTarde,  recaudacionTotal,cantCompraClientesArray);
        
        
    }
    /** 
     * This procedure fills the rooms, optimizes them to know which seat is available for purchase, which seat is restricted by sanitary measures and the seats that are not available in the movie theater
     * @param sala1M this parameter is the input of a matrix which represents a movie theater to optimize its seats
     */
    public static void optimizarSalas(int [][] sala1M){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                sala1M[i][j] = -1;
            }
        }
    
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j <=30; j++) {
                if(j>25){
                    sala1M[i][j] = -1;
                }
               
            }
        }
        
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 31; j++) {
                if(j>5 && j<26){
                    if(j % 2 != 0){
                        sala1M[i][j] = -1;
                    }
                }
            }
        }
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 31; j++) {
                if(j>5 && j<26){
                    if(j % 2 != 0){
                        sala1M[i][j] = 1;
                    }
                }
            }
        } 
                   
        for (int i = 4; i < 10; i++) {
            for (int j = 0; j < 31; j++) {
                
                if((j+1) % 2 == 0){
                    sala1M[i][j] = 1;
                }
                
            }
        }
    }
    /**
    * This function reads the file "clientes.txt" and fills in the lists of the cinema's customer data (name, Rut, surname, password and balance) and returns the variable cantClientes
     * @param nombres enter a list of String and fill it with the names of each user
     * @param apellidos enter a String list and fill it with the surnames of each user
     * @param ruts enter a list of String and fill it with the Ruts of each user
     * @param contraseñas enter a list of String and fill it with each password of each user
     * @param saldos enter a list of int and populate it with the balances of each user
     * @return returns the number of customers registered in the system
     * @throws IOException This exception is necessary to be able to read the "clientes.txt" file
     */
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
    
    /**
     * This procedure reads the file "status.txt" in order to fill out a list and know if the customer has his mobility pass or does not have it in his possession, in this procedure the "buscarPosCliente" function is called to know his location in a list
     * @param ruts enter a list of String and fill it with the Ruts of each user
     * @param status Enter this list of String and it is filled by this procedure with the data of the mobility pass of each client
     * @param cantClientes enter this int to be able to know the number of clients that exist in the system
     * @throws IOException This exception is necessary to be able to read the "status.txt" file
     */
    public static void lecturaStados (String [] ruts, String [] status, int cantClientes) throws IOException
    {
        Scanner sc = new Scanner(new File("status.txt"));        
        while(sc.hasNextLine())
        {
            String linea = sc.next();
            String [] partes = linea.split(",");
            String rutBuscar = partes[0];
            
            int posUsuario = buscarPosCliente( rutBuscar,  ruts, cantClientes);
            
            if(posUsuario != -1)
            {
                String statu = partes[1];
                status[posUsuario] = statu;
                
            }
           
        }
        
    }
    
    
    /**
     * This function reads the file "peliculas.txt", fills in the lists of (tipoPeliculas, nombrePeliculas, recaudacionTotal, funcionesMañana, functionsTarde) and returns the number of movies on the billboard, this function calls the process "ingresarFuncion"
     * @param tipoPeliculas list of type String that stores the type of movie that is in theaters (released / premiere)
     * @param nombrePeliculas String type list that stores the name of each movie on the billboard
     * @param recaudacionTotal lista de tipo int que alamcena la recaudacion de cada pelicula hasta el momento
     * @param salas list of numbered rooms (1,2,3)
     * @param funcionesMañana enter a boolean matrix to fill in the functions of each movie in the "morning" schedule
     * @param funcionesTarde enter a boolean matrix to fill in the functions of each movie in the "afternoon" schedule
     * @return retorna an int with which represents the number of movies on the billboard
     * @throws IOException This exception is necessary to be able to read the "peliculas.txt" file
     */
    public static int lecturaPeliculas (String [] tipoPeliculas,String [] nombrePeliculas,int [] recaudacionTotal,int [] salas, boolean [][] funcionesMañana, boolean [][] funcionesTarde) throws IOException 
    {
        Scanner sc = new Scanner(new File("peliculas.txt"));
        
        int cantPeliculas = 0;
        while(sc.hasNextLine()){
            String linea = sc.nextLine();
            String [] partes = linea.split(",");
            
            String nombrePelicula = partes[0];
            nombrePeliculas[cantPeliculas] = nombrePelicula;
            
            String tipoPelicula = partes[1];
            tipoPeliculas[cantPeliculas] = tipoPelicula;
            
            int recaudacionPelicula = Integer.parseInt(partes[2]);
            recaudacionTotal [cantPeliculas] = recaudacionPelicula;
            
            for (int i = 3; i < partes.length; i+=2) {
                int nSala = Integer.parseInt(partes[i]);
                String horario = partes[i+1];
                
                ingresarFuncion(horario,nSala,salas,funcionesMañana,funcionesTarde,cantPeliculas);
                
            }
            cantPeliculas++;
            
        }
        return cantPeliculas;
        
    }
    /**
     * This procedure is called from the "lecturaPeliculas" function to fill the billboard with their respective functions on the billboard.
     * @param horario enter a String that can have an "M" value or a "T" value to know the time of the respective movie.
     * @param sala enter an int with the number of the room in which the function of the movie will be given
     * @param salas list of numbered rooms (1,2,3)
     * @param funcionesMañana enter a boolean matrix to fill in the functions of each movie in the "morning" schedule
     * @param funcionesTarde enter a boolean matrix to fill in the functions of each movie in the "afternoon" schedule
     * @param cantPeliculas enter the number of movies on the billboard
     */
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
    /**
     * This procedure is used to start the cinema system. In this procedure enter the functions of: "posCliente", "verificarRut", "menuCliente" and "menuAdmin", so that the system works correctly
     * @param saldo enter this list of type int to later be called from the function "menuCliente" and "menuAdmin"
     * @param cantPeliculas enter the number of movies on the billboard
     * @param tipoPelicula enter a list of type String containing the type (premiere / released) of each movie
     * @param nombres enter a list of String and fill it with the names of each user
     * @param apellidos enter a String list and fill it with the surnames of each use
     * @param contraseñas enter a list of String and fill it with each password of each user
     * @param cantClientes enter this int to be able to know the number of clients that exist in the system
     * @param nombrePelicula enter a list of type String containing the name of each movie
     * @param funcionMañana enter an array of type boolean with the functions in the morning time
     * @param funcionTarde enter a boolean array with the functions in the afternoon schedule
     * @param salas list of numbered rooms (1,2,3)
     * @param sala1M ingresa una matriz  con la sala 1 en el horario de mañana con los asientos de la sala
     * @param sala2M ingresa una matriz  con la sala 2 en el horario de mañana con los asientos de la sala
     * @param sala3M ingresa una matriz  con la sala 3 en el horario de mañana con los asientos de la sala
     * @param sala1T ingresa una matriz  con la sala 1 en el horario de tarde con los asientos de la sala
     * @param sala2T ingresa una matriz  con la sala 2 en el horario de tarde con los asientos de la sala
     * @param sala3T ingresa una matriz  con la sala 3 en el horario de tarde con los asientos de la sala
     * @param letras enter a list of type String with the letters of the seats
     * @param status Enter this list of String and it is filled by this procedure with the data of the mobility pass of each client
     * @param ruts enter a list of String and fill it with the Ruts of each user
     * @param cantCompraClientes enter a variable of type int with the amount of purchases that have been made
     * @param rutsEntradas enter a list of type String with the ruts that have bought at least one entry
     * @param cantEntradas enter a list of type int with the amount of tickets they have bought
     * @param peliculaClientes enter a list of type String with the movies that customers have bought
     * @param horarioEntradas enter a list of type String with the schedules that customers have bought
     * @param salasCompradas enter a list of type int with the room that each client has bought
     * @param matrizEntradaCliente enter an array of type String with the seats that each customer bought
     * @param peliculasClientes enter a list of type String containing the movies purchased by customers
     * @param salaComprada enter a list of type int with the room that the customer has bought
     * @param rutEntradas enter a list of type String with the ruts that have bought at least one entry 
     * @param recaudacionMañana enter a list type int that contains the collection for the time of "tomorrow"
     * @param recaudacionTarde enter a list type int that contains the collection for the "afternoon" schedule
     * @param recaudacionTotal list of type int that stores the collection of each movie so far
     * @param cantCompraClientesArray enter a list of type int containing the purchase quantity of the customers
     */
    public static void sistema ( int []saldo,int cantPeliculas, String [] tipoPelicula,String [] nombres, String [] apellidos, String [] contraseñas, int cantClientes,
            String [] nombrePelicula, boolean [][] funcionMañana, boolean [][] funcionTarde, int [] salas, int [][] sala1M,int [][] sala2M,int [][] sala3M,int [][] sala1T,
            int [][] sala2T,int [][] sala3T,String [] letras, String [] status, String [] ruts, int cantCompraClientes,String [] rutsEntradas,
            int [] cantEntradas,String [] peliculaClientes, String []  horarioEntradas, int [] salasCompradas, String [][] matrizEntradaCliente,String [] peliculasClientes,
            int [] salaComprada, String [] rutEntradas, int [] recaudacionMañana, int [] recaudacionTarde, int [] recaudacionTotal,int[] cantCompraClientesArray){
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; 
        do{
            System.out.println("1. Iniciar Sesión");           
            System.out.println("2. Salir - Cerrar Sistema");
            try {
                System.out.print("Escribe una de las opciones: ");
                opcion = sn.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opción 1: Iniciar Sesión");
                        int posCliente = verificarRut( ruts,  contraseñas, cantClientes);
                       
                        if(posCliente!= -1 && posCliente != 2 ){
                            menuCliente (posCliente,nombres,apellidos, saldo, cantPeliculas,  tipoPelicula, 
                            nombrePelicula,  funcionMañana,  funcionTarde, salas,  sala1M, sala2M, sala3M, sala1T,
                            sala2T, sala3T, letras,  saldo,  status,  ruts, nombrePelicula,  cantCompraClientes, rutsEntradas,
                            cantEntradas, peliculaClientes,   horarioEntradas,  salasCompradas,  matrizEntradaCliente, peliculasClientes,
                             salaComprada,  rutEntradas, cantClientes, cantCompraClientesArray);
                        }
                        else if (posCliente == 2){
                            menuAdmin( cantPeliculas, nombrePelicula, recaudacionMañana, recaudacionTarde, recaudacionTotal,  ruts,  cantClientes, nombres, apellidos, saldo, cantEntradas, 
                                     peliculaClientes,  horarioEntradas, rutsEntradas,matrizEntradaCliente,  cantCompraClientes);
                        }else{
                            System.out.println("\nUsuario no Encontrado");
                        }
                        
                        break;
                    
                    case 2:
                        System.out.println("Has seleccionado la opción 2: Salir - Cerrar Sistema");
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 2");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
        while (!salir) ;
    }
    /**
     * This procedure is for the purchase of tickets for each client, the information of each user, the return of tickets and for the billboard sample.
     * @param posCliente enter the position of the customer to be searched in the list of ruts
     * @param nombres enter a list of String and fill it with the names of each user
     * @param apellidos enter a String list and fill it with the surnames of each use
     * @param saldos enter a list of int and populate it with the balances of each user
     * @param cantPeliculas enter the number of movies on the billboard
     * @param tipoPelicula enter a list of type String containing the type (premiere / released) of each movie
     * @param nombrePelicula enter a list of type String containing the name of each movie
     * @param funcionMañana enter an array of type boolean with the functions in the morning time
     * @param funcionTarde enter a boolean array with the functions in the afternoon schedule
     * @param salas list of numbered rooms (1,2,3)
     * @param sala1M enter a matrix with room 1 in the morning schedule with room seats
     * @param sala2M enter a matrix with room 2 in the morning schedule with room seats
     * @param sala3M enter a matrix with room 3 in the morning schedule with room seats
     * @param sala1T enter a matrix with room 1 in the afternoon with the room seats
     * @param sala2T enter a matrix with room 2 in the afternoon with the room seats
     * @param sala3T enter a matrix with room 3 in the afternoon with the room seats
     * @param letras enter a list of type String with the letters of the seats
     * @param saldo enter a list of type int with the balances of each customer
     * @param status Enter this list of String and it is filled by this procedure with the data of the mobility pass of each client
     * @param ruts enter a list of String and fill it with the Ruts of each user     
     * @param nombrePeliculas enter a list of type String with the name of each movie
     * @param cantCompraClientes enter a variable of type int with the amount of purchases that have been made
     * @param rutsEntradas enter a list of type String with the ruts that have bought at least one entry
     * @param cantEntradas enter a list of type int with the amount of tickets they have bought
     * @param peliculaClientes enter a list of type String with the movies that customers have bought
     * @param horarioEntradas enter a list of type String with the schedules that customers have bought
     * @param salasCompradas enter a list of type int with the room in which each client bought
     * @param matrizEntradaCliente enter an array of type String with the seats that each customer bought
     * @param peliculasClientes enter a list of type String containing the movies purchased by customers
     * @param salaComprada enter a list of type int with the room that the customer has bought
     * @param rutEntradas enter a list of type String with the ruts that have bought at least one entry
     * @param cantClientes enter this int to be able to know the number of clients that exist in the system
     * @param cantCompraClientesArray  enter a list of type int containing the purchase quantity of the customers
     */
    public static void menuCliente(int posCliente, String [] nombres, String[] apellidos,int []saldos,int cantPeliculas, String [] tipoPelicula, 
            String [] nombrePelicula, boolean [][] funcionMañana, boolean [][] funcionTarde, int [] salas, int [][] sala1M,int [][] sala2M,int [][] sala3M,int [][] sala1T,
            int [][] sala2T,int [][] sala3T,String [] letras, int [] saldo, String [] status, String [] ruts,String [] nombrePeliculas, int cantCompraClientes,String [] rutsEntradas,
            int [] cantEntradas,String [] peliculaClientes, String []  horarioEntradas, int [] salasCompradas, String [][] matrizEntradaCliente,String [] peliculasClientes,
            int [] salaComprada, String [] rutEntradas, int cantClientes,int[] cantCompraClientesArray){
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; 
        do{
            System.out.println("1. Comprar Entrada.");      
            System.out.println("2. Información Usuario."); 
            System.out.println("3. Devolución Entrada."); 
            System.out.println("4. Cartelera."); 
            System.out.println("5. Salir");
            try {
                System.out.print("Escribe una de las opciones: ");
                opcion = sn.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("\nHas seleccionado la opción 1: Comprar Entrada.");
                        horarioDisponibleDePelicula ( posCliente, saldos, cantPeliculas,   tipoPelicula, 
             nombrePelicula,  funcionMañana,  funcionTarde,  salas,  sala1M, sala2M, sala3M, sala1T,
            sala2T, sala3T, letras,  saldo,  status, ruts, nombrePeliculas,  cantCompraClientes, rutsEntradas,
            cantEntradas, peliculaClientes,   horarioEntradas,  salasCompradas,  matrizEntradaCliente,  cantCompraClientesArray );
                        
                        break;
                    case 2:
                        System.out.println("\nHas seleccionado la opción 2: Información Usuario.");
                        obtenerInformaciónUsuario ( posCliente,  ruts, nombres,  apellidos, saldos,
                        rutEntradas,  cantEntradas,  peliculasClientes,  horarioEntradas,  salaComprada, matrizEntradaCliente,
                         cantCompraClientesArray);
                        
                        break;
                    case 3:
                        System.out.println("\nHas seleccionado la opción 3: Devolución Entrada.");
                        String rut=ruts[posCliente];
                        devolucionEntrada( rut,  rutsEntradas, cantEntradas,  peliculaClientes, horarioEntradas, salaComprada,  matrizEntradaCliente,  cantCompraClientes, ruts, cantClientes, saldos,
                                         peliculasClientes,  letras,  sala1M,  sala2M,  sala3M, sala1T,sala2T, sala3T, salasCompradas, cantCompraClientesArray);
                        break;
                    case 4:
                        System.out.println("\nHas seleccionado la opción 4: Cartelera.");
                        cartelera (cantPeliculas,  funcionMañana,  funcionTarde,  salas, nombrePeliculas);
                        break;
                    case 5:
                        System.out.println("\nHas seleccionado la opción 5: Salir.");
                        salir = true;
                        break;
                    default:
                        System.out.println("\nSolo números entre 1 y 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
        while (!salir) ;
    }
        /**
     * procedure that is called from "menuCliente" administrator option that displays "taquilla" and "informacionCliente" options     
     * @param cantPeliculas enter the number of movies on the billboard
     * @param nombrePeliculas String type list that stores the name of each movie on the billboard
     * @param recaudacionMañana enter a list type int that contains the collection for the time of "tomorrow"
     * @param recaudacionTarde enter a list type int that contains the collection for the "afternoon" schedule
     * @param recaudacionTotal list of type int that stores the collection of each movie so far
     * @param ruts enter a list of String and fill it with the Ruts of each user
     * @param cantClientes enter this int to be able to know the number of clients that exist in the system
     * @param nombres enter a list of String and fill it with the names of each user
     * @param apellidos enter a String list and fill it with the surnames of each use
     * @param saldos enter a list of int and populate it with the balances of each user
     * @param cantEntradas enter a list of type int with the amount of tickets they have bought
     * @param peliculaClientes enter a list of type String with the movies that customers have bought
     * @param horarioEntradas enter a list of type int with the room that each client has bought
     * @param rutsEntradas enter a list of type String with the ruts that have bought at least one entry
     * @param matrizEntradaCliente enter an array of type String with the seats that each customer bought
     * @param cantCompraClientes enter a variable of type int with the amount of purchases that have been made
     */
        public static void menuAdmin(int cantPeliculas, String[]nombrePeliculas,int [] recaudacionMañana,int [] recaudacionTarde,int [] recaudacionTotal, 
            String [] ruts, int cantClientes, String[] nombres, String[] apellidos, int[] saldos, int []cantEntradas, String[] peliculaClientes,
            String[] horarioEntradas,String[] rutsEntradas,String[][]matrizEntradaCliente, int cantCompraClientes){
        
        
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; 
        do{
            System.out.println("1. Taquilla.");      
            System.out.println("2. Información Cliente.");              
            System.out.println("3. Salir");
            try {
                System.out.print("Escribe una de las opciones: ");
                opcion = sn.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opción 1: Taquilla.");
                        obtenerRecaudacion(cantPeliculas,nombrePeliculas,recaudacionMañana,recaudacionTarde,recaudacionTotal);
                        
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opción 2: Información Cliente.");
                        obtenerInformacionUsuarioAdmin( ruts,  cantClientes,nombres,  apellidos,saldos, cantEntradas,peliculaClientes,  horarioEntradas,  rutsEntradas,matrizEntradaCliente,  cantCompraClientes);
                        
                        break;
                    
                    case 3:
                        System.out.println("Has seleccionado la opción 3: Salir.");
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
        while (!salir) ;
        
        
        
        
        
    }
    /**
     * Function that verifies if the entered routine is correct with your password, and in case of choosing the administrator menu
     * @param ruts enter a list of String and fill it with the Ruts of each user
     * @param contraseñas enter a list of String and fill it with each password of each user
     * @param cantClientes enter this int to be able to know the number of clients that exist in the system
     * @return reotrna la variable "dev" en caso de ser distinto de -1 y 2 en rut es correcto, en caso de tener valor 2 es administrador y en caso de ser -1 el rut no es valido
     */
    public static int verificarRut(String [] ruts, String [] contraseñas, int cantClientes){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nIngrese Rut: ");
        String rutVerificar = sc.next();
        
        System.out.print("\nIngrese Contraseña: ");
        String contraseñaVerificar = sc.next();
        
        
        int dev = -1;
        if(rutVerificar.equals("ADMIN") && rutVerificar.equals("ADMIN")){
            dev =2; // es admin
        }
        else{
            for (int i = 0; i < cantClientes; i++) {
                if(ruts[i].equalsIgnoreCase(rutVerificar) && contraseñas[i].equals(contraseñaVerificar)){
                    dev = i; // obtengo la pos del cliente si es que lo encontro
                }
                if (i>= cantClientes){
                    dev= -1;
                }
            }
        }
        return dev;
    }
    /**
     * procedure for the option of returning tickets purchased by a customer
     * @param rut input of variable of type String which represents the client's routine that wants to return the inputs
     * @param rutsEntradas enter a list of type String with the ruts that have bought at least one entry
     * @param cantEntradas enter a list of type int with the amount of tickets they have bought
     * @param peliculaClientes enter a list of type String with the movies that customers have bought
     * @param horarioEntradas enter a list of type String with the schedules that customers have bought
     * @param salaComprada enter a list of type int with the room that the customer has bought
     * @param matrizEntradaCliente enter an array of type String with the seats that each customer bought
     * @param cantCompraClientes enter a variable of type int with the amount of purchases that have been made
     * @param ruts enter a list of String and fill it with the Ruts of each user
     * @param cantClientes enter this int to be able to know the number of clients that exist in the system
     * @param saldos enter a list of int and populate it with the balances of each user
     * @param peliculaCliente ingreso de lista de tipo String con la pelicula compradada de cada cliente
     * @param letras enter a list of type String with the letters of the seats
     * @param sala1M enter a matrix with room 1 in the morning schedule with room seats
     * @param sala2M enter a matrix with room 2 in the morning schedule with room seats
     * @param sala3M enter a matrix with room 3 in the morning schedule with room seats     
     * @param sala1T enter a matrix with room 1 in the afternoon with the room seats
     * @param sala2T enter a matrix with room 2 in the afternoon with the room seats
     * @param sala3T enter a matrix with room 3 in the afternoon with the room seats
     * @param salasCompradas enter a list of type int with the room in which each client bought
     * @param cantCompraClientesArray enter a list of type int containing the purchase quantity of the customers
     */
    public static void devolucionEntrada(String rut, String [] rutsEntradas, int [] cantEntradas, String [] peliculaClientes,String [] horarioEntradas, int [] salaComprada, String [][] matrizEntradaCliente, int cantCompraClientes, String[]ruts,int cantClientes,int[]saldos,
                                        String [] peliculaCliente, String [] letras, int [][] sala1M, int [][] sala2M, int [][] sala3M, int [][] sala1T,
                                        int [][] sala2T,int [][] sala3T,int [] salasCompradas, int [] cantCompraClientesArray){
        
        
        String rutBuscado = rut;
        
        int pos = buscarPosCliente( rutBuscado, ruts,  cantClientes);
        
        obtenerEntradasUsuario( rutBuscado,peliculaClientes, cantCompraClientes, pos,
            peliculaCliente,horarioEntradas,salaComprada,  matrizEntradaCliente,  cantEntradas, rutsEntradas,
             saldos,  letras, sala1M, sala2M, sala3M, sala1T,  sala2T, sala3T, salasCompradas, cantCompraClientesArray);
        
    }
    

    /**
     * procedure that prints the different types of collections
     * @param cantPeliculas enter the number of movies on the billboard
     * @param nombrePeliculas String type list that stores the name of each movie on the billboard
     * @param recaudacionMañana enter a list type int that contains the collection for the time of "tomorrow"
     * @param recaudacionTarde enter a list type int that contains the collection for the "afternoon" schedule
     * @param recaudacionTotal  list of type int that stores the collection of each movie so far
     */
    public static void obtenerRecaudacion(int cantPeliculas, String[]nombrePeliculas,int [] recaudacionMañana,int [] recaudacionTarde,int [] recaudacionTotal) {
        for(int i=0;i<cantPeliculas;i++){
            int total=0;
            total=recaudacionMañana[i]+recaudacionTarde[i];            
            System.out.println("La recaudacion total por la pelicula: ´"+nombrePeliculas[i]+"´ es de: "+recaudacionTotal[i]+" pesos");
            System.out.println("\tEl monto recaudado a lo largo del dia es de: "+total+" pesos");
            System.out.println("\tEl monto recaudado en el horario de mañana es de: "+recaudacionMañana[i]+" pesos");
            System.out.println("\tEl monto recaudado en el horario de tarde es de: "+recaudacionTarde[i]+" pesos");
        }
    }
    /**
     * procedure used to obtain the information of each user and print it
     * @param ruts enter a list of String and fill it with the Ruts of each user
     * @param cantClientes enter this int to be able to know the number of clients that exist in the system
     * @param nombres enter a list of String and fill it with the names of each user
     * @param apellidos enter a String list and fill it with the surnames of each use
     * @param saldos enter a list of int and populate it with the balances of each user
     * @param cantEntradas enter a list of type int with the amount of tickets they have bought
     * @param peliculaClientes enter a list of type String with the movies that customers have bought
     * @param horarioEntradas enter a list of type String with the schedules that customers have bought
     * @param rutsEntradas enter a list of type String with the ruts that have bought at least one entry
     * @param matrizEntradaCliente enter an array of type String with the seats that each customer bought
     * @param cantCompraClientes enter a variable of type int with the amount of purchases that have been made
     */
    public static void obtenerInformacionUsuarioAdmin(String [] ruts, int cantClientes, String[] nombres, String[] apellidos, int[] saldos, 
            int []cantEntradas, String[] peliculaClientes, String[] horarioEntradas, String[] rutsEntradas,
            String[][]matrizEntradaCliente, int cantCompraClientes) {
        
        int rut=0;
        Scanner sc=new Scanner(System.in);
        System.out.print("Ingrese el rut a buscar: ");
        String rutBuscado=sc.next();
        int pos=buscarPosCliente(rutBuscado,ruts,cantClientes);
        
        while(pos==-1){
            System.out.println("Ingrese un rut correcto ");
            rutBuscado=sc.nextLine();
            pos=buscarPosCliente(rutBuscado,ruts,cantClientes);
        }
        
        int pos2=buscarPosCliente(rutBuscado,rutsEntradas,cantCompraClientes);
        
        System.out.println("Pos2: "+pos2);
        
        System.out.println("\n"+nombres[pos]+" "+apellidos[pos]+" con un saldo de: "+saldos[pos]+" pesos\n");
        
        if(pos2!=-1)
        {
            for (int i = 0; i < cantCompraClientes; i++) {
            if(rutsEntradas[i].equalsIgnoreCase(rutBuscado)){
                //System.out.println(nombres[pos]+" "+apellidos[pos]+" con un saldo de: "+saldos[pos]+" pesos");
                System.out.println("Las entradas que ha comprado este clientes es de: "+cantEntradas[pos2]);
                System.out.println("La pelicula comprada fue: "+peliculaClientes[pos2]+" en el horario de: "+horarioEntradas[pos2]);
                for (int j = 0; j < cantCompraClientes; j++) {
                    String linea=matrizEntradaCliente[pos2][j];
                    String[]partes=linea.split(",");
                    System.out.println("Asiento: "+partes[0]+partes[1]);            
                }         
            }
        }
            
        }else{
            System.out.println("\tUsuario no ha realizado compras");
        }
        
        
         
    }
    /**
     * function that looks for a client in a list, in this case the list of ruts
     * @param rutBuscado enter variable of type String which is the Rut to search
     * @param ruts enter a list of String and fill it with the Ruts of each user
     * @param cantClientes enter this int to be able to know the number of clients that exist in the system
     * @return retorna the position of the rut in case of being found, in case of not being found returns -1
     */
    public static int buscarPosCliente(String rutBuscado, String [] ruts, int cantClientes) {
        int pos=-1;
        
        
        for (int i = 0; i < cantClientes; i++) {
            if(ruts[i].equalsIgnoreCase(rutBuscado)){
                pos = i;
            }
        }
        return pos;
    }
    
    /**
     * procedure that prints the movie (s) purchased by the customer, the number of tickets, the time of entry, the room purchased, the seats purchased and reimburses the tickets if necessary
     * @param rutBuscado enter variable of type String which is the Rut to search
     * @param peliculaClientes enter a list of type String with the movies that customers have bought
     * @param cantCompraClientes enter a variable of type int with the amount of purchases that have been made
     * @param pos variable of type int that contains the position of the rut that has been searched
     * @param peliculaCliente Entering a list of type String with the purchased movie of each client
     * @param horarioEntradas enter a list of type String with the schedules that customers have bought
     * @param salaComprada enter a list of type int with the room that the customer has bought
     * @param matrizEntradaCliente enter an array of type String with the seats that each customer bought
     * @param cantEntradas enter a list of type int with the amount of tickets they have bought
     * @param rutsEntradas enter a list of type String with the ruts that have bought at least one entry
     * @param saldos enter a list of int and populate it with the balances of each user
     * @param letras enter a list of type String with the letters of the seats     * @param sala1M enter a matrix with room 1 in the morning schedule with room seats
     * @param sala2M enter a matrix with room 2 in the morning schedule with room seats
     * @param sala3M enter a matrix with room 3 in the morning schedule with room seats
     * @param sala1T enter a matrix with room 1 in the afternoon with the room seats
     * @param sala2T enter a matrix with room 2 in the afternoon with the room seats
     * @param sala3T enter a matrix with room 3 in the afternoon with the room seats
     * @param salasCompradas enter a list of type int with the room in which each client bought
     * @param cantCompraClientesArray enter a list of type int containing the purchase quantity of the customers
     */
    public static void obtenerEntradasUsuario(String rutBuscado,String[]peliculaClientes,int cantCompraClientes,int pos,
            String[]peliculaCliente,String[]horarioEntradas, int[]salaComprada, String [][] matrizEntradaCliente, int [] cantEntradas,String[] rutsEntradas,
            int [] saldos, String [] letras,int [][] sala1M,int [][] sala2M,int [][] sala3M,int [][] sala1T, int [][] sala2T,int [][] sala3T,int [] salasCompradas, int []cantCompraClientesArray) {
        Scanner sc = new Scanner(System.in);
        int posClienteCompras =0;
        for (int j = 0; j < cantCompraClientesArray[0]; j++) {
            if(rutsEntradas[j].equalsIgnoreCase(rutBuscado)){
                posClienteCompras = j;
                System.out.println("La/s peliculas del cliente son: ");
                System.out.println("\t"+peliculaClientes[j]+" con "+ cantEntradas[j]+" entradas.");
                System.out.println("\t"+peliculaCliente[j]+" en el horario de: "+ horarioEntradas[j]+" en la sala n° "+salaComprada[j]);
                System.out.println("\"\\t\"+Posicion de las butacas para la pelicula: ");
                for (int i = 0; i <cantEntradas[pos]; i++) {
                    String linea = matrizEntradaCliente[j][i];
                    String [] partes = linea.split(",");
                    System.out.println("\t\t/ "+partes[0]+partes[1]+" /");
                }
                
            }
        }
        
        System.out.println("Ingrese la pelicula a reembolsar");
        String peliculaReembolso =sc.nextLine();
        
        int posPelicula  = 0;
        for (int i = 0; i < cantCompraClientesArray[0]; i++) {
            if(rutsEntradas[i].equalsIgnoreCase(rutBuscado) && peliculaCliente[i].equalsIgnoreCase(peliculaReembolso) ){
                posPelicula = i;
            }
        }
        
        opDevolucion(cantEntradas, posPelicula, posClienteCompras, saldos, matrizEntradaCliente,  sala1M, sala2M,sala3M, sala1T,
             sala2T, sala3T,  horarioEntradas, salasCompradas,  letras, cantCompraClientesArray, rutsEntradas, peliculaCliente);
        
        
        
    }
    /**
     * procedure that fulfills the function of making the return of tickets and refund of money
     * @param cantEntradas enter a list of type int with the amount of tickets they have bought
     * @param posPelicula position of the purchased movie in a list
     * @param posClienteCompras posicion del cliente que ha comprado la entrada en una lista 
     * @param saldos enter a list of int and populate it with the balances of each user
     * @param matrizEntradaCliente enter an array of type String with the seats that each customer bought
     * @param sala1M enter a matrix with room 1 in the morning schedule with room seats
     * @param sala2M enter a matrix with room 2 in the morning schedule with room seats
     * @param sala3M enter a matrix with room 3 in the morning schedule with room seats
     * @param sala1T enter a matrix with room 1 in the afternoon with the room seats
     * @param sala2T enter a matrix with room 2 in the afternoon with the room seats
     * @param sala3T enter a matrix with room 3 in the afternoon with the room seats
     * @param horarioEntradas enter a list of type String with the schedules that customers have bought
     * @param salasCompradas enter a list of type int with the room in which each client bought
     * @param letras enter a list of type String with the letters of the seats
     * @param cantCompraClientesArray enter a list of type int containing the purchase quantity of the customers
     * @param rutsEntradas enter a list of type String with the ruts that have bought at least one entry
     * @param peliculasClientes enter a list of type String containing the movies purchased by customers
     */
    public static void opDevolucion(int []cantEntradas,int posPelicula,int posClienteCompras, int[]saldos, String [][] matrizEntradaCliente,  int [][] sala1M,int [][] sala2M,int [][] sala3M,int [][] sala1T,
            int [][] sala2T,int [][] sala3T, String []  horarioEntradas, int [] salasCompradas, String [] letras,int []cantCompraClientesArray,String [] rutsEntradas, String [] peliculasClientes) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Cantidad de entradas a reembolsar: ");
        int cant=sc.nextInt();
        
        
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
                
                if(entrada.equalsIgnoreCase(entradasPos)){
                    
                    if( matrizEntradaCliente[posClienteCompras][j].equalsIgnoreCase(entrada)){
                        String horario = horarioEntradas[posClienteCompras];
                        int sala = salasCompradas[posClienteCompras];

                            if(horario.equalsIgnoreCase("M"))
                            {

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
        
        
        
       
        for(int k = posPelicula ; k <cantCompraClientesArray[0]-1; k++) {
            rutsEntradas[k] = rutsEntradas[k + 1];
            cantEntradas[k] = cantEntradas[k + 1];
            peliculasClientes[k] = peliculasClientes[k + 1];
            horarioEntradas[k] = horarioEntradas[k + 1];
            salasCompradas[k] = salasCompradas[k + 1];
            for (int i = 0; i < cantEntradas[k]; i++) {
                matrizEntradaCliente[k] = matrizEntradaCliente[k + 1];
            }
        }
       cantCompraClientesArray[0]--; 
        
        
        
        
        
    }
    
    /**
     * procedure that performs the vacating of a seat after the refund of a ticket
     * @param sala input array of type int containing the room in which the input was purchased
     * @param fil input of variable of type String that contains the letters of the seat
     * @param col input of a variable of type int with the seat number
     * @param letras enter a list of type String with the letters of the seats
     */
    public static void devolucionButaca(int [][] sala, String fil, int col,String [] letras){
        int posFil = 0;
        for (int i = 0; i < 10; i++) {
            if(letras[i].equalsIgnoreCase(fil)){
                posFil = i;
            }
        }
        
        sala[posFil][col-1] = 0;
        
    }
    /**
     * procedure where you enter the movie to search and print the billboard where the available times appear
     * @param posCliente enter the position of the customer to be searched in the list of ruts
     * @param saldos enter a list of int and populate it with the balances of each user
     * @param cantPeliculas enter the number of movies on the billboard
     * @param tipoPelicula enter a list of type String containing the type (premiere / released) of each movie
     * @param nombrePelicula enter a list of type String containing the name of each movie
     * @param funcionMañana enter an array of type boolean with the functions in the morning time
     * @param funcionTarde enter a boolean array with the functions in the afternoon schedule
     * @param salas list of numbered rooms (1,2,3)
     * @param sala1M enter a matrix with room 1 in the morning schedule with room seats
     * @param sala2M enter a matrix with room 2 in the morning schedule with room seats
     * @param sala3M enter a matrix with room 3 in the morning schedule with room seats
     * @param sala1T enter a matrix with room 1 in the afternoon with the room seats
     * @param sala2T enter a matrix with room 2 in the afternoon with the room seats
     * @param sala3T enter a matrix with room 3 in the afternoon with the room seats
     * @param letras enter a list of type String with the letters of the seats
     * @param saldo enter a list of type int with the balances of each customer
     * @param status Enter this list of String and it is filled by this procedure with the data of the mobility pass of each client
     * @param ruts enter a list of String and fill it with the Ruts of each user
     * @param nombrePeliculas String type list that stores the name of each movie on the billboard
     * @param cantCompraClientes enter a variable of type int with the amount of purchases that have been made
     * @param rutsEntradas enter a list of type String with the ruts that have bought at least one entry
     * @param cantEntradas enter a list of type int with the amount of tickets they have bought
     * @param peliculaClientes enter a list of type String with the movies that customers have bought
     * @param horarioEntradas enter a list of type String with the schedules that customers have bought
     * @param salasCompradas enter a list of type int with the room in which each client bought
     * @param matrizEntradaCliente enter an array of type String with the seats that each customer bought
     * @param cantCompraClientesArray enter a list of type int containing the purchase quantity of the customers
     */
    public static void horarioDisponibleDePelicula (int posCliente, int []saldos,int cantPeliculas, String [] tipoPelicula, 
            String [] nombrePelicula, boolean [][] funcionMañana, boolean [][] funcionTarde, int [] salas, int [][] sala1M,int [][] sala2M,int [][] sala3M,int [][] sala1T,
            int [][] sala2T,int [][] sala3T,String [] letras, int [] saldo, String [] status, String [] ruts,String [] nombrePeliculas, int cantCompraClientes,String [] rutsEntradas,
            int [] cantEntradas,String [] peliculaClientes, String []  horarioEntradas, int [] salasCompradas, String [][] matrizEntradaCliente ,int[] cantCompraClientesArray)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese nombre de pelicula a buscar: ");
        String peliculaSolicitud = sc.next();
        
        
        boolean horarioT = false;
        boolean horarioM = false;
        int posPelicula = 0;
        for (int i = 0; i < cantPeliculas; i++) 
        {
            if(peliculaSolicitud.equalsIgnoreCase(nombrePelicula[i]))
            {
                posPelicula = i;
                
                
                    for (int k = 0; k < 3; k++) 
                    {
                        if(funcionMañana[i][k] == true)
                        {
                            horarioM = true;
                            System.out.println("Funcion disponible en las siguiente(s) sala(s) en el horario de la mañana: ");
                            System.out.println("Sala "+salas[k]);
                        }
                        else if(funcionTarde[i][k] == true)
                        {
                            horarioT = true;
                            System.out.println("Funcion disponible en las siguiente(s) sala(s) en el horario de la tarde: ");
                            System.out.println("Sala "+salas[k]);
                        }
                    }
                
            }
            else if(i == cantPeliculas)
            {
                System.out.println("Pelicula no encontrada");
            }
        }
        
        
        
        
        
        System.out.print("Ingrese horario de la función: ");
        String horarioSeleccion = sc.next();
        
        
        
        if(horarioSeleccion.equalsIgnoreCase("Mañana") && horarioM == true)
        {
            System.out.print("Ingrese sala, funcion Mañana: ");
            int sala = sc.nextInt();
            String opHorario = "Mañana";
            switch (sala) 
            {
                case 1:  
                    // int [][] sala1M;;
                    //obtenerSalasAsientos ( sala1M,letras);
                    obtenerSalasAsientos ( sala1M,  letras);
                    confirmarCompra(  letras,  posPelicula,  tipoPelicula, saldo,  posCliente, status,
                        ruts,  nombrePeliculas,  opHorario,  sala, sala1M,  cantCompraClientes,  rutsEntradas, cantEntradas,
                        peliculaClientes,  horarioEntradas,  salasCompradas,  matrizEntradaCliente,cantCompraClientesArray,saldos);
                    break;
                case 2:  
                    // int [][] sala2M;;
                    //obtenerSalasAsientos ( sala2M,letras);
                    obtenerSalasAsientos ( sala2M,  letras);
                    confirmarCompra(  letras,  posPelicula,  tipoPelicula, saldo,  posCliente, status,
                        ruts,  nombrePeliculas,  opHorario,  sala, sala2M,  cantCompraClientes,  rutsEntradas, cantEntradas,
                        peliculaClientes,  horarioEntradas,  salasCompradas,  matrizEntradaCliente,cantCompraClientesArray,saldos);
                    break;
                case 3:  
                    // int [][] sala3M;;
                    //obtenerSalasAsientos ( sala3M,letras);
                    obtenerSalasAsientos ( sala3M,  letras);
                    confirmarCompra(  letras,  posPelicula,  tipoPelicula, saldo,  posCliente, status,
                        ruts,  nombrePeliculas,  opHorario,  sala, sala3M,  cantCompraClientes,  rutsEntradas, cantEntradas,
                        peliculaClientes,  horarioEntradas,  salasCompradas,  matrizEntradaCliente,cantCompraClientesArray,saldos);
                    break;

                default: 
                    System.out.println("Sala invalida");
                    break;
        
            }
            
        }
        else if (horarioSeleccion.equalsIgnoreCase("Tarde") && horarioT == true)
        {
            System.out.print("Ingrese sala, funcion Tarde: ");
            int sala = sc.nextInt();
            
            String opHorario ="Tarde";
            switch (sala) 
            {
                case 1:  
                    // int [][] sala1T;
                    //obtenerSalasAsientos ( sala1T,letras);
                    obtenerSalasAsientos ( sala1T,  letras);
                    confirmarCompra(  letras,  posPelicula,  tipoPelicula, saldo,  posCliente, status,
                        ruts,  nombrePeliculas,  opHorario,  sala, sala1T,  cantCompraClientes,  rutsEntradas, cantEntradas,
                        peliculaClientes,  horarioEntradas,  salasCompradas,  matrizEntradaCliente,cantCompraClientesArray,saldos);
                    
                    break;
                case 2:  
                    // int [][] sala2T;
                    //obtenerSalasAsientos ( sala2T,letras);
                    obtenerSalasAsientos ( sala2T,  letras);
                    confirmarCompra(  letras,  posPelicula,  tipoPelicula, saldo,  posCliente, status,
                        ruts,  nombrePeliculas,  opHorario,  sala, sala2T,  cantCompraClientes,  rutsEntradas, cantEntradas,
                        peliculaClientes,  horarioEntradas,  salasCompradas,  matrizEntradaCliente,cantCompraClientesArray,saldos);
                    break;
                case 3:  
                    // int [][] sala3T;
                    //obtenerSalasAsientos ( sala3T,letras);
                    obtenerSalasAsientos ( sala3T,  letras);
                    confirmarCompra(  letras,  posPelicula,  tipoPelicula, saldo,  posCliente, status,
                        ruts,  nombrePeliculas,  opHorario,  sala, sala3T,  cantCompraClientes,  rutsEntradas, cantEntradas,
                        peliculaClientes,  horarioEntradas,  salasCompradas,  matrizEntradaCliente,cantCompraClientesArray,saldos);
                    break;

                default: 
                    System.out.println("Sala invalida");
                    break;
        
            }  
            
        }
    }
    /**
     * procedure where the seats of the requested room are printed
     * @param sala1M matrix entry corresponding to seats in a room
     * @param letras enter a list of type String with the letters of the seats
     */
    public static void obtenerSalasAsientos (int [][] sala1M, String [] letras){
        System.out.print ("\n");
        for (int i = 1; i < 31; i++) {
            
            System.out.print(i+"\t");
            
        }
        System.out.println("\n");
        for (int i = 0; i < 10; i++) {
            System.out.print(letras[i]+ " |");
            for (int j = 0; j < 31; j++) {
                
                if (i>=0 && i<4){
                    
                    if(j<5||j>25){
                        if(j == 4){
                            System.out.print(sala1M[i][j]+"\t");
                            
                        }else{
                            System.out.print(sala1M[i][j]+"\t");
                        }
                    }
                    else if (j>5 && j<26){
                        System.out.print(sala1M[i][j]+"\t");
                    }
                }
                
                else if(i>3&& j<30){
                    
                    System.out.print(sala1M[i][j]+"\t");
                    
                    
                }
                
            }
            System.out.print("\n");
           
        }
    }
    /**
     * procedure where the client makes the purchase of their tickets, and all the procedures that this entails
     * @param letras  enter a list of type String with the letters of the seats
     * @param posPelicula position of the purchased movie in a list
     * @param tipoPelicula enter a list of type String containing the type (premiere / released) of each movie
     * @param saldo enter a list of type int with the balances of each customer
     * @param posCliente enter the position of the customer to be searched in the list of ruts
     * @param Status Enter this list of String and it is filled by this procedure with the data of the mobility pass of each client
     * @param ruts enter a list of String and fill it with the Ruts of each user
     * @param nombrePeliculas String type list that stores the name of each movie on the billboard
     * @param horario enter a String that can have an "M" value or a "T" value to know the time of the respective movie.
     * @param salaCompradaFuncion variable de tipo int que contiene la sala comprada
     * @param matrizSala ingreso de matriz de tipo int que contiene la sala
     * @param cantCompraClientes enter a variable of type int with the amount of purchases that have been made
     * @param rutsEntradas enter a list of type String with the ruts that have bought at least one entry
     * @param cantEntradas enter a list of type int with the amount of tickets they have bought
     * @param peliculaClientes enter a list of type String with the movies that customers have bought
     * @param horarioEntradas enter a list of type String with the schedules that customers have bought
     * @param salasCompradas enter a list of type int with the room in which each client bought
     * @param matrizEntradaCliente enter an array of type String with the seats that each customer bought
     * @param cantCompraClientesArray enter a list of type int containing the purchase quantity of the customers
     * @param saldos enter a list of int and populate it with the balances of each user
     */
    public static void confirmarCompra( String [] letras, int posPelicula, String [] tipoPelicula, int [] saldo, int posCliente, String [] Status,
            String[]ruts, String [] nombrePeliculas, String horario, int salaCompradaFuncion, int [][] matrizSala, int cantCompraClientes, String [] rutsEntradas, int [] cantEntradas,
            String [] peliculaClientes, String [] horarioEntradas, int [] salasCompradas, String [][] matrizEntradaCliente, int [] cantCompraClientesArray, int [] saldos){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Ingrese la cantidad de entradas a comprar: ");
        int cantEntradasAComprar = sc.nextInt();
        
        int valorEntrada = 0;
        
        if(tipoPelicula[posPelicula].equalsIgnoreCase("Estreno")){
            valorEntrada += cantEntradasAComprar * 5500 ;
        }
        else if (tipoPelicula[posPelicula].equalsIgnoreCase("Liberada")){
            valorEntrada += cantEntradasAComprar * 4000 ;
        }
        
        if(Status[posCliente].equalsIgnoreCase("Habilitado")){
            System.out.println("Por tener su pase de movilidad HABILITADO, posee descuento.");
            double descuento = (valorEntrada +0.15);
            valorEntrada += (int) (valorEntrada-descuento);
        }else{
            System.out.println("No posee descuento. No tiene pase de movilidad habilitado.");
        }
        
        if (saldo[posCliente]<valorEntrada){
            System.out.println("Saldo Insuficiente,¿Desea agreagar saldo a su cuenta? Si/No");
            String opc = sc.next();
            
            while (!opc.equalsIgnoreCase("Si") && !opc.equalsIgnoreCase("No")){
                System.out.println("Opcion Incorrecta, vuelva a ingresar la opcion: Si/No");
                opc = sc.next();
            }
            
            if(opc.equalsIgnoreCase("Si")){
                cargarSaldo ( posCliente,  saldo);
            }
            else if (opc.equalsIgnoreCase("No")){
                System.out.println("Operacion Cancelada");
            }
            
        }else{
            
            buscarPosSala(posCliente, ruts, posPelicula,  nombrePeliculas, horario,   salaCompradaFuncion , cantEntradasAComprar,  matrizSala,
             letras,  cantCompraClientes, rutsEntradas, cantEntradas, peliculaClientes, horarioEntradas, salasCompradas, matrizEntradaCliente, cantCompraClientesArray, valorEntrada,saldos);
        }
        
        
    }
    /**
     * procedure used to charge balance to a customer's account in case their balance is insufficient for the purchase
     * @param posCliente enter the position of the customer to be searched in the list of ruts
     * @param saldo enter a list of type int with the balances of each customer
     */
    public static void cargarSaldo (int posCliente, int [] saldo){
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese cantidad de dinero a ingresar: ");
        int saldoAgregar = sc.nextInt();
        
        saldo[posCliente] = saldoAgregar;
    }
    /**
     * procedure that looks for the position of a seat for the purchase of a ticket, restricts the seats on each side due to the sanitary restriction
     * @param posCliente enter the position of the customer to be searched in the list of ruts
     * @param ruts enter a list of String and fill it with the Ruts of each user
     * @param posPelicula position of the purchased movie in a list
     * @param nombrePeliculas String type list that stores the name of each movie on the billboard
     * @param horario enter a String that can have an "M" value or a "T" value to know the time of the respective movie.
     * @param salaCompradaFuncion variable de tipo int que contiene la sala comprada
     * @param cantEntradasAComprar input of variable of type int that contains the number of inputs that the customer will buy
     * @param matrizSala input array of type int containing room
     * @param letras enter a list of type String with the letters of the seats
     * @param cantCompraClientes enter a variable of type int with the amount of purchases that have been made
     * @param rutsEntradas enter a list of type String with the ruts that have bought at least one entry
     * @param cantEntradas enter a list of type int with the amount of tickets they have bought
     * @param peliculaClientes enter a list of type String with the movies that customers have bought
     * @param horarioEntradas enter a list of type String with the schedules that customers have bought
     * @param salasCompradas enter a list of type int with the room in which each client bought
     * @param matrizEntradaCliente enter an array of type String with the seats that each customer bought
     * @param cantCompraClientesArray enter a list of type int containing the purchase quantity of the customers
     * @param cobro variable of type int that shows the collection of the tickets purchased by the client
     * @param saldos enter a list of int and populate it with the balances of each user
     */
    public static void buscarPosSala(int posCliente, String []ruts,int posPelicula, String [] nombrePeliculas,String horario,  int salaCompradaFuncion ,int cantEntradasAComprar, int [][] matrizSala,
            String [] letras, int cantCompraClientes,String [] rutsEntradas, int [] cantEntradas,String [] peliculaClientes,String [] horarioEntradas,int [] salasCompradas, String [][] matrizEntradaCliente, int [] cantCompraClientesArray, int cobro, int [] saldos){
        
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < cantEntradasAComprar; i++) {
            System.out.print("Ingrese la Fila de la butaca (A - J): ");
            
            String posSalaFil = sc.next();
            
            while (!posSalaFil.equalsIgnoreCase("A")&& !posSalaFil.equalsIgnoreCase("B")&& !posSalaFil.equalsIgnoreCase("C")&& !posSalaFil.equalsIgnoreCase("D")&& !posSalaFil.equalsIgnoreCase("E")&&
                !posSalaFil.equalsIgnoreCase("F")&& !posSalaFil.equalsIgnoreCase("G")&& !posSalaFil.equalsIgnoreCase("H")&& !posSalaFil.equalsIgnoreCase("I")&& !posSalaFil.equalsIgnoreCase("J")){
            System.out.println("Error, vuelva a ingresar la opcion");
            System.out.print("Ingrese la Fila de la butaca (A - J): ");
            posSalaFil = sc.next();
        }
            
            
            System.out.print("Ingrese la Columna de la butaca(1 - 30): ");
            int posSalaCol = sc.nextInt();
            
            while (posSalaCol<1 && posSalaCol>30){ 
                System.out.println("Numero de columna incorrecto, vuelva a ingresar la opción.");
                System.out.print("Ingrese la Columna de la butaca(1 - 30): ");
                posSalaCol = sc.nextInt();
            }
            
            int posFil = 0;
            for (int k = 0; k < 10; k++) {
                if(letras[k].equalsIgnoreCase(posSalaFil)){
                    posFil = k;
                }
            }

            int posCol = 0;
            for (int j = 0; j <30 ; j++) {
                if((posSalaCol-1) == j){
                    posCol = j;
                }
            }
            
                        
            if(matrizSala [posFil][posCol] == -1 || matrizSala[posFil][posCol] == 1 || matrizSala[posFil][posCol] == 2){
                System.out.println("Posición no disponible");
            }else{
                System.out.println("\t----Entrada Comprada----");
                
                saldos[posCliente]-=cobro;                
                matrizSala [posFil][posCol] = 2;
                peliculaClientes[cantCompraClientes] = nombrePeliculas[posPelicula];
                rutsEntradas[cantCompraClientes] = ruts[posCliente];
                cantEntradas[cantCompraClientes] = cantEntradasAComprar;
                horarioEntradas[cantCompraClientes] = horario;
                salasCompradas[cantCompraClientes] = salaCompradaFuncion;
                for (int h = 0; h < cantEntradasAComprar; h++) {
                    
                    matrizEntradaCliente[cantCompraClientes][h] = letras[posCol]+","+(posCol+1);
                }
                cantCompraClientesArray[0]+=1;
                
            }
            
        }
        
            
        
           
        
        
        
    }
    
    /**
     * procedure that prints the information of each user (name, surname, routine, balance, purchased movie, entry time, seats)
     * @param posCliente enter the position of the customer to be searched in the list of ruts
     * @param ruts enter a list of String and fill it with the Ruts of each user
     * @param nombres enter a list of String and fill it with the names of each user
     * @param apellidos enter a String list and fill it with the surnames of each use
     * @param saldos enter a list of int and populate it with the balances of each user
     * @param rutEntradas enter a list of type String with the ruts that have bought at least one entry
     * @param cantEntradas enter a list of type int with the amount of tickets they have bought
     * @param peliculasClientes enter a list of type String containing the movies purchased by customers
     * @param horarioEntradas enter a list of type String with the schedules that customers have bought
     * @param salaComprada enter a list of type int with the room that the customer has bought
     * @param matrizEntradaCliente enter an array of type String with the seats that each customer bought
     * @param cantCompraClientes enter a variable of type int with the amount of purchases that have been made
     */
    public static void obtenerInformaciónUsuario (int posCliente, String [] ruts, String [] nombres, String [] apellidos, int [] saldos,
        String [] rutEntradas, int [] cantEntradas, String [] peliculasClientes, String [] horarioEntradas, int [] salaComprada,String [][] matrizEntradaCliente,
        int [] cantCompraClientes){
        System.out.println("Información del Cliente");
        
        for (int i = 0; i < cantCompraClientes[0]; i++) {
            
            if(rutEntradas[i].equalsIgnoreCase(ruts[posCliente])){
                System.out.println("\tNombre del Cliente : "+ nombres[posCliente]+ " "+ apellidos[posCliente]);
                System.out.println("\tRut del Cliente : "+ruts[posCliente]);
                System.out.println("\tSaldo del Cliente: "+saldos[posCliente]);
                if(cantEntradas[i] == 0){
                    System.out.println("El cliente no ha comprado ninguna entrada");
                }else{
                    System.out.println("\tEl nombre de la pelicula es: "+ peliculasClientes[i]);
                    System.out.println("\tEl horario de la pelicula es en la "+horarioEntradas[i]);
                    System.out.println("\tLas butacas compradas por el cliente para esta pelicula son: ");
                    for (int j = 0; j < cantEntradas[i]; j++) {
                        String entradaComa = matrizEntradaCliente[i][j];
                        String [] linea = entradaComa.split(",");
                        
                        System.out.println("\t\t"+"/ "+linea[0]+linea[1]+" / ");
                    }
                }
            }
        }
    }
    
    /**
     * procedure that displays the billboard of available films
     * @param cantPeliculas enter the number of movies on the billboard
     * @param funcionMañana enter an array of type boolean with the functions in the morning time
     * @param funcionTarde enter a boolean array with the functions in the afternoon schedule
     * @param salas list of numbered rooms (1,2,3)
     * @param nombrePeliculas String type list that stores the name of each movie on the billboard
     */    
    public static void cartelera (int cantPeliculas, boolean [][] funcionMañana, boolean [][] funcionTarde, int [] salas,  String [] nombrePeliculas){
        
        for (int i = 0; i < cantPeliculas; i++) {
            System.out.println("\nLa pelicula "+nombrePeliculas[i]+", tiene los siguientes horarios: ");
            System.out.println("\tHorarios disponibles en la MAÑANA");
            for (int j = 0; j < 3; j++) {
                if (funcionMañana[i][j] = true ){
                    
                    System.out.println("\t\tFuncion disponible en la Sala / "+salas[j] +" /" );
                }
            }
            System.out.println("\tHorarios disponibles en la TARDE");
            for (int k = 0; k < 3; k++) {
                if (funcionTarde[i][k] = true ){
                    
                    System.out.println("\t\tFuncion disponible en la Sala  / "+salas[k] +" /" );
                }
            }
        }
    }
}
