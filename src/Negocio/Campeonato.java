package Negocio;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Campeonato
{
	private ArrayList<Carrera> carreras;
	private ArrayList<Piloto> pilotos;
	private Map<Integer,Integer> puntos;
	private Map<Integer,Integer> campeonatoParalelo;
	
	
	////CONSTRUCTOR////
	public Campeonato()
	{
		carreras = new ArrayList<Carrera>();
		pilotos = new ArrayList<Piloto>();
		puntos = new TreeMap<Integer, Integer>();
		campeonatoParalelo = new TreeMap<Integer, Integer>();
	}


	////GETTERS Y SETTERS////
	public ArrayList<Carrera> getCarreras() 
	{
		return carreras;
	}

	public void setCarreras(ArrayList<Carrera> carreras) 
	{
		this.carreras = carreras;
	}

	public ArrayList<Piloto> getPilotos() 
	{
		return pilotos;
	}

	public void setPilotos(ArrayList<Piloto> pilotos) 
	{
		this.pilotos = pilotos;
	}

	public Map<Integer, Integer> getPuntos() 
	{
		return puntos;
	}

	public void setPuntos(Map<Integer, Integer> puntos) 
	{
		this.puntos = puntos;
	}

	public Map<Integer, Integer> getCampeonatoParalelo() 
	{
		return campeonatoParalelo;
	}

	public void setCampeonatoParalelo(Map<Integer, Integer> campeonatoParalelo) 
	{
		this.campeonatoParalelo = campeonatoParalelo;
	}
	
	
	////METODOS////
	
	//Agrega un piloto a la lista de pilotos
	public void agregarPiloto(int numero, String nombre)
	{
		Piloto p = new Piloto(nombre, numero);
		if(pilotos.contains(p))
			throw new InvalidParameterException();
		pilotos.add(p);
	}
	
	//Agrega una carrera a la lista de carreras
	public void agregarCarrera(String autodromo, String fecha)
	{
		Carrera c = new Carrera(fecha, autodromo);
		if(carreras.contains(c))
			throw new InvalidParameterException();
		carreras.add(c);
	}
	
	

}
