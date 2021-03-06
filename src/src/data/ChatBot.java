package data;

import sistemaproduccion.SistemaDeProduccion;

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
    
    private SistemaDeProduccion sistemaDeProduccion = new SistemaDeProduccion();

    public static void main(String[] args){
        new ChatBot();
    }

    
    String[][] hechos = {
            {"SALUDAR", "BUENOS DIAS", "BUENAS TARDES", "BUENAS NOCHES", "HOLA", "QUE TAL", "COMO ESTAS"}, // 0 saludar
            {"PREGUNTAR", }, // preguntar
            {"RECOMENDAR"}, // recomendar
            {"DESPEDIR","ADIOS", "GRACIAS", "NOS VEMOS"}, // despedir
            {"CLARIFICACION"}, // clarificacion
            {"REINICIAR"}, // 5 reiniciar
            {"CALIDO", "CALUROSO", "CALIENTE", "CALOR", "CARIBEÑO", "CARIBENIO"}, // calido
            {"FRESCO", "FRIO", "BAJA TEMPERATURA", "GLACIAL", "NIEVE", "NIEVA"}, // frio
            {"URBANO", "CIUDAD"}, // urbano
            {"RURAL", "ALEJADO", "RUSTICO", "CAMPO"}, // rural
            {"monta" + '\u00f1' + "oso", "MONTAÑOSO", "MONTAÑA", "MONTANIOSO", "MONTANIA", "ESCALAR"}, // 10 montaña
            {"BOSCOSO", "BOSQUE", "SELVA", "ARBOLEDA" }, // bosque
            {"CON PLAYA", "PLAYA", "SURF"}, // playa
            {"NACIONAL", "ARGENTINA"}, // nacional
            {"INTERNACIONAL", "EXTERIOR", "AFUERA"}, // internacional
            {"HISTORICO", "MUSEO", "MONUMENTO", "ANTIGUO"}, // 15 historico
            {"CON ATRACCIONES", "ATRACCIONES", "MONTAÑA RUSA", "MONTANIA RUSA", "ENTRETENIMIENTO", "CINE", "SHOPPING", "CASINO", "DIVERSION", "SAUNA"}, // atracciones
            {"NATURAL", "PARQUE", "PLAZA", "LAGO", "RESERVA"}, // naturaleza
            {"TEMPLADO"},
            {"BARILOCHE"},
            {"PARIS"},
            {"RIO DE JANEIRO"},
            {"CORDOBA"},
            {"DISNEY"},
            {"CUBA"},
            {"CATARATAS DEL IGUAZU"},
            {"ESTAMBUL"},
            {"SALTA"},
            {"LAS VEGAS"},
            {"MAR DE AJO"}
    };
    
    
    private ArrayList<Integer> buscarHechos(String gtext) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        gtext = gtext.toUpperCase();
        for(int i=0; i<hechos.length; i++){
            for(String hecho : hechos[i]){
                if(gtext.contains(hecho)){
                    ret.add(i);
                    break;
                }
            }
        }
        Object nacional = 13;
        if(ret.contains(14) && ret.contains(13)) ret.remove(nacional);
        return ret;
    };


    private String mergeRespuestas(List<String> respuestas) {
        if(respuestas == null || respuestas.isEmpty()) return "No ingresaste nada";
        String response = "";
        Boolean firstLine = true;
        for(int i=0; i<respuestas.size(); i++){
            if(!firstLine) response += "\n           ";
            response+=respond(respuestas.get(i));
            if(respuestas.get(i) == "Recomendar") {
                Boolean nxt=false;
                while(i+1<respuestas.size() && respuestas.get(i+1) != "Despedida"){
                    if(nxt) response+=", ";
                    response += respuestas.get(i+1);
                    i++;
                    nxt=true;
                }
            }
            if(respuestas.get(i)=="Lugar elegido") {
            	++i;
            	response+= respuestas.get(i);
            	response+=".\n           ";
            	++i;
            	response+="En este lugar el clima es "+respuestas.get(i)+".\n           ";
            	++i;
            	response+="En cuanto a geografia, podriamos clasificarlo como "+respuestas.get(i)+".\n           ";
            	++i;
            	if(respuestas.get(i)=="Nacional")
            	response+="Este destino es nacional, por lo tanto no hace falta sacar visa ni pasaporte.\n           ";
            	else response+="Este destino es en el exterior, por lo que deberas tener el pasaporte al dia!.\n           ";
            	++i;
            	if(respuestas.get(i)=="Historico")
            	response+="En este lugar podras ver varios sitios historicos.\n           ";
            	else if (respuestas.get(i)=="Naturaleza")
            		response+="En este lugar podras disfrutar de la naturaleza en todo su esplendor.\n           ";
            	else response+="En este lugar podras visitar diversas atracciones turisticas.\n           ";
            	++i;
            	response+="Podemos ofrecerle paquetes turisticos hacia este destino por $"+respuestas.get(i)+".\n            Para mas informacion consultar nuestra web turismoalmundo.com .\n           ";
            	++i;
            	
            }
            firstLine=false;
        }
        return response;
    };
    
    String[] conectores = {"Genial, ", "Comprendo, ", "Perfecto, ", "Buena decision, ", "Excelente, "};

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
        frame.setSize(600, 600);
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
                List<String> respuestas = null;
                String response = "";
                try {
                    ArrayList<Integer> hechosActual = buscarHechos(gtext);
                    System.out.print("hechos: ");
                    for(Integer ha : hechosActual) System.out.print(ha + ", ");
                    System.out.println();
                    for(Integer ha : hechosActual){
                        if(ha > 5 && ha<19){
                            response += conectores[(int)(Math.random()*conectores.length)];
                            response += " un lugar " + hechos[ha][0].toLowerCase() + ".\n";
                            response+="           ";
                        }
                    }
                    respuestas = sistemaDeProduccion.ejecutar(hechosActual);
                }
                catch (Exception e) {
                	e.printStackTrace();
                    System.out.println("Exception thrown.");
                }
                response += mergeRespuestas(respuestas);
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
        //Saludos, Aviso de reinicio, Clarificacion,
        // Recomendar, Temperatura, Localidad, Geografia, TipoAtracciones, Despedida
        String[] categorias = {
                "Saludos", "Aviso de reinicio", "Clarificacion",
                "Recomendar", "Temperatura", "Localidad",
                "Geografia", "TipoAtracciones", "Despedida", "Lugar elegido"//,"ContarTemperatura",
                //"ContarGeografia","Localidad","Tipo"
        };
        String[][] respuestas = {
                {"Buen dia. ", "Hola que tal. ", "Buenos dias. "}, // saludos
                {"Se han reiniciado las preferencias. "}, // reinicio
                {"Disculpe, no entendi bien lo que quizo decir. ", "Perdone, no pude entender lo que quiso decir. "}, // clarificacion
                {"Le recomendaria la localidad de ", "Le sugiero programar un viaje a "}, // recomendar
                {"Prefiere un clima frio, templado o calido?", "Le gustaria viajar a un lugar con nieve, templado o que haga calor?"}, // temperatura
                {"Le gustaria una localidad cercana o algun lugar internacional? ", "Preferiria un viaje al exterior o nacional? "}, // localidad
                {"Que paisajes prefiere, algun lugar con monta" + '\u00f1' + "as? ", "Le gustaria algun lugar con playas o que preferiria? "}, // geografia
                {"Que actividades preferiria realizar, visitar algun lugar historico? ", "Le gustaria algun lugar en la naturaleza o que le gustaria hacer en su viaje? "}, // atracciones
                {"Que tenga un buen resto del dia. ", "Ojala haya encontrado lo que buscaba, saludos. ", "Espero haberlo ayudado, saludos. "}, // despedida
                {"Ahh veo que te intereso ", "Buenisimo, te cuento sobre ", "Te gustaria saber mas sobre "}//Lugar elegido
        };
        
        String respuestaElegida = "Y... es un tema complicado...\n";
        for(int i = 0; i<categorias.length; i++){
            if(category == categorias[i]) {
                respuestaElegida = respuestas[i][(int)(Math.random()*respuestas[i].length)];
                break;
            }
        }
    
        System.out.println("Se eligio la siguiente respuesta: " + respuestaElegida + " siguiendo el criterio de aleatoriedad para que no se repitan siempre las mismas respuestas");
        return respuestaElegida;

    }

}
