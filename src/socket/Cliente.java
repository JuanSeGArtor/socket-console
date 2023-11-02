/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Cliente {

    // Puerto
    private final static int PORT = 5000;
    
    // Host o Server
    private final static String SERVER = "localhost";
    
    
    public static void main(String[] args) {
        // Controlar ciclo del programa
        boolean exit = false;
        
        //Socket para la comunicacion cliente servidor        
        Socket socket;
        
        try {            
            System.out.println("<Cliente> Cliente iniciado correctamente!");  
            
            while( !exit ){        
                socket = new Socket(SERVER, PORT);             
                // Leer lo que envie el servidor      
                BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));                
                // Imprimir datos del servidor
                PrintStream output = new PrintStream(socket.getOutputStream());                
                // Leer lo que escriba el usuario            
                BufferedReader brRequest = new BufferedReader(new InputStreamReader(System.in));            
                System.out.println("<Cliente> Ingrese un comando");                
                
                // Capturar comando escrito por el usuario
                String request = brRequest.readLine();
                // Realizar peticion al servidor
                output.println(request);
                
                // Capturar respuesta del servidor e imprimirla
                String st = input.readLine();
                if( st != null ) System.out.println("<Servidor> " + st );    
                
                // Terminar aplicacion
                if(request.equals("exit")){
                    exit=true;                  
                    System.out.println("<Cliente> Ha finalizado el programa");    
                }  
                socket.close();
            }                                 
       } catch (IOException ex) {        
         System.err.println("<Cliente> " + ex.getMessage());   
       }
    }
    
}
