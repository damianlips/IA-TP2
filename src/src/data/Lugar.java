package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lugar {
    private String nombre;
    private Map<String, String> atributos;
    private Integer precio;
    public Lugar(String nombre){
        this.nombre=nombre;
        this.atributos= new HashMap<String,String>();
    }
    public Map<String,String> getAtributos(){
        return atributos;
    }
    public void agregarAtributo(String key, String valor){
        atributos.put(key, valor);
    }
    public void setPrecio(int precio){
        this.precio=precio;
    }
    public int matches(Map<String,String> atrib){
        int i=0;
        if(atrib.get("Temperatura")!=null&&atrib.get("Temperatura").equals(atributos.get("Temperatura")))
            i++;
        if(atrib.get("Geografia")!=null&&atrib.get("Geografia").equals(atributos.get("Geografia")))
            i++;
        if(atrib.get("Localidad")!=null&&atrib.get("Localidad").equals(atributos.get("Localidad")))
            i++;
        if(atrib.get("Tipo")!=null&&atrib.get("Tipo").equals(atributos.get("Tipo")))
            i++;
        return i;
    }
    public boolean matchesAll(Map<String,String> atrib){
        if(atrib.get("Temperatura")!=null&&!atrib.get("Temperatura").equals(atributos.get("Temperatura")))
            return false;
        if(atrib.get("Geografia")!=null&&!atrib.get("Geografia").equals(atributos.get("Geografia")))
            return false;
        if(atrib.get("Localidad")!=null&&!atrib.get("Localidad").equals(atributos.get("Localidad")))
            return false;
        if(atrib.get("Tipo")!=null&&!atrib.get("Tipo").equals(atributos.get("Tipo")))
            return false;
        return true;
    }
    public static List<Lugar> listaLugares(){
        List<Lugar> lugares= new ArrayList<>();
        Lugar lugar= new Lugar("Bariloche");
        lugar.agregarAtributo("Temperatura","Frio");
        lugar.agregarAtributo("Geografia", "Montana");
        lugar.agregarAtributo("Localidad", "Nacional");
        lugar.agregarAtributo("Tipo", "Naturaleza");
        lugar.setPrecio(10000);
        lugares.add(lugar);
        lugar= new Lugar("Paris");
        lugar.agregarAtributo("Temperatura","Templado");
        lugar.agregarAtributo("Geografia", "Urbano");
        lugar.agregarAtributo("Localidad", "Internacional");
        lugar.agregarAtributo("Tipo", "Historico");
        lugar.setPrecio(20000);
        lugares.add(lugar);
        lugar= new Lugar("Rio de janeiro");
        lugar.agregarAtributo("Temperatura","Calido");
        lugar.agregarAtributo("Geografia", "Playa");
        lugar.agregarAtributo("Localidad", "Internacional");
        lugar.agregarAtributo("Tipo", "Naturaleza");
        lugar.setPrecio(15000);
        lugares.add(lugar);
        lugar= new Lugar("Cordoba");
        lugar.agregarAtributo("Temperatura","Templado");
        lugar.agregarAtributo("Geografia", "Montana");
        lugar.agregarAtributo("Localidad", "Nacional");
        lugar.agregarAtributo("Tipo", "Naturaleza");
        lugar.setPrecio(5000);
        lugares.add(lugar);
        lugar= new Lugar("Disney");
        lugar.agregarAtributo("Temperatura","Templado");
        lugar.agregarAtributo("Geografia", "Urbano");
        lugar.agregarAtributo("Localidad", "Internacional");
        lugar.agregarAtributo("Tipo", "Atracciones");
        lugar.setPrecio(25000);
        lugares.add(lugar);
        lugar= new Lugar("Cuba");
        lugar.agregarAtributo("Temperatura","Calido");
        lugar.agregarAtributo("Geografia", "Playa");
        lugar.agregarAtributo("Localidad", "Internacional");
        lugar.agregarAtributo("Tipo", "Naturaleza");
        lugar.setPrecio(15000);
        lugares.add(lugar);
        lugar= new Lugar("Cataratas del iguaz�");
        lugar.agregarAtributo("Temperatura","Calido");
        lugar.agregarAtributo("Geografia", "Rural");
        lugar.agregarAtributo("Localidad", "Nacional");
        lugar.agregarAtributo("Tipo", "Naturaleza");
        lugar.setPrecio(25000);
        lugares.add(lugar);
        lugar= new Lugar("Estambul");
        lugar.agregarAtributo("Temperatura","Templado");
        lugar.agregarAtributo("Geografia", "Urbano");
        lugar.agregarAtributo("Localidad", "Interacional");
        lugar.agregarAtributo("Tipo", "Atracciones");
        lugar.setPrecio(25000);
        lugares.add(lugar);
        lugar= new Lugar("Salta");
        lugar.agregarAtributo("Temperatura","Calido");
        lugar.agregarAtributo("Geografia", "Rural");
        lugar.agregarAtributo("Localidad", "Nacional");
        lugar.agregarAtributo("Tipo", "Naturaleza");
        lugar.setPrecio(25000);
        lugares.add(lugar);
        lugar= new Lugar("Las Vegas");
        lugar.agregarAtributo("Temperatura","Calido");
        lugar.agregarAtributo("Geografia", "Urbano");
        lugar.agregarAtributo("Localidad", "Internacional");
        lugar.agregarAtributo("Tipo", "Atracciones");
        lugar.setPrecio(25000);
        lugares.add(lugar);
        lugar= new Lugar("Mar de ajo");
        lugar.agregarAtributo("Temperatura","Calido");
        lugar.agregarAtributo("Geografia", "Playa");
        lugar.agregarAtributo("Localidad", "Nacional");
        lugar.agregarAtributo("Tipo", "Naturaleza");
        lugar.setPrecio(15000);
        lugares.add(lugar);
        return lugares;
    }
	@Override
	public String toString() {
		return nombre;
	}
}
