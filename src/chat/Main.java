/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno
 */
public class Main {
    
    public static void main(String[] args) {
        
        try {
            Registry registry = LocateRegistry.createRegistry(2001);
            
            ServidorCliente servidor = new ServidorCliente();
            MensagemInterface skeletonObj = (MensagemInterface)UnicastRemoteObject.exportObject(servidor, 0);
            
            registry.bind("Chat", skeletonObj);
            
            servidor.criaUsuario(JOptionPane.showInputDialog("Seu nome: "));
            servidor.setIpServidor(JOptionPane.showInputDialog("IP Servidor: "));
            servidor.conectar();
         
            servidor.mostrarChat();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
}
