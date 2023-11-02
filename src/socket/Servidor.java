/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class Servidor {
    
    private final static int PORT = 5000;
    
    public static void main(String[] args) {
        //Socket de servidor para esperar peticiones de la red
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("<Servidor> Servidor iniciado correctamente :D");    
            System.out.println("<Servidor> En espera de un cliente...");    
            //Socket de cliente
            Socket clientSocket;
            
            while(true){
                //en espera de conexion, si existe la acepta
                clientSocket = serverSocket.accept();
                //Para leer lo que envie el cliente
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //para imprimir datos de salida                
                PrintStream output = new PrintStream(clientSocket.getOutputStream());
                //se lee peticion del cliente
                String request = input.readLine();
                System.out.println("<Cliente> petición [" + request +  "]");
                //se procesa la peticion y se espera resultado
                String strOutput = process(request);                
                //Se imprime en consola "servidor"
                System.out.println("<Servidor> Resultado de petición:");                    
                System.out.println("<Servidor> \"" + strOutput + "\"");
                //se imprime en cliente
                output.flush();//vacia contenido
                output.println(strOutput);                
                //cierra conexion
                clientSocket.close();
            }    
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static String process(String request){
        String result="";  
        
        // Lista de videojuegos
        String[] videogames = {
            "Dark Souls - Prepared to Die",
            "Halo Reach",
            "Warcraft III - Reign of Chaos",
            "Warcraft III - The Frozen Throne",
            "Dark Souls II - Scholar of the First Sin",
            "Super Mario 64",
            "The Legend of Zelda",
            "The Elder Scrolls V - Skyrim",
            "The Elder Scrolls IV - Oblivion",
            "PES 2015",
            "Bloodborne",
            "Elden Ring",
            "Dark Souls III - The Ringed City",
            "Fallout - New Vegas",
            "Dragons Dogma - The Dark Arisen",
            "Cuphead",
            "Time Splitters - Futuro Perfecto",
        };
	ArrayList<String> videogamesList = new ArrayList<>();
	Collections.addAll(videogamesList, videogames);
        
        // Lista de libros
        String[] books = {
            "La Divina Comedia - Dante Alighieri", 
            "Don Quijote de la Mancha - Miguel de Cervantes",
            "Cien años de soledad - Gabriel García Márquez",
            "Moby Dick - Herman Melville",
            "Ana Karenina - Lev Tolstói",
            "Eneida - Virgilio",
            "Otelo - William Shakespeare",
            "El viejo y el mar - Ernest Hemingway",
            "Orgullo y prejuicio - Jane Austen",
        };
	ArrayList<String> booksList = new ArrayList<>();
	Collections.addAll(booksList, books);  
        
        // Lista de canciones
        String[] songs = {
            "Metallica - The Unforgiven",
            "Rolling Stone - Pait it Black",
            "ACDC - Thunder",
            "Iron Maiden - The Trooper",
            "Rammstein - Du Hast",
            "Pantera - Walk",
            "Remy Zero - Save Me",
            "WarCry - La Vida en un Beso",
            "Stratovarius - Destiny",
            "Therion - The Rise of Sodom and Gomorrah",
            "Children of Bodom - Rebell Yell",
        };
	ArrayList<String> songsList = new ArrayList<>();
	Collections.addAll(songsList, songs);        
        
        // Validar según la petición para retornar una determinada respuesta
        if(request!=null) switch(request){
            case "videojuego":
                Collections.shuffle(videogamesList);
                result = videogamesList.get(0);
                break;
            case "libro":
                Collections.shuffle(booksList);
                result = booksList.get(0);
                break;
            case "cancion":
                Collections.shuffle(songsList);
                result = songsList.get(0);
                break;
            case "exit":                
                result = "Ha finalizado la conexion al servidor (el servidor aun se esta encendido)";
                break;
            default:
                result = "Solicita algo que este dentro de mis capacidades por favor.";
                break;
        }
        
        return result;
    }
}
