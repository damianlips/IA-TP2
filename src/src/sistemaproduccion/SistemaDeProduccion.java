package sistemaproduccion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import data.Lugar;

public class SistemaDeProduccion {
	public List<Boolean> hechos;
	public Map<String,String> atributos;
	public List<String> preguntas;
	
	/*
	 * Indice de hechos:
	 * 0: Saludar
	 * 1: Preguntar
	 * 2: Recomendar
	 * 3: Despedir
	 * 4: Clarificacion
	 * 5: Reiniciar
	 * 
	 * 
	 * 
	 * 6: Calido
	 * 7: Frio
	 * 8: Urbano
	 * 9: Rural
	 * 10: Montaña
	 * 11: Bosque
	 * 12: Playa
	 * 13: Nacional
	 * 14: Internacional
	 * 15: Historico
	 * 16: Atracciones
	 * 17: Naturaleza
	 * 18: Templado
	 */
	
	public SistemaDeProduccion() {
		hechos = new ArrayList<Boolean>();
		for(int i = 0; i<9 ; ++i) hechos.add(false);
		atributos= new HashMap<String,String>();
		preguntas = new ArrayList<String>();
		preguntas.add("Temperatura");
		preguntas.add("Localidad");
		preguntas.add("Geografia");
		preguntas.add("TipoAtracciones");
		
		hechos.set(0, true);
	}
	
	public List<String> ejecutar(List<Integer> claves) {
		ArrayList<String> respuestas = new ArrayList<String>();
		for(Integer clave : claves) {
			if(clave<6) hechos.set(clave, true);
			else
			switch(clave) {
			case 6:
				atributos.put("Temperatura", "Calido");
				preguntas.remove("Temperatura");
				break;
			case 7:
				atributos.put("Temperatura", "Frio");
				preguntas.remove("Temperatura");
				break;
			case 8:
				atributos.put("Geografia", "Urbano");
				preguntas.remove("Geografia");
				break;
			case 9:
				atributos.put("Geografia", "Rural");
				preguntas.remove("Geografia");
				break;
			case 10:
				atributos.put("Geografia", "Montana");
				preguntas.remove("Geografia");
				break;
			case 11:
				atributos.put("Geografia", "Bosque");
				preguntas.remove("Geografia");
				break;
			case 12:
				atributos.put("Geografia", "Playa");
				preguntas.remove("Geografia");
				break;
			case 13:
				atributos.put("Localidad", "Nacional");
				preguntas.remove("Localidad");
				break;
			case 14:
				atributos.put("Localidad", "Internacional");
				preguntas.remove("Localidad");
				break;
			case 15:
				atributos.put("Tipo", "Historico");
				preguntas.remove("TipoAtracciones");
				break;
			case 16:
				atributos.put("Tipo", "Atracciones");
				preguntas.remove("TipoAtracciones");
				break;
			case 17:
				atributos.put("Tipo", "Naturaleza");
				preguntas.remove("TipoAtracciones");
				break;
			case 18:
				atributos.put("Temperatura", "Templado");
				preguntas.remove("Temperatura");
				break;
			}
		}
		if(preguntas.isEmpty()) {
			hechos.set(2, true);
			hechos.set(1, false);
		}
		if(hechos.get(0)) {
			System.out.println("Se selecciono la regla saludar por el criterio de prioridad");
			respuestas.add("Saludos");
			hechos.set(0, false);
			hechos.set(1, true);
		}
		
		if(hechos.get(5)) {
			System.out.println("Se selecciono la regla reinicio por el criterio de prioridad");
			respuestas.add("Aviso de reinicio");
			hechos.set(5, false);
			reiniciar();
		}
		
		if(hechos.get(4)) {
			System.out.println("Se selecciono la regla clarificacion por el criterio de prioridad");
			respuestas.add("Clarificacion");
			hechos.set(4, false);
		}
		
		if(hechos.get(2)) {
			respuestas.add("Recomendar");
			respuestas.addAll(this.recomendacion());
			hechos.set(2, false);
			hechos.set(3, true);
		}
		
		if(hechos.get(1)) {
			System.out.println("Se selecciono la regla preguntar por el criterio de prioridad");
			if(preguntas.isEmpty()) {
				hechos.set(1, false);
				hechos.set(2, true);
			}
			else {
				int elegida = (int) (Math.random()*preguntas.size());
				respuestas.add(preguntas.get(elegida));
				preguntas.remove(elegida);
			}
			
			
		}
		if(hechos.get(3)) {
			System.out.println("Se selecciono la regla despedir al final por el criterio de prioridad");
			respuestas.add("Despedida");
			hechos.set(3, false);
		}
		
		return respuestas;
	}
	
	private List<String> recomendacion(){
		List<Lugar> lugares = Lugar.listaLugares().stream().collect(Collectors.toList());
		lugares = lugares.stream()
				.sorted( (l1,l2) -> (l2.matches(atributos) - l1.matches(atributos) ))
				.collect(Collectors.toList());
		System.out.println(lugares);
		lugares.stream().mapToInt(l -> l.matches(atributos)).forEach(i -> System.out.println(i));
		
		int maximo =lugares.get(0).matches(atributos);
		List<String> resultados = new ArrayList<String>();
		int i=0;
		while(i<lugares.size() && lugares.get(i).matches(atributos)==maximo) {
			resultados.add(lugares.get(i).toString());
			++i;
		}
		System.out.println("Se seleccionó la siguiente lista de resultados" + resultados + "siguiendo el criterio de especificidad");
		return resultados;
	}
	private void reiniciar(){
		hechos = new ArrayList<Boolean>();
		for(int i = 0; i<9 ; ++i) hechos.add(false);
		atributos= new HashMap<String,String>();
		preguntas = new ArrayList<String>();
		preguntas.add("Temperatura");
		preguntas.add("Localidad");
		preguntas.add("Geografia");
		preguntas.add("TipoAtracciones");

		hechos.set(0, true);
	}

}
