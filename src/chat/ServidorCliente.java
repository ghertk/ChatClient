package chat;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ServidorCliente implements MensagemInterface{
    
    private final Chat chat = new Chat(this);
    private Usuario usuario;
    private String ipServidor;
    private MensagemInterface objeto;
    
    public void conectar() throws NotBoundException, MalformedURLException, RemoteException {
        this.objeto = null;
        
        this.objeto = (MensagemInterface) Naming.lookup("//" + this.ipServidor + ":2001/Chat");
        List<String> mensagens = this.objeto.conectarUsuario(this.usuario.getIpPessoal(), this.usuario.getNome());
        
        for (String mensagem : mensagens) {
            this.chat.adicionarMensagem(mensagem);
        }
        this.chat.adicionarMensagem("<br>Conectado! Olá <b>" + this.usuario.getNome() + "</b>");
        
        System.out.println("Conectado ao servidor: " + this.ipServidor);
    }
    
    public void enviarMensagemServidor(String mensagem) throws RemoteException{
        this.objeto.enviarMensagem(mensagem, this.usuario.getIpPessoal());
    }
    
    @Override
    public void enviarMensagem(String mensagem, String ipCliente) throws RemoteException {
        this.chat.adicionarMensagem(mensagem);
    }

    @Override
    public List<String> conectarUsuario(String ipCliente, String nome) throws RemoteException {
        System.out.println("Outro usuario tentou realizar a conexão em sua maquina cliente"
                         + "Nome: " + nome
                         + "IP: " + ipCliente);
        return new ArrayList<>();
    }
    
    public void criaUsuario(String nome) throws UnknownHostException {
        try {
            this.usuario = new Usuario(nome, InetAddress.getLocalHost().getHostAddress());
            System.out.println("Usuário criado com sucesso");
        } catch (UnknownHostException ex) {
            System.out.println("problemas na criação do usuário: " + ex.getMessage());
            throw ex;
        }
    }
    
    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
        System.out.println("IP Servidor adicionado: " + this.ipServidor);
    }

    void mostrarChat() {
        this.chat.setVisible(true);
    }

    public String getIpServidor() {
        return this.ipServidor;
    }
    
}
