package chat;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Chat extends javax.swing.JFrame {

    private ServidorCliente servidor;
    private String prefixo = "<html>";
    private String sufixo = "</html>";
    private String elementoQuebraLinha = "<br>";
    private String mensagens = "";
    private StringBuilder construtorMensagem = new StringBuilder();
    
    public Chat(ServidorCliente servidor) {
        initComponents();
        this.servidor = servidor;
    }
    
    public void adicionarMensagem(String mensagem) {
        this.construtorMensagem.append(this.mensagens);
        this.construtorMensagem.append(mensagem + this.elementoQuebraLinha);
        this.chatGeral.setText(this.prefixo + this.construtorMensagem.toString() + this.sufixo);
        this.mensagens = this.construtorMensagem.toString();
        this.construtorMensagem.setLength(0);
    }
    
    private void verificaTamanhoMensagem() {
        String mensagem = this.mensagemEnvio.getText();
        if(mensagem.length() > 200) {
            this.mensagemEnvio.setText(mensagem.substring(0, 200));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botaoEnviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatGeral = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        mensagemEnvio = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botaoEnviar.setText("Enviar");
        botaoEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEnviarActionPerformed(evt);
            }
        });

        chatGeral.setEditable(false);
        chatGeral.setContentType("text/html"); // NOI18N
        jScrollPane1.setViewportView(chatGeral);

        mensagemEnvio.setColumns(20);
        mensagemEnvio.setRows(5);
        mensagemEnvio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mensagemEnvioKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(mensagemEnvio);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoEnviar))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 66, Short.MAX_VALUE)
                        .addComponent(botaoEnviar))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEnviarActionPerformed
        try {
            this.servidor.enviarMensagemServidor(this.mensagemEnvio.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_botaoEnviarActionPerformed

    private void mensagemEnvioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mensagemEnvioKeyReleased
        this.verificaTamanhoMensagem();
    }//GEN-LAST:event_mensagemEnvioKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoEnviar;
    private javax.swing.JTextPane chatGeral;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea mensagemEnvio;
    // End of variables declaration//GEN-END:variables

}
