package data;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.lang.Override;
import java.lang.Thread;


import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class ChatBot extends JFrame{

    private JFrame frame;
    private JTextArea chatArea;
    private JTextField chatBox;
    private JScrollPane scroll;
    private Border border;

    public static void main(String[] args){
        new ChatBot();
    }

    List<String> hechos = List.of(new String[]{
            "SALUDAR",
            "PREGUNTAR",
            "RECOMENDAR",
            "DESPEDIR",
            "CLARIFICACION",
            "REINICIAR",
            "CALIDO",
            "FRIO",
            "URBANO",
            "RURAL",
            "MONTAÑA",
            "BOSQUE",
            "PLAYA",
            "NACIONAL",
            "INTERACIONAL",
            "HISTORICO",
            "ATRACCIONES",
            "NATURALEZA"
    });

    
    
    private ArrayList<Integer> buscarHechos(String gtext) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        gtext = gtext.toUpperCase();
        System.out.println(gtext);
        for(int i=0; i<hechos.size(); i++){
            if(gtext.contains(hechos.get(i))) ret.add(i);
        }
        return ret;
    };

    public ChatBot(){
        frame = new JFrame("ChatBot de IA");
        chatArea = new JTextArea(20,50);
        chatBox = new JTextField();
        scroll = new JScrollPane(chatArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        border = BorderFactory.createLineBorder(Color.GREEN, 1);
        chatArea.setEditable(false);
        chatArea.setSize(300, 300);
        chatArea.setLocation(50,50);
        chatBox.setSize(540, 30);
        chatBox.setLocation(18, 18);
        chatBox.setBorder(border);
        frame.setResizable(false);
        frame.setSize(600, 400);
        frame.add(chatBox);
        frame.add(scroll);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        bot("Bienvenido al \"Bot recepcionista\"!  Ingrese \"SALIR\" para terminar. \n\n");
        chatArea.append("   Chats: \n");
        chatBox.setText("");

        chatBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String gtext = chatBox.getText();
                chatArea.append("   Usuario: " +gtext + "\n");
                chatBox.setText("");
                if(gtext.equals("SALIR")) {
                    sleep(500);
                    System.exit(0);
                }
                String category = "";
                try {
                    if(gtext.equals("hola")) category = "saludos";
                    else {
                        ArrayList<Integer> hechosActual = buscarHechos(gtext);
                        if(!hechosActual.isEmpty()){
                            System.out.print("hechos: ");
                            for(Integer ha : hechosActual) System.out.print(ha + ", ");
                            System.out.println();
                        }
                        else System.out.println( " Empty ");
                    }
                    // aca hay q aplicar algo de faia
                }
                catch (Exception e) {
                    System.out.println("Exception thrown.");
                }
                String response = respond(category);
                if(gtext.charAt(0) == 'd') response = "obvio";
                bot(response);
            }

          
        });


    }

    private void bot(String string)
    {
        chatArea.append("   Bot: " + string + "\n");
    }


    private void sleep(int x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String respond(String category)
    {
        String[] saludos = {"Que tal", "como andamos?", "que tal pascual"};
        if (category.equals("saludos")) return saludos[(int) (Math.random()*saludos.length)];
        else return "Sorry";
    }

}
